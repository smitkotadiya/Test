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
	
	//filteration combo
	@FindBy(id="filterAuthor")
	private WebElement filterAuthor;
	@FindBy(id="filterCategory")
	private WebElement filterCategory;
	@FindBy(id="filterLocation")
	private WebElement filterLocation;
	
	
	@FindBy(id="search")
	private WebElement txtSearch;
	
	
	@FindBy(id="showPreviousEvents")
	private WebElement btnPreviousEvent;
	@FindBy(id="showUpcomingEvents")
	private WebElement btnUpcommingEvent;
	
	@FindBy(xpath="//a[contains(.,'Dashboard tutorial')]")
	private WebElement linkDashboardTutorial;
	
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
	
	public EventCreationPage fillEventInfo(String criteria,String eventName,String eventType)
	{
		if(criteria.equalsIgnoreCase("Add"))
		{
			common.type(txtEventName, eventName);
			WebElement eventTypeButton=driver.findElement(By.xpath("//div[@class='event-type-container']/div/input[@value='"+eventType+"']"));
			eventTypeButton.click();
			usesMatricUnitCheckBox.click();
			btnDoneInEventInfo.click();
			common.pause(2);
		}
		else
		{
			common.type(txtEventName, eventName);
			btnDoneInEventInfo.click();
			common.pause(2);
		}
		
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage fillRoomSettingsForm(String criteria)
	{
		if(criteria.equalsIgnoreCase("clone"))
		{
			common.clickOn(driver, btnDoneInRoomSetting);
			common.pause(3);
		}
		else
		{
			common.clickOn(driver, btnDoneInRoomSetting);
			common.pause(3);
		}
		
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
		try{
		driver.findElement(By.xpath(".//*[@id='list-container']/a[not(contains(@style,'display: none;'))][1]/span[@class='eventTools']/i[1]")).click();
		}
		catch(Exception e)
		{
			log("There is no existing event available to delete");
		}
		common.pause(2);
		
		driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public DashboardPage filteration(String criteria,String value)
	{
		if(criteria.equalsIgnoreCase("Author"))
		{
			common.selectFromComboByVisibleElement(filterAuthor, value);
		}
		else if(criteria.equalsIgnoreCase("Category"))
		{
			common.selectFromComboByVisibleElement(filterCategory, value);
		}
		else
		{
			common.selectFromComboByVisibleElement(filterLocation, value);
		}
		
		return new DashboardPage(driver);
	}
	
	public DashboardPage searchEvent(String searchString)
	{
		common.type(txtSearch, searchString);
		return new DashboardPage(driver);
	}
	
	public EventCreationPage clickOnCloneIcone()
	{
		driver.findElement(By.xpath(".//*[@id='list-container']/a[not(contains(@style,'display: none;'))][1]/span[@class='eventTools']/i[2]")).click();
		common.pause(2);
		driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
		common.pause(2);
		return new EventCreationPage(driver);
	}
	
	public DashboardPage clickOnUpcomingEvent()
	{
		btnUpcommingEvent.click();
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public DashboardPage clickOnPreviousEvent()
	{
		btnPreviousEvent.click();
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public DashboardPage clickOnDashboardTutorial()
	{
		linkDashboardTutorial.click();
		common.pause(1);
		return new DashboardPage(driver);
	}
	
}
