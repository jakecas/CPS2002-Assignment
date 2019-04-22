import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    Position position1;
    Position position2;

    @Before
    public void setUp(){
        position1 = new Position(0, 0);
        position2 = new Position(0, 0);
    }

    @Test
    public void testEquals_samePosition_returnsTrue(){
        assertEquals("Checking positions (0,0) are equal.", position1, position2);
    }
}