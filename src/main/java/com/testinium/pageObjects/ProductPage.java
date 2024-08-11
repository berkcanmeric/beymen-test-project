package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProductPage extends BasePage {
    private static final String ERROR_INVALID_SIZE = "Error: Invalid size. Please choose from 'XS', 'S', 'M', 'L', or 'XL'.";
    private static final String[] VALID_SIZES = {"XS", "S", "M", "L", "XL"};

    private static final String PRODUCT_FILE = "product_details.txt";
    private static final String PRODUCT_WRITE_SUCCESS = "Successfully wrote product details to the file.";
    private static final String PRODUCT_WRITE_FAILURE = "An error occurred while writing product details to the file.";
    private static final String PRODUCT_SIZE_DISABLED_ERROR_MESSAGE = "The selected size is disabled.";
    private static final String PRODUCT_SIZE_NOT_FOUND_ERROR_MESSAGE = "Size not found.";

    private final By addBasket = By.id("addBasket");

    private final By productBrand = By.className("o-productDetail__brandLink");
    private final By productDescription = By.className("o-productDetail__description");
    private final By productColor = By.cssSelector(".m-colorsSlider__top .m-variation__label");
    private final By productPrice = By.className("m-price__new");
    private final By productSize = By.className("m-variation__item");
    private final By chooseSizeLabel = By.cssSelector("#sizes .m-variation__label");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToBasket() {
        click(addBasket);
    }

    public void chooseSize(String size) {
        WebElement sizeElement = getSizeElement(size);
        if (sizeElement != null) {
            sizeElement.click();
        }
    }

    public WebElement getSizeElement(String size) {
        if (!isValidSize(size)) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
       WebElement sizeElement= driver.findElements(productSize).stream()
                .filter(e -> e.getText().equalsIgnoreCase(size))
                .findFirst()
                .orElse(null);

        if (sizeElement != null) {
            String classAttribute = sizeElement.getAttribute("class");

            if (classAttribute != null && classAttribute.contains("disabled")) {
                throw new IllegalStateException(PRODUCT_SIZE_DISABLED_ERROR_MESSAGE);
            }

            return sizeElement;
        } else {
            throw new NoSuchElementException(PRODUCT_SIZE_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    private boolean isValidSize(String size) {
        for (String validSize : VALID_SIZES) {
            if (validSize.equalsIgnoreCase(size)) {
                return true;
            }
        }
        return false;
    }

    public int getPrice() {
        waitForElementToBeVisible(productPrice);
        String priceText = driver.findElement(productPrice).getText().replace("TL", "").trim();
        priceText = priceText.replace(",00", "");
        priceText = priceText.replace(",0", "");
        return Integer.parseInt(priceText);
    }

    public Product getProduct() throws InterruptedException {
        String brand = driver.findElement(productBrand).getText();
        String description = driver.findElement(productDescription).getText();
        String color = driver.findElement(productColor).getText();
        String price = driver.findElement(productPrice).getText();

        return new Product(brand, description, color, price);
    }

    public void saveProduct(Product product) {
        File file = new File(PRODUCT_FILE);
        if (file.exists()) {
            file.delete();
        }

        try (FileWriter myWriter = new FileWriter(PRODUCT_FILE, true)) {
            myWriter.write(product.brand() + "\n");
            myWriter.write(product.description() + "\n");
            myWriter.write(product.color() + "\n");
            myWriter.write(product.price() + "\n");
            System.out.println(PRODUCT_WRITE_SUCCESS);
        } catch (IOException e) {
            System.out.println(PRODUCT_WRITE_FAILURE);
            e.printStackTrace();
        }
    }

    public By getAddBasket() {
        return addBasket;
    }

    public By getChooseSizeLabel() {
        return chooseSizeLabel;
    }

    public record Product(String brand, String description, String color, String price) {
    }
}
