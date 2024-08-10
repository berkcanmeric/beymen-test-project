package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    private static final int TIMEOUT = 10;
    private static final int POLLING = 100;

    public final String SIZE_XS = "XS";
    public final String SIZE_S = "S";
    public final String SIZE_M = "M";
    public final String SIZE_L = "L";
    public final String SIZE_XL = "XL";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(POLLING));
    }

    protected void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitForElementToBeClickable(locator);
        driver.findElement(locator).click();
    }

    protected void sendKeys(By locator, String text) {
        waitForElementToBeVisible(locator);
        driver.findElement(locator).sendKeys(text);
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

}