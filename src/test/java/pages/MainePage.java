package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Constant.BASE_URL;

public class MainePage {
    private WebDriver driver;
    private WebDriverWait wait;
    // ============ ЛОКАТОРЫ ЭЛЕМЕНТОВ ============
    // Кнопка входа на главной
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // Кнопка "Личный кабинет" в шапке
    private final By personalAccountButton = By.xpath(".//a[@href='/account']");
    // Кнопка "Конструктор" в шапке
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    // Логотип Stellar Burgers
    private final By logo = By.className("AppHeader_header__logo__2D0X2");
    // Раздел "Булки" в конструкторе
    private final By bunsSection = By.xpath(".//span[text()='Булки']/parent::div");
    // Раздел "Соусы" в конструкторе
    private final By saucesSection = By.xpath(".//span[text()='Соусы']/parent::div");
    // Раздел "Начинки" в конструкторе
    private final By fillingsSection = By.xpath(".//span[text()='Начинки']/parent::div");
    // Активный раздел
    private final By activeSection = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/span");
    // Кнопка "Оформить заказ"
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    // ============ КОНСТРУКТОР ============
    public MainePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // ============ МЕТОДЫ ============
    @Step("Проверить, что пользователь на главная странице с конструктором")
    public boolean visabilityConstructorButton() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorButton)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    @Step("Открыть главную страницу")
    public void open() {
        driver.get(BASE_URL);
    }
    @Step("Нажать кнопку 'Войти в аккаунт' на главной странице")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    @Step("Нажать на кнопку 'Личный Кабинет' в шапке сайта")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }
    @Step("Нажать на кнопку 'Конструктор' в шапке сайта")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }
    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }
    @Step("Нажать на раздел 'Булки' в конструкторе")
    public void clickBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection)).click();
    }
    @Step("Нажать на раздел 'Соусы' в конструкторе")
    public void clickSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection)).click();
    }
    @Step("Нажать на раздел 'Начинки' в конструкторе")
    public void clickFillingsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection)).click();
    }
    @Step("Получить название текущего активного раздела")
    public String getActiveSection() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeSection)).getText();
    }
    @Step("Ожидать, что раздел {expectedSection} станет активным")
    public void waitForActiveSection(String expectedSection) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(activeSection, expectedSection));
    }
    @Step("Проверить, что пользователь авторизован (кнопка 'Оформить заказ' видна)")
    public boolean isUserLoggedIn() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    @Step("Проверить, что пользователь авторизован (кнопка 'Войти в аккаунт' не видна)")
    public boolean invisibilityLoginButton() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(loginButton));
    }
    @Step("Проверить, что пользователь не авторизован (кнопка 'Оформить заказ' не видна)")
    public boolean isUserLoggedOut() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(loginButton));
    }
    @Step("Проверить, что пользователь не авторизован (кнопка 'Войти в аккаунт' видна)")
    public boolean visibilityLoginButton() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}