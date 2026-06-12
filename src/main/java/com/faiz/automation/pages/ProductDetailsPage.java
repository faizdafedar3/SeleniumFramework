package com.faiz.automation.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = ".product-name h1")
    private WebElement productName;

    @FindBy(id = "product_attribute_28_7_10")
    private WebElement sneakerSize;

    @FindBy(css = "input[id^='add-to-cart-button-']")
    private WebElement addToCartButton;

    @FindBy(css = "input.add-to-wishlist-button")
    private WebElement addToWishlistButton;

    @FindBy(css = "input.add-to-compare-list-button")
    private WebElement addToCompareButton;

    @FindBy(css = ".bar-notification")
    private WebElement successNotification;

    @FindBy(css = ".bar-notification.success a[href='/cart']")
    private WebElement cartNotificationLink;

    @FindBy(css = ".bar-notification.success a[href='/wishlist']")
    private WebElement wishlistNotificationLink;

    @FindBy(css = ".bar-notification.success a[href='/compareproducts']")
    private WebElement compareNotificationLink;

    public ProductDetailsPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public ProductDetailsPage openFictionBook() {
        driver.get("https://demowebshop.tricentis.com/fiction");
        wait.until(ExpectedConditions.visibilityOf(productName));
        return this;
    }

    public ProductDetailsPage openNotebook() {
        driver.get("https://demowebshop.tricentis.com/141-inch-laptop");
        wait.until(ExpectedConditions.visibilityOf(productName));
        return this;
    }

    public ProductDetailsPage openSneaker() {
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");
        wait.until(ExpectedConditions.visibilityOf(productName));
        selectSneakerSizeIfDisplayed();
        return this;
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName))
                .getText()
                .trim();
    }

    public CartPage addToCartAndOpenCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        wait.until(ExpectedConditions.visibilityOf(successNotification));
        wait.until(ExpectedConditions.elementToBeClickable(cartNotificationLink)).click();
        return new CartPage(driver);
    }

    public WishlistPage addToWishlistAndOpenWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(addToWishlistButton)).click();
        wait.until(ExpectedConditions.visibilityOf(successNotification));
        wait.until(ExpectedConditions.elementToBeClickable(wishlistNotificationLink)).click();
        return new WishlistPage(driver);
    }

    public CompareProductsPage addToCompareAndOpenCompareList() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCompareButton)).click();
        return new CompareProductsPage(driver);
    }

    private void selectSneakerSizeIfDisplayed() {
        try {
            new Select(wait.until(ExpectedConditions.visibilityOf(sneakerSize)))
                    .selectByVisibleText("8");
        } catch (Exception e) {
            // Other products do not have sneaker size.
        }
    }
}
