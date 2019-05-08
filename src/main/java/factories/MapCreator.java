package factories;

import enums.Difficulty;
import enums.MapType;
import objects.maps.Map;

public class MapCreator {

    public Map createMap(MapType mapType, Difficulty difficulty, int mapSize){
        switch (mapType){
            case SQUARE:
                return new SquareMapCreator().createSquareMap(difficulty, mapSize);
            default:
                return null; // Throw an exception
        }
    }

    public Map createMap(MapType mapType, Difficulty difficulty, char[][] seed){
        switch (mapType){
            case SQUARE:
                return new SquareMapCreator().createSquareMap(difficulty, seed);
            default:
                return null; // Throw an exception
        }
    }

}
