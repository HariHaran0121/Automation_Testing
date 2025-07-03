package com.cng.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SitePage {
	
	Robot robot;
	
	@FindBy(xpath ="//clr-icon[@title='Sites']")
	private WebElement sitespage;

	@FindBy(xpath ="//span[text()=' Add Site ']/../../button") 
	private WebElement addSite;
	
	@FindBy(id = "SiteName")
	private WebElement addSiteName;
	
	@FindBy(id="selected-addr")
	private WebElement searchbar;
	
	@FindBy(xpath="(//clr-dg-row[@role='rowgroup']/div[1]/div[2]/div[1]/clr-dg-cell[2])")
	private WebElement chooseSite;
	
	@FindBy(xpath="//button[text()=' Save ']")
	private WebElement savebtn;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement canclebtn;
	
	@FindBy(xpath="//button[text()='OK']")
	private WebElement okbtn;
	
	public SitePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	
	public void setSitePages(String sitename, String searchSite) throws InterruptedException, AWTException
	{
		sitespage.click();
		addSite.click();
		addSiteName.sendKeys(sitename);
		Thread.sleep(3000);
		searchbar.sendKeys(searchSite);
		robot = new Robot();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		//savebtn.click();
		canclebtn.click();
		//okbtn.click();
		
	}
	
	
	
	
	public String findSiteName(String site_name) throws InterruptedException 
	{
		Thread.sleep(2000);
		String site_check = chooseSite.getText();
		return site_check;
	}
	
	
	
}
	