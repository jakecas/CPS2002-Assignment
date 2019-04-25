import enums.Direction;
import enums.TileType;
import exceptions.HTMLGenerationException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Game {
    private static int turns;
    private static Player[] players;
    private static Map map;

    public static void startGame(int numOfPlayers, int mapSize){

        turns = 0;

        map = new Map();

        map.setMapSize(mapSize);
        map.generate();

        setNumPlayers(numOfPlayers);

        for (int i = 0; i < players.length; i++) {

            Position position;
            do {
                position = Position.randomPosition(map.getMapSize());
            }while (map.getTileType(position) != TileType.GRASS);

            players[i] = new Player(position, map);
            players[i].getMap().getTile(position).revealTile();
        }

        generateHTMLFiles();
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

    public Map getMap(){
        return map;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("How many players? (2-8 players)");
        int playerCount = input.nextInt();
        System.out.println("How large is the map? (5-50 for 2-4 players, 8-50 for 5-8 players)");
        int mapSize = input.nextInt();

        startGame(playerCount, mapSize);

        boolean win = false;

        do {
            for(int i = 0; i < playerCount; i++){
                Player player = players[i];
                System.out.println("Player " + (i+1) + "; Choose a direction:");
                System.out.println("1. North");
                System.out.println("2. South");
                System.out.println("3. East");
                System.out.println("4. West");
                switch (input.nextInt()){
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
                        System.out.println("Invalid direction, please try again.");
                }
                if(map.getTileType(player.getPosition()) == TileType.TREASURE){
                    win = true;
                }
            }
            generateHTMLFiles();
        } while(!win);
    }
}
