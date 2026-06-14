package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class NotebooksPage extends BasePage {

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

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public NotebooksPage open() {

        driver.get(
                "https://demowebshop.tricentis.com/notebooks");

        waitForVisibility(
                notebookProduct);

        return this;
    }

    public void addNotebookToCart() {

        click(
                addToCartButton);

        waitForVisibility(
                successNotification);
    }

    public ShoppingCartPage openShoppingCart() {

        if (isDisplayed(
                notificationCartLink)) {

            click(
                    notificationCartLink);

            return new ShoppingCartPage(
                    driver);
        }

        closeSuccessNotificationIfDisplayed();

        click(
                shoppingCartLink);

        return new ShoppingCartPage(
                driver);
    }

    private void closeSuccessNotificationIfDisplayed() {

        if (isDisplayed(
                notificationClose)) {

            click(
                    notificationClose);
        }
    }
}