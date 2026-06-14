package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class TopMenuPage extends BasePage {

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//ul[contains(@class,'top-menu')]//a[normalize-space()='Books']")
    private WebElement booksMenu;

    @FindBy(xpath = "//ul[contains(@class,'top-menu')]//a[normalize-space()='Computers']")
    private WebElement computersMenu;

    @FindBy(xpath = "//ul[contains(@class,'top-menu')]//a[normalize-space()='Electronics']")
    private WebElement electronicsMenu;

    @FindBy(xpath = "//ul[contains(@class,'top-menu')]//a[normalize-space()='Apparel & Shoes']")
    private WebElement apparelAndShoesMenu;

    @FindBy(xpath = "//ul[contains(@class,'top-menu')]//a[normalize-space()='Digital downloads']")
    private WebElement digitalDownloadsMenu;

    @FindBy(xpath = "//ul[contains(@class,'top-menu')]//a[normalize-space()='Jewelry']")
    private WebElement jewelryMenu;

    @FindBy(xpath = "//ul[contains(@class,'top-menu')]//a[normalize-space()='Gift Cards']")
    private WebElement giftCardsMenu;

    public TopMenuPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openBooks() {

        clickTopMenu(booksMenu);
    }

    public void openComputers() {

        clickTopMenu(computersMenu);
    }

    public void openElectronics() {

        clickTopMenu(electronicsMenu);
    }

    public void openApparelAndShoes() {

        clickTopMenu(apparelAndShoesMenu);
    }

    public void openDigitalDownloads() {

        clickTopMenu(digitalDownloadsMenu);
    }

    public void openJewelry() {

        clickTopMenu(jewelryMenu);
    }

    public void openGiftCards() {

        clickTopMenu(giftCardsMenu);
    }

    public String getPageTitle() {

        return getText(pageTitle)
                .trim();
    }

    private void clickTopMenu(
            WebElement menuLink) {

        click(menuLink);

        waitForVisibility(pageTitle);
    }
}