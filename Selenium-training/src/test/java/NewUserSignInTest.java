import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class NewUserSignInTest {
    private WebDriver driver;
    @BeforeEach
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void checkNewUserSignInTest() {
        openMainPage();

        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/create_account']")).click();

        driver.findElement(By.name("firstname")).sendKeys("Bill");
        driver.findElement(By.name("lastname")).sendKeys("Smith");
        driver.findElement(By.name("address1")).sendKeys("Wall Street 12/10");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("New York");
        driver.findElement(By.className("select2-selection__arrow")).click();
        driver.findElement(By.cssSelector("[id$=US]")).click();
        Select zoneCode = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        zoneCode.selectByValue("NY");
        String userEmail = emailGenerator();
        driver.findElement(By.name("email")).sendKeys(userEmail);
        driver.findElement(By.name("phone")).sendKeys("+99999999999");
        driver.findElement(By.name("password")).sendKeys(userPassword);
        driver.findElement(By.name("confirmed_password")).sendKeys(userPassword);
        driver.findElement(By.name("create_account")).click();

        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/logout']")).click();
        driver.findElement(By.name("email")).sendKeys(userEmail);
        driver.findElement(By.name("password")).sendKeys(userPassword);
        driver.findElement(By.name("login")).click();
        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/logout']")).click();
    }

    @AfterEach
    public void stop(){
        driver.quit();
        driver = null;
    }

    public void openMainPage() {
        driver.get("http://localhost/litecart/");
    }

    public String emailGenerator() {
        String generatedString = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        return generatedString + "@gmail.com";
    }

    final String userPassword = "user";
}
