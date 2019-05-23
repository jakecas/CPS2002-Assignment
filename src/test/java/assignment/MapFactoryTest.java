package assignment;

import assignment.enums.Difficulty;
import assignment.enums.MapType;
import assignment.enums.TileType;
import assignment.factories.*;
import assignment.objects.maps.HazardousSquareMap;
import assignment.objects.maps.Map;
import assignment.objects.maps.SafeSquareMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import assignment.objects.Position;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class MapFactoryTest {
    MapCreator mapCreator;
    Map safeMap;
    Map hazardousMap;

    @Before
    public void setUp(){
        mapCreator = new MapCreator();
        safeMap = mapCreator.createMap(MapType.SQUARE, Difficulty.SAFE, 5);
        hazardousMap = mapCreator.createMap(MapType.SQUARE, Difficulty.HAZARDOUS, 5);
    }

    @After
    public void tearDown(){
        mapCreator = null;
        safeMap = null;
        hazardousMap = null;
    }

    @Test
    public void testGenerate_safeMap_correctPercentageOfWater_returnsTrue(){
        assertTrue("Testing if water tile percentage is less than 10%", waterPercent(safeMap) <= 0.1);
    }

    @Test
    public void testGenerate_HazardousMap_correctPercentageOfWater_returnsTrue(){
        assertTrue("Testing if water tile percentage is between 25% - 35%",
                (waterPercent(hazardousMap) >= 0.25) && (waterPercent(hazardousMap) <= 0.35));
    }

    @Test
    public void testCreateMap_safeSquareMap_isOfTypeSafeSquareMap(){
        SafeSquareMap safeSquareMap = SafeSquareMap.getInstance();
        assertThat("Testing if the new map object is of type SafeSquareMap", safeSquareMap, instanceOf(safeMap.getClass()));
    }

    @Test
    public void testCreateMap_hazardousSquareMap_isOfTypeHazardousSquareMap(){
        HazardousSquareMap hazardousSquareMap = HazardousSquareMap.getInstance();
        assertThat("Testing if the new map object is of type HazardousSquareMap", hazardousSquareMap, instanceOf(hazardousMap.getClass()));
    }

    private double waterPercent(Map map){
        int waterTiles = 0;
        for(int i = 0; i < map.getMapSize(); i++){
            for(int j = 0; j < map.getMapSize(); j++){
                if (map.getTileType(new Position(i,j)).equals(TileType.WATER))
                    waterTiles++;
            }
        }
        return (double)waterTiles/(map.getMapSize()*map.getMapSize());
    }

}
