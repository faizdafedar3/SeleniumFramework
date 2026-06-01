package com.faiz.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(
                            "test-output/ExtentReport.html");

            spark.config().setDocumentTitle(
                    "Automation Report");

            spark.config().setReportName(
                    "Selenium Test Execution Report");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo(
                    "Tester",
                    "Faiz");

            extent.setSystemInfo(
                    "Environment",
                    "QA");
        }

        return extent;
    }
}