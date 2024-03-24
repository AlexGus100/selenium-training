import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthAdminTest {

    private WebDriver driver;

    @BeforeEach
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void authAdminScenario() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
    }

    @AfterEach
    public void stop(){
        driver.quit();
        driver = null;
    }
}
