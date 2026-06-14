package com.faiz.automation.dataproviders;

import org.testng.annotations.DataProvider;

import com.faiz.automation.utils.ExcelUtil;

public class TestDataProvider {

	@DataProvider(name = "newsletterData")
	public Object[][] newsletterData() {

	    return ExcelUtil.getSheetData(
	            "src/test/resources/testdata/NewsletterData.xlsx",
	            "Newsletter");
	}
    }
