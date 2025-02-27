import java.util.Scanner;

public class Main {
    // Задание 1. Метод 1 для вывода 3 слов в стобец
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // Задание 2. Метод для проверки суммы двух чисел
    public static void checkSumSign() {
        int a = 5; // Значение для переменной a
        int b = -3; // Значение для переменной b
        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // Задание 3. Вывод цвета в зависимости от значения
    public static void printColor() {
        int value = 75; // Значение для переменной value

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // Задание 4. Сравнение двух числел
    public static void compareNumbers() {
        int a = 10; // Пример значения для переменной a
        int b = 5;  // Пример значения для переменной b

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // Задание 5. Проверка суммы двух числел на принадлежность интервалу 10 - 20
    public static boolean checkSumRange() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int a = scanner.nextInt();

        System.out.print("Введите второе число: ");
        int b = scanner.nextInt();

        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // Задание 6. Проверка числа. Положительное или отрицательное. Ноль считаем положительным
    public static void checkNumber(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    // Задание 7. Целое или отрицательное. Булевое.
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // Задание 8. Напечатать указанную строку заданное число раз.
    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    // Задание 9. Определяем високосный год или нет.
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    // Задание 10. Задаем массив из 0 и 1. Ии заменяем их на противоположные.
    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Задание 11. Массив длиной 100 и значениями от 1 до 100
    public static void flipArrayValues(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 0 ? 1 : 0;
        }
    }

    // Задание
    public static void fillArraySequentially(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    // Задание 12. Задаем массив и умножаем числа меньше 6 на 2.
    public static void multiplyIfLessThanSix(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    // Задание 13. Квадратный массив.
    public static void fillDiagonals(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            array[i][i] = 1;
            array[i][array.length - 1 - i] = 1;
        }
    }

    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();

        boolean sumRangeResult = checkSumRange();
        System.out.println("Сумма в пределах от 10 до 20: " + sumRangeResult);

        int num = -5;
        checkNumber(num);
        System.out.println("Число отрицательное: " + isNegative(num));

        printStringMultipleTimes("Hello, я мультистрока!", 5); // Параметры для мультистроки

        int year = 2024;
        System.out.println(year + " год високосный: " + isLeapYear(year));

        int[] array1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Задание 10. Исходный массив:");
        printArray(array1);
        flipArrayValues(array1);
        System.out.println("Задание 10. Измененный массив:");
        printArray(array1);

        int[] array2 = new int[100];
        fillArraySequentially(array2);
        System.out.println("Задание 11. Массив от 1 до 100.:");
        printArray(array2);

        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Задание 12. Исходный массив:");
        printArray(array3);
        multiplyIfLessThanSix(array3);
        System.out.println("Задание 12. Измененный массив:");
        printArray(array3);

        int size = 5;
        int[][] array4 = new int[size][size];
        fillDiagonals(array4);
        System.out.println("Задание 13. Квадратный массив:");
        for (int[] row : array4) {
            printArray(row);
        }

        int[] array5 = createArray(5, 9);
        System.out.println("Задание 14. Массив:");
        printArray(array5);
    }
}