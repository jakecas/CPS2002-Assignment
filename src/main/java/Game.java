import enums.TileType;
import exceptions.HTMLGenerationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Game {
    private int turns;
    private Player[] players;
    private Map map;

    public void startGame(int numOfPlayers, int mapSize){

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

    public boolean setNumPlayers(int n){
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

    public void generateHTMLFiles() {
        for(int i = 0; i < players.length; i++) {
            try{
                String mapHTML = players[i].printMap();
                mapHTML = mapHTML.replaceAll("%pnum", String.valueOf(i+1));
                mapHTML = mapHTML.replace("%tnum", String.valueOf(turns));

                Files.write(Paths.get("Player_Files/map_player_"+String.valueOf(i+1)+".html"), mapHTML.getBytes());
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

        Game game = new Game();

        game.startGame(playerCount, mapSize);
    }
}
