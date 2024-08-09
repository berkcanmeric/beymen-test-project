package com.testinium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final String CHROME_DRIVER_PATH = "drivers/chromedriver.exe";
    private static final String FIREFOX_DRIVER_PATH = "drivers/geckodriver.exe";

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver(String browserType) {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            switch (browserType.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser type: " + browserType);
            }
            driverThreadLocal.set(driver);
        }
        return driver;
    }

    private WebDriver initializeDriver() {
        // Implement the logic to initialize the WebDriver instance
        // This method should return the initialized WebDriver object
        String browserType = System.getProperty("browser", "chrome");
        WebDriver driver = getDriver(browserType);
        return driver;
    }

    private void teardown() {
        // Implement the logic to close the WebDriver instance
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}