import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game;

    @Before
    public void setUp(){
        game = new Game(4);
    }

    @After
    public void tearDown() {
        game = null;
    }

    @Test
    public void testNumOfPlayers_tooManyPlayers_returnsFalse(){
        assertFalse("Testing for too many players", game.setNumPlayers(9));
    }

    @Test
    public void testNumOfPlayers_enoughPlayers_returnsTrue(){
        assertTrue("Testing for players within limit", game.setNumPlayers(5));
        assertTrue("Testing for players lower limit", game.setNumPlayers(2));
        assertTrue("Testing for players upper limit", game.setNumPlayers(8));
    }

    @Test
    public void testSetMapSizeAfterSetNumPlayers_smallMap_returnsFalse(){
        game.setNumPlayers(3);
        Map map = new Map();
        assertFalse("Testing for small map creation", map.getIsLarge());
    }

    @Test
    public void testSetMapSizeAfterSetNumPlayers_largeMap_returnsTrue(){
        game.setNumPlayers(5);
        Map map = new Map();
        assertTrue("Testing for small map creation", map.getIsLarge());
    }
}
