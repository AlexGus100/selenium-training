import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminPanelSectionsTest {

    private WebDriver driver;

    @BeforeEach
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void CheckAdminPanelSectionsTest() {
        authAdmin();

        List<WebElement> sections = driver.findElement(By.id("box-apps-menu")).findElements(By.cssSelector("[id^=app-]"));
        System.out.println(sections.size());
        for (int i = 0; i < sections.size(); i++) {
            sections.get(i).click();
            assertTrue(isHeaderExist());
            if (areSubSectionsExist()) {
                checkSubSections();
            }
            sections = driver.findElements(By.cssSelector("[id^=app-]"));
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

    public boolean isHeaderExist() {
        return driver.findElement(By.id("content")).findElements((By.tagName("h1"))).size() > 0;
    }

    public boolean areSubSectionsExist() {
        return driver.findElement(By.id("box-apps-menu")).findElements(By.cssSelector("[id^=doc-]")).size() > 0;
    }

    public void checkSubSections() {
        List<WebElement> subSections = driver.findElement(By.id("box-apps-menu")).findElements(By.cssSelector("[id^=doc-]"));
        for (int i = 0; i < subSections.size(); i++) {
            subSections.get(i).click();
            assertTrue(isHeaderExist());
            subSections = driver.findElements(By.cssSelector("[id^=doc-]"));
        }
    }
}
