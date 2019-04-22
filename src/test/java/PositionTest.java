import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    Position origin;
    Position position34;

    @Before
    public void setUp(){
        origin = new Position(0, 0);
        position34 = new Position(3, 4);
    }

    @Test
    public void testIsWithinLimits_withinLimits_returnsTrue(){
        assertTrue("Checking that position within the limits returns true.", position34.isWithinLimit(0,5));
    }

    @Test
    public void testIsWithinLimits_atLimits_returnsTrue(){
        assertTrue("Checking that position at the limits returns true.", position34.isWithinLimit(0,4));
    }

    @Test
    public void testIsWithinLimits_outsideLimits_returnsFalse(){
        assertFalse("Checking that position outside the limits returns false.", position34.isWithinLimit(0,1));
    }


    @Test
    public void testEquals_samePosition_returnsTrue(){
        Position origin2 = new Position(0, 0);
        assertEquals("Checking positions (0,0) are equal.", origin, origin2);
    }
}