package com.cng.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportPage {

	//----------------Click on the Report button in the menu bar----------//
	
	@FindBy(xpath="//clr-icon[@title='Reports']")
	private WebElement click_Report;
	
	//--------------Click on the Date dropdown-------------------/
	
	@FindBy(xpath="//app-dropdown-date-filter[@class='date-drop']/ng-select/div/span")
	private WebElement date_Dropdown;
	
	//--------------Options under the Date dropdown----------------------//
	
	@FindBy(xpath="//div[text()='Daily']")
	private WebElement dropdown_Daily;
	
	@FindBy(xpath="//div[text()='Weekly']")
	private WebElement dropdown_Weekly;
	
	@FindBy(xpath="//div[text()='FortNightly']")
	private WebElement dropdown_FortNightly;
	
	@FindBy(xpath="//div[text()='Monthly']")
	private WebElement dropdown_Monthly;
	
	
	//---------------------Click on the Report drowdown------------------//
	
	@FindBy(id= "report-type")
	private WebElement report_Dropdown;
	
	//--------------------Options under Report dropdown-----------------------//
	
	@FindBy(xpath= "//option[text()=' Pan ']")
	private WebElement dropdown_Pan;
	
	@FindBy(xpath= "//option[text()=' Region ']")
	private WebElement dropdown_Region;
	
	@FindBy(xpath= "//option[text()='  State  ']")
	private WebElement dropdown_State;
	
	@FindBy(xpath= "//option[text()=' Site ']")
	private WebElement dropdown_Site;
	
	//----------------------Generate Button---------------------------------//
	
	@FindBy(xpath="//button[text()=' Generate Report ']")
	private WebElement generate_Report;
	
	public ReportPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void setReport() throws InterruptedException
	{
		click_Report.click();
		date_Dropdown.click();
		dropdown_Daily.click();
		
		report_Dropdown.click();
		dropdown_Pan.click();
		
		generate_Report.click();
		Thread.sleep(10000);
	}
	
	
}
