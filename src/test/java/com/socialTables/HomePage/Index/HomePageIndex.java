package com.socialTables.HomePage.Index;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.socialTables.init.Common;
import com.socialTables.init.SeleniumInit;

public class HomePageIndex extends SeleniumInit
{

	@Test
	public void login_ValidCredential()
	{
		Common common = new Common(driver);
		int numOfFailure=0;
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
		
		if(numOfFailure>0)
		{
			Assert.assertTrue(false);
		}
	}
}
