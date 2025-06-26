package com.cng.testscript;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cng.generic.BaseClass;
import com.cng.pom.ReportPage;

@Listeners({ com.cng.utils.ExtentReporterNG.class, com.cng.generic.Listerner.class })

public class Report extends BaseClass {

	@Test 
	public void reports () throws InterruptedException
	{
		ReportPage report = new ReportPage(driver);
		report.setReport();
	}
}
