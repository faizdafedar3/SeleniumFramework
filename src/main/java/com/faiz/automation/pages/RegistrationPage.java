package com.faiz.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {

        this.driver = driver;
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
        registerLink.click();
    }

    public void registerUser(String fname,
                             String lname,
                             String emailId,
                             String pwd,
                             String confirmPwd) {

        genderMale.click();

        firstName.clear();
        firstName.sendKeys(fname);

        lastName.clear();
        lastName.sendKeys(lname);

        email.clear();
        email.sendKeys(emailId);

        password.clear();
        password.sendKeys(pwd);

        confirmPassword.clear();
        confirmPassword.sendKeys(confirmPwd);

        registerButton.click();
    }

    public boolean isRegistrationSuccessful() {

        try {

            return successMessage.isDisplayed()
                    && successMessage.getText()
                            .contains("Your registration completed");

        } catch (Exception e) {

            return false;
        }
    }

    public String getSuccessMessage() {

        try {

            return successMessage.getText();

        } catch (Exception e) {

            return "";
        }
    }

    public void logout() {

        try {

            if (logoutLink.isDisplayed()) {
                logoutLink.click();
            }

        } catch (Exception e) {

        }
    }
}