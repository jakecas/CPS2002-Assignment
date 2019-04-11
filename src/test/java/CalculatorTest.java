import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calculator;

    @Before
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void add_twoNumbers_shouldBeAdded() {
        assertEquals("Testing add method with 5+3=8.", 8, calculator.add(5,3), 0);
    }

    @Test
    public void subtract_twoNumbers_shouldBeSubtracted() {
        assertEquals("Testing subtract method with 5-3=2.", 2, calculator.subtract(5,3), 0);
    }

    @Test
    public void multiply_twoNumbers_shouldBeMultiplied() {
        assertEquals("Testing multiply method with 8*3=24.", 24, calculator.multiply(8,3), 0);
    }

    @Test
    public void divide_twoNumbers_shouldBeDivided() {
        assertEquals("Testing divide method with 10/5=2.", 2, calculator.divide(10,5), 0);
    }
}