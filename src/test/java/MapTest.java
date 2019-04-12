import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class MapTest {
    Map map;

    @Before
    void setUp(){
        map = new Map();
    }

    @After
    void tearDown() {
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
        for(int i = 0; i < tiles.length; i++)
            for(int j = 0; i < tiles[i].length; j++)
                assertNotNull("Testing to see that map array at (" + i + ", " + j  +  ") isn't null", tiles[i][j]);
    }

    @Test
    public void testGenerate_checkNumberOfTiles(){
        map.setMapSize(5);
        map.generate();
        TileType tiles[][] = map.getTiles();
        assertEquals("Testing size of array is as intended", 25, tiles.length*tiles[0].length);
    }

    @Rule
    public final ExpectedException e = ExpectedException.none();

    @Test
    public void testGetTileType_nonExistingTile_throwsPositionOutOfBoundsException() {
        map.setMapSize(5);
        e.expect(IndexOutOfBoundsException.class);
        e.expectMessage("message");
        map.getTileType(8, 5);
    }

    public static void main(String[] args) {
        MapTest testing = new MapTest();

        testing.setUp();

        // Map size tests
        testing.testSetMapSize_tooSmall_returnsFalse();
        testing.testSetMapSize_tooSmallForLargeMap_returnsFalse();
        testing.testSetMapSize_tooLarge_returnsFalse();
        testing.testSetMapSize_correctSize_returnsTrue();

        // Generate map tests
        testing.testGenerate_notNull();
        testing.testGenerate_checkNumberOfTiles();

        // Tile type tests
        testing.testGetTileType_nonExistingTile_throwsPositionOutOfBoundsException();

        testing.tearDown();
    }
}
