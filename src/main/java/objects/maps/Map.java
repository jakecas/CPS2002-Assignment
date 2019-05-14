package objects.maps;

import enums.TileType;
import objects.Position;
import objects.Tile;

public interface Map {

    void generate(char[][] seed);
    char[][] generateSeed();
    String generateHTML(Position position);

    boolean setMapSize(int size);
    void setIsLarge(boolean isLarge);

    int getMapSize();
    Tile getTile(Position position);
    TileType getTileType(Position position);
    Tile[][] getTiles();
    char[][] getSeed();
    boolean getIsLarge();
}
