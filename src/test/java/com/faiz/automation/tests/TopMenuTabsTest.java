package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.faiz.automation.base.BaseTest;
import com.faiz.automation.listeners.TestListener;
import com.faiz.automation.pages.TopMenuPage;

@Listeners(TestListener.class)
public class TopMenuTabsTest extends BaseTest {

    @Test
    public void verifyBooksMenuTab() {

        TopMenuPage topMenuPage =
                new TopMenuPage(driver);

        topMenuPage.openBooks();

        Assert.assertEquals(
                topMenuPage.getPageTitle(),
                "Books");
    }

    @Test
    public void verifyComputersMenuTab() {

        TopMenuPage topMenuPage =
                new TopMenuPage(driver);

        topMenuPage.openComputers();

        Assert.assertEquals(
                topMenuPage.getPageTitle(),
                "Computers");
    }

    @Test
    public void verifyElectronicsMenuTab() {

        TopMenuPage topMenuPage =
                new TopMenuPage(driver);

        topMenuPage.openElectronics();

        Assert.assertEquals(
                topMenuPage.getPageTitle(),
                "Electronics");
    }

    @Test
    public void verifyApparelAndShoesMenuTab() {

        TopMenuPage topMenuPage =
                new TopMenuPage(driver);

        topMenuPage.openApparelAndShoes();

        Assert.assertEquals(
                topMenuPage.getPageTitle(),
                "Apparel & Shoes");
    }

    @Test
    public void verifyDigitalDownloadsMenuTab() {

        TopMenuPage topMenuPage =
                new TopMenuPage(driver);

        topMenuPage.openDigitalDownloads();

        Assert.assertEquals(
                topMenuPage.getPageTitle(),
                "Digital downloads");
    }

    @Test
    public void verifyJewelryMenuTab() {

        TopMenuPage topMenuPage =
                new TopMenuPage(driver);

        topMenuPage.openJewelry();

        Assert.assertEquals(
                topMenuPage.getPageTitle(),
                "Jewelry");
    }

    @Test
    public void verifyGiftCardsMenuTab() {

        TopMenuPage topMenuPage =
                new TopMenuPage(driver);

        topMenuPage.openGiftCards();

        Assert.assertEquals(
                topMenuPage.getPageTitle(),
                "Gift Cards");
    }
}