package main;

import enums.Difficulty;
import enums.MapType;
import objects.maps.Map;
import enums.Direction;
import enums.TileType;
import exceptions.HTMLGenerationException;
import exceptions.PositionOutOfBoundsException;
import factories.MapCreator;
import observables.TeamList;
import observers.Team;
import org.apache.commons.io.FileUtils;
import objects.*;

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
    private static Team[] teams;
    private static TeamList teamList;

    public static void startGame(int numOfPlayers, int numOfTeams, int mapSize){

        turns = 0;

        MapCreator mapCreator = new MapCreator();

        map = mapCreator.createMap(MapType.SQUARE, Difficulty.SAFE, mapSize);

        setNumPlayers(numOfPlayers);

        teamList = new TeamList(map);
        teams = new Team[numOfTeams];

        int teamSize = (int)Math.ceil(numOfPlayers/numOfTeams);

        for(int i = 0; i < teams.length; i++){
            String teamName = "Team" + (i+1);
            teams[i] = new Team(teamName, teamList);
            teamList.addTeam(teams[i]);

            for (int j = i*teamSize; (j < (i+1)*teamSize && j < players.length); j++) {
                Position position;
                do {
                    position = Position.randomPosition(map.getMapSize());
                }while (map.getTileType(position) != TileType.GRASS);

                players[j] = new Player(position, map);
                teams[i].addPlayer(players[i]);
            }
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
        // Cleaning the directory of objects.maps from previous iterations.
        FileUtils.cleanDirectory(new File("Player_Files/"));
        // Copying the stylemap resource to the directory.
        File css = new File(Game.class.getClassLoader().getResource("style_map.css").getFile());
        FileUtils.copyFile(css, new File("Player_Files/style_map.css"));
    }

    public static void generateHTMLFiles() {
        // The directory is cleaned and re-generated every turn.
        try {
            generateDirectory();
        } catch(IOException e){
            throw new HTMLGenerationException(e.getMessage());
        }
        for(int i = 0; i < players.length; i++) {
            try{
                String mapHTML = players[i].printMap();
                mapHTML = mapHTML.replaceAll("%pnum", String.valueOf(i+1));
                mapHTML = mapHTML.replace("%tnum", String.valueOf(turns));

                Files.write(Paths.get("Player_Files/map_player_" + (i + 1) + ".html"), mapHTML.getBytes());
            }catch (IOException e) {
                throw new HTMLGenerationException(e.getMessage());
            }
        }
    }

    public static Map getMap(){
        return map;
    }

    public static Player[] getPlayers(){
        return players;
    }

    public static boolean menu(String teamName, Player player, int playerNum){
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
                    teamList.revealTile(teamName, player.move(Direction.NORTH));
                    break;
                case 2:
                    teamList.revealTile(teamName, player.move(Direction.SOUTH));
                    break;
                case 3:
                    teamList.revealTile(teamName,player.move(Direction.EAST));
                    break;
                case 4:
                    teamList.revealTile(teamName,player.move(Direction.WEST));
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
        System.out.println("How many teams?");
        int numOfTeams = input.nextInt();
        System.out.println("How large is the map? (5-50 for 2-4 players, 8-50 for 5-8 players)");
        int mapSize = input.nextInt();

        startGame(playerCount, numOfTeams, mapSize);
        generateHTMLFiles();

        boolean win = false;
        boolean[] winners = new boolean[playerCount];

        int teamSize = (int)Math.ceil(playerCount/numOfTeams);

        do {
            for(int i = 0; i < teams.length; i++) {
                for (int j = 0; j < teams[i].getTeamSize(); j++) {
                    Player player = teams[i].getPlayer(j);
                    boolean valid = false;
                    // i.e: team 2 player 1 with 5 player teams = 6
                    // Change to Team X Player X for display?
                    int globalPlayerNum = teams[0].getTeamSize()*i + j;
                    while (!valid) {
                        valid = menu(teams[i].getTeamName(), player, globalPlayerNum);
                    }

                    if (map.getTileType(player.getPosition()) == TileType.TREASURE) {
                        win = true;
                        winners[i] = true;
                    } else if (map.getTileType(player.getPosition()) == TileType.WATER) {
                        System.out.println("objects.Player " + (i + 1) + " drowned!");
                        player.resetToInitialPosition();
                    }
                }
            }
            teamList.endTurn(); // Notify observers of all changes
            turns++;
            generateHTMLFiles();
        } while(!win);

        for (int i = 0; i < playerCount; i++) {
            if(winners[i] == true){
                System.out.println("Congratulations! objects.Player " + (i+1) + " found the treasure!");
            }
        }
    }
}
