package assignment.factories;

import assignment.objects.maps.HazardousSquareMap;
import assignment.objects.maps.Map;

public class HazardousSquareMapCreator extends SquareMapCreator {

    public Map createHazardousSquareMap(int mapSize){
        HazardousSquareMap map =  HazardousSquareMap.getInstance();
        map.setMapSize(mapSize);
        map.generate(map.generateSeed());
        return map;
    }
}
