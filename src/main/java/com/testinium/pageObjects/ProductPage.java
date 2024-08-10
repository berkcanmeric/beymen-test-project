package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage extends BasePage {
    private static final String ERROR_INVALID_SIZE = "Error: Invalid size. Please choose from 'XS', 'S', 'M', 'L', or 'XL'.";
    private static final String[] VALID_SIZES = {"XS", "S", "M", "L", "XL"};

    private final By addBasket = By.id("addBasket");
    private final By productSize = By.className("m-variation__item");
    private final By goBasket = By.xpath("(//*[@class='o-header__userInfo--text'])[3]");
    private final By productPrice = By.className("m-productPrice__salePrice");
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

        return driver.findElements(productSize).stream()
                .filter(e -> e.getText().equalsIgnoreCase(size))
                .findFirst()
                .orElse(null);
    }

    private boolean isValidSize(String size) {
        for (String validSize : VALID_SIZES) {
            if (validSize.equalsIgnoreCase(size)) {
                return true;
            }
        }
        return false;
    }

    public By getAddBasket() {
        return addBasket;
    }

    public By getChooseSizeLabel() {
        return chooseSizeLabel;
    }
}
