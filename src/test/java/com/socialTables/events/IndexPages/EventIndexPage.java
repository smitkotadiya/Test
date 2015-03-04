package com.socialTables.events.IndexPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.events.verifications.DashboardPage;
import com.socialTables.events.verifications.EventCreationPage;
import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class EventIndexPage extends AbstractPage
{
	@FindBy(xpath=".//*[@id='newEvent']/a")
	private WebElement btnNewEvent;
	@FindBy(id="s-name")
	private WebElement txtEventName;
	@FindBy(id="s-date")
	private WebElement txtStartDate;
	@FindBy(id="end-date")
	private WebElement txtEndDate;
	@FindBy(id="s-chairtype")
	private WebElement selectChairType;
	@FindBy(id="guest-list-enabled")
	private WebElement guestListCheckBox;
	@FindBy(id="uses-metric")
	private WebElement usesMatricUnitCheckBox;
	@FindBy(id="settings-done")
	private WebElement btnDoneInEventInfo;
	@FindBy(id="logo")
	private WebElement logo;
	
	@FindBy(id="close-edit")
	private WebElement btnDoneInRoomSetting;
	
	
	//div[@class='event-type-container']/div/input[@value='gala']
	Common common = new Common(driver);

	public EventIndexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public EventCreationPage clickOnNewEvent()
	{
		btnNewEvent.click();
		common.pause(5);
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage fillEventInfo(String eventName,String eventType)
	{
		txtEventName.sendKeys(eventName);
		WebElement eventTypeButton=driver.findElement(By.xpath("//div[@class='event-type-container']/div/input[@value='"+eventType+"']"));
		eventTypeButton.click();
		usesMatricUnitCheckBox.click();
		btnDoneInEventInfo.click();
		common.pause(2);
		
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage fillRoomSettingsForm()
	{
		common.clickOn(driver, btnDoneInRoomSetting);
		common.pause(3);
		return new EventCreationPage(driver);
	}
	
	public DashboardPage clickOnLogo()
	{
		common.clickOn(driver, logo);
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public EventCreationPage clickOnEvent()
	{
		try{
		driver.findElement(By.xpath(".//*[@id='list-container']/a[not(contains(@style,'display: none;'))][1]")).click();
		}
		catch(Exception e)
		{
			log("There is no existing event available to view");
		}
		common.pause(4);
		return new EventCreationPage(driver);
	}
	
	public DashboardPage clickOnDeleteEvent()
	{
		driver.findElement(By.xpath(".//*[@id='list-container']/a[1]/span[@class='eventTools']/i[1]")).click();
		common.pause(2);
		driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
		common.pause(2);
		return new DashboardPage(driver);
	}
}
