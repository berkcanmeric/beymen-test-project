package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class SearchPage extends BasePage {

    public final By chooseProduct = By.className("m-productCard__desc");
    public final By addbasket = By.xpath("//button[@id='addBasket']");
    public final By productdetail = By.className("o-productDetail__description");
    public final By productprice = By.className("m-price__new");
    public final By productsize = By.className("-criticalStock");
    public final By gobasket = By.xpath("(//*[@class='o-header__userInfo--text'])[3]");
    public final By basketPrice = By.className("m-productPrice__salePrice");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void findProduct(int index) throws InterruptedException {
        List<WebElement> products = driver.findElements(chooseProduct);

        if (products.isEmpty()) {
            throw new IllegalStateException("No products found on the page.");
        }

        if (index < 0 || index >= products.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + products.size());
        }

        WebElement element = products.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        Thread.sleep(500);
    }


    public void createTxtFile() throws InterruptedException {

        String productInfo= driver.findElement(productdetail).getText();
        String productPrice =  driver.findElement(productprice).getText();

        try {
            FileWriter myWriter = new FileWriter("variables.txt");
            myWriter.write(productInfo);
            myWriter.write(" ");
            myWriter.write(productPrice);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

}
