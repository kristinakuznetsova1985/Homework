import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialCalculatorTest {

    @Test
    public void testCalculateFactorial() {
        assertEquals(FactorialCalculator.calculateFactorial(0), 1); // Факториал 0 равен 1
        assertEquals(FactorialCalculator.calculateFactorial(1), 1); // Факториал 1 равен 1
        assertEquals(FactorialCalculator.calculateFactorial(5), 120); // Факториал 5 равен 120
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateFactorialNegative() {
        FactorialCalculator.calculateFactorial(-1); // Ожидаем исключение для отрицательного числа
    }
}
