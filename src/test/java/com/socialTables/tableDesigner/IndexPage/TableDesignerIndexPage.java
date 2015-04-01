package com.socialTables.tableDesigner.IndexPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;
import com.socialTables.tableDesigner.verifications.TableDesignerPage;

public class TableDesignerIndexPage extends AbstractPage 
{
	
	@FindBy(xpath="//button[contains(.,'Create Table Design')]")
	private WebElement btnCreateTableDesign;
	@FindBy(xpath="//input[@type='text']")
	private WebElement txtTableName;
	@FindBy(xpath="//select")
	private WebElement selectType;
	@FindBy(xpath=".//*[@id='settings-modal-dimensions-section']/input[1]")
	private WebElement enterFirstDimensions;
	@FindBy(xpath=".//*[@id='settings-modal-dimensions-section']/input[2]")
	private WebElement enterSecondDimensions;
	@FindBy(xpath=".//*[@id='settings-modal-input-section']/div[5]/input")
	private WebElement enterNumOfTebles;
	@FindBy(xpath=".//*[@id='settingsModal']/div[3]/a[1]")
	private WebElement btnCancel;
	@FindBy(id="save-table-settings-btn")
	private WebElement btnSave;
	@FindBy(id="logo")
	private WebElement btnLogo;
	@FindBy(xpath="//button[contains(.,'Share')]")
	private WebElement btnShare;
	@FindBy(xpath="//button[contains(.,'Settings')]")
	private WebElement btnSettings;
	@FindBy(xpath="//button[contains(.,'Save as Template')]")
	private WebElement btnSaveAsTemplate;
	

	Common common = new Common(driver);
	public TableDesignerIndexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public TableDesignerPage clickOnTableDesign()
	{
		btnCreateTableDesign.click();
		return new TableDesignerPage(driver);
	}
	
	public TableDesignerPage fillTableDetail(String tableName)
	{
		common.type(txtTableName, tableName);
		btnSave.click();
		return new TableDesignerPage(driver);
	}
	
	public TableDesignerPage clickOnLogo()
	{
		btnLogo.click();
		return new TableDesignerPage(driver);
	}
}
