package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.faiz.automation.base.BaseTest;
import com.faiz.automation.listeners.TestListener;
import com.faiz.automation.pages.LoginPage;
import com.faiz.automation.utils.ConfigReader;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test
    public void verifyLogin() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.clickLoginLink();

        loginPage.login(
                ConfigReader.getProperty("email"),
                ConfigReader.getProperty("password"));

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login Failed");

        System.out.println(
                "Login Successful");
    }
}