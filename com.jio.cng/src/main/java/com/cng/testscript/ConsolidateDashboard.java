package com.cng.testscript;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cng.generic.BaseClass;
import com.cng.generic.FileLib;
import com.cng.pom.ConsolidateDashboardPage;

@Listeners({ com.cng.utils.ExtentReporterNG.class, com.cng.generic.Listerner.class })

public class ConsolidateDashboard extends BaseClass {

	ConsolidateDashboardPage cdp;

	
// Code to check the broken image links in the consolidate dashboard.	
	@SuppressWarnings("deprecation")
	@Test(enabled = true)
	public void dummy() throws Exception {
		Thread.sleep(2000);
		List<WebElement> images = driver.findElements(By.tagName("img"));
		System.out.println(images.size());

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
				// TODO Auto-generated catch block
				System.out.println(imageSrc);
			}

		}
	}

// Code to check the outlet status 
	@Test(enabled = false)
	public void outletStatus() throws InterruptedException {
		cdp = new ConsolidateDashboardPage(driver);
		cdp.setoutletList();
	}

// Code to check the camera status 
	@Test(enabled = false)
	public void cameraStatus() throws InterruptedException {
		cdp = new ConsolidateDashboardPage(driver);
		cdp.setCameraStatus();
	}

// Code to download Web User Manual and Alert Compliance Report 	
	@Test(enabled = false)
	public void exportData() throws InterruptedException {
		String home_Page = driver.getWindowHandle();
		cdp = new ConsolidateDashboardPage(driver);
		cdp.setExportdropdown(home_Page);
	}

//Code to read and compare all the usecase list from the dashoard cards under "All violation based on Criticality" and from the excel sheet.
	@Test(enabled = false)
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
}
