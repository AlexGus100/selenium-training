package PageObjectForBasketTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/");
    }

    public List<WebElement> getAllProducts() {
        return  driver.findElements(By.className("product"));
    }

    public boolean checkQuantity(Integer expectedQuantity) {
        return wait.until(textToBePresentInElement(driver.findElement(By.className("quantity")),expectedQuantity.toString()));
    }

    public void goToBasket() {
        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/checkout'].link")).click();
    }
}
