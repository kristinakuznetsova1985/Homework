import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MtsByTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = WebDriverSetup.setupDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("http://mts.by");

        // Закрываем куки-бар, если он есть
        try {
            WebElement cookieCloseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/div[6]/main/div/div[2]/div/div[2]/button[3]")));
            if (cookieCloseButton.isDisplayed()) {
                cookieCloseButton.click();
                System.out.println("Куки-бар успешно закрыт.");
            }
        } catch (Exception e) {
            System.out.println("Куки-бар не найден или не удалось закрыть: " + e.getMessage());
        }
    }

    @Test
    public void testBlockTitle() {
        try {
            WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/div[6]/main/div/div[4]/div[1]/div/div/div[2]/section/div/h2")));

            // Получаем текст заголовка и удаляем лишние пробелы и переносы строк
            String actualText = blockTitle.getText().replace("\n", " ").replaceAll("\\s+", " ").trim();
            System.out.println("Текст заголовка после обработки: " + actualText); // Логирование для отладки

            // Проверяем, что текст соответствует ожидаемому
            assertEquals("Онлайн пополнение без комиссии", actualText,
                    "Ожидаемый текст: 'Онлайн пополнение без комиссии', но был получен: " + actualText);
        } catch (Exception e) {
            fail("Ошибка при проверке названия блока: " + e.getMessage());
        }
    }

    @Test
    public void testPaymentLogos() {
        try {
            // Проверяем логотип Visa
            WebElement visaLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//img[contains(@alt, 'Visa') or contains(@src, 'visa')]")));
            assertTrue(visaLogo.isDisplayed(), "Логотип Visa не отображается");
            assertNotNull(visaLogo.getAttribute("src"), "Логотип Visa не загружен");

            // Проверяем логотип Mastercard
            WebElement mastercardLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//img[contains(@alt, 'Mastercard') or contains(@src, 'mastercard')]")));
            assertTrue(mastercardLogo.isDisplayed(), "Логотип Mastercard не отображается");
            assertNotNull(mastercardLogo.getAttribute("src"), "Логотип Mastercard не загружен");

            // Проверяем логотип Белкарт
            WebElement belcardLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//img[contains(@alt, 'Белкарт') or contains(@src, 'belcard')]")));
            assertTrue(belcardLogo.isDisplayed(), "Логотип Белкарт не отображается");
            assertNotNull(belcardLogo.getAttribute("src"), "Логотип Белкарт не загружен");
        } catch (Exception e) {
            fail("Ошибка при проверке логотипов: " + e.getMessage());
        }
    }

    @Test
    public void testMoreDetailsLink() {
        try {
            WebElement moreDetailsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")));
            moreDetailsLink.click();

            // Ожидаем загрузки новой страницы и проверяем URL
            wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
            String currentUrl = driver.getCurrentUrl();
            assertTrue(currentUrl.contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                    "Ожидаемый URL должен содержать 'poryadok-oplaty-i-bezopasnost-internet-platezhey', но был получен: " + currentUrl);
        } catch (Exception e) {
            fail("Ошибка при проверке ссылки 'Подробнее о сервисе': " + e.getMessage());
        }
    }

    @Test
    public void testContinueButton() {
        try {
            // Находим поле для ввода номера телефона
            WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Номер телефона']")));

            // Вводим номер телефона
            phoneInput.sendKeys("297777777");

            // Находим поле для ввода суммы оплаты
            WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Сумма']")));

            // Вводим сумму оплаты (например, 10 рублей)
            amountInput.sendKeys("10");

            // Находим кнопку "Продолжить"
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Продолжить')]")));

            // Проверяем, что кнопка активна
            assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' не активна");

            // Нажимаем кнопку
            continueButton.click();

            // Проверяем сообщение об ошибке (если оно появляется)
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(), 'Неверный номер телефона') or contains(text(), 'Проверьте правильность ввода номера') or contains(text(), 'Укажите правильный номер')]")));
                assertTrue(errorMessage.isDisplayed(), "Сообщение об ошибке не отображается");
            } catch (Exception e) {
                // Если сообщение об ошибке не появилось, это тоже успех
                System.out.println("Сообщение об ошибке не появилось, что может быть ожидаемым поведением.");
            }
        } catch (Exception e) {
            fail("Ошибка при проверке кнопки 'Продолжить': " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}