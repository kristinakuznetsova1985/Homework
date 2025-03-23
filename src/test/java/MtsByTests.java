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

public class MtsByTests {
    private WebDriver driver;
    private MtsByHomePage homePage;
    private PaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        driver = WebDriverSetup.setupDriver();
        homePage = new MtsByHomePage(driver);
        driver.get("https://www.mts.by/"); // Исправленный URL

        // Ожидаем загрузки страницы
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.urlContains("mts.by"));
            System.out.println("Страница успешно загружена.");
        } catch (Exception e) {
            System.out.println("Страница не загрузилась: " + e.getMessage());
        }

        // Закрываем куки-бар
        homePage.closeCookieBar();
    }

    @Test
    public void testBlockTitle() {
        String actualText = homePage.getBlockTitleText();
        assertEquals("Онлайн пополнение без комиссии", actualText,
                "Ожидаемый текст: 'Онлайн пополнение без комиссии', но был получен: " + actualText);
    }

    @Test
    public void testContinueButton() {
        homePage.enterPhoneNumber("297777777");
        homePage.enterAmount("10");
        assertTrue(homePage.isContinueButtonEnabled(), "Кнопка 'Продолжить' не активна");
        homePage.clickContinueButton();

        paymentPage = new PaymentPage(driver);
        assertTrue(paymentPage.isErrorMessageDisplayed(), "Сообщение об ошибке не отображается");
    }

    @Test
    public void testPaymentPageFieldsAndLogos() {
        // Выбираем "Услуги связи"
        homePage.selectServices();

        // Вводим номер телефона и сумму
        homePage.enterPhoneNumberForPayment("297777777");
        homePage.enterAmount("10");

        // Нажимаем кнопку "Продолжить"
        homePage.clickContinueButton();

        // Переходим на страницу оплаты
        paymentPage = new PaymentPage(driver);

        // Логируем текущий URL для отладки
        System.out.println("Текущий URL: " + driver.getCurrentUrl());

        // Ожидаем загрузки страницы оплаты
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // 1. Устанавливаем фокус на поле ввода номера карты
        paymentPage.focusOnCardNumberInput();

        // 2. Проверяем плейсхолдер для поля "Номер карты"
        assertEquals("Номер карты", paymentPage.getCardNumberPlaceholder(), "Неверный плейсхолдер для номера карты");

        // 3. Проверяем плейсхолдер для поля "Срок действия"
        WebElement cardExpiryInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Срок действия']")));
        assertEquals("Срок действия", cardExpiryInput.getAttribute("placeholder"), "Неверный плейсхолдер для срока действия");

        // 4. Проверяем плейсхолдер для поля "CVV"
        WebElement cardCvvInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='CVV']")));
        assertEquals("CVV", cardCvvInput.getAttribute("placeholder"), "Неверный плейсхолдер для CVV");

        // 5. Проверяем плейсхолдер для поля "Имя держателя (как на карте)"
        WebElement cardHolderNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Имя держателя (как на карте)']")));
        assertEquals("Имя держателя (как на карте)", cardHolderNameInput.getAttribute("placeholder"), "Неверный плейсхолдер для имени держателя карты");

        // 6. Проверяем сумму и номер телефона
        assertEquals("10.00 BYN", paymentPage.getPaymentAmountTop(), "Неверная сумма оплаты сверху страницы");
        assertEquals("Оплатить 10.00 BYN", paymentPage.getPaymentAmountButton(), "Неверная сумма оплаты на кнопке");
        assertTrue(paymentPage.getDisplayedPhoneNumber().contains("375297777777"), "Неверный номер телефона");

        // 7. Проверяем логотипы
        assertTrue(paymentPage.isVisaLogoDisplayed(), "Логотип Visa не отображается");

        // Проверяем логотип Mastercard с ожиданием
        try {
            WebElement mastercardLogo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[contains(@src, 'mastercard-system.svg')]")));
            assertTrue(mastercardLogo.isDisplayed(), "Логотип Mastercard не отображается");
        } catch (Exception e) {
            System.out.println("Логотип Mastercard не найден: " + e.getMessage());
            fail("Логотип Mastercard не отображается");
        }

        // Проверяем остальные логотипы
        assertTrue(paymentPage.isBelcardLogoDisplayed(), "Логотип Белкарт не отображается");
        assertTrue(paymentPage.isMaestroOrMirLogoDisplayed(), "Логотип Maestro или Мир не отображается");
    }

    @Test
    public void testEmptyFieldsPlaceholders() {
        homePage.enterPhoneNumber("297777777");
        homePage.enterAmount("10");
        homePage.clickContinueButton();

        paymentPage = new PaymentPage(driver);
        assertEquals("Номер телефона", homePage.getPhoneNumberPlaceholder(), "Неверный плейсхолдер для номера телефона");
        assertEquals("Сумма", homePage.getAmountPlaceholder(), "Неверный плейсхолдер для суммы");
        assertEquals("Номер карты", paymentPage.getCardNumberPlaceholder(), "Неверный плейсхолдер для номера карты");
        assertEquals("Срок действия", paymentPage.getCardExpiryPlaceholder(), "Неверный плейсхолдер для срока действия");
        assertEquals("CVV", paymentPage.getCardCvvPlaceholder(), "Неверный плейсхолдер для CVV");
    }

    @Test
    public void testHomeInternetPayment() {
        try {
            // 1. Логируем начало теста
            System.out.println("Запуск теста: проверка надписей в полях при выборе 'Домашний интернет'.");

            // 2. Выбираем "Домашний интернет"
            homePage.selectHomeInternet();
            System.out.println("Пункт 'Домашний интернет' успешно выбран.");

            // 3. Проверяем плейсхолдеры полей
            assertEquals("Номер абонента", homePage.getPhoneNumberPlaceholder(), "Неверный плейсхолдер для номера телефона");
            assertEquals("Сумма", homePage.getInternetAmountPlaceholder(), "Неверный плейсхолдер для суммы"); // Используем новый метод
            assertEquals("E-mail для отправки чека", homePage.getEmailPlaceholder(), "Неверный плейсхолдер для e-mail");

            // 4. Проверяем, что поля доступны для ввода
            assertTrue(homePage.isPhoneInputEnabled(), "Поле 'Номер абонента' недоступно для ввода");
            assertTrue(homePage.isAmountInputEnabled(), "Поле 'Сумма' недоступно для ввода");
            assertTrue(homePage.isEmailInputEnabled(), "Поле 'E-mail' недоступно для ввода");

            // 5. Логируем успешное завершение теста
            System.out.println("Тест успешно завершен: все плейсхолдеры и поля корректны.");
        } catch (Exception e) {
            // 6. Логируем ошибку, если что-то пошло не так
            System.out.println("Тест завершился с ошибкой: " + e.getMessage());
            throw e; // Перебрасываем исключение, чтобы тест был отмечен как неудачный
        }
    }

    @Test
    public void testInstallmentPayment() {
        // 1. Открываем выпадающий список и выбираем "Рассрочка"
        homePage.selectInstallment();

        // 2. Проверяем текст в пустых полях
        assertEquals("Номер счета на 44", homePage.getAccountNumberPlaceholder(), "Неверный плейсхолдер для номера счета");
        assertEquals("Сумма", homePage.getAmountPlaceholder(), "Неверный плейсхолдер для суммы");
        assertEquals("E-mail для отправки чека", homePage.getEmailPlaceholder(), "Неверный плейсхолдер для e-mail");
    }

    @Test
    public void testDebtPayment() {
        try {
            // 1. Логируем начало теста
            System.out.println("Запуск теста: проверка надписей в полях при выборе 'Задолженность'.");

            // 2. Выбираем "Задолженность"
            homePage.selectDebt();
            System.out.println("Пункт 'Задолженность' успешно выбран.");

            // 3. Проверяем плейсхолдеры полей
            assertEquals("Номер счета на 2073", homePage.getArrearsAccountNumberPlaceholder(), "Неверный плейсхолдер для номера счета");
            assertEquals("Сумма", homePage.getArrearsAmountPlaceholder(), "Неверный плейсхолдер для суммы");
            assertEquals("E-mail для отправки чека", homePage.getArrearsEmailPlaceholder(), "Неверный плейсхолдер для e-mail");

            // 4. Проверяем, что поля доступны для ввода
            assertTrue(homePage.isArrearsAccountNumberInputEnabled(), "Поле 'Номер счета на 2073' недоступно для ввода");
            assertTrue(homePage.isArrearsAmountInputEnabled(), "Поле 'Сумма' недоступно для ввода");
            assertTrue(homePage.isArrearsEmailInputEnabled(), "Поле 'E-mail' недоступно для ввода");

            // 5. Логируем успешное завершение теста
            System.out.println("Тест успешно завершен: все плейсхолдеры и поля корректны.");
        } catch (Exception e) {
            // 6. Логируем ошибку, если что-то пошло не так
            System.out.println("Тест завершился с ошибкой: " + e.getMessage());
            throw e; // Перебрасываем исключение, чтобы тест был отмечен как неудачный
        }
    }

    @Test
    public void testServicesPayment() {
        try {
            // 1. Логируем начало теста
            System.out.println("Запуск теста: проверка надписей в полях при выборе 'Услуги связи'.");

            // 2. Выбираем "Услуги связи"
            homePage.selectServices();
            System.out.println("Пункт 'Услуги связи' успешно выбран.");

            // 3. Проверяем плейсхолдеры полей
            assertEquals("Номер телефона", homePage.getConnectionPhonePlaceholder(), "Неверный плейсхолдер для номера телефона");
            assertEquals("Сумма", homePage.getConnectionAmountPlaceholder(), "Неверный плейсхолдер для суммы");
            assertEquals("E-mail для отправки чека", homePage.getConnectionEmailPlaceholder(), "Неверный плейсхолдер для e-mail");

            // 4. Проверяем, что поля доступны для ввода
            assertTrue(homePage.isConnectionPhoneInputEnabled(), "Поле 'Номер телефона' недоступно для ввода");
            assertTrue(homePage.isConnectionAmountInputEnabled(), "Поле 'Сумма' недоступно для ввода");
            assertTrue(homePage.isConnectionEmailInputEnabled(), "Поле 'E-mail' недоступно для ввода");

            // 5. Логируем успешное завершение теста
            System.out.println("Тест успешно завершен: все плейсхолдеры и поля корректны.");
        } catch (Exception e) {
            // 6. Логируем ошибку, если что-то пошло не так
            System.out.println("Тест завершился с ошибкой: " + e.getMessage());
            throw e; // Перебрасываем исключение, чтобы тест был отмечен как неудачный
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}