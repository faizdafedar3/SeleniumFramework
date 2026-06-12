package com.faiz.automation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WishlistPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    // Correct locator for wishlist products
    @FindBy(css = "td.product a")
    private List<WebElement> productNames;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                   .getText()
                   .trim();
    }

    public boolean containsProduct(String expectedProductName) {

        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));

        System.out.println("================================================");
        System.out.println("Expected Product : " + expectedProductName);
        System.out.println("Current URL      : " + driver.getCurrentUrl());
        System.out.println("Products Found   : " + productNames.size());
        System.out.println("================================================");

        for (WebElement product : productNames) {
            System.out.println("Actual Product : " + product.getText().trim());
        }

        return productNames.stream()
                .map(product -> product.getText().trim())
                .anyMatch(name ->
                        name.equalsIgnoreCase(expectedProductName.trim()));
    }
}