package tests;

import api.Authorization;
import api.UserApi;
import api.UserCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constant.BASE_URL;

@DisplayName("Тесты навигации")
public class NavigationTest extends BaseTest {

    @Test
    @DisplayName("Переход в личный кабинет по клику на 'Личный кабинет'")
    public void testNavigateToPersonalAccount() {
        // Создаем пользователя и логинимся
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

        // Тест
        mainePage.clickPersonalAccountButton();

        assertTrue(driver.getCurrentUrl().contains("/account"),
                "Должен открыться личный кабинет");

        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по кнопке 'Конструктор'")
    public void testNavigateFromPersonalAccountToConstructor() {
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

        mainePage.clickPersonalAccountButton();

        personalAccountPage.clickConstructorButton();

        assertTrue(driver.getCurrentUrl().equals(BASE_URL) ||
                        driver.getCurrentUrl().equals(BASE_URL + "/"),
                "Должна открыться главная страница с конструктором");

        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по логотипу")
    public void testNavigateFromPersonalAccountViaLogo() {
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

        mainePage.clickPersonalAccountButton();

        personalAccountPage.clickLogo();

        assertTrue(driver.getCurrentUrl().equals(BASE_URL) || mainePage.invisibilityLoginButton(),
                "Должна открыться главная страница с конструктором");

        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
    }
}