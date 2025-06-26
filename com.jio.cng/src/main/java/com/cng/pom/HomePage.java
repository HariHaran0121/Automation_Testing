package com.cng.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(xpath = "//div[@class='header-actions']")
	private WebElement profilebtn;

	@FindBy(xpath = "//span[text()='Log out']")
	private WebElement lgOutbtn;
	
	@FindBy(id = "userName")
	private WebElement untbx;

	@FindBy(id = "password")
	private WebElement pwtbx;

	@FindBy(xpath = "//button[text()=' Login ']")
	private WebElement lgBtn;
	
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void setLogout() throws InterruptedException {
		Thread.sleep(3000);
		profilebtn.click();
		Thread.sleep(1000);
		lgOutbtn.click();
	}
	

}
