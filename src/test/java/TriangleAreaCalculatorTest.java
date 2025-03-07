import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaCalculatorTest {

    @Test
    void testCalculateArea() {
        assertEquals(10.0, TriangleAreaCalculator.calculateArea(5, 4)); // Площадь треугольника с основанием 5 и высотой 4
        assertEquals(7.5, TriangleAreaCalculator.calculateArea(3, 5)); // Площадь треугольника с основанием 3 и высотой 5
    }

    @Test
    void testCalculateAreaNegativeOrZero() {
        // Проверяем, что при нулевых или отрицательных значениях выбрасывается исключение
        assertThrows(IllegalArgumentException.class, () -> TriangleAreaCalculator.calculateArea(0, 5));
        assertThrows(IllegalArgumentException.class, () -> TriangleAreaCalculator.calculateArea(5, -1));
    }
}
