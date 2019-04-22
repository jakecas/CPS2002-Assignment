import enums.TileType;
import exceptions.MapSizeUndefinedException;
import exceptions.PositionOutOfBoundsException;

public class Map {

    private int size;
    private boolean isLarge;
    private TileType[][] tiles;

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

    public TileType[][] getTiles(){
        return tiles;
    }

    public void generate() {
        if(size == -1){
            throw new MapSizeUndefinedException();
        }
        tiles = new TileType[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                tiles[i][j] = TileType.GRASS;
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
        tiles[position.getX()][position.getY()] = TileType.TREASURE;

        for(int i = 1; i < specialTilesSize; i++){
            position = specialTiles[i];
            tiles[position.getX()][position.getY()] = TileType.WATER;
        }
    }

    public TileType getTileType(Position position) {
        if(tiles == null || position == null){
            return null;
        }
        if(!position.isWithinLimit(0, size)){
            throw new PositionOutOfBoundsException();
        }

        return tiles[position.getX()][position.getY()];
    }
}
