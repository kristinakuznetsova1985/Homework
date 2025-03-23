import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Тесты для сайта MTS.BY")
@Feature("Проверка функционала оплаты")
public class MtsByTests {
    private WebDriver driver;
    private MtsByHomePage homePage;
    private PaymentPage paymentPage;

    @BeforeEach
    @Step("Настройка драйвера и открытие сайта")
    public void setUp() {
        driver = WebDriverSetup.setupDriver();
        homePage = new MtsByHomePage(driver);
        driver.get("https://www.mts.by/");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.urlContains("mts.by"));
            Allure.addAttachment("Страница загружена", "text/plain", "Страница успешно загружена.");
            System.out.println("Страница успешно загружена.");
        } catch (Exception e) {
            Allure.addAttachment("Ошибка загрузки", "text/plain", "Страница не загрузилась: " + e.getMessage());
            System.out.println("Страница не загрузилась: " + e.getMessage());
        }

        homePage.closeCookieBar();
    }

    @Test
    @Story("Проверка заголовка блока")
    @Description("Тест проверяет корректность отображения заголовка блока оплаты")
    @Severity(SeverityLevel.CRITICAL)
    public void testBlockTitle() {
        String actualText = homePage.getBlockTitleText();
        assertEquals("Онлайн пополнение без комиссии", actualText,
                "Ожидаемый текст: 'Онлайн пополнение без комиссии', но был получен: " + actualText);
        Allure.addAttachment("Результат проверки", "text/plain", "Заголовок блока корректен: " + actualText);
    }

    @Test
    @Story("Проверка страницы оплаты")
    @Description("Тест проверяет поля и логотипы на странице оплаты")
    @Severity(SeverityLevel.BLOCKER)
    public void testPaymentPageFieldsAndLogos() {
        homePage.selectServices();
        Allure.addAttachment("Выбор услуги", "text/plain", "Выбран пункт 'Услуги связи'.");
        System.out.println("Выбран пункт 'Услуги связи'.");

        String phoneNumber = "297777777";
        String amount = "10";
        homePage.enterPhoneNumberForPayment(phoneNumber);
        System.out.println("Введен номер телефона: " + phoneNumber + ".");
        homePage.enterAmount(amount);
        System.out.println("Введена сумма: " + amount + ".");
        Allure.addAttachment("Ввод данных", "text/plain",
                String.format("Введен номер: %s и сумма: %s", phoneNumber, amount));

        homePage.clickContinueButton();
        System.out.println("Нажата кнопка 'Продолжить'.");
        Allure.addAttachment("Переход", "text/plain", "Нажата кнопка 'Продолжить'.");

        paymentPage = new PaymentPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[8]/div/iframe")));
        driver.switchTo().frame(iframe);
        System.out.println("Переключение на iframe выполнено.");
        Allure.addAttachment("Iframe", "text/plain", "Переключение на iframe выполнено.");

        WebElement cardNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Номер карты']")));
        assertEquals("Номер карты", cardNumberLabel.getText(), "Неверный плейсхолдер для номера карты");
        System.out.println("Плейсхолдер 'Номер карты' корректен.");
        Allure.addAttachment("Проверка поля", "Плейсхолдер 'Номер карты' корректен");

        WebElement expiryDateLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Срок действия']")));
        assertEquals("Срок действия", expiryDateLabel.getText(), "Неверный плейсхолдер для срока действия");
        System.out.println("Плейсхолдер 'Срок действия' корректен.");
        Allure.addAttachment("Проверка поля", "Плейсхолдер 'Срок действия' корректен");

        WebElement cvvLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='CVC']")));
        assertEquals("CVC", cvvLabel.getText(), "Неверный плейсхолдер для CVC");
        System.out.println("Плейсхолдер 'CVC' корректен.");
        Allure.addAttachment("Проверка поля", "Плейсхолдер 'CVC' корректен");

        WebElement cardHolderLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Имя держателя (как на карте)']")));
        assertEquals("Имя держателя (как на карте)", cardHolderLabel.getText(), "Неверный плейсхолдер для имени держателя карты");
        System.out.println("Плейсхолдер 'Имя держателя (как на карте)' корректен.");
        Allure.addAttachment("Проверка поля", "Плейсхолдер 'Имя держателя' корректен");

        WebElement visaLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'visa-system.svg')]")));
        assertTrue(visaLogo.isDisplayed(), "Логотип Visa не отображается");
        System.out.println("Логотип Visa отображается корректно.");
        Allure.addAttachment("Проверка логотипа", "Логотип Visa отображается");

        WebElement mastercardLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'mastercard-system.svg')]")));
        assertTrue(mastercardLogo.isDisplayed(), "Логотип Mastercard не отображается");
        System.out.println("Логотип Mastercard отображается корректно.");
        Allure.addAttachment("Проверка логотипа", "Логотип Mastercard отображается");

        WebElement belcardLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'belkart-system.svg')]")));
        assertTrue(belcardLogo.isDisplayed(), "Логотип Белкарт не отображается");
        System.out.println("Логотип Белкарт отображается корректно.");
        Allure.addAttachment("Проверка логотипа", "Логотип Белкарт отображается");

        try {
            WebElement maestroLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'maestro-system.svg')]")));
            assertTrue(maestroLogo.isDisplayed(), "Логотип Maestro не отображается");
            System.out.println("Логотип Maestro отображается корректно.");
            Allure.addAttachment("Проверка логотипа", "Логотип Maestro отображается");
        } catch (Exception e) {
            WebElement mirLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'mir-system-ru.svg')]")));
            assertTrue(mirLogo.isDisplayed(), "Логотип Мир не отображается");
            System.out.println("Логотип Мир отображается корректно.");
            Allure.addAttachment("Проверка логотипа", "Логотип Мир отображается");
        }

        WebElement paymentButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/button")
        ));
        String paymentAmountText = paymentButton.getText();
        assertEquals("Оплатить 10.00 BYN", paymentAmountText, "Текст на кнопке оплаты не совпадает с ожидаемым");
        System.out.println("Текст на кнопке оплаты корректен: " + paymentAmountText);
        Allure.addAttachment("Проверка кнопки", "Текст на кнопке: " + paymentAmountText);

        WebElement paymentAmountTop = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), '10.00 BYN')]")
        ));
        String paymentAmountTopText = paymentAmountTop.getText();
        assertEquals("10.00 BYN", paymentAmountTopText, "Сумма оплаты вверху страницы не совпадает с ожидаемой");
        System.out.println("Сумма оплаты вверху страницы корректен: " + paymentAmountTopText);
        Allure.addAttachment("Проверка суммы", "Сумма вверху: " + paymentAmountTopText);

        WebElement phoneNumberDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), 'Номер:375297777777')]")
        ));
        String phoneNumberText = phoneNumberDisplay.getText();
        assertTrue(phoneNumberText.contains("375297777777"), "Номер телефона вверху страницы не совпадает с ожидаемым");
        System.out.println("Номер телефона вверху страницы корректен: " + phoneNumberText);
        Allure.addAttachment("Проверка номера", "Номер телефона: " + phoneNumberText);

        driver.switchTo().defaultContent();
        System.out.println("Возврат к основному контенту выполнен.");
        Allure.addAttachment("Переключение", "Возврат к основному контенту");
    }

    @Test
    @Story("Проверка оплаты домашнего интернета")
    @Description("Тест проверяет поля при выборе опции 'Домашний интернет'")
    @Severity(SeverityLevel.NORMAL)
    public void testHomeInternetPayment() {
        try {
            System.out.println("Запуск теста: проверка надписей в полях при выборе 'Домашний интернет'.");
            homePage.selectHomeInternet();
            System.out.println("Пункт 'Домашний интернет' успешно выбран.");
            Allure.addAttachment("Выбор услуги", "text/plain", "Выбран пункт 'Домашний интернет'.");

            assertEquals("Номер абонента", homePage.getPhoneNumberPlaceholder(), "Неверный плейсхолдер для номера телефона");
            assertEquals("Сумма", homePage.getInternetAmountPlaceholder(), "Неверный плейсхолдер для суммы");
            assertEquals("E-mail для отправки чека", homePage.getEmailPlaceholder(), "Неверный плейсхолдер для e-mail");
            Allure.addAttachment("Проверка плейсхолдеров", "Все плейсхолдеры корректны");

            assertTrue(homePage.isPhoneInputEnabled(), "Поле 'Номер абонента' недоступно для ввода");
            assertTrue(homePage.isAmountInputEnabled(), "Поле 'Сумма' недоступно для ввода");
            assertTrue(homePage.isEmailInputEnabled(), "Поле 'E-mail' недоступно для ввода");
            Allure.addAttachment("Проверка доступности полей", "Все поля доступны для ввода");

            System.out.println("Тест успешно завершен: все плейсхолдеры и поля корректны.");
        } catch (Exception e) {
            System.out.println("Тест завершился с ошибкой: " + e.getMessage());
            Allure.addAttachment("Ошибка", "text/plain", "Тест завершился с ошибкой: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Story("Проверка оплаты рассрочки")
    @Description("Тест проверяет поля при выборе опции 'Рассрочка'")
    @Severity(SeverityLevel.NORMAL)
    public void testInstallmentPayment() {
        homePage.selectInstallment();
        Allure.addAttachment("Выбор услуги", "text/plain", "Выбран пункт 'Рассрочка'.");

        assertEquals("Номер счета на 44", homePage.getAccountNumberPlaceholder(), "Неверный плейсхолдер для номера счета");
        assertEquals("Сумма", homePage.getAmountPlaceholder(), "Неверный плейсхолдер для суммы");
        assertEquals("E-mail для отправки чека", homePage.getEmailPlaceholder(), "Неверный плейсхолдер для e-mail");
        Allure.addAttachment("Проверка плейсхолдеров", "Все плейсхолдеры корректны");
    }

    @Test
    @Story("Проверка оплаты задолженности")
    @Description("Тест проверяет поля при выборе опции 'Задолженность'")
    @Severity(SeverityLevel.NORMAL)
    public void testDebtPayment() {
        try {
            System.out.println("Запуск теста: проверка надписей в полях при выборе 'Задолженность'.");
            homePage.selectDebt();
            System.out.println("Пункт 'Задолженность' успешно выбран.");
            Allure.addAttachment("Выбор услуги", "text/plain", "Выбран пункт 'Задолженность'.");

            assertEquals("Номер счета на 2073", homePage.getArrearsAccountNumberPlaceholder(), "Неверный плейсхолдер для номера счета");
            assertEquals("Сумма", homePage.getArrearsAmountPlaceholder(), "Неверный плейсхолдер для суммы");
            assertEquals("E-mail для отправки чека", homePage.getArrearsEmailPlaceholder(), "Неверный плейсхолдер для e-mail");
            Allure.addAttachment("Проверка плейсхолдеров", "Все плейсхолдеры корректны");

            assertTrue(homePage.isArrearsAccountNumberInputEnabled(), "Поле 'Номер счета на 2073' недоступно для ввода");
            assertTrue(homePage.isArrearsAmountInputEnabled(), "Поле 'Сумма' недоступно для ввода");
            assertTrue(homePage.isArrearsEmailInputEnabled(), "Поле 'E-mail' недоступно для ввода");
            Allure.addAttachment("Проверка доступности полей", "Все поля доступны для ввода");

            System.out.println("Тест успешно завершен: все плейсхолдеры и поля корректны.");
        } catch (Exception e) {
            System.out.println("Тест завершился с ошибкой: " + e.getMessage());
            Allure.addAttachment("Ошибка", "text/plain", "Тест завершился с ошибкой: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Story("Проверка оплаты услуг связи")
    @Description("Тест проверяет поля при выборе опции 'Услуги связи'")
    @Severity(SeverityLevel.NORMAL)
    public void testServicesPayment() {
        try {
            System.out.println("Запуск теста: проверка надписей в полях при выборе 'Услуги связи'.");
            homePage.selectServices();
            System.out.println("Пункт 'Услуги связи' успешно выбран.");
            Allure.addAttachment("Выбор услуги", "text/plain", "Выбран пункт 'Услуги связи'.");

            assertEquals("Номер телефона", homePage.getConnectionPhonePlaceholder(), "Неверный плейсхолдер для номера телефона");
            assertEquals("Сумма", homePage.getConnectionAmountPlaceholder(), "Неверный плейсхолдер для суммы");
            assertEquals("E-mail для отправки чека", homePage.getConnectionEmailPlaceholder(), "Неверный плейсхолдер для e-mail");
            Allure.addAttachment("Проверка плейсхолдеров", "Все плейсхолдеры корректны");

            assertTrue(homePage.isConnectionPhoneInputEnabled(), "Поле 'Номер телефона' недоступно для ввода");
            assertTrue(homePage.isConnectionAmountInputEnabled(), "Поле 'Сумма' недоступно для ввода");
            assertTrue(homePage.isConnectionEmailInputEnabled(), "Поле 'E-mail' недоступно для ввода");
            Allure.addAttachment("Проверка доступности полей", "Все поля доступны для ввода");

            System.out.println("Тест успешно завершен: все плейсхолдеры и поля корректны.");
        } catch (Exception e) {
            System.out.println("Тест завершился с ошибкой: " + e.getMessage());
            Allure.addAttachment("Ошибка", "text/plain", "Тест завершился с ошибкой: " + e.getMessage());
            throw e;
        }
    }

    @AfterEach
    @Step("Завершение теста")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}