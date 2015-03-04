package com.socialTables.events.Index;

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
		eventName = driver.findElement(By.xpath(".//*[@id='list-container']/a[1]")).getText();
		numOfEventsInGrid = common.getNumOfElements(driver, By.xpath(".//*[@id='list-container']/a"));
		try
		{
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
		}
		catch(Exception e)
		{
			log("There is no existing event available to delete");
		}
		
		if(numOfFailure>0)
		{
			Assert.assertTrue(false);
		}
	}
	
}
