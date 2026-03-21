package utils;


import api.UserApi;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.*;

public class BaseTest  {
    protected  WebDriver driver;
    protected MainePage mainePage;
    protected  LoginPage loginPage;
    protected  UserApi userApi;
    protected  RegisterPage registerPage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected PersonalAccountPage personalAccountPage;



    @BeforeEach
    public void setUp() {
        String browserName = System.getProperty("browser", "chrome");
        driver = BrowserFactory.getDriver(browserName);

        driver.manage().window().maximize();
        mainePage = new MainePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);

    }
    @AfterEach
    public void tearDown() {
        // Удаляем пользователя, если он был создан
        if (userApi != null) {
            userApi.deleteUser();
        }

        // Закрываем браузер
        if (driver != null) {
            driver.quit();
        }
    }

    protected Faker faker = new Faker();

    //Генерирует валидный пароль (от 6 до 10 символов)
    public String generateValidPassword() {
        return faker.internet().password(6, 10, true, true, true);
    }
    //Генерирует невалидный пароль (от 1 до 5 символов)
    public String generateInvalidPassword() {
        return faker.internet().password(1, 5);
    }
    // Генерирует случайный email
    public String generateRandomEmail() {
        return faker.internet().emailAddress();
    }
    public String generateRandomName() {
        return faker.name().firstName();
    }
}
