package Tests;

import static com.testinium.driver.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Base.Hooks;
import com.testinium.pageObjects.CartPage;
import com.testinium.pageObjects.HomePage;
import com.testinium.pageObjects.ProductPage;
import com.testinium.pageObjects.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

public class WebAutomationTest extends Hooks {
    private static final Logger logger = LogManager.getLogger(WebAutomationTest.class);

    private static final String EXPECTED_URL = "https://www.beymen.com/tr";
    private static final String URL_ERROR_MESSAGE = "The opened page is not beymen.com\"";
    private static final String PRICE_NOT_EQUAL_ERROR_MESSAGE = "The prices are not equal.";
    private static final String SEARCH_TERM_SHORT = "şort";
    private static final String SEARCH_TERM_JACKET = "gömlek";
    private static final String ITEM_QUANTITY = "2";
    private static final String CART_UPDATE_MESSAGE = "Sepetiniz Güncellenmiştir";
    private static final String CART_ITEM_REMOVED_MESSAGE = "Ürün Silindi";
    private static final String CART_UPDATE_ERROR_MESSAGE = "Cart update failed.";
    private static final int SHORT_WAIT = 2000;
    private static final int LONG_WAIT = 5000;

    @Test
    public void Test() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(getDriver());
        SearchPage searchPage = new SearchPage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        homePage.navigate();

        String actualUrl = homePage.getUrl();

        assertEquals(URL_ERROR_MESSAGE, EXPECTED_URL, actualUrl);

        homePage.acceptCookies();
        homePage.selectGenderMan();
        Thread.sleep(SHORT_WAIT);

        homePage.search(SEARCH_TERM_SHORT);
        homePage.deleteSuggestion();
        Thread.sleep(LONG_WAIT);

        homePage.searchSuggestion(SEARCH_TERM_JACKET + Keys.ENTER);
        Thread.sleep(LONG_WAIT);

        searchPage.saveProductDetails(searchPage.getProductDetails(1));
        Assert.assertTrue(getDriver().findElement(productPage.getChooseSizeLabel()).isDisplayed());
        Assert.assertTrue(getDriver().findElement(productPage.getAddBasket()).isDisplayed());
        Thread.sleep(SHORT_WAIT);

        productPage.addToBasket();
        Thread.sleep(SHORT_WAIT);
        Assert.assertTrue(getDriver().findElement(productPage.getChooseSizeLabel()).getAttribute("class").contains("hasError"));

        productPage.chooseSize(productPage.SIZE_XL);
        Assert.assertTrue(productPage.getSizeElement(productPage.SIZE_XL).getAttribute("class").contains("-active"));
        Thread.sleep(SHORT_WAIT);

        productPage.addToBasket();
        Thread.sleep(SHORT_WAIT);

        int productPrice = productPage.getPrice();
        productPage.goToCart();
        Thread.sleep(LONG_WAIT);

        Assert.assertEquals(PRICE_NOT_EQUAL_ERROR_MESSAGE, productPrice, cartPage.getPrice());

        cartPage.selectQuantity(ITEM_QUANTITY);
        Thread.sleep(SHORT_WAIT);

        Assert.assertEquals(CART_UPDATE_ERROR_MESSAGE, cartPage.getCartUpdateMessageText(), CART_UPDATE_MESSAGE);
        Assert.assertEquals(PRICE_NOT_EQUAL_ERROR_MESSAGE, productPrice * 2, cartPage.getPrice());

        cartPage.removeItemFromCart();
        Thread.sleep(SHORT_WAIT);

        Assert.assertEquals(CART_UPDATE_ERROR_MESSAGE, cartPage.getCartUpdateMessageText(), CART_ITEM_REMOVED_MESSAGE);
    }

}