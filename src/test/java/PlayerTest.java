import assignment.enums.Difficulty;
import assignment.enums.MapType;
import assignment.objects.maps.Map;
import assignment.enums.Direction;
import assignment.exceptions.PositionOutOfBoundsException;
import assignment.factories.MapCreator;
import assignment.observables.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import assignment.observers.Player;
import assignment.objects.Position;

import static org.junit.Assert.*;

public class PlayerTest {
    Position position;
    Map map;
    Player player;
    Team team;

    @Before
    public void setUp() {
        position = new Position(0, 0);
        MapCreator mapCreator = new MapCreator();
        map = mapCreator.createMap(MapType.SQUARE, Difficulty.SAFE, 5);
        map.setMapSize(5);
        map.generate(map.generateSeed());
        team = new Team(map);
        player = new Player(position, map, team);
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
        team.revealTile(player.move(Direction.SOUTH));
        assertTrue("Checking position changed by.", team.isRevealed(player.getPosition()));
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