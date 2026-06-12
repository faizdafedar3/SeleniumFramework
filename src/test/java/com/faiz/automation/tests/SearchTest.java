package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.faiz.automation.base.BaseTest;
import com.faiz.automation.listeners.TestListener;
import com.faiz.automation.pages.SearchResultsPage;
import com.faiz.automation.pages.StoreHeaderPage;

@Listeners(TestListener.class)
public class SearchTest extends BaseTest {

    @Test
    public void verifyValidProductSearch() {

        StoreHeaderPage headerPage =
                new StoreHeaderPage(driver);

        SearchResultsPage searchResultsPage =
                headerPage.search("computer");

        Assert.assertEquals(
                searchResultsPage.getPageTitle(),
                "Search");

        Assert.assertTrue(
                searchResultsPage.hasSearchResults(),
                "Search results should be displayed");
    }

    @Test
    public void verifyInvalidProductSearch() {

        StoreHeaderPage headerPage =
                new StoreHeaderPage(driver);

        SearchResultsPage searchResultsPage =
                headerPage.search("invalidproductname12345");

        Assert.assertEquals(
                searchResultsPage.getPageTitle(),
                "Search");

        Assert.assertTrue(
                searchResultsPage.getNoResultsMessage().contains("No products were found")
                        || !searchResultsPage.hasSearchResults(),
                "No products should be displayed for invalid search");
    }
}