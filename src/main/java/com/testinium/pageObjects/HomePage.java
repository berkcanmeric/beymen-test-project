package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final String url = "http://beymen.com/tr";
    private final By searchBox= By.cssSelector("input.o-header__search--input");
    private final By acceptCookiesButton= By.id("onetrust-accept-btn-handler");
    private final By genderManButton= By.id("genderManButton");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigate(){
        driver.get(url);
    }

    public void search(String keyword){
        sendKeys(searchBox, keyword);
    }

    public void delete() throws InterruptedException {
        driver.findElement(searchBox).sendKeys("şort");
        Thread.sleep(2000);
        driver.findElement(searchBox).sendKeys(Keys.COMMAND + "a");
        driver.findElement(searchBox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        driver.findElement(searchBox).sendKeys("gömlek",Keys.ENTER);
        Thread.sleep(2000);
    }

    public void acceptCookies(){
        click(acceptCookiesButton);
    }

    public void selectGenderMan(){
        click(genderManButton);
    }
}
