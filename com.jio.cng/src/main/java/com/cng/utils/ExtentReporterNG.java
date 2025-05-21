package com.cng.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG implements IReporter {
	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(outputDirectory + File.separator + "CNG_ExtentReport.html", true);
		// Adding system information
		extent.addSystemInfo("OS", System.getProperty("os.name"));
		extent.addSystemInfo("Java Version", System.getProperty("java.version"));
		for (ISuite suite : suites) {
			extent.addSystemInfo("Suite Name", suite.getName());
			Map<String, ISuiteResult> result = suite.getResults();
			for (Map.Entry<String, ISuiteResult> entry : result.entrySet()) {
				ISuiteResult suiteResult = entry.getValue();
				ITestContext context = suiteResult.getTestContext();
				extent.addSystemInfo("Test Name", context.getName());
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}
		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		if (tests.size() > 0) {
			List<ITestResult> sortedResults = tests.getAllResults().stream()
					.sorted((r1, r2) -> Integer.compare(r1.getMethod().getPriority(), r2.getMethod().getPriority()))
					.collect(Collectors.toList());
			for (ITestResult result : sortedResults) {
				ExtentTest test = extent.startTest(result.getMethod().getMethodName());
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				for (String group : result.getMethod().getGroups()) {
					test.assignCategory(group);
				}
				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable().getMessage());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed successfully.");
				}
				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}
