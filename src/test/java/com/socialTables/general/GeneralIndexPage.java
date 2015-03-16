package com.socialTables.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.init.Common;

public class GeneralIndexPage extends AbstractPage
{

	@FindBy(xpath="//a[contains(.,'Login')]")
	private WebElement clickOnLoginTab;
	@FindBy(xpath="//div[@class='contextual-links']/a[contains(.,'Team Members & Venues')]")
	private WebElement teamMemberAndVenue;
	@FindBy(xpath="//div[@class='contextual-links']/a[contains(.,'Team Settings')]")
	private WebElement teamSettings;
	@FindBy(xpath="//div[@class='contextual-links']/a[contains(.,'Statistics')]")
	private WebElement Statastics;
	@FindBy(xpath="//div[@class='contextual-links']/a[contains(.,'Corporate Dashboard')]")
	private WebElement corpporateDashboard;
	@FindBy(xpath="//div[@class='contextual-links']/a[contains(.,'Events')]")
	private WebElement events;
	@FindBy(xpath="//div[@class='contextual-links']/a[contains(.,'Table Designer')]")
	private WebElement tableDesigner;
	
	Common common = new Common(driver);
	
	public GeneralIndexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void clickOnLoginTab()
	{
		clickOnLoginTab.click();
		common.pause(2);
	}

	public GeneralVerificationPage performSorting(int columnNum)
	{
		
		driver.findElement(By.xpath("html/body/div[4]/div[4]/div[2]/a["+columnNum+"]")).click();
		common.pause(3);
		return new GeneralVerificationPage(driver);
	}

	public ArrayList<String> getAllRecordsOfColumn(String columnName)
	{
		List<WebElement> allEles = driver.findElements(By.xpath(".//*[@id='list-container']/a[not(contains(@style,'display: none;'))]/span[@class='"+columnName+"']"));
		ArrayList<String> allRecords = new ArrayList<String>();
		for(WebElement ele:allEles)
		{
			allRecords.add(ele.getText());
		}
		return allRecords;
	}
	
	public void getAllRecordsInAscending(ArrayList<String> allRecords)
	{
		Collections.sort(allRecords);
	}

	public void getAllRecordsInDescending(ArrayList<String> allRecords)
	{
		Collections.sort(allRecords,Collections.reverseOrder());
	}
	
	public GeneralVerificationPage navigateToTeamMemberAndVenue()
	{
		teamMemberAndVenue.click();
		return new GeneralVerificationPage(driver);
	}
	public GeneralVerificationPage navigateToTeamSettings()
	{
		teamSettings.click();
		return new GeneralVerificationPage(driver);
	}
	public GeneralVerificationPage navigateToTableDesigner()
	{
		tableDesigner.click();
		return new GeneralVerificationPage(driver);
	}
	
	public GeneralVerificationPage navigateToStatistics()
	{
		Statastics.click();
		return new GeneralVerificationPage(driver);
	}
	public GeneralVerificationPage navigateToCorporateDashboardPage()
	{
		corpporateDashboard.click();
		return new GeneralVerificationPage(driver);
	}
	public GeneralVerificationPage navigateToEvent()
	{
		events.click();
		return new GeneralVerificationPage(driver);
	}
	
	public GeneralVerificationPage checkMailInMailinator(String email)
	{
		common.type(driver.findElement(By.id("inboxfield")), email);
		driver.findElement(By.xpath("//btn[contains(.,'Check it')]")).click();
		common.pause(2);
		
		return new GeneralVerificationPage(driver);
	}
	
	
	
}
