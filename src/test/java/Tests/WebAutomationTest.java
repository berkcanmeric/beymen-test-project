package Tests;

import static com.testinium.driver.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import Base.Hooks;
import com.testinium.pageObjects.HomePage;
import com.testinium.pageObjects.ProductPage;
import com.testinium.pageObjects.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebAutomationTest extends Hooks {
    private static final Logger logger = LogManager.getLogger(WebAutomationTest.class);

    @Test
    public void Test1() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(getDriver());
        SearchPage searchPage = new SearchPage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        homePage.navigate();

        String actualUrl = homePage.getUrl();
        String expectedUrl = "https://www.beymen.com/tr";

        assertEquals("The opened page is not beymen.com", expectedUrl, actualUrl);

        homePage.acceptCookies();
        homePage.selectGenderMan();
        Thread.sleep(2000);

        homePage.search("şort");
        homePage.deleteSuggestion();
        Thread.sleep(5000);

        homePage.searchSuggestion("gömlek" + Keys.ENTER);
        Thread.sleep(5000);

        searchPage.saveProductDetails(searchPage.getProductDetails(3));
        Assert.assertTrue(getDriver().findElement(productPage.addBasket).isDisplayed());
        Thread.sleep(2000);

    }

    @Test
    public void Test2() throws InterruptedException {

    }

    @Test
    public void Test3() throws InterruptedException {

    }
}