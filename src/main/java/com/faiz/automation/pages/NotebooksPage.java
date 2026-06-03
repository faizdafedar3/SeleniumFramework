package com.faiz.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotebooksPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By notebookProduct =
            By.linkText("14.1-inch Laptop");

    private final By addToCartButton =
            By.xpath("//a[text()='14.1-inch Laptop']/ancestor::div[contains(@class,'product-item')]//input[@value='Add to cart']");

    private final By successNotification =
            By.cssSelector(".bar-notification.success");

    private final By notificationClose =
            By.cssSelector(".bar-notification.success .close");

    private final By notificationCartLink =
            By.cssSelector(".bar-notification.success a[href='/cart']");

    private final By shoppingCartLink =
            By.cssSelector("a.ico-cart");

    public NotebooksPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public NotebooksPage open() {

        driver.get("https://demowebshop.tricentis.com/notebooks");
        wait.until(ExpectedConditions.visibilityOfElementLocated(notebookProduct));
        return this;
    }

    public void addNotebookToCart() {

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification));
    }

    public ShoppingCartPage openShoppingCart() {

        if (!driver.findElements(notificationCartLink)
                .isEmpty()) {

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

        if (!driver.findElements(notificationClose)
                .isEmpty()) {

            wait.until(ExpectedConditions.elementToBeClickable(notificationClose))
                    .click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(successNotification));
        }
    }
}