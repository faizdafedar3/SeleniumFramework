package com.faiz.automation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private final WebDriverWait wait;

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    @FindBy(css = ".cart .product-name")
    private List<WebElement> productNames;

    @FindBy(name = "removefromcart")
    private List<WebElement> removeCheckboxes;

    @FindBy(name = "updatecart")
    private WebElement updateCartButton;

    @FindBy(css = ".order-summary-content")
    private WebElement orderSummaryContent;

    public CartPage(WebDriver driver) {

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

    public void removeAllProducts() {

        for (WebElement removeCheckbox : removeCheckboxes) {
            if (!removeCheckbox.isSelected()) {
                removeCheckbox.click();
            }
        }

        wait.until(ExpectedConditions.elementToBeClickable(updateCartButton)).click();
    }

    public String getCartMessage() {

        return wait.until(ExpectedConditions.visibilityOf(orderSummaryContent))
                .getText()
                .trim();
    }
}
