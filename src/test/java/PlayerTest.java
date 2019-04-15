import enums.Direction;
import exceptions.PositionOutOfBoundsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;
    Position position;

    @Before
    public void setUp() {
        position = new Position(0, 0);
        player = new Player(position, new Map());
    }

    @After
    public void tearDown() {
        player = null;
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void testSetPosition_negativePosition_throwsException() {
        player.setPosition(new Position(-1,-1));
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void testSetPosition_positionTooLarge_throwsException() {
        player.setPosition(new Position(51,20));
    }

    @Test
    public void testSetPosition_validPosition_positionIsSet(){
        Position position = new Position(4,2);
        player.setPosition(position);

        assertEquals("Checking that position is set correctly.", position, player.getPosition());
    }

    @Test
    public void testMove_southDirection_positionChanges(){
        player.move(Direction.SOUTH);
        assertNotEquals("Checking position changed.", position, player.getPosition());
    }
}