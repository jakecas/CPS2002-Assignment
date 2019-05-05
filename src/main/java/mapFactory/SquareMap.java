package mapFactory;

import propertyObjects.*;
import main.Game;
import enums.TileType;
import exceptions.HTMLGenerationException;
import exceptions.PositionOutOfBoundsException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public  abstract class SquareMap implements Map{

    protected static int size;
    private boolean isLarge;
    private Tile[][] tiles;

    public SquareMap() {
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

    public double waterPercent(){
        int waterTiles = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if (tiles[i][j].equals(TileType.WATER))
                    waterTiles++;
            }
        }
        return waterTiles/size;
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

    public TileType getTileType(Position position){
        return getTile(position).getTileType();
    }

    public String generateHTML(Position position){
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
                tilesHTML.append(tiles[col][row].toHTML());
                tilesHTML.append( "\">");
                tilesHTML.append(col);
                tilesHTML.append(",");
                tilesHTML.append(row);

                if (col == position.getX()){
                    if (row == position.getY()) {
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
