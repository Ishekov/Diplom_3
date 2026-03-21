package tests;

import api.Authorization;
import api.UserApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseTest;

import java.time.Duration;

import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.hc.core5.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты регистрации")
public class RegisterTest extends BaseTest {



    @Test
    @DisplayName("Успешная регистрация с валидными данными")
    public void testSuccessfulRegistration() {
        String name = generateRandomName();
        String email = generateRandomEmail();
        String password = generateValidPassword();

        mainePage.open();
        mainePage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.fillRegisterForm(name, email, password);
        registerPage.clickRegisterButtonAndExpectSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isLoginPageLoaded = wait.until(ExpectedConditions.urlContains("login"));

        assertTrue(isLoginPageLoaded, "После успешной регистрации должен быть переход на страницу входа");

        userApi = new UserApi();
        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
        int statusCode = userApi.loginUser(auth).extract().statusCode();

        assertEquals(SC_OK, statusCode, "Ожидается статус ответа 200");
    }

    @Test
    @DisplayName("Ошибка при регистрации с невалидным паролем")
    public void testRegistrationWithInvalidPassword() {
        String name = generateRandomName();
        String email = generateRandomEmail();
        String password = generateInvalidPassword();

        mainePage.open();
        mainePage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.fillRegisterForm(name, email, password);
        registerPage.clickRegisterButtonAndExpectSuccess();

        boolean isErrorDisplayed = registerPage.isErrorMessageDisplayed();
        String errorText = registerPage.getErrorMessageText();

        assertTrue(isErrorDisplayed, "Ожидаем появление сообщения 'Некорректный пароль'");
        assertEquals("Некорректный пароль", errorText,
                "Ожидается сообщение об ошибке 'Некорректный пароль'");

        userApi = new UserApi();
        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
        int statusCode = userApi.loginUser(auth).extract().statusCode();

        assertEquals(SC_UNAUTHORIZED, statusCode, "Ожидается статус ответа 401");
    }
}
