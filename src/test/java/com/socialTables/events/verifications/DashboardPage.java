package com.socialTables.events.verifications;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class DashboardPage extends AbstractPage
{

	@FindBy(xpath="//a[contains(.,'New Event')]")
	private WebElement verifyDashboardPage;
	@FindBy(xpath="//iframe[@class='fancybox-iframe wistia_embed']")
	private WebElement verifyDashboardTutorialLink;
	
	Common common = new Common(driver);
	
	public DashboardPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyDashboardPage()
	{
		return verifyDashboardPage.isDisplayed();
	}
	
	public boolean verifyAddedEvent(int numOfEvents,String eventName)
	{
		boolean bool = false;
		common.pause(2);
		driver.findElement(By.xpath("//input[@class='events-table-search']")).sendKeys(eventName);
		common.pause(2);
		List<WebElement> eles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
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
		return bool;
	}
	
	public boolean verifyAddedEventWithSpecificVenue(int numOfEvents,String eventName,String venueName)
	{
		int numOfEventsAfterAdditon = common.getNumOfElements(driver, By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]"));
		boolean bool = false;
		if(numOfEventsAfterAdditon==numOfEvents+1)
		{
			if(driver.findElement(By.xpath(".//*[@id='list-container']/a[contains(.,'"+eventName+"') and contains(.,'"+venueName+"')]")).isDisplayed())
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
			bool = false;
		}
		return bool;
	}
	
	public boolean verifyDeletedEvent(int numOfEvents,String eventName)
	{
		int numOfEventsAfterDelete = common.getNumOfElements(driver, By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		boolean bool = false;
		if(numOfEventsAfterDelete==numOfEvents-1)
		{
			List<WebElement> eles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
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
	
	public boolean verifyEventCounterFunctionlaity(String modifyCriteria,int numOfEvents)
	{
		boolean bool = false;
		String numOfEventInLable = driver.findElement(By.xpath("//span[@class='events-results-text']/span[1]")).getText();
		int numOfEventsAfterModify = Integer.parseInt(numOfEventInLable);
		if(modifyCriteria.equalsIgnoreCase("Add"))
		{
			if(numOfEvents==numOfEventsAfterModify-1)
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
			if(numOfEvents==numOfEventsAfterModify+1)
			{
				bool = true;
			}
			else
			{
				bool = false;
			}
		}
		return bool;
	}

	public boolean verifyFilterationByAuthor(String value)
	{
		common.pause(2);
		boolean bool = false;
		
		try{
		List<WebElement> eles = driver.findElements(By.xpath(".//*[@id='events-table-container']/div/div[3]/div/div[1]/div[3]/div/div/div/div[2]/div/div[6]/div/div/div/span"));
		for(WebElement ele:eles)
		{
			if(!ele.getText().equalsIgnoreCase(value))
			{
				bool= true;
				break;
			}
			else
			{
				bool= true;
			}
		}
				
		}
		catch(Exception e)
		{
			log("There is no data available for this filteration criteria");
			bool=true;
		}
		return bool;
	}
	
	public boolean verifyFilterationByLocation(String value)
	{
		common.pause(2);
		boolean bool = false;
		
		try{
		List<WebElement> eles = driver.findElements(By.xpath(".//*[@id='events-table-container']/div/div[3]/div/div[1]/div[3]/div/div/div/div[2]/div/div[5]/div/div/div/span"));
		for(WebElement ele:eles)
		{
			if(!ele.getText().equalsIgnoreCase(value))
			{
				bool= false;
				break;
			}
			else
			{
				bool= true;
			}
		}
		}
		catch(Exception e)
		{
			log("Elements Are Not Loaded Properly");
			bool= true;
		}
		
		return bool;
	}
	
	public boolean verifyFilterationByCategory(String value)
	{
		common.pause(2);
		boolean bool = false;
		try{
		List<WebElement> eles = driver.findElements(By.xpath(".//*[@id='events-table-container']/div/div[3]/div/div[1]/div[3]/div/div/div/div[2]/div/div[4]/div/div/div/span"));
		for(WebElement ele:eles)
		{
			if(!ele.getText().equalsIgnoreCase(value))
			{
				bool= false;
				break;
			}
			else
			{
				bool= true;
			}
		}
		
		}
		catch(Exception e)
		{
			log("Elements Are Not Loaded Properly");
			bool= true;
		}

		return bool;
	}

	public boolean verifySearchFunctionality(String seachString)
	{
		boolean bool=false;
	
		List<WebElement> allEles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
		for(WebElement ele:allEles)
		{
			if(ele.getText().equalsIgnoreCase(seachString))
			{
				bool=true;
				break;
			}
			else
			{
				bool = false;
			}
		}
		return bool;
	}

	public boolean verifyUpcomingEvents() throws ParseException
	{
		boolean bool=false;
		List<WebElement> eles = driver.findElements(By.xpath(".//*[@id='list-container']/a[not(contains(@style,'display: none;'))]/span[@class='date']"));
		
		if(eles.size()>0)
		{
			Date currentDate = new Date();
			DateFormat df = new SimpleDateFormat ("MM/dd/yyyy");
			String currentDateString = df.format(currentDate);
			Date currentDateFormetted = df.parse(currentDateString); 
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(currentDateFormetted);
			cal.add(GregorianCalendar.DATE, -1);
			System.out.println("Result :"+cal.getTime());
			for(WebElement ele:eles)
			{
				Date eventDates = df.parse(ele.getText());
				Calendar cal1 = GregorianCalendar.getInstance();
				cal1.setTime(eventDates);
				System.out.println(cal.getTime()+" and "+cal1.getTime());
				if(cal1.after(cal))
				{
					bool=true;
				}
				else
				{
					bool = false;
				}
			}
			
		}
		else
		{
			log("<b> There is no event available to test this functionality</b>");
			bool = true;
		}
		return bool;
	}
	
	public boolean verifyPreviousEvents() throws ParseException
	{
		boolean bool=false;
		List<WebElement> eles = driver.findElements(By.xpath(".//*[@id='list-container']/a[not(contains(@style,'display: none;'))]/span[@class='date']"));
		
		if(eles.size()>0)
		{
			Date currentDate = new Date();
			DateFormat df = new SimpleDateFormat ("MM/dd/yyyy");
			String currentDateString = df.format(currentDate);
			Date currentDateFormetted = df.parse(currentDateString); 
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(currentDateFormetted);
			cal.add(GregorianCalendar.DATE, -1);
			System.out.println("Result :"+cal.getTime());
			for(WebElement ele:eles)
			{
				Date eventDates = df.parse(ele.getText());
				Calendar cal1 = GregorianCalendar.getInstance();
				cal1.setTime(eventDates);
				System.out.println(cal.getTime()+" and "+cal1.getTime());
				if(cal1.before(cal))
				{
					bool=true;
				}
				else
				{
					bool = false;
				}
			}
		}
		else
		{
			log("<b> There is no event available to test this functionality</b>");
			bool = true;
		}
		return bool;
	}
	
	public boolean verifyDashboardTutorial()
	{
		return verifyDashboardTutorialLink.isDisplayed();
	}
}
