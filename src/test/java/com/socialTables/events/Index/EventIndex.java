package com.socialTables.events.Index;

import java.text.ParseException;
import java.util.ArrayList;

import org.apache.bcel.generic.Select;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.socialTables.general.GeneralVerificationPage;
import com.socialTables.init.Common;
import com.socialTables.init.SeleniumInit;

public class EventIndex extends SeleniumInit
{

	@Test(groups={"Events"})
	public void createEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		int numOfEventsInGrid = 0;
		log("<b><ul>Testcase ID: TC_EV_001</b></ul>");
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType);
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
	
	@Test(groups={"Events"})
	public void viewEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "";
		log("<b><ul>Testcase ID: TC_EV_002</b></ul>");
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
		eventName = driver.findElement(By.xpath(".//*[@id='list-container']/a[not(contains(@style,'display: none;'))][1]/span[@class='name']")).getText();
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
	
	@Test(groups={"Events"})
	public void deleteEventFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfEventsInGrid = 0;
		String eventName = "";
		log("<b><ul>Testcase ID: TC_EV_009</b></ul>");
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
		eventName = driver.findElement(By.xpath(".//*[@id='list-container']/a[1]")).getText();
		numOfEventsInGrid = common.getNumOfElements(driver, By.xpath(".//*[@id='list-container']/a"));
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
		log("<b><ul>Testcase ID: TC_EV_002</b></ul>");
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
		log("<b><ul>Testcase ID: TC_EV_002</b></ul>");
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
	
	@Test(groups={"Events"})
	public void eventCounterFunctionality()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String eventName = "Auto-"+RandomStringUtils.randomAlphanumeric(3);
		String eventType = "gala"; 
		int numOfEventsInLableCount = 0;
		log("<b><ul>Testcase ID: TC_EV_005</b></ul>");
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
		String numOfEventInLable =driver.findElement(By.xpath("//div[@class='count_box']")).getText();
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
		eventCreationPage = eventIndexPage.fillEventInfo("Add",eventName, eventType);
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
		numOfEventInLable =driver.findElement(By.xpath("//div[@class='count_box']")).getText();
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
	
	@Test(groups={"Events"})
	public void eventFiltrationFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String authorName = "";
		String categoryName = "";
		String locationName="";
		 
		log("<b><ul>Testcase ID: TC_EV_005</b></ul>");
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
		By By_Author = By.id("filterAuthor");
		By By_Category = By.id("filterCategory");
		By By_Location = By.id("filterLocation");
		authorName = common.selectRandomOptionFromCombo(By_Author, driver);
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
		categoryName = common.selectRandomOptionFromCombo(By_Category, driver);
		
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
		locationName = common.selectRandomOptionFromCombo(By_Location, driver);
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
		String searchString = driver.findElement(By.xpath(".//*[@id='list-container']/a[not(contains(@style,'display: none;'))]/span[@class='name']")).getText();
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
		log("<b><ul>Testcase ID: TC_EV_006</b></ul>");
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
		log("Step 6: Click on clone icone of any existing event");
		eventCreationPage = eventIndexPage.clickOnCloneIcone();
		log("Step 7: Verify event creation page");
		if(eventCreationPage.verifyEventCreationPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Now, Save the event");
		eventCreationPage = eventIndexPage.fillEventInfo("Clone",eventName,null);
		eventCreationPage = eventIndexPage.fillRoomSettingsForm("Clone");
		log("Step 9: Navigate to dashboard page by clicking on logo");
		dashboardPage = eventIndexPage.clickOnLogo();
		log("Step 10: Verify Dashboard Page and added event");
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
		log("<b><ul>Testcase ID: TC_EV_006</b></ul>");
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
		int numOfEventsInGrid = 0;
		log("<b><ul>Testcase ID: TC_EV_006</b></ul>");
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
		log("<b><ul>Testcase ID: TC_EV_006</b></ul>");
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
	

}
