package com.cng.testscript;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.cng.generic.BaseClass;
import com.cng.generic.FileLib;
import com.cng.pom.AssertManagmentPage;
import com.cng.pom.CustomerConfigPage;
import com.cng.pom.CustomerPage;
import com.cng.pom.DatasetManagmentPage;
import com.cng.pom.SitePage;
import com.cng.pom.UserPage;
import com.cng.pom.UserSessionsPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Listeners({com.cng.utils.ExtentReporterNG.class, com.cng.generic.Listerner.class}) 


public class TestScript extends BaseClass {

	
	// ************Customer Page********************
	@Test(enabled=true)
	@Epic("EP001")
	@Story("Customer Page")
	@Feature("Feature 1 : Adding Customer in the portal")
	@Description("This page contains the details of the customer page and we can create , edit and delete the customer information.")
	@Severity(SeverityLevel.MINOR)
	public void testCustomerPage() throws Exception {
		Allure.label("tag", "Customer Page");
		Allure.label("tag", "Customer Details");
		Allure.label("tag", "Customer Information");
		CustomerPage cus = new CustomerPage(driver);
		cus.setCustomer();
		cus.setAddCustomer();
	}

	// ****************SITE PAGE********************
	@Test(enabled=true)
	@Epic("EP002")
	@Story("Site Page")
	@Feature("Feature 2 : Adding Site in the portal")
	@Description("This page contains the details of the site page and we can create , edit and delete the site information.")
	@Severity(SeverityLevel.NORMAL)
	public void testSitePage() throws Exception{	
		Allure.label("tag", "Site Page");
		Allure.label("tag", "Site Details");
		Allure.label("tag", "Site Information");
		FileLib excel = new FileLib();
		String siteName = excel.getExcelData("sitedata", 1, 0);//sitename	
		String searchSite = excel.getExcelData("sitedata", 1, 1);//site location
		SitePage site = new SitePage(driver);
		site.setSitePages(siteName, searchSite);
		
		//code to check the site is there are not in the prod server
		//String site_check =site.findSiteName(siteName);
		//AssertJUnit.assertEquals(siteName, site_check);
	}
		
	
	// ********************UserSession Page************************
	@Test(enabled=true)
	@Epic("EP003")
	@Story("UserSession Page")
	@Feature("Feature 3 : Checking the users activities")
	@Description("This page contains the details of the user when they have logged in and logged out. The time stamp will be saved in the table.")
	@Severity(SeverityLevel.MINOR)
	public void testUserSessionPage() throws InterruptedException, AWTException
	{
		UserSessionsPage us = new UserSessionsPage(driver);
		us.setUserSession();
		
	}
	

	// ********************USERS Page********************
	@Test(enabled=false)
	@Epic("EP004")
	@Story("User Page")
	@Feature("Feature  : Checking the users creation page")
	@Description("This page contains to create , edit and delete the user details.")
	@Severity(SeverityLevel.MINOR)
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
		user.setAddUsers(user_name,email,first_name,last_name,contact_no,password,
				confirm_pass,role,status,notification,site,emp_id);
		
//		String verifiedUser = user.getVerifiedUser();
//		AssertJUnit.assertEquals(email, verifiedUser);		
	}
		
	@Test(dependsOnMethods="testAddUsersPage", enabled=false)
	@Epic("EP004")
	@Story("User Page")
	public void testEditUserDetails() throws InterruptedException, EncryptedDocumentException, IOException 
	{
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
		
		user.editUser(user_name, email, first_name, last_name, contact_no, password, confirm_pass, role, status, notification, site, emp_id);
	}
	
	@Test(dependsOnMethods = "testEditUserDetails", enabled=false)
	@Epic("EP004")
	@Story("User Page")
	public void testUserDetails() throws InterruptedException
	{
		UserPage user = new UserPage(driver);
		user.userDetails();
	}
	
	@Test(dependsOnMethods = "testUserDetails", enabled=false)
	@Epic("EP004")
	@Story("User Page")
	public void testUserPasswordReset() throws InterruptedException
	{
		UserPage user = new UserPage(driver);
		user.editPassword();
	}
	
	
	//code to delete a user only if AddUsersPage method works. <Make sure change enabled = false into enabled = true>
	@Test(dependsOnMethods = "testAddUsersPage", enabled=false)
	@Epic("EP004")
	@Story("User Page")
	public void testDeleteUser() throws EncryptedDocumentException, IOException
	{
		UserPage user = new UserPage(driver);
		String verify_delete_user = user.deleteUser();
		FileLib excel = new FileLib();
		String email = excel.getExcelData("userdata", 1, 1);
		SoftAssert a = new SoftAssert();
		a.assertNotEquals(email, verify_delete_user );	
	}
	
	@Test(enabled=false)
	@Epic("EP005")
	@Story("Assert Managment")
	@Feature("Feature 4 : Checking the Assert managment page")
	@Description("This page contains the details of the user when they have logged in and logged out. The time stamp will be saved in the table.")
	@Severity(SeverityLevel.MINOR)
	public void testAssertManagmentPage() throws InterruptedException
	{
		AssertManagmentPage amp = new AssertManagmentPage(driver);
		amp.setAssertManagment();
	}
	
	@Test(enabled=false)
	@Epic("EP006")
	@Story("Customer Config")
	@Feature("Feature 5 : Checking the congifuartion of the users [Notification , Supress and Schedule alerts]")
	@Description("This page contains the details of the Customer configuration where we can modify their notification and alert status.")
	@Severity(SeverityLevel.MINOR)
	public void testCustomerConfigPage()
	{
		CustomerConfigPage config = new CustomerConfigPage(driver);
		config.customerCongif();
	}
	
	@Test(enabled=false)
	@Epic("EP007")
	@Story("Dataset Managment")
	@Feature("Feature 6 : Cheacking the Dataset Managment")
	@Description("Looking for the discription for this page")
	@Severity(SeverityLevel.MINOR)
	public void testDatasetManagmentPage() throws InterruptedException
	{
		DatasetManagmentPage ds = new DatasetManagmentPage(driver);
		ds.setDatasetPage();
	}
		
}