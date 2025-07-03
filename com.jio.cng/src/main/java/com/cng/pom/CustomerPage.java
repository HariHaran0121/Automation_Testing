package com.cng.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerPage {

	Robot robot;
	public WebDriver driver;

	@FindBy(xpath="//clr-icon[@title='Customers']")
	private WebElement select_Customer;

	@FindBy(xpath = "//span[text()='Add Customer']")
	private WebElement addCusbtn;
	
	@FindBy(xpath = "//span[text()='Back']")
	private WebElement backbtn;
	
	@FindBy(id="companyName")
	private WebElement customerName;
	
	@FindBy(xpath="//div[@role='combobox']/input")
	private WebElement otherCus;
	
	@FindBy(xpath="//div[text()='Convenience Store - Small']")
	private WebElement chooseOther;
	
	@FindBy(xpath="//button[text()='OK']")
	private WebElement warningOkBtn;
	
	@FindBy(id="address")
	private WebElement address;
	
	@FindBy(xpath="//label[@for='address1']")
	private WebElement address1;
	
	@FindBy(xpath="//label[@for='address2']")
	private WebElement address2;
	
	@FindBy(xpath="//label[@for='address3']")
	private WebElement address3;
	
	@FindBy(id="selected-addr")
	private WebElement searchbar;
	
	@FindBy(id="city")
	private WebElement city;
	
	@FindBy(id="state")
	private WebElement state;
	
	@FindBy(id="country")
	private WebElement country;
	
	@FindBy(id="postalcode")
	private WebElement postalcode;
	
	@FindBy(xpath="//button[text()=' OK ']")
	private WebElement okbtn;
	
	@FindBy(xpath="//button[text()=' Close ']")
	private WebElement closebtn;
	
	@FindBy(id="companyemail")
	private WebElement customerEmail;
	
	@FindBy(id="contactNumber")
	private WebElement contactNo;
	
	@FindBy(xpath="//label[text()='Notifications Enabled']")
	private WebElement notification_checkbox;
	
	
	
	public CustomerPage(WebDriver driver) {		
		PageFactory.initElements(driver, this);
	}
	
	public void setCustomer() throws InterruptedException
	{
		Thread.sleep(3000);
		select_Customer.click();
	}
	
	
	public void setAddress() throws InterruptedException, AWTException
	{
		address.click();
		Thread.sleep(4000);
		searchbar.sendKeys("Sparkcognition");
		robot = new Robot();
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
//		city.sendKeys("banglaore");
//		state.sendKeys("karnataka");
//		country.sendKeys("India");
//		postalcode.sendKeys("560022");	
		okbtn.click();
		
	}
	
	
	
	public void setAddCustomer() throws InterruptedException, AWTException 
	{
		Thread.sleep(2000);
	addCusbtn.click();
	Thread.sleep(2000);
	customerName.sendKeys("spark12345");
	Thread.sleep(5000);
	otherCus.click();
	Thread.sleep(2000);
	chooseOther.click();
	Thread.sleep(2000);
	warningOkBtn.click();
	
	setAddress();
	Thread.sleep(1000);
	customerEmail.sendKeys("sparkcognition@gmail.com");
	contactNo.sendKeys("123456789");	
	notification_checkbox.click();
	Thread.sleep(2000);
	backbtn.click();
	
	}
	
	
	
}
