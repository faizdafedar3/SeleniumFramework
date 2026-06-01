package com.faiz.automation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.faiz.automation.base.BaseTest;
import com.faiz.automation.utils.ExtentManager;
import com.faiz.automation.utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private ExtentReports extent =
            ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(
            ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getMethod()
                        .getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        test.get().fail(result.getThrowable());

        try {

            BaseTest base =
                    (BaseTest) result.getInstance();

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            base.getDriver(),
                            result.getMethod()
                            .getMethodName());

            test.get()
            .addScreenCaptureFromPath(
                    screenshotPath);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(
            ITestContext context) {

        extent.flush();
    }
}