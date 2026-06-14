package com.faiz.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class SearchResultsPage extends BasePage {

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    @FindBy(css = ".product-title a")
    private List<WebElement> productTitles;

    @FindBy(css = ".page-body")
    private WebElement pageBody;

    public SearchResultsPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {

        return getText(pageTitle)
                .trim();
    }

    public boolean hasSearchResults() {

        return !productTitles.isEmpty();
    }

    public String getNoResultsMessage() {

        return getText(pageBody)
                .trim();
    }
}