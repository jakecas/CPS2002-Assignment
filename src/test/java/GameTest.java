import enums.Direction;
import exceptions.PositionOutOfBoundsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class GameTest {
    Game game;

    @Before
    public void setUp(){
        Game.startGame(4, 5);
    }

    @After
    public void tearDown() {
        game = null;
    }

    @Test
    public void testNumOfPlayers_tooManyPlayers_returnsFalse(){
        assertFalse("Testing for too many players", Game.setNumPlayers(9));
    }

    @Test
    public void testNumOfPlayers_enoughPlayers_returnsTrue(){
        assertTrue("Testing for players within limit", Game.setNumPlayers(5));
        assertTrue("Testing for players lower limit", Game.setNumPlayers(2));
        assertTrue("Testing for players upper limit", Game.setNumPlayers(8));
    }

    @Test
    public void testSetMapSizeAfterSetNumPlayers_smallMap_returnsFalse(){
        Map map = Game.getMap();
        Game.setNumPlayers(3);
        assertFalse("Testing for small map creation", map.getIsLarge());
    }

    @Test
    public void testSetMapSizeAfterSetNumPlayers_largeMap_returnsTrue(){
        Map map = Game.getMap();
        Game.setNumPlayers(5);
        assertTrue("Testing for large map creation", map.getIsLarge());
    }

    @Test
    public void testHTMLGeneration_correctNumberOfFiles(){
        Game.startGame(2, 5);
        Game.generateHTMLFiles();
        int numOfFiles = new File(Paths.get("Player_Files").toString()).list().length;
        numOfFiles--; //One of the files is a CSS file
        assertEquals("Testing if the required number of files were created", 2, numOfFiles);
    }

    @Test
    public void testMenu_inputCharacter_returnsFalse(){
        Game.startGame(2, 5);
        Game.generateHTMLFiles();
        Player[] players = Game.getPlayers();
        ByteArrayInputStream in = new ByteArrayInputStream("f".getBytes());
        System.setIn(in);
        assertFalse("Testing that the menu doesn't accept a character", Game.menu(players[0], 0));
        System.setIn(System.in);
    }

    @Test
    public void testMenu_inputIntegerOutOfLimits_returnFalse(){
        Game.startGame(2, 5);
        Game.generateHTMLFiles();
        Player[] players = Game.getPlayers();
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        assertFalse("Testing that the menu doesn't accept an integer outside its range", Game.menu(players[0], 0));
        System.setIn(System.in);
    }

    @Test
    public void testMenu_inputIntegerWithinLimits_outOfBoundsMove_returnsFalse(){
        Game.startGame(2, 5);
        Game.generateHTMLFiles();
        Player[] players = Game.getPlayers();
        players[0].setPosition(new Position(4, 4)); //Set position to bottom-right of map
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes()); //Move South
        System.setIn(in);
        assertFalse("Testing that the menu accepts integer in range of an invalid move", Game.menu(players[0], 0));
        System.setIn(System.in);
    }

    @Test
    public void testMenu_inputIntegerWithinLimits_withinBoundsMove_returnsTrue(){
        Game.startGame(2, 5);
        Game.generateHTMLFiles();
        Player[] players = Game.getPlayers();
        players[0].setPosition(new Position(2, 2)); //Set position to middle of map
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes()); //Move North
        System.setIn(in);
        assertTrue("Testing that the menu accepts integer in range of a valid move", Game.menu(players[0], 0));
        System.setIn(System.in);
    }

}
