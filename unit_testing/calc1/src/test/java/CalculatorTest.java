import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testAdd_positiveNums_positiveResult() {
        var calc = new Calculator();
        calc.add(5);
        assertEquals(5, calc.getCurrent());
    }

    @Test
    public void testAdd_HugeNum_ThrowsException() {
        var calc = new Calculator(2000000000);
        try {
            calc.add(2000000000);
            fail("Expected IllegalArgumentException not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Argument too large", e.getMessage());
        }

    }

}