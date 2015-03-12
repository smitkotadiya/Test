package com.socialTables.events.verifications;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class EventThreeDPage extends AbstractPage
{

	@FindBy(xpath=".//*[@id='leaveBox']/div")
	private WebElement verifyThreeDPage;
	Common common = new Common(driver);
	public EventThreeDPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyThreeDView()
	{
		common.pause(10);
		if(verifyThreeDPage.isDisplayed())
		{
			return true;
		}
		else
		{
			log("<b> It may take too much time to load the resources.</b>");
			return false;
		}
	}
}
