package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class StoreHeaderPage extends BasePage {

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

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage search(
            String productName) {

        type(
                searchTextBox,
                productName);

        click(
                searchButton);

        return new SearchResultsPage(
                driver);
    }

    public void subscribeNewsletter(
            String email) {

        type(
                newsletterEmail,
                email);

        click(
                newsletterSubscribeButton);
    }

    public String getNewsletterMessage() {

        waitForVisibility(
                newsletterResult);

        wait.until(
                driver ->
                        !newsletterResult
                                .getText()
                                .trim()
                                .isEmpty());

        return getText(
                newsletterResult)
                .trim();
    }
}