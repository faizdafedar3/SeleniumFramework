package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {

        super(driver);
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

        click(loginLink);
    }

    public void login(String emailId, String pwd) {

        type(email, emailId);
        type(password, pwd);
        click(loginButton);
    }

    public boolean isLoginSuccessful() {

        try {

            return isDisplayed(logoutLink);

        } catch (Exception e) {

            return false;
        }
    }

    public void logout() {

        try {

            if (isDisplayed(logoutLink)) {

                click(logoutLink);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}