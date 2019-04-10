import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void add_twoNumbers_shouldBeAdded() {
        assertEquals("Testing add method with 5+3=8.", 8, Main.add(5,3), 0);
    }

    @Test
    public void subtract_twoNumbers_failingTest() {
        assertEquals("Testing subtract method with 5-3=8.", 8, Main.subtract(5,3), 0);
    }
}