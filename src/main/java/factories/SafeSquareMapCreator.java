package factories;

import objects.maps.Map;
import objects.maps.SafeSquareMap;

public class SafeSquareMapCreator extends SquareMapCreator {

    public Map createSafeSquareMap(int mapSize){
        SafeSquareMap map =  SafeSquareMap.getInstance();
        map.setMapSize(mapSize);
        map.generate(map.generateSeed());
        return map;
    }
}
