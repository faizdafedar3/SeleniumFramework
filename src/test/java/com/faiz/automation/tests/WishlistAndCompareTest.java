package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.faiz.automation.listeners.TestListener;
import com.faiz.automation.pages.ProductDetailsPage;
import com.faiz.automation.pages.WishlistPage;
import com.faiz.automation.tests.base.BaseTest;

@Listeners(TestListener.class)
public class WishlistAndCompareTest extends BaseTest {

    @Test
    public void verifyProductCanBeAddedToWishlist() {

        ProductDetailsPage productDetailsPage =
                new ProductDetailsPage(getDriver())
                        .openSneaker();

        String productName =
                productDetailsPage.getProductName();

        System.out.println("Selected Product : " + productName);

        WishlistPage wishlistPage =
                productDetailsPage.addToWishlistAndOpenWishlist();

        Assert.assertEquals(
                wishlistPage.getPageTitle(),
                "Wishlist",
                "Wishlist page title mismatch");

        Assert.assertTrue(
                wishlistPage.containsProduct(productName),
                "Product should be available in wishlist");
    }
}
