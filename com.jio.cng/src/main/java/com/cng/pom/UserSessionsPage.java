package com.cng.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserSessionsPage {

	Robot robot;

	@FindBy(xpath="//clr-icon[@title='User Sessions']")
	private WebElement select_UserSession;
	
	@FindBy(xpath="//label[text()='Last 5 Minutes']")
	private WebElement five_Minutes;
	
	@FindBy(xpath="//label[text()='Last 1 Hour']")
	private WebElement one_Hour;
	
	@FindBy(xpath="//label[text()='Today']")
	private WebElement today;
	
	@FindBy(xpath="//label[text()='Last 2 Days']")
	private WebElement two_days;
	
	@FindBy(xpath="//label[text()='1 Week']")
	private WebElement one_Week;
	
	
	
	@FindBy(id="user-filter")
	private WebElement userFilter;
	
	public UserSessionsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void setUserSession() throws InterruptedException, AWTException
	{
		select_UserSession.click();
		Thread.sleep(1000);
		five_Minutes.click();
		one_Hour.click();
		today.click();
		two_days.click();
		Thread.sleep(1000);
		one_Week.click();
		Thread.sleep(4000);
		userFilter.sendKeys("Pavan");
		robot = new Robot();
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(500);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(500);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
	}
	
	
}
