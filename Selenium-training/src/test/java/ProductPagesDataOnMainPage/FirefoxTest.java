package ProductPagesDataOnMainPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirefoxTest extends TestBase{

    private WebDriver driver;
    @BeforeEach
    public void start(){
        driver = new FirefoxDriver();
    }

    @Test
    public void checkProductNamesTest() {
        openMainPage();
        List<WebElement> products = driver.findElements(By.className("product"));
        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);
            String productNameOnMainPage = product.findElement(By.className("name")).getText();
            product.click();
            String productNameOnProductPage = driver.findElement((By.tagName("h1"))).getText();
            assertEquals(productNameOnMainPage, productNameOnProductPage);
            openMainPage();
            products = driver.findElements(By.className("product"));
        }
    }

    @Test
    public void checkProductPricesTest() {
        openMainPage();
        List<WebElement> products = driver.findElements(By.className("product"));
        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);
            boolean isDiscount = product.findElements(By.className("campaign-price")).size()==1;
            if (isDiscount) {
                String productRegularPriceOnMainPage = product.findElement(By.className("regular-price")).getText();
                String productCampaignPriceOnMainPage = product.findElement(By.className("campaign-price")).getText();
                product.click();
                String productRegularPriceOnProductPage = driver.findElement(By.className("regular-price")).getText();
                String productCampaignPriceOnProductPage = driver.findElement(By.className("campaign-price")).getText();
                assertEquals(productRegularPriceOnMainPage, productRegularPriceOnProductPage);
                assertEquals(productCampaignPriceOnMainPage, productCampaignPriceOnProductPage);
            } else {
                String productPriceOnMainPage = product.findElement(By.className("price")).getText();
                product.click();
                String productPriceOnProductPage = driver.findElement(By.className("price")).getText();
                assertEquals(productPriceOnMainPage, productPriceOnProductPage);
            }
            openMainPage();
            products = driver.findElements(By.className("product"));
        }
    }

    @Test
    public void checkProductPricesStylesTest() {
        openMainPage();
        List<WebElement> campaignPricesOnMainPage = driver.findElements(By.className("campaign-price"));
        boolean isDiscount = !campaignPricesOnMainPage.isEmpty();
        if (isDiscount) {
            for (int i = 0; i < campaignPricesOnMainPage.size(); i++) {
                WebElement campaignPriceOnMainPage = campaignPricesOnMainPage.get(0);
                WebElement regularPriceOnMainPage = campaignPricesOnMainPage.get(i).findElement(By.xpath("//../preceding-sibling::s"));
                assertTrue(isTextCrossed(regularPriceOnMainPage));
                assertTrue(isTextGrey(regularPriceOnMainPage));
                assertTrue(isTextBoldOnMainPage(campaignPriceOnMainPage));
                assertTrue(isTextRed(campaignPriceOnMainPage));
                assertTrue(isRightBiggerThanLeft(regularPriceOnMainPage, campaignPriceOnMainPage));

                regularPriceOnMainPage.click();

                WebElement regularPriceOnProductPage = driver.findElement(By.className("regular-price"));
                WebElement campaignPriceOnProductPage = driver.findElement(By.className("campaign-price"));
                assertTrue(isTextCrossed(regularPriceOnProductPage));
                assertTrue(isTextGrey(regularPriceOnProductPage));
                assertTrue(isTextBoldOnProductPage(campaignPriceOnProductPage));
                assertTrue(isTextRed(campaignPriceOnProductPage));
                assertTrue(isRightBiggerThanLeft(regularPriceOnProductPage, campaignPriceOnProductPage));

                openMainPage();
                campaignPricesOnMainPage = driver.findElements(By.className("campaign-price"));
            }
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

    @Override
    public boolean isTextRed(WebElement element) {
        char g = element.getCssValue("color").charAt(9);
        char b = element.getCssValue("color").charAt(12);
        return g=='0' && b=='0';
    }

    @Override
    public boolean isTextBoldOnMainPage(WebElement element) {
        String fontWeight = element.getCssValue("font-weight");
        return fontWeight.equals("900");
    }
}
