package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class ShoppingCartPage extends BasePage {

    @FindBy(linkText = "14.1-inch Laptop")
    private WebElement productName;

    @FindBy(id = "termsofservice")
    private WebElement termsOfService;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public ShoppingCartPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isNotebookDisplayed() {

        return isDisplayed(productName);
    }

    public CheckoutPage acceptTermsAndCheckout() {

        click(termsOfService);

        click(checkoutButton);

        return new CheckoutPage(driver);
    }
}