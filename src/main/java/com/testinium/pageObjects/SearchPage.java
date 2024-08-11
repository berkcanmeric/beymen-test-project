package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {
    private static final String NO_PRODUCTS_ERROR = "No products found on the page.";
    private static final String INDEX_OUT_OF_BOUNDS_ERROR = "Index %d out of bounds for length %d";

    private final By productDetail = By.className("o-productCard__content--desc");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void goProductPage(int index) throws InterruptedException {
        List<WebElement> productDetails = driver.findElements(productDetail);
        if (productDetails.isEmpty()) {
            throw new IllegalStateException(NO_PRODUCTS_ERROR);
        }

        if (index < 0 || index >= productDetails.size()) {
            throw new IndexOutOfBoundsException(String.format(INDEX_OUT_OF_BOUNDS_ERROR, index, productDetails.size()));
        }

        WebElement productDetail = productDetails.get(index);

        String detail = productDetail.getText();
        click(this.productDetail);
    }

}