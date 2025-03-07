import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    @Test
    void testCalculateFactorial() {
        assertEquals(1, FactorialCalculator.calculateFactorial(0)); // Факториал 0 равен 1
        assertEquals(1, FactorialCalculator.calculateFactorial(1)); // Факториал 1 равен 1
        assertEquals(120, FactorialCalculator.calculateFactorial(5)); // Факториал 5 равен 120
    }

    @Test
    void testCalculateFactorialNegative() {
        // Проверяем, что при отрицательном числе выбрасывается исключение
        assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.calculateFactorial(-1));
    }
}
