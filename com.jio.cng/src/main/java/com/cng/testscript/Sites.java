package com.cng.testscript;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cng.generic.BaseClass;
import com.cng.generic.FileLib;
import com.cng.pom.SitePage;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({com.cng.utils.ExtentReporterNG.class, com.cng.generic.Listerner.class}) 
public class Sites extends BaseClass{

	@Epic("")
	@Story("Site Page")
	@Description("The site page checks by passing all the values in the from the excel sheet.")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled=true)
	public void testSitePage() throws Exception{	
		Allure.label("tag", "Site Page");
		Allure.label("tag", "Site Details");
		Allure.label("tag", "Site Information");
		FileLib excel = new FileLib();
		String siteName = excel.getExcelData("sitedata", 1, 0);//sitename	
		String searchSite = excel.getExcelData("sitedata", 1, 1);//site location
		SitePage site = new SitePage(driver);
		site.setSitePages(siteName, searchSite);
}
}
