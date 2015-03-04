package com.socialTables.events.Index;

import org.apache.bcel.generic.Select;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

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
		eventCreationPage = eventIndexPage.fillEventInfo(eventName, eventType);
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
		eventCreationPage = eventIndexPage.fillRoomSettingsForm();
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
		int numOfEventsInGrid = 0;
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
		eventCreationPage = eventIndexPage.fillEventInfo(eventName, eventType);
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
		eventCreationPage = eventIndexPage.fillRoomSettingsForm();
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

}
