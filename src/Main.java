// Пользовательские исключения
class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int sum = sumArrayElements(array);
            System.out.println("Sum of array elements: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Array size error: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Array data error: " + e.getMessage());
        }

        // Генерация и поимка ArrayIndexOutOfBoundsException
        try {
            int[] arr = new int[5];
            int value = arr[10]; // Это вызовет ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }

    public static int sumArrayElements(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Array must have 4 rows.");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Row " + i + " must have 4 columns.");
            }
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Invalid data at cell [" + i + "][" + j + "]");
                }
            }
        }
        return sum;
    }
}