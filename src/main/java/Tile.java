import enums.TileType;

public class Tile {
    private TileType tileType;
    private boolean isRevealed = false;

    public Tile(TileType tileType){
        this.tileType = tileType;
    }

    public void revealTile(){
        isRevealed = true;
    }

    public boolean isRevealed(){
        return isRevealed;
    }

    public TileType getTileType(){
        return tileType;
    }
}
