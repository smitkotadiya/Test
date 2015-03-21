package com.socialTables.teamSettings.IndexPages;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.Alert;
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
	@FindBy(xpath="//button[contains(.,'Create a Custom Chair')]")
	private WebElement btnCreateCustomChair;
	@FindBy(id="chair-name")
	private WebElement txtChairName;
	@FindBy(xpath="//div[@class='chair-content']/div/select")
	private WebElement selectChairType;
	@FindBy(xpath="//div[@class='save-cancel-elem']/button[contains(.,'Save')]")
	private WebElement btnSave;
	
	
	
	
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
	
	public TeamSettingsPage clickOnMeasurmentSettings()
	{
		tabMeasurementSettings.click();
		common.pause(2);
		return new TeamSettingsPage(driver);
	}
	
	public TeamSettingsPage clickOnPrintAndExport()
	{
		tabPrintAndExportSettings.click();
		common.pause(2);
		return new TeamSettingsPage(driver);
	}
	
	public TeamSettingsPage clickOnTableAndObject()
	{
		tabTableAndObjects.click();
		common.pause(2);
		return new TeamSettingsPage(driver);
	}
	
	public TeamSettingsPage clickOnLanguageSettings()
	{
		tabLanguageSettings.click();
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
		driver.findElement(By.xpath("//*[@id='custom_event_categories_table']/tbody/tr["+(rowNum)+"]/td[4]/button[1]")).click();
		
		return new TeamSettingsPage(driver);
	}
	
	public TeamSettingsPage deleteEventCategory()
	{
		try{
		List<WebElement> allEles = driver.findElements(By.xpath("//button[contains(.,'Delete')]"));
		allEles.get(0).click();
		common.pause(2);
		Alert alerat = driver.switchTo().alert();
		alerat.accept();
		common.pause(2);
		}
		catch(Exception e)
		{
			log("<b> There is no custom category available to delete </b>");
		}
		
		return new TeamSettingsPage(driver);
	}
	
	public TeamSettingsPage clickOnCustomObjects()
	{
		tabCustomObjects.click();
		common.pause(2);
		return new TeamSettingsPage(driver);
	}
	
	public TeamSettingsPage clickOnCreateCustomChair()
	{
		btnCreateCustomChair.click();
		common.pause(2);
		return new TeamSettingsPage(driver);
	}
	
	public TeamSettingsPage createCustomObject(String chairName)
	{
		String size = RandomStringUtils.randomNumeric(2);
		List<WebElement> allEles = driver.findElements(By.xpath("//input[@class='chair-input']"));
		common.type(allEles.get(0),size);
		common.type(allEles.get(1), size);
		common.type(txtChairName, chairName);
		btnSave.click();
		common.pause(2);
		return new TeamSettingsPage(driver);
	}
	
	public TeamSettingsPage deleteCustomObject()
	{
		try{
		List<WebElement> allEles = driver.findElements(By.xpath("//button[contains(.,'Delete')]"));
		allEles.get(0).click();
		common.pause(2);
		}
		catch(Exception e)
		{
			log("<b> There is no custom object available to delete </b>");
		}
		return new TeamSettingsPage(driver);
	}
	

}
