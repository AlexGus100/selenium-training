package PageObjectForBasketTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addProductToBasket() {
        if (isOptionPresent()) {
            Select option = new Select(driver.findElement(By.name("options[Size]")));
            option.selectByValue("Small");
        }
        driver.findElement(By.name("add_cart_product")).click();
    }

    private boolean isOptionPresent() {
        return !driver.findElements(By.name("options[Size]")).isEmpty();
    }

}
