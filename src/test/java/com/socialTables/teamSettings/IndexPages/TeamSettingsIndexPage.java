package com.socialTables.teamSettings.IndexPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;
import com.socialTables.teamSettings.verifications.TeamSettingsPage;

public class TeamSettingsIndexPage extends AbstractPage
{
	
	@FindBy(xpath="//a[contains(.,'Event Categories')]")
	private WebElement tabEventCategory;
	
	@FindBy(xpath="//a[contains(.,'Tables & Objects')]")
	private WebElement tabTableAndObjects;
	@FindBy(xpath="//a[contains(.,'Print/Export Settings')]")
	private WebElement tabPrintAndExportSettings;
	@FindBy(xpath="//a[contains(.,'Measurement Settings')]")
	private WebElement tabMeasurementSettings;
	@FindBy(xpath="//a[contains(.,'Language Settings')]")
	private WebElement tabLanguageSettings;
	@FindBy(xpath="//a[contains(.,'Custom Objects')]")
	private WebElement tabCustomObjects;
	
	@FindBy(xpath="//button[contains(.,'Add Custom Category')]")
	private WebElement btnAddCategory;
	@FindBy(xpath="//input[@data-bind='value: newCategory.name']")
	private WebElement txtNewCategory;
	@FindBy(id="toolbar-rectangle-table-size")
	private WebElement selectCategoryIcon;
	
	
	Common common = new Common(driver);

	public TeamSettingsIndexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public TeamSettingsPage clickOnEventCategory()
	{
		tabEventCategory.click();
		common.pause(2);
		return new TeamSettingsPage(driver);
	}
	
	public TeamSettingsPage addNewCategory(String category,int rowNum) throws InterruptedException
	{
		btnAddCategory.click();
		common.pause(2);
		common.type(txtNewCategory, category);
		String categoryIcon = common.selectRandomOptionFromCombo(By.id("toolbar-rectangle-table-size"), driver);
		common.selectFromComboByVisibleElement(selectCategoryIcon, categoryIcon);
		common.pause(2);
		driver.findElement(By.xpath("//*[@id='custom_event_categories_table']/tbody/tr["+(rowNum+1)+"]/td[4]/button[1]")).click();
		
		
		return new TeamSettingsPage(driver);
	}
	

}
