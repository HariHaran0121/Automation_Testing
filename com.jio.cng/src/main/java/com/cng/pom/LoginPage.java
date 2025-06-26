package com.cng.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	@FindBy(xpath = "//div[@class='header-actions']")
	private WebElement profilebtn;

	@FindBy(xpath = "//span[text()='Log out']")
	private WebElement lgOutbtn;
	
	@FindBy(xpath = "//button[text()='OK']")
	private WebElement logutOkBtn;
	
	@FindBy(className = "alert-text")
	private WebElement checkErrorMessage;

	@FindBy(id = "userName")
	private WebElement untbx;

	@FindBy(id = "password")
	private WebElement pwtbx;

	@FindBy(xpath = "//button[text()=' Login ']")
	private WebElement lgBtn;

	@FindBy(id = "customer-search")
	private WebElement search_Customer;

	@FindBy(xpath = "//div[@class='cust-list']/div[1]")
	private WebElement cus_Choice;

	@FindBy(xpath = "//button[text()=' OK ']")
	private WebElement cusOkBtn;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	public void setLogout() throws InterruptedException {
		Thread.sleep(3000);
		profilebtn.click();
		Thread.sleep(1000);
		lgOutbtn.click();
		Thread.sleep(1000);
		logutOkBtn.click();
	}
	
	public void setLogin(String un, String pw, String customer) throws InterruptedException {
		untbx.sendKeys(un);
		pwtbx.sendKeys(pw);
		lgBtn.click();
		search_Customer.sendKeys(customer);
		cus_Choice.click();
		Thread.sleep(1000);
		cusOkBtn.click();
	}
	
	
	public String setCheckWarning() throws InterruptedException
	{
		Thread.sleep(3000);
		untbx.sendKeys("1isndadyi");
		pwtbx.sendKeys("asasdsd");
		lgBtn.click();
		return checkErrorMessage.getText();
	}
}
