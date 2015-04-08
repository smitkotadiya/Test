package com.socialTables.teamSettings.Index;

import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.socialTables.init.Common;
import com.socialTables.init.SeleniumInit;

public class TeamSettingIndex extends SeleniumInit
{

	@Test
	public void createEventCategoryFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String categoryName = "Auto-"+RandomStringUtils.randomAlphabetic(3);
		log("<b><ul>Testcase ID: TC_EC_005</b></ul>");
		log("<b><ul>TestScenario: To verify 'Add Custom Category' functionality.</b></ul>");
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
		log("Step 6:Navigate to 'Team Settings' Module");
		generalVerificationPage = generalIndexPage.navigateToTeamSettings();
		log("Step 7:Verify 'Team Setting' page");
		if(generalVerificationPage.verifyTeamSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'Event Category' tab");
		teamSettingsPage = teamSettingsIndexPage.clickOnEventCategory();
		log("Step 9:Verify event category page");
		if(teamSettingsPage.verifyEventCategoryPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		int numOfcategory = common.getNumOfElements(driver, By.xpath(".//*[@id='custom_event_categories_table']/tbody/tr"));
		log("Step 10:Click on new Category button, add detail and click on save.");
		teamSettingsPage = teamSettingsIndexPage.addNewCategory(categoryName, numOfcategory);
		log("Step 11: Verify added event on list.");
		if(teamSettingsPage.verifyAddedCategory(numOfcategory, categoryName))
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
	public void deleteEventCategoryFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EC_004</b></ul>");
		log("<b><ul>TestScenario: To verify 'Delete' functionality in Event Categories.</b></ul>");
		log("Step 1: Click on 'login' tab");
		generalIndexPage.clickOnLoginTab();
		log("Step 2: Enter User Name");
		log("Step 3: Enter Password");
		log("<strong>User Name: </strong>"+userName_Owner);
		log("<strong>Password: </strong>"+password_Owner);
		log("Step 4: Click On 'Login' Button");
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
		log("Step 6:Navigate to 'Team Settings' Module.");
		generalVerificationPage = generalIndexPage.navigateToTeamSettings();
		log("Step 7:Verify 'Team Setting' page");
		if(generalVerificationPage.verifyTeamSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'Event Category' tab.");
		teamSettingsPage = teamSettingsIndexPage.clickOnEventCategory();
		log("Step 9:Verify event category page");
		if(teamSettingsPage.verifyEventCategoryPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		int numOfRow = common.getNumOfElements(driver, By.xpath(".//*[@id='custom_event_categories_table']/tbody/tr"));

		log("Step 10: Click on 'Delete' button to delete custom category");
		teamSettingsPage = teamSettingsIndexPage.deleteEventCategory();
		log("Step 11: Verify deleted category");
		if(teamSettingsPage.verifyDeletedCategory(numOfRow))
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
	public void addCustomChairsFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		String chairName = "Auto-Obj"+RandomStringUtils.randomAlphanumeric(2);
		log("<b><ul>Testcase ID: TC_EC_008</b></ul>");
		log("<b><ul>TestScenario: To verify 'Create Custom Chair' Functionality in 'Custom Objects'.</b></ul>");
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
		log("Step 6:Navigate to 'Team Settings' Module");
		generalVerificationPage = generalIndexPage.navigateToTeamSettings();
		log("Step 7:Verify 'Team Setting' page");
		if(generalVerificationPage.verifyTeamSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'Custom Objects' tab");
		teamSettingsPage = teamSettingsIndexPage.clickOnCustomObjects();
		log("Step 9:Verify Custom Objects page");
		if(teamSettingsPage.verifyCustomObjectPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		int numOfRow = common.getNumOfElements(driver, By.xpath("//div[@class='chair-content']/span[@class='chair-name']/span[2]"));
		log("Step 10: Click on 'Create custom chair' button");
		teamSettingsPage = teamSettingsIndexPage.clickOnCreateCustomChair();
		log("Step 11: Now create custom objetc by filling required information.");
		teamSettingsPage = teamSettingsIndexPage.createCustomObject(chairName);
		log("Step 12: Verify creted objetc in list");
		if(teamSettingsPage.verifyAddedObject(chairName, numOfRow))
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
	public void deleteCustomChairsFunctionality() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_EC_009</b></ul>");
		log("<b><ul>TestScenario:To verify 'Delete' functionality in 'Custom Objects'.</b></ul>");
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
		log("Step 6:Navigate to 'Team Settings' Module");
		generalVerificationPage = generalIndexPage.navigateToTeamSettings();
		log("Step 7:Verify 'Team Setting' page");
		if(generalVerificationPage.verifyTeamSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'Custom Objects' tab");
		teamSettingsPage = teamSettingsIndexPage.clickOnCustomObjects();
		log("Step 9:Verify Custom Objects page");
		if(teamSettingsPage.verifyCustomObjectPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		int numOfRow = common.getNumOfElements(driver, By.xpath("//div[@class='chair-content']/span[@class='chair-name']/span[2]"));
		log("Step 10: Click on delete buttonn of any existing custom objetcs ");
		teamSettingsPage = teamSettingsIndexPage.deleteCustomObject();
		log("Step 11: Verify deleted events");
		if(teamSettingsPage.verifyDeletedObject(numOfRow))
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
	public void verifyAllLinksOnTeamSettings() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_TS_001</b></ul>");
		log("<b><ul>TestScenario: To verify All links in Team setting.</b></ul>");
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
		log("Step 6:Navigate to 'Team Settings' Module");
		generalVerificationPage = generalIndexPage.navigateToTeamSettings();
		log("Step 7:Verify 'Team Setting' page");
		if(generalVerificationPage.verifyTeamSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'Print/Export Settings' tab");
		teamSettingsPage = teamSettingsIndexPage.clickOnPrintAndExport();
		log("Step 9: Verify Print/Export Settings Page");
		if(teamSettingsPage.verifyPrintExportPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Click on 'Table & Object' tab ");
		teamSettingsPage = teamSettingsIndexPage.clickOnTableAndObject();
		log("Step 11: Verify Table & object page");
		if(teamSettingsPage.verifyTableAndObjectPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 12: Click on 'Event Category' tab");
		teamSettingsPage = teamSettingsIndexPage.clickOnEventCategory();
		log("Step 13: Verify Event Category page");
		if(teamSettingsPage.verifyEventCategoryPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 14: Click on 'Measurentment Setting' tab");
		teamSettingsPage = teamSettingsIndexPage.clickOnMeasurmentSettings();
		log("Step 15: Verify Measurentment Setting page");
		if(teamSettingsPage.verifyMeausrmentSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 16: Click on 'Language Sttings' tab");
		teamSettingsPage = teamSettingsIndexPage.clickOnLanguageSettings();
		log("Step 17: Verify Language Sttings page");
		if(teamSettingsPage.verifyLanguageSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 18: Click on 'Custom objects' tab");
		teamSettingsPage = teamSettingsIndexPage.clickOnCustomObjects();
		log("Step 19: Verify Custom objects page");
		if(teamSettingsPage.verifyCustomObjectPage())
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
	public void verifyHideObjectInTeamSettings() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_TS_002</b></ul>");
		log("<b><ul>TestScenario: To verify 'Hide' functionality in Table & Objects.</b></ul>");
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
		log("Step 6:Navigate to 'Team Settings' Module");
		generalVerificationPage = generalIndexPage.navigateToTeamSettings();
		log("Step 7:Verify 'Team Setting' page");
		if(generalVerificationPage.verifyTeamSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'Table & Object' tab ");
		teamSettingsPage = teamSettingsIndexPage.clickOnTableAndObject();
		log("Step 9: Verify Table & object page");
		if(teamSettingsPage.verifyTableAndObjectPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		
		List<WebElement> allVisibleEles = driver.findElements(By.xpath("//div[@class='folder'][1]/div[contains(@class,'visible')]/button"));
		List<WebElement> allVsisbleElesForClass = driver.findElements(By.xpath("//div[@class='folder'][1]/div[contains(@class,'visible')]/i"));
		List<WebElement> allVisibleElesForLable = driver.findElements(By.xpath("//div[@class='folder'][1]/div[contains(@class,'visible')]/div"));
		
		Random r = new Random();
		int index = r.nextInt(allVisibleEles.size());
		String objectName = allVisibleElesForLable.get(index).getText();
		String className = allVsisbleElesForClass.get(index).getAttribute("class");
		System.out.println("Selected Class is---->"+className);
		log("Step 10: Now hide any object");
		teamSettingsPage = teamSettingsIndexPage.clickOnAnyObject(allVisibleEles.get(index));
		log("Step 11: Verify Button lable ");
		if(teamSettingsPage.verifyShowButtonLable(allVisibleEles.get(index)))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 12: Navigate to 'Event' Page");
		generalVerificationPage = generalIndexPage.navigateToEvent();
		log("Step 13: Now click on any existing event and ");
		eventCreationPage = eventIndexPage.clickOnEvent();
		log("Step 14: Now Search relevant object.");
		eventCreationPage = eventIndexPage.searchObjects(objectName);
		log("Step 15: Verify 'Hide' object should not appear in object panel ");
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
	public void verifyShowObjectInTeamSettings() throws InterruptedException
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		log("<b><ul>Testcase ID: TC_TS_003</b></ul>");
		log("<b><ul>TestScenario: To verify 'Show' functionality in Table & Objects..</b></ul>");
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
		log("Step 6:Navigate to 'Team Settings' Module");
		generalVerificationPage = generalIndexPage.navigateToTeamSettings();
		log("Step 7:Verify 'Team Setting' page");
		if(generalVerificationPage.verifyTeamSettings())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 8: Click on 'Table & Object' tab ");
		teamSettingsPage = teamSettingsIndexPage.clickOnTableAndObject();
		log("Step 9: Verify Table & object page");
		if(teamSettingsPage.verifyTableAndObjectPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		
		List<WebElement> allVisibleEles = driver.findElements(By.xpath("//div[@class='folder'][1]/div[not(contains(@class,'visible'))]/button"));
		List<WebElement> allVsisbleElesForClass = driver.findElements(By.xpath("//div[@class='folder'][1]/div[not(contains(@class,'visible'))]/i"));
		List<WebElement> allVisibleElesForLable = driver.findElements(By.xpath("//div[@class='folder'][1]/div[not(contains(@class,'visible'))]/div"));
		
		Random r = new Random();
		int index = r.nextInt(allVisibleEles.size());
		String objectName = allVisibleElesForLable.get(index).getText();
		String className = allVsisbleElesForClass.get(index).getAttribute("class");
		System.out.println("Selected Class is---->"+className);
		log("Step 10: Now Show any object");
		teamSettingsPage = teamSettingsIndexPage.clickOnAnyObject(allVisibleEles.get(index));
		log("Step 11: Verify Button lable ");
		if(teamSettingsPage.verifyHideButtonLable(allVisibleEles.get(index)))
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 12: Navigate to 'Event' Page");
		generalVerificationPage = generalIndexPage.navigateToEvent();
		log("Step 13: Now click on any existing event and ");
		eventCreationPage = eventIndexPage.clickOnEvent();
		log("Step 14: Now Search relevant object.");
		eventCreationPage = eventIndexPage.searchObjects(objectName);
		log("Step 15: Verify 'Show' object should appear in object panel ");
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
	
}
