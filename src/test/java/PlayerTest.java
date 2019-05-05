import enums.Difficulty;
import enums.MapType;
import mapFactory.Map;
import enums.Direction;
import exceptions.PositionOutOfBoundsException;
import mapFactory.MapCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import propertyObjects.Player;
import propertyObjects.Position;

import static org.junit.Assert.*;

public class PlayerTest {
    Position position;
    Map map;
    Player player;

    @Before
    public void setUp() {
        position = new Position(0, 0);
        MapCreator mapCreator = new MapCreator();
        map = mapCreator.createMap(MapType.SQUARE, Difficulty.SAFE);
        map.setMapSize(5);
        map.generate(map.generateSeed());
        player = new Player(position, map);
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
    public void testMove_southDirection_positionMovesDown(){
        Position newPosition = new Position(0, 1);
        player.move(Direction.SOUTH);
        assertEquals("Checking position changed by.", newPosition, player.getPosition());
    }

    @Test
    public void testMove_southDirection_positionIsRevealed(){
        player.move(Direction.SOUTH);
        assertTrue("Checking position changed by.", player.getMap().getTile(player.getPosition()).isRevealed());
    }

    @Test
    public void testResetToInitialPosition_afterMovement_positionIsReset(){
        player.move(Direction.EAST);
        player.move(Direction.SOUTH);
        player.move(Direction.SOUTH);
        player.move(Direction.WEST);
        assertNotEquals("Checking position is not equal to origin before reset.", player.getPosition(), player.getInitialPosition());
        player.resetToInitialPosition();
        assertEquals("Checking position is reset to origin.", player.getPosition(), player.getInitialPosition() );
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void testMove_illegalNorthDirection_throwsException(){
        player.move(Direction.NORTH);
    }
}