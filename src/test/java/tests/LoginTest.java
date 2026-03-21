package tests;

import api.Authorization;
import api.UserApi;
import api.UserCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты входа в аккаунт")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    public void testLoginViaMainPageButton() {
        // пользователя для теста
        String name = generateRandomName();
        String email = generateRandomEmail();
        String password = generateValidPassword();

        userApi = new UserApi();
        UserCreate userCreate = new UserCreate(email, password, name);
        userApi.createUser(userCreate);

        mainePage.open();
        mainePage.clickLoginButton();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        boolean condition1 = mainePage.isUserLoggedIn();
        boolean condition2 = mainePage.invisibilityLoginButton();
        //Проверка, что кнопка 'Оформить заказ' видна или 'Войти в аккаунт' не видна
        assertTrue(condition1||condition2,
                "После входа должна открыться главная страница");

        // Сохраняем токен для удаления
        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginViaPersonalAccountButton() {
        String name = generateRandomName();
        String email = generateRandomEmail();
        String password = generateValidPassword();

        userApi = new UserApi();
        UserCreate userCreate = new UserCreate(email, password, name);
        userApi.createUser(userCreate);

        mainePage.open();
        mainePage.clickPersonalAccountButton();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        boolean condition1 = mainePage.isUserLoggedIn();
        boolean condition2 = mainePage.invisibilityLoginButton();

        assertTrue(condition1||condition2,
                "После входа должна открыться главная страница");

        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginViaRegisterForm() {
        String name = generateRandomName();
        String email = generateRandomEmail();
        String password = generateValidPassword();

        userApi = new UserApi();
        UserCreate userCreate = new UserCreate(email, password, name);
        userApi.createUser(userCreate);

        mainePage.open();
        mainePage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        boolean condition1 = mainePage.isUserLoggedIn();
        boolean condition2 = mainePage.invisibilityLoginButton();

        assertTrue(condition1||condition2,
                "После входа должна открыться главная страница");

        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginViaForgotPasswordForm() {
        String name = generateRandomName();
        String email = generateRandomEmail();
        String password = generateValidPassword();

        userApi = new UserApi();
        UserCreate userCreate = new UserCreate(email, password, name);
        userApi.createUser(userCreate);

        mainePage.open();
        mainePage.clickLoginButton();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.clickLoginLink();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();

        boolean condition1 = mainePage.isUserLoggedIn();
        boolean condition2 = mainePage.invisibilityLoginButton();

        assertTrue(condition1||condition2,
                "После входа должна открыться главная страница");

        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
    }
}