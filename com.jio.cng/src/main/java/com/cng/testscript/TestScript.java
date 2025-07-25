package com.cng.testscript;

import java.awt.AWTException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.cng.generic.BaseClass;
import com.cng.generic.FileLib;
import com.cng.pom.AssertManagmentPage;
import com.cng.pom.ConsolidateDashboardPage;
import com.cng.pom.CustomerConfigPage;
import com.cng.pom.CustomerPage;
import com.cng.pom.DatasetManagmentPage;
import com.cng.pom.ReportPage;
import com.cng.pom.SitePage;
import com.cng.pom.UserPage;
import com.cng.pom.UserSessionsPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({ com.cng.utils.ExtentReporterNG.class, com.cng.generic.Listerner.class })

public class TestScript extends BaseClass {

	ConsolidateDashboardPage cdp;

// Code to check the broken image links in the consolidate dashboard.	
	@Epic("")
	@Story("Broken Image")
	@Description("It checks for the broken images in the consolidate dashboard")
	@Severity(SeverityLevel.MINOR)
	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1)
	public void dummy() throws Exception {
		Thread.sleep(2000);
		List<WebElement> images = driver.findElements(By.tagName("img"));
		// System.out.println(images.size());

		for (WebElement image : images) {
			String imageSrc = image.getAttribute("src");
			try {
				URL url = new URL(imageSrc);
				URLConnection urlConnection = url.openConnection();
				HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
				httpURLConnection.setConnectTimeout(5000);
				httpURLConnection.connect();
				Assert.assertEquals(httpURLConnection.getResponseCode(), 200);
//				if (httpURLConnection.getResponseCode() == 200) {
//					System.out.println(imageSrc + ">>" + httpURLConnection.getResponseCode());
//				} else {
//					System.err.println(imageSrc + ">>" + httpURLConnection.getResponseCode());
//				}
			} catch (IOException e) {
				Reporter.log(imageSrc);
			}
		}
	}

// Code to check the outlet status 
	@Epic("")
	@Story("Outlet Status")
	@Description("Checks the outlet status in the Consolidate Dashboard.")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 2)
	public void outletStatus() throws InterruptedException {
		cdp = new ConsolidateDashboardPage(driver);
		cdp.setoutletList();
	}

// Code to check the camera status 
	@Epic("")
	@Story("Camera Status")
	@Description("Checks the Camera Status status in the Consolidate Dashboard.")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 3)
	public void cameraStatus() throws InterruptedException {
		cdp = new ConsolidateDashboardPage(driver);
		cdp.setCameraStatus();
	}

// Code to download Web User Manual and Alert Compliance Report 	
	@Epic("")
	@Story("Manual")
	@Description("Checks the UserManual and Alert Compliance Report")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 4)
	public void exportData() throws InterruptedException {
		String home_Page = driver.getWindowHandle();
		cdp = new ConsolidateDashboardPage(driver);
		cdp.setExportdropdown(home_Page);
	}

//Code to read and compare all the usecase list from the dashoard cards under "All violation based on Criticality" and from the excel sheet.
	@Epic("")
	@Story("Usecase List")
	@Description("Checks the usecase list under the All Violation Based on Criticality")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 5)
	public void usecaseList() throws EncryptedDocumentException, IOException {
		List<WebElement> elements = driver.findElements(
				By.xpath("//div[@class='dashboard-tile has-cont-busy-ind ng-star-inserted']/div[1]/div[1]/span[1]"));
		int count = elements.size();
		// System.out.print(count); //18
		FileLib excel = new FileLib();

		for (int i = 0; i < count - 1; i++) {
			Assert.assertEquals(elements.get(i).getText(), excel.getExcelData("usecase_list", 1 + i, 0));
		}
	}

	// ************Customer Page********************
	@Epic("")
	@Story("Customer Page")
	@Description("It checks the customer page by passing the values taken from the Excel sheet")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 6)
	public void testCustomerPage() throws Exception {
		Allure.label("tag", "Customer Page");
		Allure.label("tag", "Customer Details");
		Allure.label("tag", "Customer Infor, priority = 1mation");
		CustomerPage cus = new CustomerPage(driver);
		cus.setCustomer();
		cus.setAddCustomer();
	}

	// ****************SITE PAGE********************
	@Epic("")
	@Story("Site Page")
	@Description("The site page checks by passing all the values in the from the excel sheet.")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 7)
	public void testSitePage() throws Exception {
		Allure.label("tag", "Site Page");
		Allure.label("tag", "Site Details");
		Allure.label("tag", "Site Information");
		FileLib excel = new FileLib();
		String siteName = excel.getExcelData("sitedata", 1, 0);// sitename
		String searchSite = excel.getExcelData("sitedata", 1, 1);// site location
		SitePage site = new SitePage(driver);
		site.setSitePages(siteName, searchSite);

		// code to check the site is there are not in the prod server
		// String site_check =site.findSiteName(siteName);
		// AssertJUnit.assertEquals(siteName, site_check);
	}

	// ****************REPORT PAGE********************
	@Epic("")
	@Story("Report Page")
	@Description("This check wheather the report are downloadable or not")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 8)
	public void testReportsPage() throws InterruptedException {
		ReportPage report = new ReportPage(driver);
		report.setReport();
	}

	// ********************UserSession Page************************
	@Epic("")
	@Story("User Session Page")
	@Description("Checks for the user session")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 9)
	public void testUserSessionPage() throws InterruptedException, AWTException {
		UserSessionsPage us = new UserSessionsPage(driver);
		us.setUserSession();

	}

	// ********************USERS Page********************
	@Epic("")
	@Story("User Page")
	@Description("This check for the adding of the user")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 10)
	public void testAddUsersPage() throws InterruptedException, EncryptedDocumentException, IOException {

		UserPage user = new UserPage(driver);
		FileLib excel = new FileLib();

		String user_name = excel.getExcelData("userdata", 1, 0);

		String email = excel.getExcelData("userdata", 1, 1);
		String first_name = excel.getExcelData("userdata", 1, 2);
		String last_name = excel.getExcelData("userdata", 1, 3);
		String contact_no = excel.getExcelData("userdata", 1, 4);
		String password = excel.getExcelData("userdata", 1, 6);
		String confirm_pass = excel.getExcelData("userdata", 1, 7);
		String role = excel.getExcelData("userdata", 1, 8);
		String status = excel.getExcelData("userdata", 1, 9);
		String notification = excel.getExcelData("userdata", 1, 11);
		String site = excel.getExcelData("userdata", 1, 12);
		String emp_id = excel.getExcelData("userdata", 1, 13);

		Thread.sleep(5000);
		user.setAddUsers(user_name, email, first_name, last_name, contact_no, password, confirm_pass, role, status,
				notification, site, emp_id);

//		String verifiedUser = user.getVerifiedUser();
//		AssertJUnit.assertEquals(email, verifiedUser);		
	}

	@Story("User Page")
	@Description("This check for to edit of the user")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "testAddUsersPage", enabled = false)
	public void testEditUserDetails() throws InterruptedException, EncryptedDocumentException, IOException {
		UserPage user = new UserPage(driver);
		FileLib excel = new FileLib();
		String user_name = excel.getExcelData("userdata", 1, 0);
		String email = excel.getExcelData("userdata", 1, 1);
		String first_name = excel.getExcelData("userdata", 1, 2);
		String last_name = excel.getExcelData("userdata", 1, 3);
		String contact_no = excel.getExcelData("userdata", 1, 4);
		String password = excel.getExcelData("userdata", 1, 6);
		String confirm_pass = excel.getExcelData("userdata", 1, 7);
		String role = excel.getExcelData("userdata", 1, 8);
		String status = excel.getExcelData("userdata", 1, 9);
		String notification = excel.getExcelData("userdata", 1, 11);
		String site = excel.getExcelData("userdata", 1, 12);
		String emp_id = excel.getExcelData("userdata", 1, 13);

		user.editUser(user_name, email, first_name, last_name, contact_no, password, confirm_pass, role, status,
				notification, site, emp_id);
	}

	@Story("User Page")
	@Description("This check for the editing of the user")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "testEditUserDetails", enabled = false)
	public void testUserDetails() throws InterruptedException {
		UserPage user = new UserPage(driver);
		user.userDetails();
	}

	@Story("User Page")
	@Description("This code for the Checking of the of the user")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "testUserDetails", enabled = false)
	public void testUserPasswordReset() throws InterruptedException {
		UserPage user = new UserPage(driver);
		user.editPassword();
	}

	// code to delete a user only if AddUsersPage method works. <Make sure change
	// enabled= true into enabled= true>
	@Story("User Page")
	@Description("This code for the checking of the deleted user")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "testAddUsersPage", enabled = false)
	public void testDeleteUser() throws EncryptedDocumentException, IOException {
		UserPage user = new UserPage(driver);
		String verify_delete_user = user.deleteUser();
		FileLib excel = new FileLib();
		String email = excel.getExcelData("userdata", 1, 1);
		SoftAssert a = new SoftAssert();
		a.assertNotEquals(email, verify_delete_user);
	}

	@Epic("")
	@Story("Assert Managment Page")
	@Description("This is for checking the assert managment page.")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 11)
	public void testAssertManagmentPage() throws InterruptedException {
		AssertManagmentPage amp = new AssertManagmentPage(driver);
		amp.setAssertManagment();
	}

	@Epic("")
	@Story("Customer Page")
	@Description("This is for checking the customer page.")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 12)
	public void testCustomerConfigPage() {
		CustomerConfigPage config = new CustomerConfigPage(driver);
		config.customerCongif();
	}

	@Epic("")
	@Story("Dataset Managment Page")
	@Description("This is to check the Dataset managment page.")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true, priority = 13)
	public void testDatasetManagmentPage() throws InterruptedException {
		DatasetManagmentPage ds = new DatasetManagmentPage(driver);
		ds.setDatasetPage();
	}

}