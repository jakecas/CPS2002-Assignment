package objects;

import enums.TileType;

public class Tile {
    private TileType tileType;

    public Tile(TileType tileType){
        this.tileType = tileType;
    }

    public TileType getTileType(){
        return tileType;
    }

    public String toHTML(boolean isRevealed){
        if (isRevealed) {
            return "\t\t\t<div class=\"grid-item tile " + tileType.getText();
        }
        else {
            return "\t\t\t<div class=\"grid-item tile unknown";
        }
    }
}
