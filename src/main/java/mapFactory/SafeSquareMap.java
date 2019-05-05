package mapFactory;

import exceptions.MapSizeUndefinedException;
import propertyObjects.Position;

public class SafeSquareMap extends SquareMap {

    public char[][] generateSeed() {
        if(size == -1){
            throw new MapSizeUndefinedException("Generating Seed");
        }
        char[][] tiles = new char[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                tiles[i][j] = 'g';
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
        tiles[position.getX()][position.getY()] = 't';

        for(int i = 1; i < specialTilesSize; i++){
            position = specialTiles[i];
            tiles[position.getX()][position.getY()] = 'w';
        }

        return tiles;
    }

}
