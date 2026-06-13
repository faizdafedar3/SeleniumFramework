package com.faiz.automation.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.faiz.automation.base.BaseTest;
import com.faiz.automation.listeners.TestListener;
import com.faiz.automation.pages.RegistrationPage;

@Listeners(TestListener.class)
public class RegistrationTest extends BaseTest {

    @Test(dataProvider = "registrationData")
    public void verifyRegistration(
            String firstName,
            String lastName,
            String email,
            String password,
            String confirmPassword,
            String expectedResult) {

        getDriver().get(
                "https://demowebshop.tricentis.com/register");

        RegistrationPage registerPage =
                new RegistrationPage(getDriver());

        String uniqueEmail = email;

        if (!email.isEmpty()
                && email.contains("@")
                && email.contains(".com")) {

            uniqueEmail =
                    System.currentTimeMillis()
                    + email;
        }

        registerPage.registerUser(
                firstName,
                lastName,
                uniqueEmail,
                password,
                confirmPassword);

        boolean actualResult =
                registerPage.isRegistrationSuccessful();

        System.out.println(
                "FirstName=" + firstName +
                ", LastName=" + lastName +
                ", Email=" + email +
                ", Expected=" + expectedResult +
                ", Actual=" + actualResult);

        if (expectedResult.equalsIgnoreCase("PASS")) {

            Assert.assertTrue(
                    actualResult,
                    "Registration should be successful");

        } else {

            Assert.assertFalse(
                    actualResult,
                    "Registration should fail");
        }

        if (actualResult) {
            registerPage.logout();
        }
    }

    @DataProvider(name = "registrationData")
    public Object[][] registrationData() {

        return new Object[][] {

                {
                    "Faiz",
                    "Dafedar",
                    "faiz12345@gmail.com",
                    "Test@123",
                    "Test@123",
                    "PASS"
                },

                {
                    "F",
                    "Dafedar",
                    "faiz1@gmail.com",
                    "Test@123",
                    "Test@123",
                    "PASS"
                },

                {
                    "",
                    "Dafedar",
                    "faiz@gmail.com",
                    "Test@123",
                    "Test@123",
                    "FAIL"
                },

                {
                    "Faiz",
                    "",
                    "faiz@gmail.com",
                    "Test@123",
                    "Test@123",
                    "FAIL"
                },

                {
                    "Faiz",
                    "Dafedar",
                    "",
                    "Test@123",
                    "Test@123",
                    "FAIL"
                },

                {
                    "Faiz",
                    "Dafedar",
                    "invalidemail",
                    "Test@123",
                    "Test@123",
                    "FAIL"
                },

                {
                    "Faiz",
                    "Dafedar",
                    "faiz@gmail",
                    "Test@123",
                    "Test@123",
                    "FAIL"
                },

                {
                    "Faiz",
                    "Dafedar",
                    "",
                    "",
                    "",
                    "FAIL"
                },

                {
                    "Faiz",
                    "Dafedar",
                    "faiz@gmail.com",
                    "12345",
                    "12345",
                    "FAIL"
                },

                {
                    "Faiz",
                    "Dafedar",
                    "faiz@gmail.com",
                    "Test@123",
                    "Test@124",
                    "FAIL"
                }
        };
    }
}