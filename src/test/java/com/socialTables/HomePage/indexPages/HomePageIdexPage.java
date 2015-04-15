package com.socialTables.HomePage.indexPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.HomePage.Verifications.HomeVerificationPage;
import com.socialTables.events.verifications.DashboardPage;
import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class HomePageIdexPage extends AbstractPage
{


	@FindBy(xpath=".//*[@id='login-form']/div/input[@name='email']")
	private WebElement txtEmail;
	@FindBy(xpath=".//*[@id='login-form']/div/input[@name='password']")
	private WebElement txtPassword;
	@FindBy(xpath=".//*[@id='login-form']/div/input[@value='Log In']")
	private WebElement btnLogin;
	@FindBy(xpath=".//*[@id='forgot-password-form']/div/div/div/div/form/div[1]/input")
	private WebElement txtemail;
	@FindBy(xpath=".//*[@id='forgot-password-form']/div/div/div/div/form/div[2]/input")
	private WebElement resetbtn;
	@FindBy(xpath=".//*[@id='inboxfield']")
	private WebElement mailnatortxt;
	@FindBy(xpath=".//*[contains(text(),'Check it')]")
	private WebElement chckbtn;
	@FindBy(xpath=".//*[@id='mailcontainer']/li[1]/a/div[2]")
	private WebElement resetmsg;
	@FindBy(xpath=".//*[@id='reset-password-form']/div[1]/input")
	private WebElement txtpass1;
	@FindBy(xpath=".//*[@id='reset-password-form']/div[2]/input")
	private WebElement txtpass2;
	@FindBy(xpath=".//*[@id='reset-password-form']/div[3]/input")
	private WebElement updatepassbtn;
	@FindBy(id="signup-button")
	private WebElement btnTryNow;
	@FindBy(xpath="//input[contains(@value,'Create Your Free Account')]")
	private WebElement btnCreateAccount;
	
	Common common = new Common(driver);
	public HomePageIdexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public DashboardPage login(String userName,String password)
	{
		if(userName.length()>0)
		{
			common.type(txtEmail, userName);
			common.type(txtPassword, password);
			btnLogin.click();
			common.pause(5);
		}
		else
		{
			btnLogin.click();
			common.pause(5);
		}
		return new DashboardPage(driver);
	}

	public DashboardPage ForgotPassReset(String email)
	 { 
	  if (email.length()>0)
	  {
		   common.pause(2);
		   common.type(txtemail, email);
		   resetbtn.click();
		   common.pause(2);
	  }
	  else
	  {
		   resetbtn.click();
		   common.pause(5);
	  }
	  
	  return new DashboardPage(driver);
	 }
	 
	 public DashboardPage ResetPassCheck(String url,String email)
	 {
		  driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+ "t");
		  driver.get(url);
		  common.pause(2);
		  common.type(mailnatortxt,email);
		  common.pause(2);
		  chckbtn.click();
		  return new DashboardPage(driver);
	 }
	 
	 public DashboardPage ChangePass(String pass)
	 {
	   if(pass.length()>0){
		   common.pause(2);
		   common.type(txtpass1, pass);
		   common.type(txtpass2, pass);
		   common.pause(2);
		   updatepassbtn.click();
	   }
	   else
	   {
		   updatepassbtn.click();
		   common.pause(5);
	   }
	   return new DashboardPage(driver);
	 }
	
	 public HomeVerificationPage clickOnTryNow()
	 {
		 common.pause(2);
		 btnTryNow.click();
		 common.pause(2);
		 return new HomeVerificationPage(driver);
	 }
	 
	 public HomeVerificationPage clickOnCreateFreeAccount()
	 {
		 btnCreateAccount.click();
		 common.pause(2);
		 return new HomeVerificationPage(driver);
	 }

}
