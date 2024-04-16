import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BasketOnMainPageTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void basketOnMainPageTest() {
        openMainPage();

        for (int i = 0; i < 3; i++) {
            List<WebElement> products = driver.findElements(By.className("product"));
            products.get(i).click();
            if (isOptionPresent()) {
                Select option = new Select(driver.findElement(By.name("options[Size]")));
                option.selectByValue("Small");
            }
            driver.findElement(By.name("add_cart_product")).click();
            assertTrue(checkQuantityOnMainPage(i+1));
            openMainPage();
        }

        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/checkout'].link")).click();

        for (int i = 0; i < 3; i++) {
            deleteFromBasket();
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

    public boolean isOptionPresent() {
        return !driver.findElements(By.name("options[Size]")).isEmpty();
    }

    public boolean checkQuantityOnMainPage(Integer expectedQuantity) {
        return wait.until(textToBePresentInElement(driver.findElement(By.className("quantity")),expectedQuantity.toString()));
    }

    public void deleteFromBasket() {
        int tableSizeBefore = driver.findElements(By.cssSelector(".dataTable tr")).size();
        wait.until(elementToBeClickable(driver.findElement(By.name("remove_cart_item")))).click();
        if (tableSizeBefore > 6) {
            wait.until(numberOfElementsToBe(By.cssSelector(".dataTable tr"),tableSizeBefore-1));
        } else wait.until(presenceOfElementLocated(By.xpath("//*[.='There are no items in your cart.']")));
    }
}
