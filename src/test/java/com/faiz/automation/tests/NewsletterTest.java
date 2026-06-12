package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.faiz.automation.base.BaseTest;
import com.faiz.automation.listeners.TestListener;
import com.faiz.automation.pages.StoreHeaderPage;

@Listeners(TestListener.class)
public class NewsletterTest extends BaseTest {

    @Test
    public void verifyNewsletterSubscription() {

        StoreHeaderPage headerPage =
                new StoreHeaderPage(driver);

        String email =
                "newsletter"
                + System.currentTimeMillis()
                + "@gmail.com";

        headerPage.subscribeNewsletter(email);

        Assert.assertTrue(
                headerPage.getNewsletterMessage().toLowerCase().contains("sign"),
                "Newsletter response message should be displayed");
    }
}