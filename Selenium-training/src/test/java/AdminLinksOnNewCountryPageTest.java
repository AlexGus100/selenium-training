import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Set;


public class AdminLinksOnNewCountryPageTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void checkLinksOnNewCountryPage() {
        authAdmin();
        openCountriesPage();
        driver.findElement(By.xpath("//*[.=' Add New Country']")).click();

        int externalLinksSize = driver.findElements(By.className("fa-external-link")).size();
        String countriesWindow = driver.getWindowHandle();
        Set<String> existingWindows = driver.getWindowHandles();
        for (int i=0; i<externalLinksSize; i++) {
            List<WebElement> externalLinks = driver.findElements(By.className("fa-external-link"));
            externalLinks.get(i).click();
            String newWindow = wait.until(newWindowOpened(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(countriesWindow);
        }
    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

    public void authAdmin() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public void openCountriesPage() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    }

    public ExpectedCondition<String> newWindowOpened (Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply (WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return !handles.isEmpty() ? handles.iterator().next() : null;
            }
        };
    }
}
