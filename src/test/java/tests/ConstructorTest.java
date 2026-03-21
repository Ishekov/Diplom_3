package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты конструктора")
public class ConstructorTest extends BaseTest {
    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testNavigateToBuns() {
        mainePage.open();
        mainePage.clickSaucesSection();
        mainePage.waitForActiveSection("Соусы");

        mainePage.clickBunsSection();
        mainePage.waitForActiveSection("Булки");

        String activeSection = mainePage.getActiveSection();
        assertEquals("Булки", activeSection, "Активным должен быть раздел 'Булки'");
    }
    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testNavigateToSauces() {
        mainePage.open();
        mainePage.clickSaucesSection();
        mainePage.waitForActiveSection("Соусы");

        String activeSection = mainePage.getActiveSection();
        assertEquals("Соусы", activeSection, "Активным должен быть раздел 'Соусы'");
    }
    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testNavigateToFillings() {
        mainePage.open();
        mainePage.clickFillingsSection();
        mainePage.waitForActiveSection("Начинки");

        String activeSection = mainePage.getActiveSection();
        assertEquals("Начинки", activeSection, "Активным должен быть раздел 'Начинки'");
    }
}
