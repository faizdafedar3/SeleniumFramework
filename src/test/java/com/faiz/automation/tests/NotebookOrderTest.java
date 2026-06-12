package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.faiz.automation.base.BaseTest;
import com.faiz.automation.listeners.TestListener;
import com.faiz.automation.pages.CheckoutPage;
import com.faiz.automation.pages.LoginPage;
import com.faiz.automation.pages.NotebooksPage;
import com.faiz.automation.pages.ShoppingCartPage;
import com.faiz.automation.utils.ConfigReader;

@Listeners(TestListener.class)
public class NotebookOrderTest extends BaseTest {

    @Test
    public void selectNotebookAndPlaceOrder() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.clickLoginLink();

        loginPage.login(
                ConfigReader.getProperty("email"),
                ConfigReader.getProperty("password"));

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login failed");

        NotebooksPage notebooksPage =
                new NotebooksPage(driver)
                        .open();

        notebooksPage.addNotebookToCart();

        ShoppingCartPage shoppingCartPage =
                notebooksPage.openShoppingCart();

        Assert.assertTrue(
                shoppingCartPage.isNotebookDisplayed(),
                "Notebook product was not added to cart");

        CheckoutPage checkoutPage =
                shoppingCartPage.acceptTermsAndCheckout();

        checkoutPage.completeCheckout(
                ConfigReader.getProperty("email"));

        Assert.assertTrue(
                checkoutPage.isOrderPlacedSuccessfully(),
                "Order was not placed successfully");

        System.out.println(
                "Notebook order placed successfully");
    }
}
