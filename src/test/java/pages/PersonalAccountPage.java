package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ============ ЛОКАТОРЫ ЭЛЕМЕНТОВ ============
    // Кнопка выхода из аккаунта
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    // Кнопка перехода в конструктор
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    // Логотип для перехода на главную
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    // ============ КОНСТРУКТОР ============
    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // ============ МЕТОДЫ ============
    @Step("Нажать кнопку 'Выход' в личном кабинете")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Нажать на кнопку 'Конструктор' в шапке сайта")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }
    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }
}
