import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckLogsProductAdminTest {

    private WebDriver driver;

    @BeforeEach
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void CheckLogsProductAdmin() {
        authAdmin();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        int productsCount = driver.findElements(By.cssSelector("a[href^='http://localhost/litecart/admin/?app=catalog&doc=edit_product&category_id=1&product_id=']")).size();
        for (int i = 0; i < productsCount; i=i+2) {
            List<WebElement> products = driver.findElements(By.cssSelector("a[href^='http://localhost/litecart/admin/?app=catalog&doc=edit_product&category_id=1&product_id=']"));
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            products.get(i).click();
            assertTrue(logs.isEmpty());
            driver.findElement(By.name("cancel")).click();
        }
    }

    @AfterEach
    public void stop(){
        driver.quit();
        driver = null;
    }

    public void authAdmin() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
