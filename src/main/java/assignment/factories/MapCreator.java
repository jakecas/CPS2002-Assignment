package assignment.factories;

import assignment.enums.Difficulty;
import assignment.enums.MapType;
import assignment.objects.maps.Map;

public class MapCreator {

    public Map createMap(MapType mapType, Difficulty difficulty, int mapSize){
        switch (mapType){
            case SQUARE:
                return new SquareMapCreator().createSquareMap(difficulty, mapSize);
            default:
                return null; // Throw an exception
        }
    }

}
