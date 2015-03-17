package com.socialTables.events.verifications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class AttendeeManagerPage extends AbstractPage
{

	@FindBy(xpath="//h1[contains(.,'Attendee Manager')]")
	private WebElement verifyAttendeePage;
	
	Common common = new Common(driver);
	
	public AttendeeManagerPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyAttendeeManagerPage()
	{
	
		if(verifyAttendeePage.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyAddedGuestInGrid(int numOfGuest,int addedGuest)
	{
		boolean bool = false;
		common.pause(2);
		int numOfGuestAfterAddition = common.getNumOfElements(driver, By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div"));
		if(numOfGuest==numOfGuestAfterAddition-addedGuest)
		{
			 bool = true;
		}
		else
		{
			bool = false;
		}
		return bool;
	}

}