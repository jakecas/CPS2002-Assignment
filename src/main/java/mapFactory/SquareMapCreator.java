package mapFactory;

import enums.Difficulty;

public class SquareMapCreator extends MapCreator {

    public Map createSquareMap(Difficulty difficulty){
        switch (difficulty){
            case SAFE:
                return new SafeSquareMapCreator().createSafeSquareMap();
            case HAZARDOUS:
                return new HazardousSquareMapCreator().createHazardousSquareMap();
            default:
                return null; // Throw an exception
        }
    }
}
