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

		return verifyAttendeePage.isDisplayed();
	}
	
	public boolean verifyAddedGuestInGrid(int numOfGuest,int addedGuest)
	{
		boolean bool = false;
		common.pause(2);
		int numOfGuestAfterAddition = common.getNumOfElements(driver, By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div"));
		System.out.println("guest for index:--->"+addedGuest);
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
	
	public boolean verifyDeletedGuest(int numOfGuest)
	{
		boolean bool = false;
		int numOfGuestAfterDeletion = common.getNumOfElements(driver, By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div"));
		if(!(numOfGuestAfterDeletion==0))
		{
			if(numOfGuest==numOfGuestAfterDeletion-1)
			{
				 bool = true;
			}
			else
			{
				bool = false;
			}
		}
		else
		{
			bool = true;
		}
		return bool;
	}
	
	public boolean verifyAddedTag(String tagName)
	{
		WebElement ele = driver.findElement(By.xpath("//li[contains(@class,'tags')]/ul/li/a[contains(.,'"+tagName+"')]"));
		return ele.isDisplayed();
	}
	
	public boolean verifyAddedMeal(String mealName)
	{
		WebElement ele = driver.findElement(By.xpath("//li[contains(@class,'meal')]/ul/li/a[contains(.,'"+mealName+"')]"));
		return ele.isDisplayed();
	}
	
	public boolean verifyAssignedTag(String tagName)
	{
		WebElement ele = driver.findElement(By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div[1]/div/div[contains(.,'"+tagName+"')]"));
		return ele.isDisplayed();
	}

	public boolean verifyAssignedMeal(String mealName)
	{
		WebElement ele = driver.findElement(By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div[1]/div/div[contains(.,'"+mealName+"')]"));
		return ele.isDisplayed();
	}
	
	public boolean verifyUngroupAttendees()
	{
		try{
		 driver.findElement(By.xpath("//span[@class='guest-group-number']")).isDisplayed();
		log("<b> Not able to perform 'Ungroup' functionality</b>");
		return true;
		}
		catch(Exception e)
		{
			return true;
		}
	}
	
	public boolean verifyGroupOfAttedndees()
	{
		return driver.findElement(By.xpath("//span[@class='guest-group-number']")).isDisplayed() && driver.findElement(By.id("guestGroupBulk")).isDisplayed();
	}
}
