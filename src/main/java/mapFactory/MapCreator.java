package mapFactory;

import enums.Difficulty;
import enums.MapType;

public class MapCreator {

    public Map createMap(MapType mapType, Difficulty difficulty){
        switch (mapType){
            case SQUARE:
                return new SquareMapCreator().createSquareMap(difficulty);
            default:
                return null; // Throw an exception
        }
    }



}
