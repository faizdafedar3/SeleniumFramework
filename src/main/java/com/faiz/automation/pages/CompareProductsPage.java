package com.faiz.automation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareProductsPage {

    private final WebDriverWait wait;

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    @FindBy(css = ".compare-products-table .product-name")
    private List<WebElement> productNames;

    @FindBy(css = "a.clear-list")
    private WebElement clearListLink;

    @FindBy(css = ".page-body")
    private WebElement pageBody;

    public CompareProductsPage(WebDriver driver) {

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {

        return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                .getText()
                .trim();
    }

    public boolean containsProduct(String expectedProductName) {

        return productNames.stream()
                .anyMatch(product -> product.getText().trim().equals(expectedProductName));
    }

    public void clearCompareList() {

        wait.until(ExpectedConditions.elementToBeClickable(clearListLink)).click();
    }

    public String getPageBodyText() {

        return wait.until(ExpectedConditions.visibilityOf(pageBody))
                .getText()
                .trim();
    }
}
