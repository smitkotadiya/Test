package com.socialTables.events.verifications;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class EventCreationPage extends AbstractPage
{

	@FindBy(xpath=".//*[@id='dialog-header']/h3[contains(.,'Event Information')]")
	private WebElement verifyEventCreationForm;
	@FindBy(id="logo")
	private WebElement verifyLogoOnEventCreation;
	@FindBy(xpath=".//*[@id='dialog-header']/h3[contains(.,'Room Settings')]")
	private WebElement verifyRoomSetingForm;
	@FindBy(id="tabGuest")
	private WebElement verifyAttedeeTab;
	@FindBy(xpath="//h3[contains(.,'Share')]")
	private WebElement verifyShareDialog;
	@FindBy(xpath=".//*[@id='minimap-3D']")
	private WebElement verifyThreeDPriview;
	@FindBy(xpath=".//*[@id='minimap-3D-header-button-container']/button[contains(@class,'ui-icon-minusthick')]")
	private WebElement verifyFullThreeDScreen;
	
	//Verify Help DropDown Links
	@FindBy(xpath="//h2[contains(.,'Support Center')]")
	private WebElement verifySupportCenterPage;
	@FindBy(xpath="//div[@id='project_description']")
	private WebElement verifyVideoTutorialPage;
	@FindBy(xpath="//img[@class='st-logo-training']")
	private WebElement verifyTrainingPage;
	@FindBy(xpath="//img[@class='uvMastheadLogo']")
	private WebElement verifyFeedbackPage;
	@FindBy(xpath=".//*[@id='habla_topbar_div']")
	private WebElement verifyLiveChatWindow;
	
	//Help Drop Down Xpath
	@FindBy(xpath="//button[contains(.,'Help')]")
	private WebElement btnHelpDropdown;
	@FindBy(xpath="//a[contains(.,'Support Center')]")
	private WebElement btnSupportCenter;
	@FindBy(xpath="//a[contains(.,'Video Tutorials')]")
	private WebElement btnVideoTutorial;
	@FindBy(xpath="//a[contains(.,'Training')]")
	private WebElement btnTraining;
	@FindBy(xpath="//a[contains(.,'Walkthrough')]")
	private WebElement btnWalkThrough;
	@FindBy(xpath="//a[contains(.,'Feedback')]")
	private WebElement btnFeedback;
	@FindBy(xpath="//a[contains(.,'Live chat')]")
	private WebElement btnLiveChat;
	
	
	
	Common common = new Common(driver);
	public EventCreationPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public boolean verifyEventCreationPage()
	{
		return verifyEventCreationForm.isDisplayed();
	}
	
	public boolean verifyLogoInEventCreationPage()
	{
		return verifyLogoOnEventCreation.isDisplayed();
	}
	public boolean verifyRoomSettingPage()
	{
		common.pause(2);
		return verifyRoomSetingForm.isDisplayed();
	}

	public boolean verifyEventNameHeader(String eventName)
	{
		common.pause(2);
		String eventNameOnView = driver.findElement(By.xpath(".//*[@id='header-event-name']")).getText();
		System.out.println(eventNameOnView+"===="+eventName);
		if(eventName.equalsIgnoreCase(eventNameOnView))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyAttendeeListEnable()
	{
		return verifyAttedeeTab.isEnabled();
	}
	
	public boolean verifySearchedRoom(String searchString)
	{
		boolean bool = false;
		List<WebElement> eles = driver.findElements(By.xpath("//div[@class='listing-name']"));
		for(WebElement ele:eles)
		{
			if(ele.getText().equalsIgnoreCase(searchString))
			{
				bool= true;
			}
			else
			{
				bool= false;
				break;
			}
		}
		return bool;
	}
	
	public boolean verifyAddedRoomInFooter(String roomName)
	{
		WebElement roomTab = driver.findElement(By.xpath("//ul[@id='sortable-rooms']/li/a[contains(.,'"+roomName+"')]"));
		System.out.println(roomTab.getText());
		return roomTab.isDisplayed();
	}
	
	public boolean verifyShareEventDialogBox()
	{
		return verifyShareDialog.isDisplayed();
	}
	
	public boolean verifyTutorialLinks()
	{
		return driver.findElement(By.xpath("//iframe[contains(@class,'fancybox-iframe')]")).isDisplayed();
	}
	
	public boolean verifySearchedObjectIsDisplay(String className)
	{
		try
		{
			driver.findElement(By.xpath("//i[contains(@class,'"+className+"')]")).isDisplayed();
			return false;
		}
		catch(Exception e)
		{
			return true;
		}
	}
	
	public boolean verifyThreedPriview()
	{
		return verifyThreeDPriview.isDisplayed();
	}
	
	public boolean verifyFullThreeDScreen()
	{
		return verifyFullThreeDScreen.isDisplayed();
	}
	
	public boolean verifyHelpDropDown()
	{
		return btnFeedback.isDisplayed() && 
				btnLiveChat.isDisplayed() &&
				btnSupportCenter.isDisplayed() &&
				btnTraining.isDisplayed() &&
				btnLiveChat.isDisplayed() &&
				btnWalkThrough.isDisplayed();
	}
	
	public boolean verifySupportCenter()
	{
		return verifySupportCenterPage.isDisplayed();
	}
	
	public boolean verifyVideoTutorial()
	{
		return verifyVideoTutorialPage.isDisplayed();
	}
	
	public boolean verifyTraining()
	{
		return verifyTrainingPage.isDisplayed(); 
	}
	
	public boolean verifyFeedbackPage()
	{
		return verifyFeedbackPage.isDisplayed();
	}
	
	public boolean verifyLiveChat()
	{
		return verifyLiveChatWindow.isDisplayed();
	}
	
	public boolean verifyDeletedRoom(String eventName)
	{
		try{
			driver.findElement(By.xpath(".//*[@id='sortable-rooms']/li/a[contains(.,'"+eventName+"')]")).isDisplayed();
			return false;
		}
		catch(Exception e)
		{
			return true;
		}
	}
	
}