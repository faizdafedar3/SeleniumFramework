package com.faiz.automation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

    private final WebDriverWait wait;

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    @FindBy(css = ".product-title a")
    private List<WebElement> productTitles;

    @FindBy(css = ".page-body")
    private WebElement pageBody;

    public SearchResultsPage(WebDriver driver) {

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {

        return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                .getText()
                .trim();
    }

    public boolean hasSearchResults() {

        return !productTitles.isEmpty();
    }

    public String getNoResultsMessage() {

        return wait.until(ExpectedConditions.visibilityOf(pageBody))
                .getText()
                .trim();
    }
}
