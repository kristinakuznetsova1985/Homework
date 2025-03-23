import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы для суммы оплаты
    @FindBy(xpath = "//span[@class='payment-amount']")
    private WebElement paymentAmountTop;

    @FindBy(xpath = "//button[contains(text(), 'Оплатить')]")
    private WebElement paymentAmountButton;

    // Локатор для номера телефона
    @FindBy(xpath = "//div[@class='phone-number']")
    private WebElement phoneNumberDisplay;

    // Локаторы для сообщения об ошибке
    @FindBy(xpath = "//div[contains(text(), 'Ошибка')]")
    private WebElement errorMessage;

    // Локаторы для полей карты
    @FindBy(xpath = "//input[@placeholder='Номер карты']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[@placeholder='Срок действия']")
    private WebElement cardExpiryInput;

    @FindBy(xpath = "//input[@placeholder='CVV']")
    private WebElement cardCvvInput;

    @FindBy(xpath = "//input[@placeholder='Имя держателя (как на карте)']")
    private WebElement cardHolderNameInput;

    // Локаторы для логотипов
    @FindBy(xpath = "//img[contains(@alt, 'Visa')]")
    private WebElement visaLogo;

    @FindBy(xpath = "//img[contains(@src, 'mastercard-system.svg')]")
    private WebElement mastercardLogo;

    @FindBy(xpath = "//img[contains(@alt, 'Белкарт')]")
    private WebElement belcardLogo;

    @FindBy(xpath = "//img[contains(@alt, 'Maestro')]")
    private WebElement maestroLogo;

    @FindBy(xpath = "//img[contains(@alt, 'Мир')]")
    private WebElement mirLogo;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    // Метод для получения суммы оплаты сверху страницы
    public String getPaymentAmountTop() {
        try {
            wait.until(ExpectedConditions.visibilityOf(paymentAmountTop));
            return paymentAmountTop.getText();
        } catch (Exception e) {
            System.out.println("Ошибка при получении суммы оплаты сверху страницы: " + e.getMessage());
            return null;
        }
    }

    // Метод для получения суммы оплаты на кнопке
    public String getPaymentAmountButton() {
        try {
            wait.until(ExpectedConditions.visibilityOf(paymentAmountButton));
            return paymentAmountButton.getText();
        } catch (Exception e) {
            System.out.println("Ошибка при получении суммы оплаты на кнопке: " + e.getMessage());
            return null;
        }
    }

    // Метод для получения отображаемого номера телефона
    public String getDisplayedPhoneNumber() {
        try {
            wait.until(ExpectedConditions.visibilityOf(phoneNumberDisplay));
            return phoneNumberDisplay.getText();
        } catch (Exception e) {
            System.out.println("Ошибка при получении номера телефона: " + e.getMessage());
            return null;
        }
    }

    // Метод для проверки отображения сообщения об ошибке
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            System.out.println("Сообщение об ошибке не отображается: " + e.getMessage());
            return false;
        }
    }

    // Метод для получения плейсхолдера поля "Номер карты"
    public String getCardNumberPlaceholder() {
        return cardNumberInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "Срок действия"
    public String getCardExpiryPlaceholder() {
        return cardExpiryInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "CVV"
    public String getCardCvvPlaceholder() {
        return cardCvvInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "Имя держателя (как на карте)"
    public String getCardHolderNamePlaceholder() {
        return cardHolderNameInput.getAttribute("placeholder");
    }

    // Метод для проверки отображения логотипа Visa
    public boolean isVisaLogoDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(visaLogo));
            return visaLogo.isDisplayed();
        } catch (Exception e) {
            System.out.println("Логотип Visa не отображается: " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки отображения логотипа Mastercard
    public boolean isMastercardLogoDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(mastercardLogo));
            return mastercardLogo.isDisplayed();
        } catch (Exception e) {
            System.out.println("Логотип Mastercard не отображается: " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки отображения логотипа Белкарт
    public boolean isBelcardLogoDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(belcardLogo));
            return belcardLogo.isDisplayed();
        } catch (Exception e) {
            System.out.println("Логотип Белкарт не отображается: " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки отображения логотипа Maestro или Мир
    public boolean isMaestroOrMirLogoDisplayed() {
        try {
            if (maestroLogo.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            try {
                return mirLogo.isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }
}