package com.testinium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final String url = "http://beymen.com/tr";
    public final By searchBox = By.cssSelector("input.o-header__search--input");
    public final By searchBoxSuggestion = By.cssSelector("input#o-searchSuggestion__input");
    public final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    public final By genderManButton = By.id("genderManButton");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.get(url);
    }

    public void search(String keyword) {
       sendKeys(searchBox, keyword);
    }
    public void searchSuggestion(String keyword) {
        sendKeys(searchBoxSuggestion, keyword);
    }

    public void delete() {
        driver.findElement(searchBox).sendKeys(Keys.chord(Keys.COMMAND,"a", Keys.DELETE));
    }

    public void deleteSuggestion(){
        driver.findElement(searchBoxSuggestion).sendKeys(Keys.chord(Keys.COMMAND,"a", Keys.DELETE));
    }

    public void acceptCookies() {
        click(acceptCookiesButton);
    }

    public void selectGenderMan() {
        click(genderManButton);
    }
}
