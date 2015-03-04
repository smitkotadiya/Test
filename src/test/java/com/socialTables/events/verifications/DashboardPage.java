package com.socialTables.events.verifications;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class DashboardPage extends AbstractPage
{

	@FindBy(xpath=".//*[@id='main-header']/div/div/div[2]/div[1]/a")
	private WebElement verifyDashboardPage;
	
	Common common = new Common(driver);
	
	public DashboardPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyDashboardPage()
	{
		if(verifyDashboardPage.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyAddedEvent(int numOfEvents,String eventName)
	{
		int numOfEventsAfterAdditon = common.getNumOfElements(driver, By.xpath(".//*[@id='list-container']/a"));
		boolean bool = false;
		if(numOfEventsAfterAdditon==numOfEvents+1)
		{
			List<WebElement> eles = driver.findElements(By.xpath(".//*[@id='list-container']/a/span[3]"));
			for(WebElement ele:eles)
			{
				if(ele.getText().equalsIgnoreCase(eventName))
				{
					bool = true;
					break;
				}
				else
				{
					bool = false;
				}
			}
		}
		else
		{
			bool = false;
		}
		return bool;
	}
	
	public boolean verifyDeletedEvent(int numOfEvents,String eventName)
	{
		int numOfEventsAfterDelete = common.getNumOfElements(driver, By.xpath(".//*[@id='list-container']/a"));
		boolean bool = false;
		if(numOfEventsAfterDelete==numOfEvents-1)
		{
			List<WebElement> eles = driver.findElements(By.xpath(".//*[@id='list-container']/a/span[3]"));
			for(WebElement ele:eles)
			{
				if(ele.getText().equalsIgnoreCase(eventName))
				{
					bool = false;
				}
				else
				{
					bool = true;
				}
			}
		}
		else
		{
			bool = false;
		}
		return bool;
	}

}
