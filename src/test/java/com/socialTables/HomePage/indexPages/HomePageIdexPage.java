package com.socialTables.HomePage.indexPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	Common common = new Common(driver);
	public HomePageIdexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public DashboardPage login(String userName,String password)
	{
		common.type(txtEmail, userName);
		common.type(txtPassword, password);
		btnLogin.click();
		common.pause(5);
		return new DashboardPage(driver);
	}

}
