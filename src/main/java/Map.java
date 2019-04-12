public class Map {

    private int size;
    private boolean isLarge;
    private TileType tiles[][];

    public Map() {
        size = -1;
        isLarge = false;
    }

    public boolean setMapSize(int size) {
       return false;
    }

    public void setIsLarge(boolean isLarge){
        this.isLarge = isLarge;
    }

    public TileType[][] getTiles(){
        return tiles;
    }

    public void generate() {

    }

    public TileType getTileType(int x, int y) {
        return null;
    }
}
