import enums.TileType;

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
            }while (map.getTile(position).getTileType() != TileType.GRASS);
            System.out.println("Starting: " + position.getX() + ", " + position.getY());
            players[i] = new Player(position, map);
            players[i].getMap().getTile(position).revealTile();
        }

        generateHTMLFiles();
    }

    public boolean setNumPlayers(int n){
        if (n > 0 && n <= 8){
            players = new Player[n];
            map.setIsLarge(true);
            if (n <= 4)
                map.setIsLarge(false);

            return true;
        }
        else
            return false;
    }

    public void generateHTMLFiles() {

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

//        System.out.println(game.getMap().generateHTML());
    }
}
