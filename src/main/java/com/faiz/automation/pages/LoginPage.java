package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "ico-login")
    private WebElement loginLink;

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(xpath = "//input[@value='Log in']")
    private WebElement loginButton;

    @FindBy(className = "ico-logout")
    private WebElement logoutLink;

    public void clickLoginLink() {

        loginLink.click();
    }

    public void login(String emailId,
                      String pwd) {

        email.clear();
        email.sendKeys(emailId);

        password.clear();
        password.sendKeys(pwd);

        loginButton.click();
    }

    public boolean isLoginSuccessful() {

        try {

            return logoutLink.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void logout() {

        try {

            if (logoutLink.isDisplayed()) {

                logoutLink.click();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}