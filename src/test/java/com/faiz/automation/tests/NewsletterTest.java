package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.faiz.automation.dataproviders.TestDataProvider;
import com.faiz.automation.listeners.TestListener;
import com.faiz.automation.pages.StoreHeaderPage;
import com.faiz.automation.tests.base.BaseTest;

@Listeners(TestListener.class)
public class NewsletterTest extends BaseTest {

    @Test(
            dataProvider = "newsletterData",
            dataProviderClass = TestDataProvider.class)
    public void verifyNewsletterSubscription(
            String email) {

        StoreHeaderPage storeHeaderPage =
                new StoreHeaderPage(
                        getDriver());

        storeHeaderPage.subscribeNewsletter(
                email);

        String actualMessage =
                storeHeaderPage.getNewsletterMessage();

        Assert.assertFalse(
                actualMessage.isEmpty(),
                "Newsletter message should not be empty");

        System.out.println(
                "Email : " + email);

        System.out.println(
                "Result : " + actualMessage);
    }
}