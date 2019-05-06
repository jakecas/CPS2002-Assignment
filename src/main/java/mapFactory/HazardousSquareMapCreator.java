package mapFactory;

public class HazardousSquareMapCreator extends SquareMapCreator {

    public Map createHazardousSquareMap(int mapSize){
        HazardousSquareMap map =  new HazardousSquareMap();
        map.setMapSize(mapSize);
        map.generate(map.generateSeed());
        return map;
    }

    public Map createHazardousSquareMap(char[][] seed){
        HazardousSquareMap map =  new HazardousSquareMap();
        map.setMapSize(seed.length);
        map.generate(seed);
        return map;
    }
}
