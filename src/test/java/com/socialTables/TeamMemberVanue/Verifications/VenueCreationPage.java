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
		return verifyCreateEventPage.isDisplayed();
	}
	
	public boolean verifyBackgroundPage()
	{
		return verifyBackgroundPage.isDisplayed();
	}
	
	public boolean verifyFloorPlanPage()
	{
		return verifyFloorPlanPage.isDisplayed();
	}
	
}
