// Класс для расчета факториала
public class FactorialCalculator {
    public static long calculateFactorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Число не должно быть отрицательным.");
        return n == 0 ? 1 : n * calculateFactorial(n - 1);
    }
}