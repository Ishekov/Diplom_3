package tests;

import api.Authorization;
import api.UserApi;
import api.UserCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты выхода из аккаунта")
public class LogoutTest extends BaseTest {

    @Test
    @DisplayName("Выход по кнопке 'Выйти' в личном кабинете")
    public void testLogout() {
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

        personalAccountPage.clickLogoutButton();

        boolean condition1 = mainePage.isUserLoggedOut();
        boolean condition2 = mainePage.visibilityLoginButton();

        assertTrue(condition1||condition2,
                "После выхода должен открыться экран входа");

        Authorization auth = new Authorization(email, password, name);
        userApi.loginAndSaveToken(auth);
    }
}