package assignment.objects.maps;

import assignment.enums.TileType;
import assignment.objects.Position;
import assignment.objects.Tile;
import assignment.observers.Player;


public interface Map {

    void generate(char[][] seed);
    char[][] generateSeed();
    String generateHTML(Player player);

    boolean setMapSize(int size);
    void setIsLarge(boolean isLarge);

    int getMapSize();
    Tile getTile(Position position);
    TileType getTileType(Position position);
    Tile[][] getTiles();
    boolean getIsLarge();
}
