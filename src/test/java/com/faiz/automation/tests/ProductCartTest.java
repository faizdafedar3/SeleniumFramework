package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.faiz.automation.base.BaseTest;
import com.faiz.automation.listeners.TestListener;
import com.faiz.automation.pages.CartPage;
import com.faiz.automation.pages.ProductDetailsPage;

@Listeners(TestListener.class)
public class ProductCartTest extends BaseTest {

    @Test
    public void verifyProductCanBeAddedAndRemovedFromCart() {

        ProductDetailsPage productDetailsPage =
                new ProductDetailsPage(driver)
                        .openFictionBook();

        String productName =
                productDetailsPage.getProductName();

        CartPage cartPage =
                productDetailsPage.addToCartAndOpenCart();

        Assert.assertEquals(
                cartPage.getPageTitle(),
                "Shopping cart");

        Assert.assertTrue(
                cartPage.containsProduct(productName),
                "Product should be available in cart");

        cartPage.removeAllProducts();

        Assert.assertTrue(
                cartPage.getCartMessage().contains("Your Shopping Cart is empty"),
                "Cart should be empty after removing product");
    }
}
