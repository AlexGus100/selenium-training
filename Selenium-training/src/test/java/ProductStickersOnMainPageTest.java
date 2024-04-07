import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductStickersOnMainPageTest {

    private WebDriver driver;

    @BeforeEach
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void CheckProductStickersOnMainPageTest() {
        openMainPage();

        List<WebElement> products = driver.findElements(By.className("product"));
        for (int i = 0; i < products.size(); i++) {
            assertTrue(isStickerExistAndSingle(products.get(i)));
        }
    }

    @AfterEach
    public void stop(){
        driver.quit();
        driver = null;
    }

    public void openMainPage() {
        driver.get("http://localhost/litecart/");
    }

    public boolean isStickerExistAndSingle(WebElement product) {
        return product.findElements((By.className("sticker"))).size() == 1;
    }
}
