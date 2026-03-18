package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ============ ЛОКАТОРЫ ЭЛЕМЕНТОВ ============
    // Локатор ссылки "Войти" на странице восстановления пароля
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    // ============ КОНСТРУКТОР ============
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ============ МЕТОДЫ ============
    @Step("Нажать на ссылку 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}
