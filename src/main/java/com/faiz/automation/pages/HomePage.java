package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@href,'/computers')]")
    private WebElement computersMenu;

    @FindBy(xpath = "//a[contains(@href,'/notebooks')]")
    private WebElement notebooksLink;

    public void navigateToNotebooks() {

        Actions actions =
                new Actions(driver);

        actions.moveToElement(computersMenu)
               .perform();

        click(notebooksLink);
    }
}