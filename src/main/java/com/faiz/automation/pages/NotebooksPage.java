package com.faiz.automation.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotebooksPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(linkText = "14.1-inch Laptop")
    private WebElement notebookProduct;

    @FindBy(xpath = "//a[text()='14.1-inch Laptop']/ancestor::div[contains(@class,'product-item')]//input[@value='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(css = ".bar-notification.success")
    private WebElement successNotification;

    @FindBy(css = ".bar-notification.success .close")
    private WebElement notificationClose;

    @FindBy(css = ".bar-notification.success a[href='/cart']")
    private WebElement notificationCartLink;

    @FindBy(css = "a.ico-cart")
    private WebElement shoppingCartLink;
    
    
    
    
    public NotebooksPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public NotebooksPage open() {

        driver.get("https://demowebshop.tricentis.com/notebooks");
        wait.until(ExpectedConditions.visibilityOf(notebookProduct));
        return this;
    }

    public void addNotebookToCart() {

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton))
                .click();

        wait.until(ExpectedConditions.visibilityOf(successNotification));
    }

    public ShoppingCartPage openShoppingCart() {

        if (isDisplayed(notificationCartLink)) {

            wait.until(ExpectedConditions.elementToBeClickable(notificationCartLink))
                    .click();

            return new ShoppingCartPage(driver);
        }

        closeSuccessNotificationIfDisplayed();

        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink))
                .click();

        return new ShoppingCartPage(driver);
    }

    private void closeSuccessNotificationIfDisplayed() {

        if (isDisplayed(notificationClose)) {

            wait.until(ExpectedConditions.elementToBeClickable(notificationClose))
                    .click();

            wait.until(ExpectedConditions.invisibilityOf(successNotification));
        }
    }

    private boolean isDisplayed(WebElement element) {

        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}