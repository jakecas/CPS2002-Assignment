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
        assertFalse("Testing for a minimum map size 5x5", map.setMapSize(4));
    }

    @Test
    public void testSetMapSize_tooSmallForLargeMap_returnsFalse() {
        map.setIsLarge(true);
        assertFalse("Testing for a minimum map size 8x8", map.setMapSize(7));
    }

    @Test
    public void testSetMapSize_tooLarge_returnsFalse(){
        assertFalse("Testing for maximum map size 50x50", map.setMapSize(55));
    }

    @Test
    public void testSetMapSize_correctSize_returnsTrue(){
        assertTrue("Testing map size for size within limits", map.setMapSize(21));
        assertTrue("Testing map size for size at lower limit small map", map.setMapSize(5));
        assertTrue("Testing map size for size at upper limit", map.setMapSize(50));

        map.setIsLarge(true);
        assertTrue("Testing map size for size at lower limit big map", map.setMapSize(8));
    }

    @Test
    public void testGenerate_notNull(){
        map.setMapSize(5);
        map.generate(map.generateSeed());
        Tile tiles[][] = map.getTiles();
        assertNotNull("Testing array of tiles is not null", tiles);
    }

    @Test
    public void testGenerate_checkNumberOfTiles(){
        map.setMapSize(5);
        map.generate(map.generateSeed());
        Tile tiles[][] = map.getTiles();
        assertEquals("Testing size of array is as intended", 25, tiles.length*tiles[0].length);
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void testGetTileType_nonExistingTile_throwsPositionOutOfBoundsException() {
        map.setMapSize(5);
        map.generate(map.generateSeed());
        map.getTile(new Position(8, 5));
    }

}
