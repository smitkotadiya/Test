package com.socialTables.events.verifications;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class DashboardPage extends AbstractPage
{

	@FindBy(xpath=".//*[@id='main-header']/div/div/div[2]/div[1]/a")
	private WebElement verifyDashboardPage;
	
	Common common = new Common(driver);
	
	public DashboardPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyDashboardPage()
	{
		if(verifyDashboardPage.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
