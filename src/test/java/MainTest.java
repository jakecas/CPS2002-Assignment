import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void add_twoNumbers() {
        assertEquals("Testing add method with 5+3.", 8, Main.add(5,3), 0);
    }
}