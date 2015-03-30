package com.socialTables.TeamMemberVanue.Index;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;


import com.socialTables.init.Common;
import com.socialTables.init.SeleniumInit;

public class TeamMemberAndVenueIndex extends SeleniumInit
{
	public String emailID_Admin = "auto_"+RandomStringUtils.randomAlphabetic(4)+"@mailinator.com";
	public String emailID_LimitedPlanner = "auto_"+RandomStringUtils.randomAlphabetic(4)+"@mailinator.com";
	public String emailID_Planner = "auto_"+RandomStringUtils.randomAlphabetic(4)+"@mailinator.com";
	
	
	@Test
	public void createVenueFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfVenueInList = 0;
		boolean isEditable = false;
		String venueName = "auto-"+RandomStringUtils.randomAlphabetic(3);
		log("<b><ul>Testcase ID: TC_TV_005</b></ul>");
		log("<b><ul>TestScenario: To verify 'Create New Venue' functionality.</b></ul>");
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
		venueCreationPage = teamMemberAndVenueIndexPage.newOrEditVenue(isEditable);
		log("Step 9: Verify venue creation page");
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
		venueCreationPage = teamMemberAndVenueIndexPage.fillVenueDetail(venueName,isEditable);
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
	public void editVenueFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		boolean isEditable = true;
		//int numOfVenueInList = 0;
		String venueName = "auto-edit-"+RandomStringUtils.randomAlphabetic(3);
		log("<b><ul>Testcase ID: TC_TV_006</b></ul>");
		log("<b><ul>TestScenario: To verify 'Edit Venue' functionality.</b></ul>");
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
		//numOfVenueInList = common.getNumOfElements(driver, By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')]/div"));
		log("Step 8: Mouse Hover on any existing venue and click on edit icon ");
		venueCreationPage = teamMemberAndVenueIndexPage.newOrEditVenue(isEditable);
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
		log("Step 10: Edit required fields and click on 'Next' button");
		venueCreationPage = teamMemberAndVenueIndexPage.fillVenueDetail(venueName,isEditable);
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
		log("Step 16: Verify edited venue on 'Team settings and venue page'");
		if(teamMemberAndVenueVerificationPage.verifyEditedVenue(venueName))
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
	public void deleteVenueFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfVenueInList = 0;		
		log("<b><ul>Testcase ID: TC_TV_007</b></ul>");
		log("Step 1: Click on 'login' tab");
		log("<b><ul>TestScenario: To verify 'Delete Venue' functionality.</b></ul>");
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
		log("Step 8: Mouse Hover on any existing venue and click on Delete icon");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.deleteVenue();
		log("Step 9: Verify deleted venue");
		if(teamMemberAndVenueVerificationPage.verifyDeletedVenue(numOfVenueInList))
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
	public void createEventFromVenue()
	{
		Common common = new Common(driver);
		int numOfFailure=0;	
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		int numOfEventsInGrid = 0;
		log("<b><ul>Testcase ID: TC_TV_007</b></ul>");
		log("<b><ul>TestScenario: To verify 'Create event with selected venue' functionality.</b></ul>");
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
		numOfEventsInGrid = common.getNumOfElements(driver, By.xpath(".//*[@id='list-container']/a"));
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
		String venueName = driver.findElement(By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')][1]/div")).getText();
		log("Step 8: Mouse Hover on any existing venue and click on 'New Event' ");
		eventCreationPage = teamMemberAndVenueIndexPage.createEventForVenue();
		log("Step 9: Verify event creation page ");
		if(eventCreationPage.verifyEventCreationPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Enter mandetory fields to create event and Click on 'Done'");
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,true);
		log("Step 9: Navigate to 'Event' Page by clicking on logo");
		dashboardPage = eventIndexPage.clickOnLogo();
		log("Step 10: Verify Added Event with selected venue");
		if(dashboardPage.verifyAddedEventWithSpecificVenue(numOfEventsInGrid, eventName,venueName))
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
	public void searchVenuefunctionality()
	{
	
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_TV_005</b></ul>");
		log("<b><ul>TestScenario: To verify 'Search Venue' functionality.</b></ul>");
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
		String venueSeach = driver.findElement(By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')][1]/div")).getText();
		log("Step 8: Serach any existing venue and click on 'Search' button");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.searchVenue(venueSeach);
		log("Step 9: verify search result");
		if(teamMemberAndVenueVerificationPage.verifySearchVenue(venueSeach))
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
	public void verifyCancelButtonWhileCreateVenue() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_TV_011</b></ul>");
		log("<b><ul>TestScenario: To verify 'Cancel' button functionality in create venue form.</b></ul>");
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
		log("Step 8: Click on 'New Venue' Button");
		venueCreationPage = teamMemberAndVenueIndexPage.newOrEditVenue(false);
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
		log("Step 10: Click on 'Cancel' button.");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.clickOnCancel();
		log("Step 11: Verify 'Team Member and  Venue' page");
		
		if(generalVerificationPage.verifyTeamMemberAndVenuePage())
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
	public void verifyMyAccountButtonWhileCreateVenue() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_TV_011</b></ul>");
		log("<b><ul>TestScenario: To verify 'My Account' button functionality in create venue form.</b></ul>");
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
		log("Step 8: Click on 'New Venue' Button");
		venueCreationPage = teamMemberAndVenueIndexPage.newOrEditVenue(false);
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
		log("Step 10: Click on 'My Account' button.");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.clickOnMyAccount();
		log("Step 11: Verify 'Team Member and  Venue' page");
		
		if(generalVerificationPage.verifyTeamMemberAndVenuePage())
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
	public void verifyBackToVenueDdetailWhileCreateVenue() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		boolean isEditable = false;
		String venueName = "auto-"+RandomStringUtils.randomAlphabetic(3);
		log("<b><ul>Testcase ID: TC_TV_014</b></ul>");
		log("<b><ul>TestScenario: To verify 'My Account' button functionality in create venue form.</b></ul>");
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
		log("Step 8: Click on 'New Venue' Button");
		venueCreationPage = teamMemberAndVenueIndexPage.newOrEditVenue(false);
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
		venueCreationPage = teamMemberAndVenueIndexPage.fillVenueDetail(venueName,isEditable);
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
		log("Step 12: Now click on 'Back to Venue Details' link");
		venueCreationPage = teamMemberAndVenueIndexPage.clickOnBackToVenueDetail();
		log("Step 13: Verify 'Venue Detail Page'");
		if(venueCreationPage.verifyVenuePage())
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
	public void verifyBackToBackgroundDetailFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		boolean isEditable = false;
		String venueName = "auto-"+RandomStringUtils.randomAlphabetic(3);
		log("<b><ul>Testcase ID: TC_TV_005</b></ul>");
		log("<b><ul>TestScenario: To verify 'Create New Venue' functionality.</b></ul>");
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
		log("Step 8: Click on 'New Venue' Button");
		venueCreationPage = teamMemberAndVenueIndexPage.newOrEditVenue(isEditable);
		log("Step 9: Verify venue creation page");
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
		venueCreationPage = teamMemberAndVenueIndexPage.fillVenueDetail(venueName,isEditable);
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
		log("Step 14: Now Click on 'Back to Background Details' Link");
		venueCreationPage = teamMemberAndVenueIndexPage.clickOnBackToBackground();
		log("Step 15: Verify 'Back Ground Detail' Page");
		if(venueCreationPage.verifyBackgroundPage())
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
		//String email= "auto_"+RandomStringUtils.randomAlphabetic(4)+"@mailinator.com";
		String role = "admin";
		String currentWindow = driver.getWindowHandle();
		System.out.println("Current Window Handle>>>>>>>>>"+currentWindow);
		log("<b><ul>Testcase ID: TC_TV_016</b></ul>");
		log("<b><ul>TestScenario: To verify owner/admin should be able to add new member with role 'Admin'</b></ul>");
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
		log("Step 10: Fill required member detail and click on done button");
		log("<b> Member Email: </b>"+ emailID_Admin);
		log("<b> Member Role: </b>"+ role);
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.fillNewMemberDetail(emailID_Admin, role);
		log("Step 11: Verify 'Verification Email' on 'mailinator.com' and click on 'Join Now' Link in mail content.");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.verifyEmail(emailID_Admin);
		log("Step 12: Verify 'Join Team' form page");
		if(teamMemberAndVenueVerificationPage.verifyJoinTeamForm())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 13: Fill the required form detail");
		dashboardPage = teamMemberAndVenueIndexPage.fillJoinForm();
		log("Step 14: Verify 'Dashboard Page'");
		if(dashboardPage.verifyDashboardPage())
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
	public void addMemmber_Planner() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		//String email= "auto_"+RandomStringUtils.randomAlphabetic(4)+"@mailinator.com";
		String role = "planner";
		log("<b><ul>Testcase ID: TC_TV_017</b></ul>");
		log("<b><ul>TestScenario: To verify owner/admin should be able to add new member with role 'Planner'</b></ul>");
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
		log("Step 10: Fill required member detail and click on done button");
		log("<b> Member Email: </b>"+ emailID_Planner);
		log("<b> Member Role: </b>"+ role);
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.fillNewMemberDetail(emailID_Planner, role);
		log("Step 11: Verify 'Verification Email' on 'mailinator.com' and click on 'Join Now' Link in mail content.");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.verifyEmail(emailID_Planner);
		log("Step 12: Verify 'Join Team' form page");
		if(teamMemberAndVenueVerificationPage.verifyJoinTeamForm())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 13: Fill the required form detail");
		dashboardPage = teamMemberAndVenueIndexPage.fillJoinForm();
		log("Step 14: Verify 'Dashboard Page'");
		if(dashboardPage.verifyDashboardPage())
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
	public void addMemmber_LimitedPlanner() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		//String email= "auto_"+RandomStringUtils.randomAlphabetic(4)+"@mailinator.com";
		String role = "limited planner";
		log("<b><ul>Testcase ID: TC_TV_018</b></ul>");
		log("<b><ul>TestScenario: To verify owner/admin should be able to add new member with role 'Limited Planner'</b></ul>");
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
		log("Step 10: Fill required member detail and click on done button");
		log("<b> Member Email: </b>"+ emailID_LimitedPlanner);
		log("<b> Member Role: </b>"+ role);
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.fillNewMemberDetail(emailID_LimitedPlanner, role);
		log("Step 11: Verify 'Verification Email' on 'mailinator.com' and click on 'Join Now' Link in mail content.");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.verifyEmail(emailID_LimitedPlanner);
		log("Step 12: V`erify 'Join Team' form page");
		if(teamMemberAndVenueVerificationPage.verifyJoinTeamForm())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 13: Fill the required form detail");
		dashboardPage = teamMemberAndVenueIndexPage.fillJoinForm();
		log("Step 14: Verify 'Dashboard Page'");
		if(dashboardPage.verifyDashboardPage())
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
	public void verifyResendEmailFeature() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		//String email= "auto_"+RandomStringUtils.randomAlphabetic(4)+"@mailinator.com";
		String role = "admin";
		String currentWindow = driver.getWindowHandle();
		System.out.println("Current Window Handle>>>>>>>>>"+currentWindow);
		log("<b><ul>Testcase ID: TC_TV_016</b></ul>");
		log("<b><ul>TestScenario: To verify owner/admin should be able to add new member with role 'Admin'</b></ul>");
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
		log("Step 10: Fill required member detail and click on done button");
		log("<b> Member Email: </b>"+ emailID_Admin);
		log("<b> Member Role: </b>"+ role);
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.fillNewMemberDetail(emailID_Admin, role);
		log("Step 11: Verify 'Verification Email' on 'mailinator.com'.");
		if(teamMemberAndVenueVerificationPage.verifyInvitationEmail(emailID_Admin))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		driver.findElement(By.xpath("//body")).sendKeys(Keys.COMMAND+"1");
		common.pause(2);
		log("Step 12: Now click on 'Resend Email' button");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.clickOnResendEmail();
		log("Step 13: Verify email on mailinator");
		driver.findElement(By.xpath("//body")).sendKeys(Keys.COMMAND+"2");
		driver.navigate().refresh();
		if(teamMemberAndVenueVerificationPage.verifyResendEmailFunctionality())
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
	public void removeExistingMember()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfMemberBefore = 0;
		log("<b><ul>Testcase ID: TC_TV_018</b></ul>");
		log("<b><ul>TestScenario: To verify owner/admin should be able to 'Remove' exiting member</b></ul>");
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
		By by = By.xpath(".//*[@id='members-table']/tbody/tr/td[1]");
		numOfMemberBefore = common.getNumOfElements(driver, by);
		log("Step 8: Now click on remove button of any existing member");
		log("Step 9: Click on 'Ok' button in aleart");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.removeMember();
		log("Step 10: Verify deleted record on member list");
		if(teamMemberAndVenueVerificationPage.verifyDeleteMember(numOfMemberBefore))
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
	public void verifyCancelButtonFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String currentWindow = driver.getWindowHandle();
		System.out.println("Current Window Handle>>>>>>>>>"+currentWindow);
		log("<b><ul>Testcase ID: TC_TV_021</b></ul>");
		log("<b><ul>TestScenario: To verify 'cancel' functionality in Add member</b></ul>");
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
		log("Step 10: Click on 'Cancel' button");
		teamMemberAndVenueVerificationPage = teamMemberAndVenueIndexPage.clickOnCancel();
		log("Step 11: Verify added new member fields are disappear from grid");
		if(teamMemberAndVenueVerificationPage.verifyCancelButtonInMember())
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
	public void verifyAdminNotAbleToDrawVenue() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String currentWindow = driver.getWindowHandle();
		System.out.println("Current Window Handle>>>>>>>>>"+currentWindow);
		log("<b><ul>Testcase ID: TC_TV_026</b></ul>");
		log("<b><ul>TestScenario: To verify 'Admin' not able to 'Draw venue'</b></ul>");
		log("Step 1: Click on 'login' tab [Login As Admin].");
		generalIndexPage.clickOnLoginTab();
		log("Step 2: Enter User Name");
		log("Step 3: Enter Password");
		log("<strong>User Name: </strong>"+userName_Admin);
		log("<strong>Password: </strong>"+password_Owner);
		log("Step 4: Click On 'Login' Button");
		dashboardPage = homePageIndexPage.login(userName_Admin, password_Owner);
		log("Step 5: Verify Admin logged in successfully");
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
		log("Step 8: Now verify 'Draw Venue' button should not appeare.");
		if(teamMemberAndVenueVerificationPage.verifyDrawVenueNotDisplay())
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
	public void verifyPlannerAccessTabs() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_TV_029</b></ul>");
		log("<b><ul>TestScenario: To verify 'Planner' should not able to view 'Team Member' tab</b></ul>");
		log("Step 1: Click on 'login' tab [Login As Planner].");
		generalIndexPage.clickOnLoginTab();
		log("Step 2: Enter User Name");
		log("Step 3: Enter Password");
		log("<strong>User Name: </strong>"+userName_Planner);
		log("<strong>Password: </strong>"+password_Owner);
		log("Step 4: Click On 'Login' Button");
		dashboardPage = homePageIndexPage.login(userName_Planner, password_Owner);
		log("Step 5: Verify Planner logged in successfully");
		if(dashboardPage.verifyDashboardPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 6: Verify 'Team Member' tab should not appear");
		if(teamMemberAndVenueVerificationPage.verifyPlannerAccess())
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
	public void verifyLimitedPlannerAccessTabs() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_TV_028</b></ul>");
		log("<b><ul>TestScenario: To verify 'Limited Planner' should not able to view 'Team Member' tab</b></ul>");
		log("Step 1: Click on 'login' tab [Login As Planner].");
		generalIndexPage.clickOnLoginTab();
		log("Step 2: Enter User Name");
		log("Step 3: Enter Password");
		log("<strong>User Name: </strong>"+userName_LPlanner);
		log("<strong>Password: </strong>"+password_Owner);
		log("Step 4: Click On 'Login' Button");
		dashboardPage = homePageIndexPage.login(userName_LPlanner, password_Owner);
		log("Step 5: Verify Limited Planner logged in successfully");
		if(dashboardPage.verifyDashboardPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 6: Verify 'Team Member' tab should not appear");
		if(teamMemberAndVenueVerificationPage.verifyPlannerAccess())
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
