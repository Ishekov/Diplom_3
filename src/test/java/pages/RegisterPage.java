package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * Главная страница приложения Stellar Burgers
 * Содержит элементы главной страницы и методы для работы с ними
 */
public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ============ ЛОКАТОРЫ ЭЛЕМЕНТОВ ============
    // Поле ввода имени
    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    // Поле ввода email
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    // Поле ввода пароля
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    // Кнопка регистрации
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // Ссылка на страницу входа
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    // Локатор для сообщения об ошибке при некорректном пароле (появляется после нажатия кнопки)
    private final By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");

    //============ КОНСТРУКТОР ============

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ============ МЕТОДЫ ============
    @Step("Заполнить форму регистрации: имя = {name}, email = {email}, пароль = {password}")
    public void fillRegisterForm(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButtonAndExpectSuccess() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(registerButton));
        button.click();
    }

    @Step("Нажать на ссылку 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessageText() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.getText();
        } catch (TimeoutException e){
            return "";
        }
    }
    @Step("Проверить отображение сообщения об ошибке")
    public boolean isErrorMessageDisplayed() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}