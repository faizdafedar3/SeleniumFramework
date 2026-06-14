package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.faiz.automation.base.BasePage;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Register']")
    private WebElement registerLink;

    @FindBy(id = "gender-male")
    private WebElement genderMale;

    @FindBy(id = "FirstName")
    private WebElement firstName;

    @FindBy(id = "LastName")
    private WebElement lastName;

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(className = "result")
    private WebElement successMessage;

    @FindBy(className = "ico-logout")
    private WebElement logoutLink;

    public void clickRegisterLink() {

        click(registerLink);
    }

    public void registerUser(
            String fname,
            String lname,
            String emailId,
            String pwd,
            String confirmPwd) {

        click(genderMale);

        type(firstName, fname);
        type(lastName, lname);
        type(email, emailId);
        type(password, pwd);
        type(confirmPassword, confirmPwd);

        click(registerButton);
    }

    public boolean isRegistrationSuccessful() {

        try {

            return isDisplayed(successMessage)
                    && getText(successMessage)
                            .contains("Your registration completed");

        } catch (Exception e) {

            return false;
        }
    }

    public String getSuccessMessage() {

        try {

            return getText(successMessage);

        } catch (Exception e) {

            return "";
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