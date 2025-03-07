// Класс "Product"
class Product {
    // Поля класса
    private String name;
    private String productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isReserved;

    // Конструктор класса
    public Product(String name, String productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    // Переопределение метода toString для вывода информации о товаре
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Production Date: " + productionDate + "\n" +
                "Manufacturer: " + manufacturer + "\n" +
                "Country of Origin: " + countryOfOrigin + "\n" +
                "Price: " + price + "\n" +
                "Reservation Status: " + (isReserved ? "Reserved" : "Not Reserved");
    }
}

// Класс "Park" с внутренним классом "Attraction"
class Park {
    // Внутренний класс для хранения информации об аттракционах
    class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        // Конструктор внутреннего класса
        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        // Переопределение метода toString для вывода информации об аттракционе
        @Override
        public String toString() {
            return "Attraction: " + name + "\n" +
                    "Working Hours: " + workingHours + "\n" +
                    "Cost: " + cost;
        }
    }
}

// Основной класс программы
public class Main {
    public static void main(String[] args) {
        // Задача 1: Создание объекта товара и вывод информации
        Product product1 = new Product("Laptop", "2023-10-01", "Dell", "USA", 1500.0, false);
        System.out.println(product1);
        System.out.println();

        // Задача 2: Создание массива из 5 товаров
        Product[] productArray = new Product[5];
        productArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
        productArray[1] = new Product("iPhone 15", "15.09.2023", "Apple", "USA", 1299, false);
        productArray[2] = new Product("Xiaomi Mi 12", "20.03.2023", "Xiaomi", "China", 799, true);
        productArray[3] = new Product("Google Pixel 7", "10.10.2022", "Google", "USA", 899, false);
        productArray[4] = new Product("OnePlus 10 Pro", "05.01.2023", "OnePlus", "China", 999, true);

        // Вывод информации о всех товарах в массиве
        for (Product product : productArray) {
            System.out.println(product);
            System.out.println();
        }

        // Задача 3: Создание объекта парка и аттракционов
        Park park = new Park();
        Park.Attraction attraction1 = park.new Attraction("Ferris Wheel", "10:00 - 22:00", 5.0);
        Park.Attraction attraction2 = park.new Attraction("Roller Coaster", "11:00 - 20:00", 10.0);

        // Вывод информации об аттракционах
        System.out.println(attraction1);
        System.out.println();
        System.out.println(attraction2);
    }
}