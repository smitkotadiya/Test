package com.socialTables.TeamMemberVanue.Index;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.socialTables.TeamMemberVanue.Verifications.VenueCreationPage;
import com.socialTables.init.Common;
import com.socialTables.init.SeleniumInit;

public class TeamMemberAndVenueIndex extends SeleniumInit
{
	@Test
	public void createVenueFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfVenueInList = 0;
		String venueName = "auto-"+RandomStringUtils.randomAlphabetic(3);
		log("<b><ul>Testcase ID: TC_HP_002</b></ul>");
		log("Step 1: Click on 'login' tab");
		generalIndexPage.clickOnLoginTab();
		log("Step 2: Enter User Name");
		log("Step 3: Enter Password");
		log("<strong>User Name: </strong>"+userName_Owner);
		log("<strong>Password: </strong>"+password_Owner);
		log("Step 4: Click On 'Login' Button");
		dashboardPage = homePageIndexPage.login(userName_Owner, password_Owner);
		log("Step 5: Verify user logged in successfully");
		if(dashboardPage.verifyDashboardPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 6:Navigate to 'Team Member and Venue' Module");
		generalVerificationPage = generalIndexPage.navigateToTeamMemberAndVenue();
		log("Step 7:Verify 'Team Member and Venue' page");
		if(generalVerificationPage.verifyTeamMemberAndVenuePage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		numOfVenueInList = common.getNumOfElements(driver, By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')]/div"));
		log("Step 8: Click on 'New Venue' Button");
		venueCreationPage = teamMemberAndVenueIndexPage.clickOnNewVenue();
		log("Step 9: Verify event creation page");
		if(venueCreationPage.verifyVenuePage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Fill required fields to crate venue and click on 'Next' button");
		venueCreationPage = teamMemberAndVenueIndexPage.fillVenueDetail(venueName);
		log("Step 11: Verify 'Back Ground' Selection Page");
		if(venueCreationPage.verifyBackgroundPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 12: Select any existing background and click on Next");
		venueCreationPage = teamMemberAndVenueIndexPage.selectAnyBackgroud();
		log("Step 13: Verify 'Floor Plan' selection page");
		if(venueCreationPage.verifyFloorPlanPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 14: Select any existing 'Floor Plan' and click on 'Done' button");
		log("Step 15: Click on 'Ok' button");
		venueCreationPage = teamMemberAndVenueIndexPage.selectAnyWallTexture();
		if(teamMemberAndVenueVerificationPage.verifyAddedVenue(numOfVenueInList, venueName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		if(numOfFailure>0)
		{
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void addMemmber_Admin() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_TV_016</b></ul>");
		log("Step 1: Click on 'login' tab");
		generalIndexPage.clickOnLoginTab();
		log("Step 2: Enter User Name");
		log("Step 3: Enter Password");
		log("<strong>User Name: </strong>"+userName_Owner);
		log("<strong>Password: </strong>"+password_Owner);
		log("Step 4: Click On 'Login' Button");
		dashboardPage = homePageIndexPage.login(userName_Owner, password_Owner);
		log("Step 5: Verify user logged in successfully");
		if(dashboardPage.verifyDashboardPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 6:Navigate to 'Team Member and Venue' Module");
		generalVerificationPage = generalIndexPage.navigateToTeamMemberAndVenue();
		log("Step 7:Verify 'Team Member and Venue' page");
		if(generalVerificationPage.verifyTeamMemberAndVenuePage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'New Member' Button");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.clickOnNewMember();
		log("Step 9: Verify 'Add Member' fields : Email and Role ");
		if(teamMemberAndVenueVerificationPage.verifyAddMemberFields())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		
		
		if(numOfFailure>0)
		{
			Assert.assertTrue(false);
		}
	}

	
}
