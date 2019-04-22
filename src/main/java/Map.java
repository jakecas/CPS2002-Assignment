import enums.TileType;
import exceptions.MapSizeUndefinedException;
import exceptions.PositionOutOfBoundsException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Map {

    private int size;
    private boolean isLarge;
    private Tile[][] tiles;

    public Map() {
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

    public void generate() {
        if(size == -1){
            throw new MapSizeUndefinedException();
        }
        tiles = new Tile[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                tiles[i][j] = new Tile(TileType.GRASS);
            }
        }

        int specialTilesSize = (int) Math.round((size/2.0));
        Position[] specialTiles = new Position[specialTilesSize];
        for(int i = 0; i < specialTilesSize; i++){
            specialTiles[i] = Position.randomPosition(size);
            for(int j = 0; j < i; j++){
                if(Position.euclideanDistance(specialTiles[i], specialTiles[j]) <2){
                    i--;
                    break;
                }
            }
        }

        Position position = specialTiles[0];
        tiles[position.getX()][position.getY()] = new Tile(TileType.TREASURE);

        for(int i = 1; i < specialTilesSize; i++){
            position = specialTiles[i];
            tiles[position.getX()][position.getY()] = new Tile(TileType.WATER);
        }
    }

    public Tile getTile(Position position) {
        if(tiles == null || position == null){
            return null;
        }
        if(!position.isWithinLimit(0, size)){
            throw new PositionOutOfBoundsException();
        }

        return tiles[position.getX()][position.getY()];
    }

    public TileType getTileType(Position position){
        return getTile(position).getTileType();
    }

    public String generateHTML(){
        StringBuilder mapHTML = new StringBuilder();

        URL resource = Game.class.getResource("map_prototype.html");
        File file = new File(resource.getFile());

        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            mapHTML.append(content);
        }catch (IOException e){
            e.getMessage();
            return "ERROR GENERATING HTML";
        }

        StringBuilder mapColumns = new StringBuilder();

        for(int i = 0; i < size; i++)
            mapColumns.append(" 50px");

        String mapString = mapHTML.toString();
        mapString = mapString.replace("$cpx", mapColumns.toString());


        StringBuilder tilesHTML = new StringBuilder();
        for (int row = 0; row < getMapSize(); row++) {

            for (int col = 0; col < getMapSize(); col++) {
                // Output as (column, row) to match (x, y) convention
                if(tiles[col][row].isRevealed())
                    tilesHTML.append("\t\t\t<div class=\"grid-item tile " + tiles[col][row].getTileType().getText() +"\">" + col + row + "</div>\n");
                else
                    tilesHTML.append("\t\t\t<div class=\"grid-item tile unknown\">" + col + row + "</div>\n");
            }
        }

        mapString = mapString.replace("$tiles", tilesHTML.toString());

        return mapString;
    }

}