import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;

public class AddProductAdminTest {

    private WebDriver driver;

    final String testFilePath = Path.of("src/test/files/testFile.jpg").toAbsolutePath().toString();

    @BeforeEach
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void addProductAdmin() {
        authAdmin();
        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog']")).click();
        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product']")).click();

        String productNameEn = nameEnGenerator();
        String productCode = codeGenerator();

        // Tab General
        driver.findElement(By.xpath("//*[.=' Enabled']")).click();
        driver.findElement(By.name("name[en]")).sendKeys(productNameEn);
        driver.findElement(By.name("code")).sendKeys(productCode);
        driver.findElement(By.xpath("//*[.='Unisex']/preceding-sibling::td")).click();
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("5");
        driver.findElement(By.name("new_images[]")).sendKeys(testFilePath);
        driver.findElement(By.name("date_valid_from")).sendKeys("0101"+ Keys.ARROW_RIGHT + "2024");
        driver.findElement(By.name("date_valid_to")).sendKeys("3112"+ Keys.ARROW_RIGHT + "2025");

        // Tab Information
        driver.findElement(By.cssSelector("a[href='#tab-information")).click();
        waitBetweenTabs();
        Select manufacturerId = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturerId.selectByValue("1");
        driver.findElement(By.name("keywords")).sendKeys("Keywords for" + productNameEn);
        driver.findElement(By.name("short_description[en]")).sendKeys("Short desc for" + productNameEn);
        driver.findElement(By.xpath("//*[@name='description[en]']/preceding-sibling::*[@class='trumbowyg-editor']")).sendKeys("Long desc for" + productNameEn);
        driver.findElement(By.name("head_title[en]")).sendKeys("Head title for" + productNameEn);
        driver.findElement(By.name("meta_description[en]")).sendKeys("Meta desc for" + productNameEn);

        // Tab Prices
        driver.findElement(By.cssSelector("a[href='#tab-prices")).click();
        waitBetweenTabs();
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("8.50");
        Select purchasePriceCurrencyCode = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        purchasePriceCurrencyCode.selectByValue("USD");
        driver.findElement(By.name("prices[USD]")).sendKeys("10.75");

        driver.findElement(By.name("save")).click();
        driver.findElement(By.xpath("//*[.='"+ productNameEn + "']")).isDisplayed();
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

    public String nameEnGenerator() {
        return RandomStringUtils.randomAlphabetic(10).toLowerCase();
    }

    public String codeGenerator() {
        return RandomStringUtils.randomNumeric(5);
    }

    public void waitBetweenTabs() {
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
