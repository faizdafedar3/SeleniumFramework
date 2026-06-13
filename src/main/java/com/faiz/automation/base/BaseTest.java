package com.faiz.automation.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.faiz.automation.utils.ConfigReader;

public class BaseTest {

    @BeforeMethod
    public void setUp() {

        DriverFactory.setDriver(
                new ChromeDriver());

        DriverFactory.getDriver()
                     .manage()
                     .window()
                     .maximize();

        DriverFactory.getDriver()
                     .manage()
                     .timeouts()
                     .implicitlyWait(Duration.ofSeconds(10));

        DriverFactory.getDriver()
                     .get(
                             ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }

    public WebDriver getDriver() {

        return DriverFactory.getDriver();
    }
}