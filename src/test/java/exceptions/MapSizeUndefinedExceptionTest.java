package exceptions;

import objects.maps.Map;
import objects.maps.SafeSquareMap;
import org.junit.Test;


public class MapSizeUndefinedExceptionTest {
    @Test(expected = MapSizeUndefinedException.class)
    public void testGenerate_sizeUndefined_throwsException(){
        Map map = SafeSquareMap.getInstance();
        map.generate(map.generateSeed());
    }
}