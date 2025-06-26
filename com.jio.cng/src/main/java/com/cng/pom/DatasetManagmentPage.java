package com.cng.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DatasetManagmentPage {

	@FindBy(xpath="//clr-icon[@title='Dataset Management']")
	private WebElement dataset_Page;
	
	@FindBy(id="class-search")
	private WebElement searchbox;
	
	@FindBy(xpath="//clr-icon[@title='Edit']")
	private WebElement editbtn;
	
	@FindBy(xpath="//button[text()=' Close ']")
	private WebElement closebtn;
	
	@FindBy(xpath="//span[text()='Datasets']")
	private WebElement datasetsbtn;
	
	@FindBy(xpath="//span[text()='Models']")
	private WebElement modelsbtn;
	
	@FindBy(xpath="//span[text()='Server Details']")
	private WebElement serverDetailsbtn;
	
	@FindBy(xpath="//span[text()='Training Jobs']")
	private WebElement trainingJobsbtn;
	
	public DatasetManagmentPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void setDatasetPage() throws InterruptedException
	{
		dataset_Page.click();
		searchbox.sendKeys("helmet");
		editbtn.click();
		Thread.sleep(3000);
		closebtn.click();
		
		datasetsbtn.click();
		modelsbtn.click();
		serverDetailsbtn.click();
		trainingJobsbtn.click();
		
	}
	
}
