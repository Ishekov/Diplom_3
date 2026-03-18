package tests;

import org.junit.jupiter.api.Test;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexBrowserTest extends BaseTest {

    @Test
    public void testYandexBrowserWorks() {
        // Этот тест откроет главную страницу в Яндекс.Браузере
        mainePage.open();

        // Проверяем, что страница загрузилась
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("stellarburgers"),
                "Страница должна загрузиться успешно");

        // Небольшая пауза, чтобы увидеть результат
        pause(3000);
    }
}