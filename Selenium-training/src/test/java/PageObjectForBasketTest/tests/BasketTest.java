package PageObjectForBasketTest.tests;

import PageObjectForBasketTest.pages.BasketPage;
import PageObjectForBasketTest.pages.MainPage;
import PageObjectForBasketTest.pages.ProductPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketTest extends TestBase {

    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;

    @Test
    public void basketOnMainPageTest() {
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);

        for (int i = 0; i < 3; i++) {
            mainPage.open();
            mainPage.getAllProducts().get(i).click();
            productPage.addProductToBasket();
            assertTrue(mainPage.checkQuantity(i+1));
            mainPage.open();
        }

        mainPage.goToBasket();

        basketPage.deleteAllProducts();
    }
}
