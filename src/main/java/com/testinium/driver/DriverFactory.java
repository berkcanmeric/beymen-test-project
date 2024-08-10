package com.testinium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static final String CHROME_DRIVER_PATH = "src/main/java/com/testinium/driver/drivers/chromedriver";
    private static final String FIREFOX_DRIVER_PATH = "src/main/java/com/testinium/driver/drivers/geckodriver";

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (webDriver.get() == null)
            webDriver.set(createDriver());
        return webDriver.get();
    }

    public static WebDriver createDriver() {
        WebDriver driver = webDriver.get();
        String browserType = "chrome";
        if (driver == null) {
            switch (browserType.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                    ChromeOptions chromeOptions = getChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser type: " + browserType);
            }
            webDriver.set(driver);
        }
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--ignore-certifcate-errors");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-plugins");
        chromeOptions.addArguments("--disable-plugins-discovery");
        chromeOptions.addArguments("--disable-preconnect");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("'--dns-prefetch-disable'");
        chromeOptions.addArguments("'--disable-cookies'");
        chromeOptions.addArguments("test-type");
        chromeOptions.setAcceptInsecureCerts(true);
        return chromeOptions;
    }

    public static void cleanUpDriver() {
        webDriver.get().quit();
        webDriver.remove();
    }
}