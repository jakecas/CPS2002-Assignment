import enums.Direction;
import enums.TileType;
import exceptions.HTMLGenerationException;
import exceptions.PositionOutOfBoundsException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private static int turns;
    private static Player[] players;
    private static Map map;

    public static void startGame(int numOfPlayers, int mapSize){

        turns = 0;

        map = new Map();

        map.setMapSize(mapSize);
        char[][] seed = map.generateSeed();
        map.generate(seed);

        setNumPlayers(numOfPlayers);

        for (int i = 0; i < players.length; i++) {
            Map playerMap = new Map();
            playerMap.setMapSize(mapSize);
            playerMap.generate(seed);

            Position position;
            do {
                position = Position.randomPosition(map.getMapSize());
            }while (playerMap.getTileType(position) != TileType.GRASS);


            players[i] = new Player(position, playerMap);
            players[i].getMap().getTile(position).revealTile();
        }

    }

    public static boolean setNumPlayers(int n){
        if (n > 0 && n <= 8){
            players = new Player[n];
            map.setIsLarge(true);

            if (n <= 4) {
                map.setIsLarge(false);
            }

            return true;
        }
        else
            return false;
    }

    public static void generateDirectory() throws IOException{
            // Creating the directory if it doesn't exist
            FileUtils.forceMkdir(new File("Player_Files/"));
            // Cleaning the directory of maps from previous iterations.
            FileUtils.cleanDirectory(new File("Player_Files/"));
            // Copying the stylemap resource to the directory.
            File css = new File(Game.class.getResource("style_map.css").getFile());
            FileUtils.copyFile(css, new File("Player_Files/style_map.css"));
    }

    public static void generateHTMLFiles() {
        // The directory is cleaned and re-generated every turn.
        try {
            generateDirectory();
        } catch(IOException e){
            throw new HTMLGenerationException();
        }
        for(int i = 0; i < players.length; i++) {
            try{
                String mapHTML = players[i].printMap();
                mapHTML = mapHTML.replaceAll("%pnum", String.valueOf(i+1));
                mapHTML = mapHTML.replace("%tnum", String.valueOf(turns));

                Files.write(Paths.get("Player_Files/map_player_" + (i + 1) + ".html"), mapHTML.getBytes());
            }catch (IOException e) {
                throw new HTMLGenerationException();
            }
        }
    }

    public static Map getMap(){
        return map;
    }

    public static Player[] getPlayers(){
        return players;
    }

    public static boolean menu(Player player, int playerNum){
        boolean valid = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Player " + (playerNum+1) + "; Choose a direction:");
        System.out.println("1. North");
        System.out.println("2. South");
        System.out.println("3. East");
        System.out.println("4. West");
        try{
            int choice = 0;
            choice = input.nextInt();
            valid = true;
            switch (choice) {
                case 1:
                    player.move(Direction.NORTH);
                    break;
                case 2:
                    player.move(Direction.SOUTH);
                    break;
                case 3:
                    player.move(Direction.EAST);
                    break;
                case 4:
                    player.move(Direction.WEST);
                    break;
                default:
                    System.out.println("Invalid direction for Player " + (playerNum + 1) + ", please try again.");
                    valid = false;
            }
        }catch (PositionOutOfBoundsException e){
            System.out.println("Destination is outside of map for Player " + (playerNum + 1) + ", please try again.");
            valid = false;
        }catch (InputMismatchException e){
            System.out.println("Unrecognised input for Player " + (playerNum + 1)
                    + ", integer required. Please try again.");
            input.nextLine();
        }
        return valid;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("How many players? (2-8 players)");
        int playerCount = input.nextInt();
        System.out.println("How large is the map? (5-50 for 2-4 players, 8-50 for 5-8 players)");
        int mapSize = input.nextInt();

        startGame(playerCount, mapSize);
        generateHTMLFiles();

        boolean win = false;
        boolean[] winners = new boolean[playerCount];

        do {
            for(int i = 0; i < playerCount; i++){
                Player player = players[i];
                boolean valid = false;
                while(!valid){
                    valid = menu(player, i);
                }

                if(map.getTileType(player.getPosition()) == TileType.TREASURE){
                    win = true;
                    winners[i] = true;
                } else if (map.getTileType(player.getPosition()) == TileType.WATER){
                    System.out.println("Player " + (i+1) + " drowned!");
                    player.resetToInitialPosition();
                }
            }
            turns++;
            generateHTMLFiles();
        } while(!win);

        for (int i = 0; i < playerCount; i++) {
            if(winners[i] == true){
                System.out.println("Congratulations! Player " + (i+1) + " found the treasure!");
            }
        }
    }
}
