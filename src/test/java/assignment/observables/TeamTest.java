package assignment.observables;

import assignment.enums.Difficulty;
import assignment.enums.MapType;
import assignment.factories.MapCreator;
import assignment.objects.Position;
import assignment.objects.maps.Map;
import assignment.observers.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
    MapCreator mapCreator;
    Map safeMap;
    Team team;
    Position origin;
    Player player;
    Position position34;

    @Before
    public void setUp() throws Exception {
        mapCreator = new MapCreator();
        safeMap = mapCreator.createMap(MapType.SQUARE, Difficulty.SAFE, 5);
        team = new Team(safeMap);
        origin = new Position(0, 0);
        player = new Player(origin, safeMap, team);
        position34 = new Position(3, 4);
    }

    @After
    public void tearDown() throws Exception {
        mapCreator = null;
        safeMap = null;
        team = null;
        origin = null;
        player = null;
        position34 = null;
    }

    @Test
    public void testRevealTile_tileIsRevealed() {
        team.revealTile(position34);
        assertTrue("Checking that origin is revealed after calling revealTile.", team.isRevealed(safeMap.getTile(position34)));
    }

    @Test
    public void testGetTeamSize_teamEmpty_returns0() {
        assertEquals("Checking that team size is one at initialisation with one player.", 1, team.getTeamSize());
    }

    @Test
    public void testRegisterPlayer_teamEmpty_teamSizeReturns1() {
        team.register(player);
        assertEquals("Checking that team size is two after adding another player.", 2, team.getTeamSize());

    }

    @Test
    public void testUnregisterPlayer_teamHasOnePlayer_teamSizeReturns0() {
        team.unregister(player);
        assertEquals("Checking that team size is one after adding one player.", 0, team.getTeamSize());
    }
}