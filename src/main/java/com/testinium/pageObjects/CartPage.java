package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CartPage extends BasePage {

    private final By productPrice = By.cssSelector(".priceBox__salePrice");
    private final By quantitySelect = By.cssSelector(".-small.a-selectControl");
    private final By cartUpdateMessage = By.cssSelector("h4#notifyTitle");
    private final By cartRemoveButton = By.cssSelector(".m-basket__remove");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getPrice() {
        waitForElementToBeVisible(productPrice);
        String priceText = driver.findElement(productPrice).getText().replace("TL", "").trim();
        priceText = priceText.replace(".", "");
        priceText = priceText.replace(",00", "");
        priceText = priceText.replace(",0", "");
        return Integer.parseInt(priceText);
    }

    public void selectQuantity(String quantity) {
        WebElement selectElement = driver.findElement(quantitySelect);
        Select select = new Select(selectElement);
        select.selectByValue(quantity);
    }

    public String getCartUpdateMessageText(){
        waitForElementToBeVisible(cartUpdateMessage);
        return driver.findElement(cartUpdateMessage).getText();
    }

    public void removeItemFromCart(){
        click(cartRemoveButton);
    }
}
