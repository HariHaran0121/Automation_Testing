package com.cng.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssertManagmentPage {
 
	@FindBy(xpath="//clr-icon[@title='Asset Management']")
	private WebElement click_AssertManagment;

	@FindBy(xpath="//clr-icon[@dir='down']")
	private WebElement downArrow;
	
	@FindBy(xpath="(//clr-icon[@dir='right'])[2]")
	private WebElement northSideArrow;
	
	public AssertManagmentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void setAssertManagment() throws InterruptedException
	{
		click_AssertManagment.click();
		downArrow.click();
		Thread.sleep(1000);
		northSideArrow.click();
		northSideArrow.click();
	}
	
	
		
}
