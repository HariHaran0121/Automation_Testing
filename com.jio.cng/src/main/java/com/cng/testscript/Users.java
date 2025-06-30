package com.cng.testscript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.cng.generic.BaseClass;
import com.cng.generic.FileLib;
import com.cng.pom.UserPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class Users extends BaseClass {

	UserPage user;

	// ********************USERS Page with valid data********************
	@Test(enabled = false)
	@Story("User Page")
	@Feature("Feature  : Checking the users creation page")
	@Description("This page contains to create , edit and delete the user details.")
	@Severity(SeverityLevel.MINOR)
	public void testAddUsersPage() throws InterruptedException, EncryptedDocumentException, IOException {

		user = new UserPage(driver);
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
	@Story("User Page")
	public void testEditUserDetails() throws InterruptedException, EncryptedDocumentException, IOException {
		user = new UserPage(driver);
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
	@Story("User Page")
	public void testUserDetails() throws InterruptedException {
		user = new UserPage(driver);
		user.userDetails();
	}

	@Test(dependsOnMethods = "testUserDetails", enabled = false)
	@Story("User Page")
	public void testUserPasswordReset() throws InterruptedException {
		user = new UserPage(driver);
		user.editPassword();
	}

	// code to delete a user only if AddUsersPage method works. <Make sure change
	// enabled = false into enabled = true>
	@Test(dependsOnMethods = "testAddUsersPage", enabled = false)
	@Story("User Page")
	public void testDeleteUser() throws EncryptedDocumentException, IOException {
		user = new UserPage(driver);
		String verify_delete_user = user.deleteUser();
		FileLib excel = new FileLib();
		String email = excel.getExcelData("userdata", 1, 1);
		SoftAssert a = new SoftAssert();
		a.assertNotEquals(email, verify_delete_user);
	}

	// code to check wheather the duplicate data will be accepted while creating
	// user's
	
	@Test(enabled = true)
	public void testDuplicateData() throws InterruptedException, EncryptedDocumentException, IOException{
		user = new UserPage(driver);
		FileLib excel = new FileLib();
		String user_name = excel.getExcelData("userdata", 3, 0);
		String email = excel.getExcelData("userdata", 3, 1);
		Thread.sleep(1000);
		user.validateDuplicateData(user_name, email);
		
	}
}
