package com.cng.generic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.cng.pom.HomePage;
import com.cng.pom.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {

	public static WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;

	// Code to open the browser and adds the URL
	@BeforeTest
	public void openBrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--force-device-scale-factor=1.3");
		Reporter.log("openBrowser", true);
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.manage().window().maximize();
		extent = new ExtentReports(System.getProperty("user.dir") + "Extentreporter/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Jenkins");
		extent.addSystemInfo("User Name", "Hari Haran");
		extent.addSystemInfo("Environment", "QA");
		deleteDirectory("allure-results");
		deleteDirectory("allure-report");
	}

	private void deleteDirectory(String folderName) {
		try {
			Path path = Path.of(folderName);
			if (Files.exists(path)) {
				Files.walk(path).map(Path::toFile).sorted((o1, o2) -> -o1.compareTo(o2)) // delete children first
						.forEach(File::delete);
				System.out.println("Deleted old " + folderName + " folder.");
			}
		} catch (Exception e) {
			System.err.println("Failed to delete " + folderName + ": " + e.getMessage());
		}
	}

	// Code to read the file
	@BeforeClass
	public void login() throws Exception {
		Reporter.log("login", true);
		FileLib f = new FileLib();
		String url = f.getProperty("url");
		String un = f.getProperty("username");
		String pw = f.getProperty("password");
		String customer = f.getProperty("customer");
		driver.get(url);
		LoginPage l = new LoginPage(driver);
		l.setLogin(un, pw, customer);
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@AfterMethod
	public void logout(ITestResult result) throws InterruptedException, IOException {
//		if(result.getStatus()==ITestResult.FAILURE){
//			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
//			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
//			
//		String screenshotPath = ScreenshotCode.getScreenshot(driver, result.getName());
//			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
//			extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
//		}
//		else if(result.getStatus()==ITestResult.SKIP){
//			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
//		}
//		else if(result.getStatus()==ITestResult.SUCCESS){
//			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
//		}
//		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
	}

	@AfterClass
	public void LogoutCloseBrowser() throws InterruptedException, IOException{
		HomePage h = new HomePage(driver);
		Thread.sleep(5000);
		driver.quit();
	}
	

	@AfterTest
	void closeBrowser(ITestResult result) throws InterruptedException, IOException {
//		HomePage h = new HomePage(driver);
//		Thread.sleep(5000);
//		driver.quit();
	}

//	@AfterSuite
//	public void reportEnd()
//	{
//		extent.flush();
//		extent.close();
//	}

}
