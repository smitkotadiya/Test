package com.socialTables.tableDesigner.verifications;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;

public class TableDesignerPage extends AbstractPage
{

	@FindBy(xpath="//h3[contains(.,'Table Design Settings')]")
	private WebElement verifyCreateTableDesignerPage;
	
	
	public TableDesignerPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyTableDesignerPage()
	{
		return verifyCreateTableDesignerPage.isDisplayed();
	}
	
	public boolean verifyAddedTableDesign(String tableName,int numOfTableDesign)
	{
		List<WebElement> numOfTablesAfterAddition = driver.findElements(By.xpath(".//*[@id='tables-settings-tbody']/tr/td[1]"));
		WebElement displayElement = driver.findElement(By.xpath(".//*[@id='tables-settings-tbody']/tr/td[contains(.,'"+tableName+"')]"));
		
		return (numOfTableDesign==numOfTablesAfterAddition.size()-1) && displayElement.isDisplayed();
	}
	
	public boolean verifyDeletedTableDesign(int numOfTabelDesign)
	{
		List<WebElement> numOfTablesAfterDelete = driver.findElements(By.xpath(".//*[@id='tables-settings-tbody']/tr/td[1]"));
		
		return (numOfTablesAfterDelete.size()==numOfTabelDesign-1);
	}
}
