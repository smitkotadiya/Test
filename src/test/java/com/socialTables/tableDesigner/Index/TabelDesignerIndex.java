package com.socialTables.tableDesigner.Index;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.socialTables.init.Common;
import com.socialTables.init.SeleniumInit;

public class TabelDesignerIndex extends SeleniumInit
{
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

}
