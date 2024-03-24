import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void myFirstTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10,0));
        driver.get("https://mail.google.com/");
        driver.quit();
        driver = null;
    }
}