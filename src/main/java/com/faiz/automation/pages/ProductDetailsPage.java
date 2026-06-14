package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.faiz.automation.base.BasePage;

public class ProductDetailsPage extends BasePage {

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

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductDetailsPage openFictionBook() {

        driver.get(
                "https://demowebshop.tricentis.com/fiction");

        waitForVisibility(productName);

        return this;
    }

    public ProductDetailsPage openNotebook() {

        driver.get(
                "https://demowebshop.tricentis.com/141-inch-laptop");

        waitForVisibility(productName);

        return this;
    }

    public ProductDetailsPage openSneaker() {

        driver.get(
                "https://demowebshop.tricentis.com/blue-and-green-sneaker");

        waitForVisibility(productName);

        selectSneakerSizeIfDisplayed();

        return this;
    }

    public String getProductName() {

        return getText(productName)
                .trim();
    }

    public CartPage addToCartAndOpenCart() {

        click(addToCartButton);

        waitForVisibility(successNotification);

        click(cartNotificationLink);

        return new CartPage(driver);
    }

    public WishlistPage addToWishlistAndOpenWishlist() {

        click(addToWishlistButton);

        waitForVisibility(successNotification);

        click(wishlistNotificationLink);

        return new WishlistPage(driver);
    }

    public CompareProductsPage addToCompareAndOpenCompareList() {

        click(addToCompareButton);

        waitForVisibility(successNotification);

        return new CompareProductsPage(driver);
    }

    private void selectSneakerSizeIfDisplayed() {

        try {

            waitForVisibility(sneakerSize);

            new Select(sneakerSize)
                    .selectByVisibleText("8");

        } catch (Exception e) {

            // Other products don't have size selection
        }
    }
}