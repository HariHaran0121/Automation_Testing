package com.cng.testscript;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cng.generic.BaseClass;
import com.cng.pom.CustomerPage;

import io.qameta.allure.Allure;


@Listeners({ com.cng.utils.ExtentReporterNG.class, com.cng.generic.Listerner.class })

public class Customers extends BaseClass{
	
	@Test(enabled=true)
	public void testCustomerPage() throws Exception {
		Allure.label("tag", "Customer Page");
		Allure.label("tag", "Customer Details");
		Allure.label("tag", "Customer Information");
		CustomerPage cus = new CustomerPage(driver);
		cus.setCustomer();
		cus.setAddCustomer();
	}

}
