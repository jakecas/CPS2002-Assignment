package assignment.objects.maps;

import assignment.exceptions.MapSizeUndefinedException;
import assignment.objects.*;
import assignment.main.Game;
import assignment.enums.TileType;
import assignment.exceptions.HTMLGenerationException;
import assignment.exceptions.PositionOutOfBoundsException;
import assignment.observables.Team;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public abstract class SquareMap implements Map{

    private static int size;
    private boolean isLarge;
    private char[][] seed;
    private Tile[][] tiles;

    protected SquareMap() {
        size = -1;
        isLarge = false;
    }

    public int getMapSize(){
        return size;
    }

    public boolean setMapSize(int size) {
        if(size <= 50){
            if(size >= 8){
                this.size = size;
                return true;
            } else if(!isLarge && size >= 5){
                this.size = size;
                return true;
            }
        }
        return false;
    }

    public void setIsLarge(boolean isLarge){
        this.isLarge = isLarge;
    }

    public boolean getIsLarge(){
        return isLarge;
    }

    public Tile[][] getTiles(){
        return tiles;
    }

    public void generate(char[][] seed){
        tiles = new Tile[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(seed[i][j] == 'g'){
                    tiles[i][j] = new Tile(TileType.GRASS);
                }
                else if(seed[i][j] == 'w'){
                    tiles[i][j] = new Tile(TileType.WATER);
                }
                else if(seed[i][j] == 't') {
                    tiles[i][j] = new Tile(TileType.TREASURE);
                }
            }
        }
    }

    public Tile getTile(Position position) {
        if(tiles == null || position == null){
            return null;
        }
        if(!position.isWithinLimit(0, size)){
            throw new PositionOutOfBoundsException(position.toString());
        }

        return tiles[position.getX()][position.getY()];
    }

    protected char[][] generateSeed(double percentWater){
        if(size == -1){
            throw new MapSizeUndefinedException("Generating Seed");
        }
        seed = new char[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                seed[i][j] = 'g';
            }
        }

        // Calculating the number of water tiles, adding one for treasure.
        int waterTilesSize = (int) Math.floor(percentWater*size*size) + 1;

        Position[] specialTiles = new Position[waterTilesSize];
        for(int i = 0; i < waterTilesSize; i++){
            specialTiles[i] = Position.randomPosition(size);
            for(int j = 0; j < i; j++){
                if(Position.euclideanDistance(specialTiles[i], specialTiles[j]) < 1){
                    i--;
                    break;
                }
            }
        }

        Position position = specialTiles[0];
        seed[position.getX()][position.getY()] = 't';

        for(int i = 1; i < waterTilesSize; i++){
            position = specialTiles[i];
            seed[position.getX()][position.getY()] = 'w';
        }

        return seed;
    }

    public TileType getTileType(Position position){
        return getTile(position).getTileType();
    }

    public String generateHTML(Team team, Position playerPosition){
        StringBuilder mapHTML = new StringBuilder();

        URL resource = Game.class.getClassLoader().getResource("map_prototype.html");
        File file = new File(resource.getFile());

        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            mapHTML.append(content);
        }catch (IOException e){
            throw new HTMLGenerationException(e.getMessage());
        }

        StringBuilder mapColumns = new StringBuilder();

        for(int i = 0; i < size; i++) {
            mapColumns.append(" 50px");
        }

        String mapString = mapHTML.toString();
        mapString = mapString.replace("%cpx", mapColumns.toString());

        if(size > 25) {
            mapString = mapString.replace("%bigFix", ".map{\n" + "\tposition: absolute;\n" + "}");
        }
        else{
            mapString = mapString.replace("%bigFix", "\n");
        }

        StringBuilder tilesHTML = new StringBuilder();
        for (int row = 0; row < getMapSize(); row++) {

            for (int col = 0; col < getMapSize(); col++) {
                // Output as (column, row) to match (x, y) convention
                boolean isRevealed = team.isRevealed(tiles[col][row]);
                tilesHTML.append(tiles[col][row].toHTML(isRevealed));
                tilesHTML.append( "\">");
                tilesHTML.append(col);
                tilesHTML.append(",");
                tilesHTML.append(row);

                if (col == playerPosition.getX()){
                    if (row == playerPosition.getY()) {
                        tilesHTML.append("<p>P%pnum<p>");
                    }
                }
                tilesHTML.append("</div>\n");
            }
        }

        mapString = mapString.replace("%tiles", tilesHTML.toString());


        return mapString;
    }

}
