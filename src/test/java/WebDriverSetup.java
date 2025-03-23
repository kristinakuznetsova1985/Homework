import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSetup {
    public static WebDriver setupDriver() {
        // Автоматическая настройка ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Логирование для проверки
        System.out.println("ChromeDriver успешно настроен с помощью WebDriverManager.");
        System.out.println("Используемая версия ChromeDriver: " + WebDriverManager.chromedriver().getDownloadedDriverVersion());

        // Создание экземпляра ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Максимизация окна браузера (опционально)
        driver.manage().window().maximize();

        return driver;
    }
}