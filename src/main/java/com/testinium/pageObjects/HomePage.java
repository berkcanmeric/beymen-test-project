package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By searchInputLocator = By.id("search-input");
    private final By searchButtonLocator = By.id("search-button");
    private final By cartButtonLocator = By.id("cart-button");
    private final By userAccountLocator = By.id("user-account");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage enterSearchTerm(String searchTerm) {
        driver.findElement(searchInputLocator).sendKeys(searchTerm);
        return this;
    }

    public CartPage clickCartButton() {
        driver.findElement(cartButtonLocator).click();
        return new CartPage(driver);
    }

    public boolean isUserAccountDisplayed() {
        return driver.findElement(userAccountLocator).isDisplayed();
    }
}
