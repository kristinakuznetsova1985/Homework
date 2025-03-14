import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaCalculatorTest {

    @Test
    public void testCalculateArea() {
        assertEquals(TriangleAreaCalculator.calculateArea(5, 4), 10.0); // Площадь треугольника с основанием 5 и высотой 4
        assertEquals(TriangleAreaCalculator.calculateArea(3, 5), 7.5); // Площадь треугольника с основанием 3 и высотой 5
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateAreaNegativeOrZero() {
        TriangleAreaCalculator.calculateArea(0, 5); // Ожидаем исключение для нулевого основания
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateAreaNegativeHeight() {
        TriangleAreaCalculator.calculateArea(5, -1); // Ожидаем исключение для отрицательной высоты
    }
}
