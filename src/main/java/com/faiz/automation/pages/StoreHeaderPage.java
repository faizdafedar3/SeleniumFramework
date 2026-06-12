package com.faiz.automation.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreHeaderPage {

    private final WebDriver waitDriver;
    private final WebDriverWait wait;

    @FindBy(id = "small-searchterms")
    private WebElement searchTextBox;

    @FindBy(css = "input.button-1.search-box-button")
    private WebElement searchButton;

    @FindBy(id = "newsletter-email")
    private WebElement newsletterEmail;

    @FindBy(id = "newsletter-subscribe-button")
    private WebElement newsletterSubscribeButton;

    @FindBy(id = "newsletter-result-block")
    private WebElement newsletterResult;

    public StoreHeaderPage(WebDriver driver) {

        this.waitDriver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage search(String productName) {

        wait.until(ExpectedConditions.visibilityOf(searchTextBox));
        searchTextBox.clear();
        searchTextBox.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        return new SearchResultsPage(waitDriver);
    }

    public void subscribeNewsletter(String email) {

        wait.until(ExpectedConditions.visibilityOf(newsletterEmail));
        newsletterEmail.clear();
        newsletterEmail.sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(newsletterSubscribeButton)).click();
    }

    public String getNewsletterMessage() {

        wait.until(ExpectedConditions.visibilityOf(newsletterResult));
        wait.until(driver -> !newsletterResult.getText().trim().isEmpty());

        return newsletterResult.getText().trim();
    }
}
