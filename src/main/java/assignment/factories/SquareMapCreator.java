package assignment.factories;

import assignment.enums.Difficulty;
import assignment.objects.maps.Map;

public class SquareMapCreator extends MapCreator {

    public Map createSquareMap(Difficulty difficulty, int mapSize){
        switch (difficulty){
            case SAFE:
                return new SafeSquareMapCreator().createSafeSquareMap(mapSize);
            case HAZARDOUS:
                return new HazardousSquareMapCreator().createHazardousSquareMap(mapSize);
            default:
                return null; // Throw an exception
        }
    }
}
