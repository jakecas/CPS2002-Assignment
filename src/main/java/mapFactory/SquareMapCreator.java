package mapFactory;

import enums.Difficulty;

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

    public Map createSquareMap(Difficulty difficulty, char[][] seed){
        switch (difficulty){
            case SAFE:
                return new SafeSquareMapCreator().createSafeSquareMap(seed);
            case HAZARDOUS:
                return new HazardousSquareMapCreator().createHazardousSquareMap(seed);
            default:
                return null; // Throw an exception
        }
    }
}
