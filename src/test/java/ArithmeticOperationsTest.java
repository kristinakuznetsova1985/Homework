import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticOperationsTest {

    @Test
    public void testAdd() {
        assertEquals(ArithmeticOperations.add(2, 3), 5); // 2 + 3 = 5
        assertEquals(ArithmeticOperations.add(2, -3), -1); // 2 + (-3) = -1
    }

    @Test
    public void testSubtract() {
        assertEquals(ArithmeticOperations.subtract(4, 3), 1); // 4 - 3 = 1
        assertEquals(ArithmeticOperations.subtract(2, 7), -5); // 2 - 7 = -5
    }

    @Test
    public void testMultiply() {
        assertEquals(ArithmeticOperations.multiply(2, 3), 6); // 2 * 3 = 6
        assertEquals(ArithmeticOperations.multiply(2, -3), -6); // 2 * (-3) = -6
    }

    @Test
    public void testDivide() {
        assertEquals(ArithmeticOperations.divide(6, 3), 2.0); // 6 / 3 = 2.0
        assertEquals(ArithmeticOperations.divide(1, 2), 0.5); // 1 / 2 = 0.5
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        ArithmeticOperations.divide(5, 0); // Ожидаем исключение при делении на ноль
    }
}