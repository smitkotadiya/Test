package com.socialTables.TeamMemberVanue.IndexPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.TeamMemberVanue.Verifications.TeamMemberAndVenueVerificationPage;
import com.socialTables.TeamMemberVanue.Verifications.VenueCreationPage;
import com.socialTables.general.AbstractPage;
import com.socialTables.general.GeneralVerificationPage;
import com.socialTables.init.Common;

public class TeamMemberAndVenueIndexPage extends AbstractPage
{

	@FindBy(xpath="//a[contains(.,'New Venue')]")
	private WebElement btnNewVenue;
	
	Common common = new Common(driver);
	public TeamMemberAndVenueIndexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public VenueCreationPage clickOnNewVenue()
	{
		btnNewVenue.click();
		common.pause(2);
		return new VenueCreationPage(driver);
	}
	
	
}
