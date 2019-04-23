import enums.TileType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Game {
    private int turns;
    private Player[] players;
    private String[] mapHTML;
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

            mapHTML[i] = players[i].getMap().generateHTML();
            mapHTML[i] = mapHTML[i].replace("$pnum", String.valueOf(i+1));
            mapHTML[i] = mapHTML[i].replace("$pnum", String.valueOf(i+1));
            mapHTML[i] = mapHTML[i].replace("$tnum", String.valueOf(turns));

        }

        generateHTMLFiles();
    }

    public boolean setNumPlayers(int n){
        if (n > 0 && n <= 8){
            players = new Player[n];
            mapHTML = new String[n];
            map.setIsLarge(true);
            if (n <= 4)
                map.setIsLarge(false);

            return true;
        }
        else
            return false;
    }

    public void generateHTMLFiles() {
        String pathURL = Game.class.getResource("").getPath();
        pathURL = pathURL.substring(1);
        for(int i = 0; i < mapHTML.length; i++) {
            try{
                String player_map = "Player_Files/map_player_"+String.valueOf(i+1)+".html";
                String path =  pathURL.replace("target/classes/", player_map);
                Files.write(Paths.get(path), mapHTML[i].getBytes());
            }catch (IOException e) {
                e.getMessage();
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
