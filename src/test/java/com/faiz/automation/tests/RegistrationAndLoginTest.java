package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.faiz.automation.base.BaseTest;
import com.faiz.automation.pages.LoginPage;
import com.faiz.automation.pages.RegistrationPage;

public class RegistrationAndLoginTest extends BaseTest {

    @Test
    public void verifyRegistrationAndLogin() {

        RegistrationPage registerPage =
                new RegistrationPage(getDriver());

        String email =
                "faiz"
                + System.currentTimeMillis()
                + "@gmail.com";

        String password = "Test@123";

        registerPage.clickRegisterLink();

        registerPage.registerUser(
                "Faiz",
                "Dafedar",
                email,
                password,
                password);

        Assert.assertTrue(
                registerPage.isRegistrationSuccessful(),
                "Registration Failed");

        System.out.println(
                "Registration Successful : "
                + email);

        registerPage.logout();

        LoginPage loginPage =
                new LoginPage(getDriver());

        loginPage.clickLoginLink();

        loginPage.login(
                email,
                password);

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login Failed");

        System.out.println(
                "Login Successful : "
                + email);
    }
}