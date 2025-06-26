package com.cng.testscript;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cng.generic.BaseClass;
import com.cng.pom.LoginPage;

@Listeners({ com.cng.utils.ExtentReporterNG.class, com.cng.generic.Listerner.class })

public class Login extends BaseClass {

	LoginPage loginPage;

	@Test
	public void checkWarning() throws InterruptedException {
		loginPage = new LoginPage(driver);
		loginPage.setLogout();
		String setCheckWarning = loginPage.setCheckWarning();
		Reporter.log(setCheckWarning);
		Assert.assertEquals(setCheckWarning, "Invalid Username or Password");
	}

}
