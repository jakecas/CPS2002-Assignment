import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void add_twoNumbers_shouldBeAdded() {
        assertEquals("Testing add method with 5+3=8.", 8, Main.add(5,3), 0);
    }

    @Test
    public void subtract_twoNumbers_shouldBeSubtracted() {
        assertEquals("Testing subtract method with 5-3=2.", 2, Main.subtract(5,3), 0);
    }

    @Test
    public void multiply_twoNumbers_shouldBeMultiplied() {
        assertEquals("Testing multiply method with 8*3=24.", 24, Main.add(8,3), 0);
    }

    @Test
    public void divide_twoNumbers_shouldBeDivided() {
        assertEquals("Testing divide method with 10/5=2.", 2, Main.subtract(10,5), 0);
    }
}