package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ============ ЛОКАТОРЫ ЭЛЕМЕНТОВ ============
    // Поле ввода email
    private final By emailInput = By.xpath(".//input[@name='name']");
    // Поле ввода пароля
    private final By passwordInput = By.xpath(".//input[@name='Пароль']");
    // Кнопка "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    // Ссылка на регистрацию
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");
    // Ссылка восстановления пароля
    private final By forgotPasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    // Локаторы для сообщений об ошибке при неверном логине/пароле
    private final By loginError = By.xpath(".//p[contains(text(), 'email') or contains(text(), 'пароль')]");
    private final By loginErrorAlternative = By.xpath(".//p[contains(@class, 'error')]");

    // ============ КОНСТРУКТОР ============
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // ============ МЕТОДЫ ============
    @Step("Заполнить форму входа: email = {email}, пароль = {password}")
    public void fillLoginForm(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажать на ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Нажать на ссылку 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }
}
