import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSetup {
    public static WebDriver setupDriver() {
        // Настройка ChromeDriver с помощью WebDriverManager
        WebDriverManager.chromedriver().driverVersion("134.0.6998.118").setup();

        // Логирование для проверки
        System.out.println("ChromeDriver успешно настроен с помощью WebDriverManager.");
        System.out.println("Используемая версия ChromeDriver: " + WebDriverManager.chromedriver().getDownloadedDriverVersion());

        // Создание экземпляра ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // Максимизируем окно браузера
        return driver;
    }
}