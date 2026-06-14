package com.faiz.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class CompareProductsPage extends BasePage {

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    @FindBy(css = ".compare-products-table .product-name")
    private List<WebElement> productNames;

    @FindBy(css = "a.clear-list")
    private WebElement clearListLink;

    @FindBy(css = ".page-body")
    private WebElement pageBody;

    public CompareProductsPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {

        return getText(pageTitle)
                .trim();
    }

    public boolean containsProduct(
            String expectedProductName) {

        return productNames.stream()
                .anyMatch(product ->
                        product.getText()
                               .trim()
                               .equals(expectedProductName));
    }

    public void clearCompareList() {

        click(clearListLink);
    }

    public String getPageBodyText() {

        return getText(pageBody)
                .trim();
    }
}