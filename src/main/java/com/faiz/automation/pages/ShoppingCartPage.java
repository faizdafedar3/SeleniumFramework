package com.faiz.automation.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(linkText = "14.1-inch Laptop")
    private WebElement productName;

    @FindBy(id = "termsofservice")
    private WebElement termsOfService;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public ShoppingCartPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public boolean isNotebookDisplayed() {

        return wait.until(ExpectedConditions.visibilityOf(productName))
                .isDisplayed();
    }

    public CheckoutPage acceptTermsAndCheckout() {

        wait.until(ExpectedConditions.elementToBeClickable(termsOfService))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton))
                .click();

        return new CheckoutPage(driver);
    }
}