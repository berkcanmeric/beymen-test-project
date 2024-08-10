package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SearchPage extends BasePage {
    private static final String PRODUCT_DETAILS_FILE = "product_details.txt";

    private final By productTitle = By.className("o-productCard__content--name");
    private final By productDetail = By.className("o-productCard__content--desc");
    private final By productPrice = By.className("m-productCard__newPrice");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetails getProductDetails(int index) throws InterruptedException {
        List<WebElement> productTitles = driver.findElements(productTitle);
        List<WebElement> productDetails = driver.findElements(productDetail);
        List<WebElement> productPrices = driver.findElements(productPrice);
        if (productDetails.isEmpty()) {
            throw new IllegalStateException("No products found on the page.");
        }

        if (index < 0 || index >= productDetails.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + productDetails.size());
        }

        WebElement productTitle = productTitles.get(index);
        WebElement productDetail = productDetails.get(index);
        WebElement productPrice = productPrices.get(index);

        String title = productTitle.getText();
        String detail = productDetail.getText();
        String price = productPrice.getText();
        productDetail.click();
        return new ProductDetails(title, detail, price);
    }

    public void saveProductDetails(ProductDetails productDetails) {
        File file = new File(PRODUCT_DETAILS_FILE);
        if (file.exists()) {
            file.delete();
        }

        try (FileWriter myWriter = new FileWriter(PRODUCT_DETAILS_FILE, true)) {
            myWriter.write(productDetails.title() + "\n");
            myWriter.write(productDetails.detail() + "\n");
            myWriter.write(productDetails.price() + "\n");
            System.out.println("Successfully wrote product details to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing product details to the file.");
            e.printStackTrace();
        }
    }

    public record ProductDetails(String title, String detail, String price) {
    }
}