package com.socialTables.events.Index;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.Select;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.socialTables.general.GeneralVerificationPage;
import com.socialTables.init.Common;
import com.socialTables.init.SeleniumInit;

public class EventIndex extends SeleniumInit
{

	@Test
	public void createEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "Gala"; 
		int numOfEventsInGrid = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_001</b></ul>");
		log("<b><ul>TestScenario: To verify create 'New Event' functionality.</b></ul>");
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
		numOfEventsInGrid = common.getNumOfElements(driver, By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]"));
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Navigate to 'Event' Page");
		dashboardPage = eventIndexPage.clickOnLogo();
		log("Step 12: Verify Added Event");
		if(dashboardPage.verifyAddedEvent(numOfEventsInGrid, eventName))
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
	public void viewEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "";
		log("<b><ul>Testcase ID: TC_EV_002</b></ul>");
		log("<b><ul>TestScenario: To verify 'View Event' functionality.</b></ul>");
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
		List<WebElement> allEles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		eventName = allEles.get(0).getText();
		System.out.println("========"+eventName);
		log("Step 6: Click on any existing event");
		eventCreationPage = eventIndexPage.clickOnEvent();
		//try{
			log("Step 7: Verify Event Creation Page");
			if(eventCreationPage.verifyEventNameHeader(eventName))
			{
				log("<Strong><font color=#008000>Pass</font></strong>");
			}
			else
			{
				log("Fail");
				numOfFailure++;
			}
		//}
		//catch(Exception e)
		//{
			//log("There may be problem to verify event creation page/page loading issue");
		//}
			
		if(numOfFailure>0)
		{
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void deleteEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfEventsInGrid = 0;
		String eventName = "";
		log("<b><ul>Testcase ID: TC_EV_009</b></ul>");
		log("<b><ul>TestScenario: To verify 'Delete Event' functionality.</b></ul>");
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
		List<WebElement> allEles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		eventName = allEles.get(0).getText();
		numOfEventsInGrid = common.getNumOfElements(driver, By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		log("Step 6: Try to delete any existing event");
		dashboardPage = eventIndexPage.clickOnDeleteEvent();
		try{
			log("Step 7: Verify deleted event is not display on grid");
			if(dashboardPage.verifyDeletedEvent(numOfEventsInGrid, eventName))
			{
				log("<Strong><font color=#008000>Pass</font></strong>");
			}
			else
			{
				log("Fail");
				numOfFailure++;
			}
		}
		catch(Exception e)
		{
			log("There may be problem to verify delete event or page loading issue");
		}
		
		
		if(numOfFailure>0)
		{
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void upCommingEventFunctionality() throws ParseException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EV_003</b></ul>");
		log("<b><ul>TestScenario: To verify 'Upcoming Event' functionality.</b></ul>");
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
		log("Step 6: Click on upcomming event");
		//dashboardPage = eventIndexPage.clickOnUpcomingEvent();
		log("Step 7: Verify all record in grid");
		if(dashboardPage.verifyUpcomingEvents())
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
	public void previousEventFunctionality() throws ParseException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EV_004</b></ul>");
		log("<b><ul>TestScenario: To verify 'Previous Event' functionality.</b></ul>");
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
		log("Step 6: Click on previous event");
		dashboardPage = eventIndexPage.clickOnPreviousEvent();
		log("Step 7: Verify all record in grid");
		if(dashboardPage.verifyPreviousEvents())
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
	public void eventCounterFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		int numOfEventsInLableCount = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_005</b></ul>");
		log("<b><ul>TestScenario: To verify 'Event Counter' label functionality.</b></ul>");
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
		String numOfEventInLable =driver.findElement(By.xpath("//span[@class='events-results-text']/span[1]")).getText();
		numOfEventsInLableCount = Integer.parseInt(numOfEventInLable);
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Navigate to 'Event' Page");
		dashboardPage = eventIndexPage.clickOnLogo();
		log("Step 12: Verify Added Event and event counter increase by one");
		if(dashboardPage.verifyEventCounterFunctionlaity("Add", numOfEventsInLableCount))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		numOfEventInLable =driver.findElement(By.xpath("//span[@class='events-results-text']/span[1]")).getText();
		numOfEventsInLableCount = Integer.parseInt(numOfEventInLable);
		log("Step 13:Now, Try to delete any existing event");
		dashboardPage = eventIndexPage.clickOnDeleteEvent();
		try
		{
			log("Step 14: Verify deleted event is not display on grid and event counter decrese by one");
			if(dashboardPage.verifyEventCounterFunctionlaity("Del", numOfEventsInLableCount))
			{
				log("<Strong><font color=#008000>Pass</font></strong>");
			}
			else
			{
				log("Fail");
				numOfFailure++;
			}
		}
		catch(Exception e)
		{
			log("There may be problem to verify delete event or page loading issue");
		}
		if(numOfFailure>0)
		{
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void eventFiltrationFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String authorName = "";
		String categoryName = "";
		String locationName="";
		 
		log("<b><ul>Testcase ID: TC_EV_007</b></ul>");
		log("<b><ul>TestScenario: To verify all event 'Filtration' functionality.</b></ul>");
		log("Step 1: Click on 'login' tab");
		generalIndexPage.clickOnLoginTab();
		log("Step 2: Enter User Name");
		log("Step 3: Enter Password");
		log("<strong>User Name: </strong>"+userName_Owner);
		log("<strong>Password: </strong>"+password_Owner);
		log("Step 4: Click On 'Login' Button.");
		dashboardPage = homePageIndexPage.login(userName_Owner, password_Owner);
		log("Step 5: Verify user logged in successfully.");
		if(dashboardPage.verifyDashboardPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		By By_Author = By.xpath("//div[contains(@class,'events-table-dropdown-owner')]/div/div");
		By By_Category = By.xpath("//div[contains(@class,'events-table-dropdown-category')]/div/div");
		By By_Location = By.xpath("//div[contains(@class,'events-table-dropdown-location')]/div/div");
		authorName = generalIndexPage.getRandomFilterationStringInEventPage(By_Author);
		log("Step 6: Select any author from combo box. ");
		dashboardPage = eventIndexPage.filteration("Author", authorName);
		log("Step 7: Verify filteration By Author.");
		if(dashboardPage.verifyFilterationByAuthor(authorName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		common.refresh(driver);
		categoryName = generalIndexPage.getRandomFilterationStringInEventPage(By_Category);
		
		log("Step 8: Select any category from combo box. ");
		dashboardPage = eventIndexPage.filteration("Category", categoryName);
		log("Step 9: Verify filteration By Category.");
		if(dashboardPage.verifyFilterationByCategory(categoryName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		common.refresh(driver);
		locationName = generalIndexPage.getRandomFilterationStringInEventPage(By_Location);
		log("Step 10: Select any location from combo box. ");
		dashboardPage = eventIndexPage.filteration("location", locationName);
		log("Step 11: Verify filteration By location.");
		if(dashboardPage.verifyFilterationByLocation(locationName))
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
	public void searchEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EV_006</b></ul>");
		log("<b><ul>TestScenario: To verify 'Search Event' functionality.</b></ul>");
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
		List<WebElement> allEles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		String searchString = allEles.get(0).getText();
		log("Step 6: Search any existing event");
		dashboardPage = eventIndexPage.searchEvent(searchString);
		log("Step 7: Verify Ssearch result");
		if(dashboardPage.verifySearchFunctionality(searchString))
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
	public void cloneEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfEventsInGrid = 0;
		String eventName = "Auto-clone";
		log("<b><ul>Testcase ID: TC_EV_009</b></ul>");
		log("<b><ul>TestScenario: To verify 'Clone Event' functionality.</b></ul>");
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
		numOfEventsInGrid = common.getNumOfElements(driver, By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		log("Step 6: Click on clone icone of any existing event");
		eventCreationPage = eventIndexPage.clickOnCloneIcone();
		log("Step 7: Verify event creation page");
		if(eventCreationPage.verifyLogoInEventCreationPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Navigate to dashboard page by clicking on logo");
		dashboardPage = eventIndexPage.clickOnLogo();
		log("Step 9: Verify Dashboard Page and added event");
		if(dashboardPage.verifyAddedEvent(numOfEventsInGrid, eventName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}else
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
	public void viewThreeDEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EV_010</b></ul>");
		log("<b><ul>TestScenario: To verify 'View 3D' functionality.</b></ul>");
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
		log("Step 6: Click on 'View 3D Icone' of any existing event");
		eventThreeDPage = eventIndexPage.clickOnViewThreeDIcone();
		log("Step 7: Verify event '3D View' Page");
		if(eventThreeDPage.verifyThreeDView())
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
	public void SortingFunctionalityInEvent()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EV_011</b></ul>");
		log("<b><ul>TestScenario: To verify 'Sorting' Functionality of event in all available column of grid.</b></ul>");
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
		 ArrayList<String> previousList=generalIndexPage.getAllRecordsOfColumn("name");
		 generalIndexPage.getAllRecordsInAscending(previousList);
		 log("Step 6:Perform Sorting in Name column in event grid ");
		 generalIndexPage.performSorting(1);
		 ArrayList<String> currentList=generalIndexPage.getAllRecordsOfColumn("name");
		 if(generalVerificationPage.verifySorting(previousList, currentList))
		  {
			 log("<br><Strong><font color=#008000>Pass</font></strong>");
		  }
		  else
		  {
			  log("<br></br> Fail");
			  numOfFailure++;
		  }
		 
		 if(numOfFailure>0)
		 {
			 Assert.assertTrue(false);
		 }
	}
	
	@Test
	public void dashBoardTutorialLinkVerification()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EV_012</b></ul>");
		log("<b><ul>TestScenario: To verify 'Dashboard tutorial' link</b></ul>");
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
		log("Step 6: Click on 'Dashboard Tutorial' link");
		dashboardPage = eventIndexPage.clickOnDashboardTutorial();
		log("Step 7: Verift tutorial video appear on screen");
		if(dashboardPage.verifyDashboardTutorial())
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
	public void verifyAllHeaderLinks()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EV_006</b></ul>");
		log("<b><ul>TestScenario: To verify all header links on dashboard page.</b></ul>");
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
		log("Step 6: Click on 'Team Member and Venue' tab");
		generalVerificationPage = generalIndexPage.navigateToTeamMemberAndVenue();
		log("Step 7: Verify 'Team Member and venue' page");
		if(generalVerificationPage.verifyTeamMemberAndVenuePage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'Team Setting' tab");
		generalVerificationPage = generalIndexPage.navigateToTeamSettings();
		log("Step 9: Verify 'Team Settings' page");
		if(generalVerificationPage.verifyTeamSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Click on 'Statistic' tab");
		generalVerificationPage = generalIndexPage.navigateToStatistics();
		log("Step 11: Verify 'Statistic' page");
		if(generalVerificationPage.verifyStatistics())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else 
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 12: Click on 'Corporate Dashboard' tab");
		generalVerificationPage = generalIndexPage.navigateToStatistics();
		log("Step 13: Verify 'Corporate Dashboard' page");
		if(generalVerificationPage.verifyStatistics())
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
	public void verifyEventSettingsButton()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_037</b></ul>");
		log("<b><ul>TestScenario: To verify 'Event Settings' button functionality</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now Click on 'Event Settings' Button");
		eventCreationPage = eventIndexPage.clickOnEventSettings();
		log("Step 12: Verify event settings page");
		if(eventCreationPage.verifyEventCreationPage())
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
	public void createEventWithAttendeeListEnable()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-AttendeeEnable-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		int numOfEventsInGrid = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_015</b></ul>");
		log("<b><ul>TestScenario: To verify 'Guest List Enable' functionality in 'Event Information'.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now verify 'Attendee List' tab display on event creation page ");
		if(eventCreationPage.verifyAttendeeListEnable())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 12: Navigate to 'Event' Page");
		dashboardPage = eventIndexPage.clickOnLogo();
		log("Step 13: Verify Added Event with attendee list enable");
		if(dashboardPage.verifyAddedEvent(numOfEventsInGrid, eventName))
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
	public void verifySearchRoomFunctionalityWhileCreateEvent()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		int numOfEventsInGrid = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_001</b></ul>");
		log("<b><ul>TestScenario: To verify 'Search Venue' functionality in 'Room Settings' while create event.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		String serachVenue = driver.findElement(By.xpath("//div[@class='team-venues-column']/div[@class='listings-container']/div[contains(@class,'floorplan')][1]")).getText();
		log("Step 10: Serach any existing room available in room setting page");
		eventCreationPage = eventIndexPage.searchRoomInEvent(serachVenue);
		log("Step 11: Verify serch result in list");
		if(eventCreationPage.verifySearchedRoom(serachVenue))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 12: Now select searched room and click on done button");
		dashboardPage = eventIndexPage.clickOnSearchedRoom();
		log("Step 13: Navigate to event page by clicking on logo");
		dashboardPage = eventIndexPage.clickOnLogo();
		log("Step 14: Verify created event with selected venue");
		if(dashboardPage.verifyAddedEventWithSpecificVenue(numOfEventsInGrid, eventName, serachVenue))
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
	public void customizeRoomFunctionalityWhileCreateEvent()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String roomName = "Auto-Cust-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		int numOfEventsInGrid = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_018</b></ul>");
		log("<b><ul>TestScenario: To verify 'Customize Room' functionality in 'Room Settings'.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Click on 'Customize room' tab");
		eventIndexPage.clickOnCutomizeRoomTab();
		log("Step 11: Fill the room detail and click on done button");
		dashboardPage = eventIndexPage.customizeRoomSetting(roomName);
		log("Step 12: Now navigate event page by clicking on logo");
		dashboardPage = eventIndexPage.clickOnLogo();
		log("Step 13: Verify created event with customize room");
		if(dashboardPage.verifyAddedEventWithSpecificVenue(numOfEventsInGrid, eventName, roomName))
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
	public void duplicateRoomFunctionalityWhileCreateEvent()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String roomName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala";
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_018</b></ul>");
		log("<b><ul>TestScenario: To verify 'Customize Room' functionality in 'Room Settings'.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Click on 'Duplicate' button in room setting page");
		log("Step 11: Enter Room name and click on done button");
		eventCreationPage = eventIndexPage.clickOnDuplicateButton(roomName);
		log("Step 11: Verify duplicated room should be added in footer tab");
		if(eventCreationPage.verifyAddedRoomInFooter(roomName))
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
	public void AddRoomFunctionalityInCreateEvent()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String roomName = "Auto-room-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EC_027</b></ul>");
		log("<b><ul>TestScenario: To verify 'Add Room' functionality.</b></ul>");
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
		
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now Click on '+' botton in footer");
		eventCreationPage = eventIndexPage.addRoomInEvent(roomName);
		log("Step 12: Verify added room in footer");
		if(eventCreationPage.verifyAddedRoomInFooter(roomName))
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
	public void shareEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		String email = "Auto_"+RandomStringUtils.randomAlphanumeric(4)+"@mailinator.com";
		String from = "mailer@socialtables.com";
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_032</b></ul>");
		log("<b><ul>TestScenario: To verify 'Share' button functionality in 'Event Creation'.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room and click on 'Done' button");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now click on share button");
		eventCreationPage = eventIndexPage.clickOnShareEvent();
		log("Step 12: Verify share event dialog box");
		if(eventCreationPage.verifyShareEventDialogBox())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 13: Enter invitee email id, select sharing option and click on share button");
		eventCreationPage = eventIndexPage.shareEvent(email);
		log("Step 14: Open Mailinator in new tab.");
		common.openUrlInNewTab(driver, "http://mailinator.com/");
		log("Step 15: Enter Email Id and click on check it");
		generalVerificationPage = generalIndexPage.checkMailInMailinator(email);
		log("Step 16: Verify new email in inbox and its sender");
		if(generalVerificationPage.verifyMailInMailinatorInbox(from))
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
	public void verifyAllUserDropDownLinks()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String url = "";
		log("<b><ul>Testcase ID: TC_EV_032</b></ul>");
		log("<b><ul>TestScenario: To verify 'Share' button functionality in 'Event Creation'.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
		if(eventCreationPage.verifyEventCreationPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on userdrop down button");
		eventCreationPage = eventIndexPage.clickOnUserDropdown();
		List<WebElement> allEles = driver.findElements(By.xpath(".//*[@id='header-right']/div[4]/ul/li/a"));
		System.out.println("total links---->"+allEles.size());
		for(WebElement ele:allEles)
		{
			int counter=9;
			String eleName = "";
			if(ele.getText().equalsIgnoreCase("my events"))
			{
				eleName = ele.getText();
				System.out.println("Executing>>>>>> "+eleName);
				log("Step "+counter+":Open "+eleName+" in new tab");
				url = ele.getAttribute("href");
				common.openUrlInNewTab(driver, url);
				counter = counter+1;
				log("Step "+counter+":Verify "+eleName+" Page");
				if(generalVerificationPage.verifyEventPage())
				{
					log("<Strong><font color=#008000>Pass</font></strong>");
				}
				else
				{
					log("Fail");
					numOfFailure++;
				}
				driver.findElement(By.xpath("//body")).sendKeys(Keys.COMMAND+"w");
				common.pause(2);
			}
			else if(ele.getText().equalsIgnoreCase("Team Members & Venues"))
			{
				eleName = ele.getText();
				log("Step "+counter+":Open "+eleName+" in new tab");
				url = ele.getAttribute("href");
				common.openUrlInNewTab(driver, url);
				counter = counter+1;
				log("Step "+counter+":Verify "+eleName+" Page");
				if(generalVerificationPage.verifyTeamMemberAndVenuePage())
				{
					log("<Strong><font color=#008000>Pass</font></strong>");
				}
				else
				{
					log("Fail");
					numOfFailure++;
				}
				driver.findElement(By.xpath("//body")).sendKeys(Keys.COMMAND+"w");
				common.pause(2);
			}
			else if(ele.getText().equalsIgnoreCase("Team Settings"))
			{
				eleName = ele.getText();
				log("Step "+counter+":Open "+eleName+" in new tab");
				url = ele.getAttribute("href");
				common.openUrlInNewTab(driver, url);
				counter = counter+1;
				log("Step "+counter+":Verify "+eleName+" Page");
				if(generalVerificationPage.verifyTeamSettings())
				{
					log("<Strong><font color=#008000>Pass</font></strong>");
				}
				else
				{
					log("Fail");
					numOfFailure++;
				}
				driver.findElement(By.xpath("//body")).sendKeys(Keys.COMMAND+"w");
				common.pause(2);
			}
			else if(ele.getText().equalsIgnoreCase("Statistics"))
			{
				eleName = ele.getText();
				log("Step "+counter+":Open "+eleName+" in new tab");
				url = ele.getAttribute("href");
				common.openUrlInNewTab(driver, url);
				counter = counter+1;
				log("Step "+counter+":Verify "+eleName+" Page");
				if(generalVerificationPage.verifyStatistics())
				{
					log("<Strong><font color=#008000>Pass</font></strong>");
				}
				else
				{
					log("Fail");
					numOfFailure++;
				}
				driver.findElement(By.xpath("//body")).sendKeys(Keys.COMMAND+"w");
				common.pause(2);
			}
			else if(ele.getText().equalsIgnoreCase("Change Password"))
			{
				eleName = ele.getText();
				log("Step "+counter+":Open "+eleName+" in new tab");
				url = ele.getAttribute("href");
				common.openUrlInNewTab(driver, url);
				counter = counter+1;
				log("Step "+counter+":Verify "+eleName+" Page");
				if(generalVerificationPage.verifyChangePasswordPage())
				{
					log("<Strong><font color=#008000>Pass</font></strong>");
				}
				else
				{
					log("Fail");
					numOfFailure++;
				}
				driver.findElement(By.xpath("//body")).sendKeys(Keys.COMMAND+"w");
				common.pause(2);
			}
			else if(ele.getText().equalsIgnoreCase("Logout"))
			{
				eleName = ele.getText();
				log("Step "+counter+":Click On "+eleName);
				ele.click();
				counter = counter+1;
				log("Step "+counter+":Verify "+eleName+" Page");
				if(generalVerificationPage.verifyLogout())
				{
					log("<Strong><font color=#008000>Pass</font></strong>");
				}
				else
				{
					log("Fail");
					numOfFailure++;
				}
			}
			counter++;
		}
		if(numOfFailure>0)
		{
			Assert.assertTrue(false);
		}
	}

	@Test
	public void addAttendeeInAttendeeManager()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String fName = "Auto-"+RandomStringUtils.randomAlphanumeric(2);
		String lName = "QA";
		String title = "Automation";
		String Guest = "0";
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(2);
		String eventType = "gala";
		int numOfGuestInGrid = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_042</b></ul>");
		log("<b><ul>TestScenario: To verify 'Add Attendee' functionality in 'Attendee Manager' page.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now click on 'Guest' tab");
		attendeeManagerPage = eventIndexPage.clickOnGuestTab();
		log("Step 12: Verify attedee manager page");
		if(attendeeManagerPage.verifyAttendeeManagerPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
	
		numOfGuestInGrid = common.getNumOfElements(driver, By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div"));
		log("Step 13: Add guest in attendee list");
		attendeeManagerPage = eventIndexPage.addGuest(fName, lName, title, Guest);
		log("Step 14: Verify added guest in grid");
		if(attendeeManagerPage.verifyAddedGuestInGrid(numOfGuestInGrid,Integer.parseInt(Guest)+1))
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
	public void addAttendeeWithGuestInAttendeeManager()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String fName = "Auto-"+RandomStringUtils.randomAlphanumeric(2);
		String lName = "QA";
		String title = "Automation";
		String Guest = "2";
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(2);
		String eventType = "gala";
		int numOfGuestInGrid = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_043</b></ul>");
		log("<b><ul>TestScenario: To verify 'Add Attendee' functionality with Guest.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now click on 'Guest' tab");
		attendeeManagerPage = eventIndexPage.clickOnGuestTab();
		log("Step 12: Verify attedee manager page");
		if(attendeeManagerPage.verifyAttendeeManagerPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		numOfGuestInGrid = common.getNumOfElements(driver, By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div"));
		log("Step 13: Add attendee with more then one guest in attendee list");
		attendeeManagerPage = eventIndexPage.addGuest(fName, lName, title, Guest);
		log("Step 14: Verify added guests in grid");
		if(attendeeManagerPage.verifyAddedGuestInGrid(numOfGuestInGrid,Integer.parseInt(Guest)+1))
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
	public void deleteAttendeeInAttendeeManager()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(2);
		String eventType = "gala";
		int numOfGuestInGrid = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_052</b></ul>");
		log("<b><ul>TestScenario: To verify 'Delete' functionality in 'Attendee Manager'.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now click on 'Guest' tab");
		attendeeManagerPage = eventIndexPage.clickOnGuestTab();
		log("Step 12: Verify attedee manager page");
		if(attendeeManagerPage.verifyAttendeeManagerPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 13: Add guest in to attendee manager");
		attendeeManagerPage = eventIndexPage.addGuest("Auto", "QA", "Automation", "0");
		common.pause(2);
		numOfGuestInGrid = common.getNumOfElements(driver, By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div"));
		log("Step 14: Delete any guest in attendee list");
		attendeeManagerPage = eventIndexPage.deleteGuest();
		log("Step 15: Verify deleted record should not display on grid ");
		if(attendeeManagerPage.verifyDeletedGuest(numOfGuestInGrid))
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
	public void addTagMealToAttendee()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String fName = "Auto-"+RandomStringUtils.randomAlphanumeric(2);
		String lName = "QA";
		String title = "Automation";
		String Guest = "0";
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(2);
		String eventType = "gala";
		String tagName = "Auto-Tag";
		String mealName = "Auto-Meal";
		int numOfGuestInGrid = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_042</b></ul>");
		log("<b><ul>TestScenario: To verify 'Add Tag' functionality in 'Attendee Manager'.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now click on 'Guest' tab");
		attendeeManagerPage = eventIndexPage.clickOnGuestTab();
		log("Step 12: Verify attedee manager page");
		if(attendeeManagerPage.verifyAttendeeManagerPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
	
		numOfGuestInGrid = common.getNumOfElements(driver, By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div"));
		log("Step 13: Add guest in attendee list");
		attendeeManagerPage = eventIndexPage.addGuest(fName, lName, title, Guest);
		log("Step 14: Verify added guest in grid");
		if(attendeeManagerPage.verifyAddedGuestInGrid(numOfGuestInGrid,Integer.parseInt(Guest)+1))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 15: Add tag by clicking on 'Tags' tab");
		attendeeManagerPage = eventIndexPage.enterTag(tagName);
		log("Step 16: Verify added tag.");
		if(attendeeManagerPage.verifyAddedTag(tagName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 17: Assign Tag to any available attendee");
		attendeeManagerPage = eventIndexPage.assignTagToAttendee(tagName);
		log("Step 18: Verify assigned tag");
		if(attendeeManagerPage.verifyAssignedTag(tagName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 19: Add meal by clicking on 'Meal' tab");
		attendeeManagerPage = eventIndexPage.enterMeal(mealName);
		log("Step 20: Verify added meal");
		if(attendeeManagerPage.verifyAddedMeal(mealName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 21: Assign Meal to any existing attendee");
		attendeeManagerPage = eventIndexPage.assignMealToAttendee(mealName);
		log("Step 22: Verify assigned meal");
		if(attendeeManagerPage.verifyAssignedMeal(mealName))
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
	public void groupAndUngroupForAttendee()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String fName = "Auto-"+RandomStringUtils.randomAlphanumeric(2);
		String lName = "QA";
		String title = "Automation";
		String Guest = "1";
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(2);
		String eventType = "gala";
		int numOfGuestInGrid = 0;
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_042</b></ul>");
		log("<b><ul>TestScenario: To verify 'Add Tag' functionality in 'Attendee Manager'.</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now click on 'Guest' tab");
		attendeeManagerPage = eventIndexPage.clickOnGuestTab();
		log("Step 12: Verify attedee manager page");
		if(attendeeManagerPage.verifyAttendeeManagerPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
	
		numOfGuestInGrid = common.getNumOfElements(driver, By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div"));
		log("Step 13: Add more then one guest in attendee list");
		attendeeManagerPage = eventIndexPage.addGuest(fName, lName, title, Guest);
		log("Step 14: Verify added guest in grid");
		if(attendeeManagerPage.verifyAddedGuestInGrid(numOfGuestInGrid,Integer.parseInt(Guest)+1))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		
		log("Step 15: Now 'Ungroup' all attendees");
		attendeeManagerPage = eventIndexPage.groupUnGroupAttendee("ungroup");
		log("Step 16: Verify Ungroued attendees");
		if(attendeeManagerPage.verifyUngroupAttendees())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		
		log("Step 17: Now 'Group' all attendees");
		attendeeManagerPage = eventIndexPage.groupUnGroupAttendee("group");
		log("Step 18: Verify Grouped attendees");
		if(attendeeManagerPage.verifyGroupOfAttedndees())
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
	public void verifyTutorialLinksOnEventCreationPage()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		boolean isAttendeeEnable = true;
		log("<b><ul>Testcase ID: TC_EV_024</b></ul>");
		log("<b><ul>TestScenario: To verify 'Object editing tutorial' and 'Room Setting Tutorial' link..</b></ul>");
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
		log("Step 6: Click On 'New Event' Button");
		eventCreationPage = eventIndexPage.clickOnNewEvent();
		log("Step 7: Verify 'Event Creation Page'");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType,isAttendeeEnable);
		log("Step 9: Verify Room Setting Page in Event Creation");
		if(eventCreationPage.verifyRoomSettingPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Select venue from library or customize room");
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("add");
		log("Step 11: Now Click on 'object editing tutorial' Link");
		eventCreationPage = eventIndexPage.clickOnObjectEditingTutorial();
		log("Step 12: Verify video should be popup.");
		if(eventCreationPage.verifyTutorialLinks())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@class,'fancybox-iframe')]")));
		driver.findElement(By.xpath("//div[contains(@title,'Close')]")).click();
		common.pause(2);
		
		log("Step 13: Now Click on 'Room Setting tutorial' Link");
		eventCreationPage = eventIndexPage.clickOnRoomSettingTutorial();
		log("Step 14: Verify video should be popup.");
		if(eventCreationPage.verifyTutorialLinks())
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
	public void verifySearchObjectInEventCreationPage()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "";
		log("<b><ul>Testcase ID: TC_EV_023</b></ul>");
		log("<b><ul>TestScenario: To verify 'Search Objects' functionality in 'Event Creation'.</b></ul>");
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
		List<WebElement> allEles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		eventName = allEles.get(0).getText();
		System.out.println("========"+eventName);
		log("Step 6: Click on any existing event");
		eventCreationPage = eventIndexPage.clickOnEvent();
		log("Step 7: Verify Event Creation Page");
		if(eventCreationPage.verifyEventNameHeader(eventName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		
		List<WebElement> allObjects = driver.findElements(By.xpath(".//*[@id='element-group-tables']/div"));
		String objectName = allObjects.get(0).findElement(By.xpath("//div/p")).getText();
		String className = allObjects.get(0).getAttribute("class");
		log("Step 8: Now enter search string(Object Name) in to search text box");
		eventCreationPage = eventIndexPage.searchObjects(objectName);
		log("Step 9: Verify seach result");
		if(eventCreationPage.verifySearchedObjectIsDisplay(className))
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
	public void verifyThreeDPreview()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EV_029</b></ul>");
		log("<b><ul>TestScenario: To verify '3D preview' functionality in 'Event Creation'.</b></ul>");
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
		List<WebElement> allEles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		String eventName = allEles.get(0).getText();
		System.out.println("========"+eventName);
		log("Step 6: Click on any existing event");
		eventCreationPage = eventIndexPage.clickOnEvent();
		log("Step 7: Verify Event Creation Page");
		if(eventCreationPage.verifyEventNameHeader(eventName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on '3D Preview'");
		eventCreationPage = eventIndexPage.clickOnThreeDPreview();
		log("Step 9: Verify 3D preview is display on event creation page");
		if(eventCreationPage.verifyThreedPriview())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Maximize 3D Preview by click on '+' button");
		eventCreationPage = eventIndexPage.clickOnPlusButton();
		log("Step 11: Verify maximized window on event creation page");
		if(eventCreationPage.verifyFullThreeDScreen())
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
	public void verifyHelpDropdown()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EV_029</b></ul>");
		log("<b><ul>TestScenario: To verify '3D preview' functionality in 'Event Creation'.</b></ul>");
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
		List<WebElement> allEles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		String eventName = allEles.get(0).getText();
		System.out.println("========"+eventName);
		log("Step 6: Click on any existing event");
		eventCreationPage = eventIndexPage.clickOnEvent();
		log("Step 7: Verify Event Creation Page");
		if(eventCreationPage.verifyEventNameHeader(eventName))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'Help' dropdown");
		eventCreationPage=eventIndexPage.clickOnHelpDropDown();
		log("Step 9: Verify all links should be display on page");
		if(eventCreationPage.verifyHelpDropDown())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Open On Support Center");
		eventCreationPage = eventIndexPage.openSupportCenter();
		log("Step 11: Verify Support Center Page");
		if(eventCreationPage.verifySupportCenter())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		//common.closeCurrentTab(driver);
		
		if(numOfFailure>0)
		{
			Assert.assertTrue(false);
		}
	}
}

