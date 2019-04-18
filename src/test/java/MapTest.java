import enums.TileType;
import exceptions.PositionOutOfBoundsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {
    Map map;

    @Before
    public void setUp(){
        map = new Map();
    }

    @After
    public void tearDown() {
        map = null;
    }

    @Test
    public void testSetMapSize_tooSmall_returnsFalse() {
        assertEquals("Testing for a minimum map size 5x5", false, map.setMapSize(4));
    }

    @Test
    public void testSetMapSize_tooSmallForLargeMap_returnsFalse() {
        map.setIsLarge(true);
        assertEquals("Testing for a minimum map size 8x8", false, map.setMapSize(7));
    }

    @Test
    public void testSetMapSize_tooLarge_returnsFalse(){
        assertEquals("Testing for maximum map size 50x50", false, map.setMapSize(55));
    }

    @Test
    public void testSetMapSize_correctSize_returnsTrue(){
        assertEquals("Testing map size for size within limits", true, map.setMapSize(21));
        assertEquals("Testing map size for size at lower limit small map", true, map.setMapSize(5));
        assertEquals("Testing map size for size at upper limit", true, map.setMapSize(50));

        map.setIsLarge(true);
        assertEquals("Testing map size for size at lower limit big map", true, map.setMapSize(8));
    }

    @Test
    public void testGenerate_notNull(){
        map.setMapSize(5);
        map.generate();
        TileType tiles[][] = map.getTiles();
        assertNotNull("Testing array of tiles is not null", tiles);
    }

    @Test
    public void testGenerate_checkNumberOfTiles(){
        map.setMapSize(5);
        map.generate();
        TileType tiles[][] = map.getTiles();
        assertEquals("Testing size of array is as intended", 25, tiles.length*tiles[0].length);
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void testGetTileType_nonExistingTile_throwsPositionOutOfBoundsException() {
        map.setMapSize(5);
        map.generate();
        map.getTileType(new Position(8, 5));
    }

}
