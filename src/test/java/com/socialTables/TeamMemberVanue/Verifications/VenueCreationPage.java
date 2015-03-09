package com.socialTables.TeamMemberVanue.Verifications;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class VenueCreationPage extends AbstractPage
{

	@FindBy(xpath=".//*[@id='left']/div/h1")
	private WebElement verifyCreateEventPage;
	@FindBy(xpath="//h1[contains(.,'Choose Background')]")
	private WebElement verifyBackgroundPage;
	@FindBy(xpath="//h1[contains(.,'Edit 3D Settings')]")
	private WebElement verifyFloorPlanPage;
	
	
	Common common = new Common(driver);
	public VenueCreationPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyVenuePage()
	{
		if(verifyCreateEventPage.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyBackgroundPage()
	{
		if(verifyBackgroundPage.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyFloorPlanPage()
	{
		if(verifyFloorPlanPage.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
