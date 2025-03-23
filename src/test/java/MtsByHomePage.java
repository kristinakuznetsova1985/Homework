import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MtsByHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локатор для выпадающего списка
    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button")
    private WebElement dropdownField;

    // Локаторы для пунктов выпадающего списка
    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[1]/p")
    private WebElement servicesButton; // Услуги связи

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[2]/p")
    private WebElement homeInternetButton; // Домашний интернет

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[3]/p")
    private WebElement installmentButton; // Рассрочка

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[4]/p")
    private WebElement debtButton; // Задолженность

    // Локаторы для полей
    @FindBy(xpath = "//input[@placeholder='Номер абонента']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@placeholder='Номер счета на 44']")
    private WebElement accountNumberInput;

    @FindBy(xpath = "//input[@placeholder='Сумма']")
    private WebElement amountInput;

    @FindBy(xpath = "//*[@id='internet-sum']")
    private WebElement internetAmountInput;

    @FindBy(xpath = "//input[@placeholder='E-mail для отправки чека']")
    private WebElement emailInput;

    // Локаторы для полей "Задолженность"
    @FindBy(xpath = "//*[@id='score-arrears']")
    private WebElement arrearsAccountNumberInput;

    @FindBy(xpath = "//*[@id='arrears-sum']")
    private WebElement arrearsAmountInput;

    @FindBy(xpath = "//*[@id='arrears-email']")
    private WebElement arrearsEmailInput;

    // Локаторы для полей "Услуги связи"
    @FindBy(xpath = "//*[@id='connection-phone']")
    private WebElement connectionPhoneInput;

    @FindBy(xpath = "//*[@id='connection-sum']")
    private WebElement connectionAmountInput;

    @FindBy(xpath = "//*[@id='connection-email']")
    private WebElement connectionEmailInput;

    // Локаторы для кнопок и других элементов
    @FindBy(xpath = "/html/body/div[6]/main/div/div[2]/div/div[2]/button[3]")
    private WebElement cookieCloseButton;

    @FindBy(xpath = "/html/body/div[6]/main/div/div[4]/div[1]/div/div/div[2]/section/div/h2")
    private WebElement blockTitle;

    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    public MtsByHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    // Метод для закрытия куки-бара
    public void closeCookieBar() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cookieCloseButton)).click();
            System.out.println("Куки-бар успешно закрыт.");
        } catch (Exception e) {
            System.out.println("Куки-бар не найден или не удалось закрыть: " + e.getMessage());
        }
    }

    // Метод для получения текста заголовка блока
    public String getBlockTitleText() {
        return blockTitle.getText().replace("\n", " ").replaceAll("\\s+", " ").trim();
    }

    // Метод для ввода номера телефона
    public void enterPhoneNumber(String phoneNumber) {
        try {
            wait.until(ExpectedConditions.visibilityOf(phoneInput));
            wait.until(ExpectedConditions.elementToBeClickable(phoneInput));
            phoneInput.clear();
            phoneInput.sendKeys(phoneNumber);
            System.out.println("Номер телефона успешно введен.");
        } catch (Exception e) {
            System.out.println("Ошибка при вводе номера телефона: " + e.getMessage());
            throw e;
        }
    }

    // Метод для ввода номера телефона для оплаты
    public void enterPhoneNumberForPayment(String phoneNumber) {
        try {
            wait.until(ExpectedConditions.visibilityOf(connectionPhoneInput));
            wait.until(ExpectedConditions.elementToBeClickable(connectionPhoneInput));
            connectionPhoneInput.clear();
            connectionPhoneInput.sendKeys(phoneNumber);
            System.out.println("Номер телефона успешно введен.");
        } catch (Exception e) {
            System.out.println("Ошибка при вводе номера телефона: " + e.getMessage());
            throw e;
        }
    }

    // Метод для ввода суммы
    public void enterAmount(String amount) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(amountInput));
            amountInput.clear();
            amountInput.sendKeys(amount);
            System.out.println("Сумма успешно введена.");
        } catch (Exception e) {
            System.out.println("Ошибка при вводе суммы: " + e.getMessage());
            throw e;
        }
    }

    // Метод для ввода суммы для Домашнего интернет
    public void enterInternetAmount(String amount) {
        internetAmountInput.sendKeys(amount);
    }

    // Метод для клика по кнопке "Продолжить"
    public void clickContinueButton() {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
            wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
            System.out.println("Кнопка 'Продолжить' успешно нажата.");
        } catch (Exception e) {
            System.out.println("Ошибка при нажатии кнопки 'Продолжить': " + e.getMessage());
        }
    }

    // Метод для проверки активности кнопки "Продолжить"
    public boolean isContinueButtonEnabled() {
        try {
            return continueButton.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке активности кнопки 'Продолжить': " + e.getMessage());
            return false;
        }
    }

    // Метод для получения плейсхолдера поля "Номер абонента"
    public String getPhoneNumberPlaceholder() {
        return phoneInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "Номер счета на 44"
    public String getAccountNumberPlaceholder() {
        return accountNumberInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "Сумма"
    public String getAmountPlaceholder() {
        return amountInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "Сумма" для Домашнего интернет
    public String getInternetAmountPlaceholder() {
        return internetAmountInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "E-mail для отправки чека"
    public String getEmailPlaceholder() {
        return emailInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "Номер счета на 2073"
    public String getArrearsAccountNumberPlaceholder() {
        return arrearsAccountNumberInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "Сумма" для Задолженности
    public String getArrearsAmountPlaceholder() {
        return arrearsAmountInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "E-mail" для Задолженности
    public String getArrearsEmailPlaceholder() {
        return arrearsEmailInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "Номер телефона" для Услуг связи
    public String getConnectionPhonePlaceholder() {
        return connectionPhoneInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "Сумма" для Услуг связи
    public String getConnectionAmountPlaceholder() {
        return connectionAmountInput.getAttribute("placeholder");
    }

    // Метод для получения плейсхолдера поля "E-mail" для Услуг связи
    public String getConnectionEmailPlaceholder() {
        return connectionEmailInput.getAttribute("placeholder");
    }

    // Метод для открытия выпадающего списка
    public void openDropdown() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownField)).click();
            System.out.println("Выпадающий список успешно открыт.");
        } catch (Exception e) {
            System.out.println("Не удалось открыть выпадающий список: " + e.getMessage());
        }
    }

    // Метод для выбора "Услуги связи"
    public void selectServices() {
        openDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(servicesButton)).click();
        System.out.println("Выбран пункт 'Услуги связи'.");

        // Ожидаем, пока поля обновятся
        try {
            wait.until(ExpectedConditions.visibilityOf(connectionPhoneInput));
            System.out.println("Поле 'Номер телефона' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'Номер телефона' не обновилось: " + e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(connectionAmountInput));
            System.out.println("Поле 'Сумма' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'Сумма' не обновилось: " + e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(connectionEmailInput));
            System.out.println("Поле 'E-mail для отправки чека' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'E-mail для отправки чека' не обновилось: " + e.getMessage());
        }
    }

    // Метод для выбора "Домашний интернет"
    public void selectHomeInternet() {
        openDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(homeInternetButton)).click();
        System.out.println("Выбран пункт 'Домашний интернет'.");

        // Ожидаем, пока поля обновятся
        try {
            wait.until(ExpectedConditions.visibilityOf(phoneInput));
            System.out.println("Поле 'Номер абонента' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'Номер абонента' не обновилось: " + e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(internetAmountInput));
            System.out.println("Поле 'Сумма' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'Сумма' не обновилось: " + e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(emailInput));
            System.out.println("Поле 'E-mail для отправки чека' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'E-mail для отправки чека' не обновилось: " + e.getMessage());
        }
    }

    // Метод для выбора "Рассрочка"
    public void selectInstallment() {
        openDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(installmentButton)).click();
        System.out.println("Выбран пункт 'Рассрочка'.");

        // Ожидаем, пока поля обновятся
        try {
            wait.until(ExpectedConditions.visibilityOf(accountNumberInput));
            System.out.println("Поле 'Номер счета на 44' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'Номер счета на 44' не обновилось: " + e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(amountInput));
            System.out.println("Поле 'Сумма' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'Сумма' не обновилось: " + e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(emailInput));
            System.out.println("Поле 'E-mail для отправки чека' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'E-mail для отправки чека' не обновилось: " + e.getMessage());
        }
    }

    // Метод для выбора "Задолженность"
    public void selectDebt() {
        openDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(debtButton)).click();
        System.out.println("Выбран пункт 'Задолженность'.");

        // Ожидаем, пока поля обновятся
        try {
            wait.until(ExpectedConditions.visibilityOf(arrearsAccountNumberInput));
            System.out.println("Поле 'Номер счета на 2073' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'Номер счета на 2073' не обновилось: " + e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(arrearsAmountInput));
            System.out.println("Поле 'Сумма' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'Сумма' не обновилось: " + e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(arrearsEmailInput));
            System.out.println("Поле 'E-mail для отправки чека' успешно обновлено.");
        } catch (Exception e) {
            System.out.println("Поле 'E-mail для отправки чека' не обновилось: " + e.getMessage());
        }
    }

    // Метод для проверки доступности поля "Номер абонента"
    public boolean isPhoneInputEnabled() {
        try {
            return phoneInput.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке доступности поля 'Номер абонента': " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки доступности поля "Сумма"
    public boolean isAmountInputEnabled() {
        try {
            return amountInput.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке доступности поля 'Сумма': " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки доступности поля "E-mail"
    public boolean isEmailInputEnabled() {
        try {
            return emailInput.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке доступности поля 'E-mail': " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки доступности поля "Номер счета на 2073"
    public boolean isArrearsAccountNumberInputEnabled() {
        try {
            return arrearsAccountNumberInput.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке доступности поля 'Номер счета на 2073': " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки доступности поля "Сумма" для Задолженности
    public boolean isArrearsAmountInputEnabled() {
        try {
            return arrearsAmountInput.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке доступности поля 'Сумма': " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки доступности поля "E-mail" для Задолженности
    public boolean isArrearsEmailInputEnabled() {
        try {
            return arrearsEmailInput.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке доступности поля 'E-mail': " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки доступности поля "Номер телефона" для Услуг связи
    public boolean isConnectionPhoneInputEnabled() {
        try {
            return connectionPhoneInput.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке доступности поля 'Номер телефона': " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки доступности поля "Сумма" для Услуг связи
    public boolean isConnectionAmountInputEnabled() {
        try {
            return connectionAmountInput.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке доступности поля 'Сумма': " + e.getMessage());
            return false;
        }
    }

    // Метод для проверки доступности поля "E-mail" для Услуг связи
    public boolean isConnectionEmailInputEnabled() {
        try {
            return connectionEmailInput.isEnabled();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке доступности поля 'E-mail': " + e.getMessage());
            return false;
        }
    }
}