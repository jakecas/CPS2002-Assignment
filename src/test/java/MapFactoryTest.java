import enums.Difficulty;
import enums.MapType;
import mapFactory.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class MapFactoryTest {
    MapCreator mapCreator;
    Map map;

    @Before
    public void setUp(){
        mapCreator = new MapCreator();
        map = mapCreator.createMap(MapType.SQUARE, Difficulty.SAFE);
    }

    @After
    public void tearDown(){
        mapCreator = null;
        map = null;
    }

    @Test
    public void testGenerate_safeMap_correctPercentageOfWater_returnsTrue(){
        map = mapCreator.createMap(MapType.SQUARE, Difficulty.SAFE);
        assertTrue("Testing if water tile percentage is less than 10%", map.waterPercent() <= 0.1);
    }

    @Test
    public void testGenerate_HazardousMap_correctPercentageOfWater_returnsTrue(){
        map = mapCreator.createMap(MapType.SQUARE, Difficulty.HAZARDOUS);
        assertTrue("Testing if water tile percentage is between 25% - 35%",
                (map.waterPercent() >= 0.25) && (map.waterPercent() <= 0.35));
    }

    @Test
    public void testCreateMap_safeSquareMap_isOfTypeSafeSquareMap(){
        map = mapCreator.createMap(MapType.SQUARE, Difficulty.SAFE);
        SafeSquareMap safeSquareMap = new SafeSquareMap();
        assertThat("Testing if the new map object is of type SafeSquareMap", safeSquareMap, instanceOf(map.getClass()));
    }

    @Test
    public void testCreateMap_hazardousSquareMap_isOfTypeHazardousSquareMap(){
        map = mapCreator.createMap(MapType.SQUARE, Difficulty.HAZARDOUS);
        HazardousSquareMap hazardousSquareMap = new HazardousSquareMap();
        assertThat("Testing if the new map object is of type HazardousSquareMap", hazardousSquareMap, instanceOf(map.getClass()));
    }
}
