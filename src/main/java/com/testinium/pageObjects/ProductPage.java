package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    public final By addBasket = By.xpath("//button[@id='addBasket']");
    public final By productSize = By.className("-criticalStock");
    public final By goBasket = By.xpath("(//*[@class='o-header__userInfo--text'])[3]");
    public final By basketPrice = By.className("m-productPrice__salePrice");

    public ProductPage(WebDriver driver) {
        super(driver);
    }
}
