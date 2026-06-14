package com.faiz.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class CartPage extends BasePage {

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

    public void removeAllProducts() {

        for (WebElement removeCheckbox : removeCheckboxes) {

            if (!removeCheckbox.isSelected()) {

                click(removeCheckbox);
            }
        }

        click(updateCartButton);
    }

    public String getCartMessage() {

        return getText(orderSummaryContent)
                .trim();
    }
}