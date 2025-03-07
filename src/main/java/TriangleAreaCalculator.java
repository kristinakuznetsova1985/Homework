// Класс для вычисления площади треугольника
public class TriangleAreaCalculator {
    public static double calculateArea(double base, double height) {
        if (base <= 0 || height <= 0) throw new IllegalArgumentException("Основание и высота должны быть положительными.");
        return 0.5 * base * height;
    }
}
