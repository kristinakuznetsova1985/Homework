// Интерфейс Shape
interface Shape {
    // Методы для получения периметра и площади
    double getPerimeter();
    double getArea();

    // Методы для задания цветов
    void setFillColor(String color);
    void setBorderColor(String color);

    // Методы для получения цветов
    String getFillColor();
    String getBorderColor();

    // Дефолтный метод для вывода информации о фигуре
    default void displayInfo() {
        System.out.println("Perimeter: " + getPerimeter());
        System.out.println("Area: " + getArea());
        System.out.println("Fill Color: " + getFillColor());
        System.out.println("Border Color: " + getBorderColor());
        System.out.println();
    }
}

// Класс Circle (круг)
class Circle implements Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

// Класс Rectangle (прямоугольник)
class Rectangle implements Shape {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

// Класс Triangle (треугольник)
class Triangle implements Shape {
    private double side1;
    private double side2;
    private double side3;
    private String fillColor;
    private String borderColor;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public double getArea() {
        // Используем формулу Герона для расчета площади треугольника
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

// Базовый класс Animal
class Animal {
    private static int animalCount = 0;
    private static int dogCount = 0;
    private static int catCount = 0;

    protected String name;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public void run(int distance) {
        System.out.println(name + " пробежал " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м.");
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public static int getDogCount() {
        return dogCount;
    }

    public static int getCatCount() {
        return catCount;
    }

    protected static void incrementDogCount() {
        dogCount++;
    }

    protected static void incrementCatCount() {
        catCount++;
    }
}

// Класс Dog
class Dog extends Animal {
    public Dog(String name) {
        super(name);
        incrementDogCount();
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            super.run(distance);
        } else {
            System.out.println(name + " не может пробежать больше 500 м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            super.swim(distance);
        } else {
            System.out.println(name + " не может проплыть больше 10 м.");
        }
    }
}

// Класс Cat
class Cat extends Animal {
    private boolean isFed;

    public Cat(String name) {
        super(name);
        incrementCatCount();
        this.isFed = false;
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            super.run(distance);
        } else {
            System.out.println(name + " не может пробежать больше 200 м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public void eatFromBowl(Bowl bowl, int amount) {
        if (bowl.decreaseFood(amount)) {
            this.isFed = true;
            System.out.println(name + " поел и теперь сыт.");
        } else {
            System.out.println(name + " не смог поесть, в миске недостаточно еды.");
        }
    }

    public boolean isFed() {
        return isFed;
    }
}

// Класс Bowl (миска)
class Bowl {
    private int foodAmount;

    public Bowl(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public boolean decreaseFood(int amount) {
        if (foodAmount >= amount) {
            foodAmount -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        foodAmount += amount;
        System.out.println("В миску добавлено " + amount + " еды. Теперь в миске " + foodAmount + " еды.");
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}

// Основной класс для тестирования геометрических фигур
class MainShapes {
    public static void main(String[] args) {
        // Создаем фигуры
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);
        Triangle triangle = new Triangle(3, 4, 5);

        // Задаем цвета
        circle.setFillColor("Red");
        circle.setBorderColor("Black");

        rectangle.setFillColor("Blue");
        rectangle.setBorderColor("Green");

        triangle.setFillColor("Yellow");
        triangle.setBorderColor("Purple");

        // Выводим информацию о фигурах
        System.out.println("Circle:");
        circle.displayInfo();

        System.out.println("Rectangle:");
        rectangle.displayInfo();

        System.out.println("Triangle:");
        triangle.displayInfo();
    }
}

// Основной класс для тестирования животных
class MainAnimals {
    public static void main(String[] args) {
        // Создаем животных
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");

        // Тестируем бег и плавание
        dog1.run(300);
        dog1.swim(5);
        dog2.run(600);
        dog2.swim(15);

        cat1.run(150);
        cat1.swim(10);
        cat2.run(250);
        cat2.swim(0);

        // Создаем миску и массив котов
        Bowl bowl = new Bowl(30);
        Cat[] cats = {cat1, cat2};

        // Кормим котов
        for (Cat cat : cats) {
            cat.eatFromBowl(bowl, 20);
        }

        // Проверяем сытость котов
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFed());
        }

        // Добавляем еду в миску и кормим снова
        bowl.addFood(20);
        for (Cat cat : cats) {
            cat.eatFromBowl(bowl, 20);
        }

        // Выводим статистику по животным
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Animal.getDogCount());
        System.out.println("Котов: " + Animal.getCatCount());
    }
}

// Общий класс для запуска обеих программ
public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск программы для геометрических фигур:");
        MainShapes.main(args);

        System.out.println("\nЗапуск программы для животных:");
        MainAnimals.main(args);
    }
}