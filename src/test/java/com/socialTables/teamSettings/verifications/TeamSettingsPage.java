package com.socialTables.teamSettings.verifications;

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
	
	public boolean verifyEventCategoryPage()
	{
		return verifyEventCategoryPage.isDisplayed();
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

}
