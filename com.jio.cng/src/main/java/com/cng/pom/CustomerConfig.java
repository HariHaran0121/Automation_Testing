package com.cng.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerConfig {
	
	@FindBy(xpath="//clr-icon[@title='Customer Config']")
	private WebElement customer_Config_Page;
	
	@FindBy(xpath="//span[text()='Suppress Alarms']")
	private WebElement supress_Alarm;

	public CustomerConfig(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void customerCongif()
	{
		customer_Config_Page.click();
	}
	
}
