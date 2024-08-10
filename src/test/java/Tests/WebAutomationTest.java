package Tests;

import static com.testinium.driver.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import Base.Hooks;
import com.testinium.pageObjects.HomePage;
import com.testinium.pageObjects.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebAutomationTest extends Hooks {
    private static final Logger logger = LogManager.getLogger(WebAutomationTest.class);

    @Test
    public void HomePageTest() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(getDriver());
        SearchPage searchPage = new SearchPage(getDriver());
        homePage.navigate();

        String actualUrl = homePage.getUrl();
        String expectedUrl = "https://www.beymen.com/tr";

        assertEquals("The opened page is not beymen.com", expectedUrl, actualUrl);

        homePage.acceptCookies();
        homePage.selectGenderMan();

        homePage.search("şort");
        homePage.delete();

        homePage.search("gömlek" + Keys.ENTER);
//        searchPage.findProduct(2);
//        Assert.assertTrue(getDriver().findElement(searchPage.addbasket).isDisplayed());

    }
}