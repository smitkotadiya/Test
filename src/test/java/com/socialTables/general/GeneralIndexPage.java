package com.socialTables.general;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.init.Common;

public class GeneralIndexPage extends AbstractPage
{

	@FindBy(xpath="//a[contains(.,'Login')]")
	private WebElement clickOnLoginTab;
	
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

}
