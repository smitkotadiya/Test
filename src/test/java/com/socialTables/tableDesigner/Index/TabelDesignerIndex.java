package com.socialTables.tableDesigner.Index;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.socialTables.general.GeneralIndexPage;
import com.socialTables.general.GeneralVerificationPage;
import com.socialTables.init.Common;
import com.socialTables.init.SeleniumInit;
import com.socialTables.tableDesigner.IndexPage.TableDesignerIndexPage;
import com.socialTables.tableDesigner.verifications.TableDesignerPage;


public class TabelDesignerIndex extends SeleniumInit
{
		
	@Test
	public void createTableDesign()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfTablesInGrid = 0;
		String tableName = "Auto-"+RandomStringUtils.randomAlphabetic(2);
		log("<b><ul>Testcase ID: TC_EC_001</b></ul>");
		log("<b><ul>TestScenario: To verify 'Create Table Design' button functionality.</b></ul>");
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
		log("Step 6:Navigate to 'Create Table Design' Module");
		generalVerificationPage = generalIndexPage.navigateToTableDesigner();
		log("Step 7:Verify 'Table Designer' page");
		if(generalVerificationPage.verifyTableDesigner())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		numOfTablesInGrid = common.getNumOfElements(driver, By.xpath(".//*[@id='tables-settings-tbody']/tr/td[1]"));
		log("Step 8: Click On 'Create Table Design'");
		tableDesignerPage = tableDesignerIndexPage.clickOnTableDesign();
		log("Step 9: Verify 'Create Table Design' page");
		if(tableDesignerPage.verifyTableDesignerPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Fill required detail to create tabel design and click on save button");
		tableDesignerPage = tableDesignerIndexPage.fillTableDetail(tableName);
		log("Step 11: Click on 'Logo' to navigate 'Table Designer' page");
		tableDesignerPage = tableDesignerIndexPage.clickOnLogo();
		log("Step 12: Verfiy added 'Table Design' in grid");
		if(tableDesignerPage.verifyAddedTableDesign(tableName, numOfTablesInGrid))
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
	public void deleteTableDesign()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfTablesInGrid = 0;
		log("<b><ul>Testcase ID: TC_EC_020</b></ul>");
		log("<b><ul>TestScenario: To verify 'Delete' functionality of table design.</b></ul>");
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
		log("Step 6:Navigate to 'Create Table Design' Module");
		generalVerificationPage = generalIndexPage.navigateToTableDesigner();
		log("Step 7:Verify 'Table Designer' page");
		if(generalVerificationPage.verifyTableDesigner())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		numOfTablesInGrid = common.getNumOfElements(driver, By.xpath(".//*[@id='tables-settings-tbody']/tr/td[1]"));
		log("Step 8: Now delete any existing table design");
		tableDesignerPage = tableDesignerIndexPage.deleteTableDesign();
		log("Step 9: Verify Deleted table design in grid");
		if(tableDesignerPage.verifyDeletedTableDesign(numOfTablesInGrid))
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
	public void cloneTableDesign()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
		int numOfTablesInGrid = 0;
		String tableName = "Auto-Clone-"+RandomStringUtils.randomAlphabetic(2);
		log("<b><ul>Testcase ID: TC_EC_021</b></ul>");
		log("<b><ul>TestScenario: To verify 'Clone' functionality of table design.</b></ul>");
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
		log("Step 6:Navigate to 'Create Table Design' Module");
		generalVerificationPage = generalIndexPage.navigateToTableDesigner();
		log("Step 7:Verify 'Table Designer' page");
		if(generalVerificationPage.verifyTableDesigner())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		numOfTablesInGrid = common.getNumOfElements(driver, By.xpath(".//*[@id='tables-settings-tbody']/tr/td[1]"));
		log("Step 8: Click on 'Clone' icone of any existing table design");
		tableDesignerPage = tableDesignerIndexPage.cloneTableDesign();
		log("Step 9: Verify Create Table Design Page");
		if(tableDesignerPage.verifyTableDesignerPage())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
		}
		else
		{
			log("Fail");
			numOfFailure++;
		}
		log("Step 10: Enter required etail and click on 'Save' button");
		tableDesignerPage = tableDesignerIndexPage.fillTableDetail(tableName);
		log("Step 11: Now click on 'Logo' to navigate 'Table Designer' Page");
		tableDesignerPage = tableDesignerIndexPage.clickOnLogo();
		log("Step 12: Verify added clone table design on grid");
		if(tableDesignerPage.verifyAddedTableDesign(tableName, numOfTablesInGrid))
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
