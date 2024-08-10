package Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static com.testinium.driver.DriverFactory.*;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        logger.info("Setting up the test environment");
        getDriver();
    }

    @After
    public void tearDown() {
        logger.info("Tearing down the test environment");
        cleanUpDriver();
    }
}