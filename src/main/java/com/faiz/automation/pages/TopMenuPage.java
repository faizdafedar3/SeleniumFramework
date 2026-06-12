package com.faiz.automation.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenuPage {

    private final WebDriverWait wait;

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

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

        return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                .getText()
                .trim();
    }

    private void clickTopMenu(WebElement menuLink) {

        wait.until(ExpectedConditions.elementToBeClickable(menuLink))
                .click();

        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }
}