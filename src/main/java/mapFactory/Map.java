package mapFactory;

import enums.TileType;
import propertyObjects.Position;
import propertyObjects.Tile;

public interface Map {
    void generate(char[][] seed);
    char[][] generateSeed();
    String generateHTML(Position position);

    boolean setMapSize(int size);
    void setIsLarge(boolean isLarge);

    double waterPercent();
    int getMapSize();
    Tile getTile(Position position);
    TileType getTileType(Position position);
    Tile[][] getTiles();
    char[][] getSeed();
    boolean getIsLarge();
}
