package com.socialTables.events.verifications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class EventCreationPage extends AbstractPage
{

	@FindBy(xpath=".//*[@id='dialog-header']/h3[contains(.,'Event Information')]")
	private WebElement verifyEventCreationForm;
	@FindBy(xpath=".//*[@id='dialog-header']/h3[contains(.,'Room Settings')]")
	private WebElement verifyRoomSetingForm;
	@FindBy(id="tabGuest")
	private WebElement verifyAttedeeTab;
	
	Common common = new Common(driver);
	public EventCreationPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyEventCreationPage()
	{
		if(verifyEventCreationForm.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyRoomSettingPage()
	{
		common.pause(2);
		if(verifyRoomSetingForm.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean verifyEventNameHeader(String eventName)
	{
		common.pause(2);
		String eventNameOnView = driver.findElement(By.xpath(".//*[@id='header-event-name']")).getText();
		System.out.println(eventNameOnView+"===="+eventName);
		if(eventName.equalsIgnoreCase(eventNameOnView))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyAttendeeListEnable()
	{
		if(verifyAttedeeTab.isEnabled())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
