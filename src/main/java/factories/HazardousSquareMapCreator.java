package factories;

import objects.maps.HazardousSquareMap;
import objects.maps.Map;

public class HazardousSquareMapCreator extends SquareMapCreator {

    public Map createHazardousSquareMap(int mapSize){
        HazardousSquareMap map =  HazardousSquareMap.getInstance();
        map.setMapSize(mapSize);
        map.generate(map.generateSeed());
        return map;
    }
}
