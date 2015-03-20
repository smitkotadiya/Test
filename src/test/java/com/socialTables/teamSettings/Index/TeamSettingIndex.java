package com.socialTables.teamSettings.Index;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

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
		log("Step 10:Click on new Category button, add detail and click on save");
		teamSettingsPage = teamSettingsIndexPage.addNewCategory(categoryName, numOfcategory);
		log("Step 11: Verify added event on list");
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
	
}
