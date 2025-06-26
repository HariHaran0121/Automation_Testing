package com.cng.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConsolidateDashboardPage {

	public WebDriver driver;

	@FindBy(xpath = "//div[text()='Outlet Status']")
	private WebElement outletStatus;

	@FindBy(xpath = "//span[text()='Status :']/../ng-select[1]/div[1]/span[1]")
	private WebElement dropdownButton;

	@FindBy(xpath = "(//span[text()='ALL'])[2]")
	private WebElement status_All;

	@FindBy(xpath = "//span[text()='ACTIVE']")
	private WebElement status_Active;

	@FindBy(xpath = "//span[text()='INACTIVE']")
	private WebElement status_Inactive;

	@FindBy(xpath = "(//button[@title='Export as Excel'])[2]")
	private WebElement download_Excel;

	@FindBy(xpath = "//button[@class='btn-close']")
	private WebElement outlet_Close_Btn;

	// ----------------------------- Camera Status-----------------------------//

	@FindBy(xpath = "//div[text()='Camera Status on']")
	private WebElement cameraStatus;

	// -----------------------------Export -------------------------------------//
	@FindBy(xpath = "//button[@class='dropdown-manual dropdown-toggle']")
	private WebElement exportBtn;

	@FindBy(xpath = "//button[text()='Web User Manual ']")
	private WebElement userManual;

	@FindBy(xpath = "//button[text()='Alert Complaince Report ']")
	private WebElement alertComplainceReport;

	// -----------------------------Site DropDown------------------------------//
	@FindBy(xpath = "//input[@id='site-filter']")
	private WebElement siteDropdown;

	public ConsolidateDashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void setoutletList() throws InterruptedException {
		Thread.sleep(2000);
		outletStatus.click();
		Thread.sleep(2000);
		dropdownButton.click();
		Thread.sleep(2000);
		status_All.click();
		Thread.sleep(2000);
		dropdownButton.click();
		Thread.sleep(2000);
		status_Active.click();
		Thread.sleep(2000);
		dropdownButton.click();
		Thread.sleep(2000);
		status_Inactive.click();
		Thread.sleep(2000);
		download_Excel.click();
		Thread.sleep(2000);
		outlet_Close_Btn.click();
	}

	public void setCameraStatus() throws InterruptedException {
		Thread.sleep(2000);
		cameraStatus.click();
		Thread.sleep(2000);
		dropdownButton.click();
		Thread.sleep(2000);
		status_All.click();
		Thread.sleep(2000);
		dropdownButton.click();
		Thread.sleep(2000);
		status_Active.click();
		Thread.sleep(2000);
		dropdownButton.click();
		Thread.sleep(2000);
		status_Inactive.click();
		Thread.sleep(2000);
		download_Excel.click();
		Thread.sleep(2000);
		outlet_Close_Btn.click();
	}

	public void setExportdropdown(String home_Page) throws InterruptedException {
		exportBtn.click();
		userManual.click();
		Thread.sleep(1000);
		alertComplainceReport.click();
	}

	public void setSiteDropdown() {
		siteDropdown.click();
	}

}
