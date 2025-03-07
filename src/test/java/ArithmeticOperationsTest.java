import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {

    @Test
    void testAdd() {
        assertEquals(5, ArithmeticOperations.add(2, 3)); // 2 + 3 = 5
        assertEquals(-1, ArithmeticOperations.add(2, -3)); // 2 + (-3) = -1
    }

    @Test
    void testSubtract() {
        assertEquals(1, ArithmeticOperations.subtract(4, 3)); // 4 - 3 = 1
        assertEquals(-5, ArithmeticOperations.subtract(2, 7)); // 2 - 7 = -5
    }

    @Test
    void testMultiply() {
        assertEquals(6, ArithmeticOperations.multiply(2, 3)); // 2 * 3 = 6
        assertEquals(-6, ArithmeticOperations.multiply(2, -3)); // 2 * (-3) = -6
    }

    @Test
    void testDivide() {
        assertEquals(2.0, ArithmeticOperations.divide(6, 3)); // 6 / 3 = 2.0
        assertEquals(0.5, ArithmeticOperations.divide(1, 2)); // 1 / 2 = 0.5
    }

    @Test
    void testDivideByZero() {
        // Проверяем, что при делении на ноль выбрасывается исключение
        assertThrows(ArithmeticException.class, () -> ArithmeticOperations.divide(5, 0));
    }
}