package PageObjectForBasketTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BasketPage extends Page {

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void deleteAllProducts() {
        int productsInBasketCount = driver.findElement(By.className("shortcuts")).findElements(By.tagName("li")).size();
        for (int i = 0; i < productsInBasketCount; i++) {
            deleteProduct();
        }
    }

    public void deleteProduct() {
        int tableSizeBefore = driver.findElements(By.cssSelector(".dataTable tr")).size();
        wait.until(elementToBeClickable(driver.findElement(By.name("remove_cart_item")))).click();
        if (tableSizeBefore > 6) {
            wait.until(numberOfElementsToBe(By.cssSelector(".dataTable tr"),tableSizeBefore-1));
        } else wait.until(presenceOfElementLocated(By.xpath("//*[.='There are no items in your cart.']")));
    }
}
