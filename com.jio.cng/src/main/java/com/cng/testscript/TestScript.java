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

@Listeners({ com.cng.utils.ExtentReporterNG.class, com.cng.generic.Listerner.class })

public class TestScript extends BaseClass {

	ConsolidateDashboardPage cdp;

// Code to check the broken image links in the consolidate dashboard.	
	@SuppressWarnings("deprecation")
	@Test(enabled = true)
	public void dummy() throws Exception {
		Thread.sleep(2000);
		List<WebElement> images = driver.findElements(By.tagName("img"));
		//System.out.println(images.size());

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
	@Test(enabled = true)
	public void outletStatus() throws InterruptedException {
		cdp = new ConsolidateDashboardPage(driver);
		cdp.setoutletList();
	}

// Code to check the camera status 
	@Test(enabled = true)
	public void cameraStatus() throws InterruptedException {
		cdp = new ConsolidateDashboardPage(driver);
		cdp.setCameraStatus();
	}

// Code to download Web User Manual and Alert Compliance Report 	
	@Test(enabled = true)
	public void exportData() throws InterruptedException {
		String home_Page = driver.getWindowHandle();
		cdp = new ConsolidateDashboardPage(driver);
		cdp.setExportdropdown(home_Page);
	}

//Code to read and compare all the usecase list from the dashoard cards under "All violation based on Criticality" and from the excel sheet.
	@Test(enabled = true)
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
	@Test(enabled = true)
	public void testCustomerPage() throws Exception {
		Allure.label("tag", "Customer Page");
		Allure.label("tag", "Customer Details");
		Allure.label("tag", "Customer Information");
		CustomerPage cus = new CustomerPage(driver);
		cus.setCustomer();
		cus.setAddCustomer();
	}

	// ****************SITE PAGE********************
	@Test(enabled = true)
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
	@Test 
	public void reports () throws InterruptedException
	{
		ReportPage report = new ReportPage(driver);
		report.setReport();
	}

	// ********************UserSession Page************************
	@Test(enabled = true)
	public void testUserSessionPage() throws InterruptedException, AWTException {
		UserSessionsPage us = new UserSessionsPage(driver);
		us.setUserSession();

	}
	
	

	// ********************USERS Page********************
	@Test(enabled = false)
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

	@Test(dependsOnMethods = "testEditUserDetails", enabled = false)
	public void testUserDetails() throws InterruptedException {
		UserPage user = new UserPage(driver);
		user.userDetails();
	}

	@Test(dependsOnMethods = "testUserDetails", enabled = false)
	public void testUserPasswordReset() throws InterruptedException {
		UserPage user = new UserPage(driver);
		user.editPassword();
	}

	// code to delete a user only if AddUsersPage method works. <Make sure change
	// enabled = false into enabled = true>
	@Test(dependsOnMethods = "testAddUsersPage", enabled = false)
	public void testDeleteUser() throws EncryptedDocumentException, IOException {
		UserPage user = new UserPage(driver);
		String verify_delete_user = user.deleteUser();
		FileLib excel = new FileLib();
		String email = excel.getExcelData("userdata", 1, 1);
		SoftAssert a = new SoftAssert();
		a.assertNotEquals(email, verify_delete_user);
	}

	@Test(enabled = false)

	public void testAssertManagmentPage() throws InterruptedException {
		AssertManagmentPage amp = new AssertManagmentPage(driver);
		amp.setAssertManagment();
	}

	@Test(enabled = false)

	public void testCustomerConfigPage() {
		CustomerConfigPage config = new CustomerConfigPage(driver);
		config.customerCongif();
	}

	@Test(enabled = false)

	public void testDatasetManagmentPage() throws InterruptedException {
		DatasetManagmentPage ds = new DatasetManagmentPage(driver);
		ds.setDatasetPage();
	}

}