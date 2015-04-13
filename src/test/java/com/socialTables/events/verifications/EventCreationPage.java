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
	@FindBy(id="logo")
	private WebElement verifyLogoOnEventCreation;
	@FindBy(xpath=".//*[@id='dialog-header']/h3[contains(.,'Room Settings')]")
	private WebElement verifyRoomSetingForm;
	@FindBy(id="tabGuest")
	private WebElement verifyAttedeeTab;
	@FindBy(xpath="//h3[contains(.,'Share')]")
	private WebElement verifyShareDialog;
	@FindBy(xpath=".//*[@id='minimap-3D']")
	private WebElement verifyThreeDPriview;
	@FindBy(xpath=".//*[@id='minimap-3D-header-button-container']/button[contains(@class,'ui-icon-minusthick')]")
	private WebElement verifyFullThreeDScreen;
	
	
	Common common = new Common(driver);
	public EventCreationPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public boolean verifyEventCreationPage()
	{
		return verifyEventCreationForm.isDisplayed();
	}
	
	public boolean verifyLogoInEventCreationPage()
	{
		return verifyLogoOnEventCreation.isDisplayed();
	}
	public boolean verifyRoomSettingPage()
	{
		common.pause(2);
		return verifyRoomSetingForm.isDisplayed();
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
		return verifyAttedeeTab.isEnabled();
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
		return roomTab.isDisplayed();
	}
	
	public boolean verifyShareEventDialogBox()
	{
		return verifyShareDialog.isDisplayed();
	}
	
	public boolean verifyTutorialLinks()
	{
		return driver.findElement(By.xpath("//iframe[contains(@class,'fancybox-iframe')]")).isDisplayed();
	}
	
	public boolean verifySearchedObjectIsDisplay(String className)
	{
		try
		{
			driver.findElement(By.xpath("//i[contains(@class,'"+className+"')]")).isDisplayed();
			return false;
		}
		catch(Exception e)
		{
			return true;
		}
	}
	
	public boolean verifyThreedPriview()
	{
		return verifyThreeDPriview.isDisplayed();
	}
	
	public boolean verifyFullThreeDScreen()
	{
		return verifyFullThreeDScreen.isDisplayed();
	}
}