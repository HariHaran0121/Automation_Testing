package com.cng.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage {

	@FindBy(xpath ="//clr-icon[@title='Users']") 
	private WebElement userspage;

	@FindBy(xpath ="//span[text()='Add User']/../../button")    
	private WebElement addUserBtn;

	@FindBy(id = "user-name")
	private WebElement addUserName;

	@FindBy(id = "user-email")
	private WebElement addUserEmail;

	@FindBy(id = "first-name")
	private WebElement addFirstName;

	@FindBy(id = "last-name")
	private WebElement addLastName;

	@FindBy(id = "contact-number")
	private WebElement addContactNumber;

	@FindBy(id = "user-position")
	private WebElement userPos;

	@FindBy(xpath = "//div[text()='Analyst']")
	private WebElement choosePos;

	@FindBy(id = "user-role")
	private WebElement userRole;

	@FindBy(id="Password")
	private WebElement pass;
	
	@FindBy(id="confirm-password")
	private WebElement confirmPass;
	
	@FindBy(xpath = "//div[text()='ADMIN']")
	private WebElement admin_Role;

	@FindBy(xpath = "//div[text()='FIELD_USER']")
	private WebElement field_Role;

	@FindBy(id = "user-status")
	private WebElement userStatus;

	@FindBy(xpath = "//div[text()='Active']")
	private WebElement chooseStatus;

	@FindBy(id="feature-list") //label[text()=' Notification Channel ']
	private WebElement notiChannle;
	
	@FindBy(xpath="//span[@class='ng-arrow']")
	private WebElement close_wrap;

	@FindBy(xpath = "//span[text()='WHATSAPP']")
	private WebElement selectChannel;
	
	@FindBy(id="Site-Id")
	private WebElement siteId;
	
	@FindBy(xpath="//span[text()='Port Quasim']")
	private WebElement clickSite;
	
	@FindBy(id="Primary-Site")
	private WebElement primarySite;
	
	@FindBy(xpath="//div[@role='option']")     
	private WebElement primary_Site;
	
	@FindBy(xpath="//button[text()=' Save ']")
	private WebElement savebtn;

	@FindBy(xpath = "//button[text()=' Cancel ']")
	private WebElement canclebtn;
	
	@FindBy(xpath="(//clr-dg-row[@role='rowgroup'])[1]/div[1]/div[2]/div[1]/clr-dg-cell[2]/div[3]")
	private WebElement verify_user_email;
	
	@FindBy(xpath="//clr-dg-row[@role='rowgroup']/div/div[2]/div/clr-dg-cell[10]/button[1]")
	private WebElement edit_User;
	
	@FindBy(xpath="//button[text()=' Close ']")
	private WebElement close_btn;
	
	@FindBy(id="newPassword")
	private WebElement new_Password;
	
	@FindBy(id="ConfirmPassword")
	private WebElement confirm_New_Password;
	
	
	@FindBy(xpath="//clr-dg-row[@role='rowgroup']/div/div[2]/div/clr-dg-cell[10]/button[2]")
	private WebElement user_Details;
	
	@FindBy(xpath="//clr-dg-row[@role='rowgroup']/div/div[2]/div/clr-dg-cell[10]/button[3]")
	private WebElement edit_Password;
	
	
	@FindBy(xpath="//span[text()=' User with User Name already exists, please use a different Username ']")
	private WebElement userNameWarning;
	

	@FindBy(xpath="//span[text()=' User with email already exists, please use a different Email ']")
	private WebElement userEmailWarning;

	
	@FindBy(xpath="(//clr-dg-row[@role='rowgroup'])[1]/div[1]/div[2]/div[1]/clr-dg-cell[9]/button[1]")	
	private WebElement delete_user;
	

	public UserPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	public void setAddUsers(String user_name, String email,String first_name,String last_name,
			String contact_no,String password,
			String confirm_pass,String role,String status,String notification,String 
			site,String emp_id) throws InterruptedException {
		Thread.sleep(2000);
		userspage.click();
		Thread.sleep(7000);
		addUserBtn.click();
		addUserName.sendKeys(user_name);
		addUserEmail.sendKeys(email);
		addFirstName.sendKeys(first_name);
		addLastName.sendKeys(last_name);
		addContactNumber.sendKeys(contact_no);
		userPos.click();
		choosePos.click();
		pass.sendKeys(password);
		confirmPass.sendKeys(confirm_pass);
		userRole.click();
		admin_Role.click();
		Thread.sleep(1000);
		userStatus.click();
		chooseStatus.click();
		Thread.sleep(1000);
		notiChannle.click();
		selectChannel.click();
		Thread.sleep(1000);
//		close_wrap.click();
//		Thread.sleep(3000);
//		siteId.click();
//		clickSite.click();
		//Thread.sleep(3000);	////span[text()='User Sessions']/../../../a[12]
		//primarySite.click();
		//primary_Site.click();
		//close_wrap.click();
		
		canclebtn.click();
		//use savebtn to save 
	}
	
	public String getVerifiedUser()
	{
		return verify_user_email.getText();
	}
	
	
	public void editUser(String user_name, String email,String first_name,String last_name,
			String contact_no,String password,
			String confirm_pass,String role,String status,String notification,String 
			site,String emp_id) throws InterruptedException
	{
		
		Thread.sleep(2000);
		userspage.click();
		Thread.sleep(7000);
		edit_User.click();
		addUserName.clear();
		addUserName.sendKeys(user_name);
		addFirstName.sendKeys(first_name);
		addLastName.sendKeys(last_name);
		addContactNumber.sendKeys(contact_no);
		userPos.click();
		choosePos.click();
		userRole.click();
		admin_Role.click();
		Thread.sleep(1000);
		userStatus.click();
		chooseStatus.click();
		Thread.sleep(1000);
		notiChannle.click();
		selectChannel.click();
		Thread.sleep(1000);
//		close_wrap.click();
//			
		canclebtn.click();
		//use savebtn to save 
	}
	
	public void userDetails() throws InterruptedException
	{
		Thread.sleep(2000);
		userspage.click();
		Thread.sleep(3000);
		user_Details.click();
		close_btn.click();
	}
	
	public void editPassword() throws InterruptedException
	{
		Thread.sleep(2000);
		userspage.click();
		Thread.sleep(3000);
		edit_Password.click();
		new_Password.sendKeys("Hari@123");
		confirm_New_Password.sendKeys("Hari@123");
		close_btn.click();
	}
	
	public String deleteUser()
	{
		delete_user.click();
		return verify_user_email.getText();
		
	}
	

	public void validateDuplicateData(String user_name, String email) throws InterruptedException
	{
		userspage.click();
		Thread.sleep(7000);
		addUserBtn.click();
		Thread.sleep(3000);
		addUserName.sendKeys(user_name);
		Thread.sleep(3000);		
		addUserEmail.sendKeys(email);
		Thread.sleep(3000);
		userNameWarning.isDisplayed();
		addFirstName.click();
		userEmailWarning.isDisplayed();
		canclebtn.click();
	}
	
}

