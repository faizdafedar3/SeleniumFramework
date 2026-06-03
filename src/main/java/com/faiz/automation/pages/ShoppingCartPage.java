package com.faiz.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productName =
            By.linkText("14.1-inch Laptop");

    private final By termsOfService =
            By.id("termsofservice");

    private final By checkoutButton =
            By.id("checkout");

    public ShoppingCartPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isNotebookDisplayed() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName))
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