package com.socialTables.events.verifications;

import java.util.List;

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
	@FindBy(xpath="//h3[contains(.,'Share')]")
	private WebElement verifyShareDialog;
	
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
	
	public boolean verifySearchedRoom(String searchString)
	{
		boolean bool = false;
		List<WebElement> eles = driver.findElements(By.xpath("//div[@class='listing-name']"));
		for(WebElement ele:eles)
		{
			if(ele.getText().equalsIgnoreCase(searchString))
			{
				bool= true;
			}
			else
			{
				bool= false;
				break;
			}
		}
		return bool;
	}
	
	public boolean verifyAddedRoomInFooter(String roomName)
	{
		WebElement roomTab = driver.findElement(By.xpath("//ul[@id='sortable-rooms']/li/a[contains(.,'"+roomName+"')]"));
		System.out.println(roomTab.getText());
		if(roomTab.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyShareEventDialogBox()
	{
		
		if(verifyShareDialog.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
}
