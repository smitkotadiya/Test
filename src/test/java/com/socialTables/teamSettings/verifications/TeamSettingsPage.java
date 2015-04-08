package com.socialTables.teamSettings.verifications;

import java.util.List;

import org.apache.regexp.recompile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class TeamSettingsPage extends AbstractPage
{

	public TeamSettingsPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	Common common = new Common(driver);
	@FindBy(xpath="//h2[contains(.,'Event Categories')]")
	private WebElement verifyEventCategoryPage;
	@FindBy(xpath="//button[contains(.,'Create a Custom Chair')]")
	private WebElement verifyCustomObjectPage;
	@FindBy(xpath="//h2[contains(.,'Floor Plan Export Settings')]")
	private WebElement verifyPrintAndExport;
	@FindBy(xpath="//h2[contains(.,'Availability')]")
	private WebElement verifyTableAndObjectPage;
	@FindBy(xpath="//h2[contains(.,'Measurement Settings')]")
	private WebElement verifyMeasurmentSetting;
	@FindBy(xpath="//h2[contains(.,'Language Settings')]")
	private WebElement verifyLanguageSettings;

	
	
	public boolean verifyEventCategoryPage()
	{
		return verifyEventCategoryPage.isDisplayed();
	}
	
	public boolean verifyPrintExportPage()
	{
		return verifyPrintAndExport.isDisplayed();
	}
	public boolean verifyLanguageSettings()
	{
		return verifyLanguageSettings.isDisplayed();
	}
	
	public boolean verifyTableAndObjectPage()
	{
		return verifyTableAndObjectPage.isDisplayed();
	}
	
	public boolean verifyMeausrmentSettings()
	{
		return verifyMeasurmentSetting.isDisplayed();
	}
	
	public boolean verifyCustomObjectPage()
	{
		return verifyCustomObjectPage.isDisplayed();
	}
	
	
	public boolean verifyAddedCategory(int numOfRow,String categoryName)
	{
		int numOfRowAfterAddition = common.getNumOfElements(driver, By.xpath(".//*[@id='custom_event_categories_table']/tbody/tr"));
		WebElement ele = driver.findElement(By.xpath(".//*[@id='custom_event_categories_table']/tbody/tr[contains(.,'"+categoryName+"')]/td[2]"));
		if((numOfRow==numOfRowAfterAddition-1) && ele.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verify()
	{
		return true;
	}
	
	public boolean verifyDeletedCategory(int numOfRow)
	{
		common.pause(2);
		int numOfRowAfterDeletion = common.getNumOfElements(driver, By.xpath(".//*[@id='custom_event_categories_table']/tbody/tr"));
		return (numOfRow==numOfRowAfterDeletion+1);
	}
	
	public boolean verifyAddedObject(String name,int numOfObjects)
	{
		common.pause(2);
		boolean bool =false;
		List<WebElement> allChairs = driver.findElements(By.xpath("//div[@class='chair-content']/span[@class='chair-name']/span[2]"));
		int numOfObjectsAfterAddition = common.getNumOfElements(driver, By.xpath("//div[@class='chair-content']/span[@class='chair-name']/span[2]"));
		if(numOfObjectsAfterAddition==numOfObjects+1)
		{
			for(WebElement ele:allChairs)
			{
				System.out.println(name+"<-------->"+ele.getText());
				if(name.equalsIgnoreCase(ele.getText()))
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
		return bool;
	}
	
	
	public boolean verifyDeletedObject(int numOfRow)
	{
		int numOfObjectsAfterDeletion = common.getNumOfElements(driver, By.xpath("//div[@class='chair-content']/span[@class='chair-name']/span[2]"));
		return (numOfObjectsAfterDeletion==numOfRow-1);
	}
	
	public boolean verifyHideButtonLable(WebElement ele)
	{
		return "Hide".equalsIgnoreCase(ele.getText());
	}
	
	public boolean verifyShowButtonLable(WebElement ele)
	{
		return "Show".equalsIgnoreCase(ele.getText());
	}

}
