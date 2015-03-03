package com.socialTables.HomePage.Verifications;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class HomeVerificationPage extends AbstractPage
{
	@FindBy(xpath="//div[@class='contact-form']/div[contains(.,'Sorry, we could not find any account with that email in our         records. Please try again.')]")
	private WebElement verifyInvalidLoginMsg;
	

	Common common = new Common(driver);
	
	public HomeVerificationPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyInvalidLogin()
	{
		if(verifyInvalidLoginMsg.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
