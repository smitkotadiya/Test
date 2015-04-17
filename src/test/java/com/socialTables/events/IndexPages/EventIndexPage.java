package com.socialTables.events.IndexPages;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;

import com.socialTables.events.verifications.AttendeeManagerPage;
import com.socialTables.events.verifications.DashboardPage;
import com.socialTables.events.verifications.EventCreationPage;
import com.socialTables.events.verifications.EventThreeDPage;
import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class EventIndexPage extends AbstractPage
{
	@FindBy(xpath="//a[contains(.,'New Event')]")
	private WebElement btnNewEvent;
	@FindBy(id="s-name")
	private WebElement txtEventName;
	@FindBy(id="s-date")
	private WebElement txtStartDate;
	@FindBy(id="end-date")
	private WebElement txtEndDate;
	@FindBy(id="s-chairtype")
	private WebElement selectChairType;
	@FindBy(id="guest-list-enabled")
	private WebElement guestListCheckBox;
	@FindBy(id="uses-metric")
	private WebElement usesMatricUnitCheckBox;
	@FindBy(id="settings-done")
	private WebElement btnDoneInEventInfo;
	@FindBy(id="logo")
	private WebElement logo;
	
	@FindBy(id="close-edit")
	private WebElement btnDoneInRoomSetting;
	
	//filteration combo
	@FindBy(id="filterAuthor")
	private WebElement filterAuthor;
	@FindBy(id="filterCategory")
	private WebElement filterCategory;
	@FindBy(id="filterLocation")
	private WebElement filterLocation;
	
	@FindBy(xpath="//input[@class='events-table-search']")
	private WebElement txtSearch;
	
	@FindBy(id="showPreviousEvents")
	private WebElement btnPreviousEvent;
	@FindBy(id="showUpcomingEvents")
	private WebElement btnUpcommingEvent;
	
	@FindBy(xpath="//a[contains(.,'Dashboard tutorial')]")
	private WebElement linkDashboardTutorial;
	
	@FindBy(id="fpEditName")
	private WebElement txtRoomName;
	@FindBy(id="room-tab")
	private WebElement CustomizeRoomTab;
	
	@FindBy(xpath=".//*[@id='delete-space']")
	private WebElement btnDeleteRoom;
	@FindBy(id="search")
	private WebElement txtSerachRoom;
	@FindBy(xpath="//button[contains(.,'Search')]")
	private WebElement btnSearch;
	@FindBy(id="clone-space")
	private WebElement btnDuplicateRoom;
	@FindBy(xpath=".//*[@id='footer-event']/button[contains(.,'+')]")
	private WebElement btnAddRoom;
	@FindBy(id="btn-share")
	private WebElement btnShareEvent;
	@FindBy(xpath="//textarea[@class='text-box-expand']")
	private WebElement txtSendto;
	@FindBy(id="modal-message")
	private WebElement txtMessage;
	@FindBy(xpath="//button[contains(@class,'flat-ui-btn') and contains(@class,'share-btn')]")
	private WebElement btnShare;
	@FindBy(xpath="//button[contains(@class,'user-dropdown')]")
	private WebElement userNameDropDown;
	
	//Attendee Manager path
	@FindBy(id="tabGuest")
	private WebElement guestTab;
	@FindBy(xpath=".//*[@id='left-guest']/div[1]")
	private WebElement cliclOnAttendees;
	@FindBy(id="addFirstName")
	private WebElement txtFirstName;
	@FindBy(id="addLastName")
	private WebElement txtLastName;
	@FindBy(id="addTitle")
	private WebElement txtTitle;
	@FindBy(id="addOrganization")
	private WebElement txtOrganization;
	@FindBy(id="n-plus-input")
	private WebElement selectNumOfGuest;
	@FindBy(id="glm-check-in-input")
	private WebElement checkCheckIn;
	@FindBy(id="btn-glm-add")
	private WebElement btnAddAttendee;
	@FindBy(id="tagsBulk")
	private WebElement tabAddTag;
	@FindBy(id="addTagName")
	private WebElement txtTagName;
	@FindBy(id="mealsBulk")
	private WebElement tabAddMeal;
	@FindBy(id="addMealName")
	private WebElement txtMealName;
	@FindBy(id="guestGroupsBulk")
	private WebElement tabGroup;
	@FindBy(xpath="//a/span[contains(.,'Object editing tutorial')]")
	private WebElement objectEditingTutorialLink;
	@FindBy(xpath="//a/span[contains(.,'Room settings tutorial')]")
	private WebElement roomSettingsTutorialLink;
	@FindBy(id="btn-event-settings")
	private WebElement btnEventSettings;
	@FindBy(xpath="//a[contains(.,'UNGROUP')]")
	private WebElement tabUngroup;
	
	@FindBy(xpath="//div[@id='left-object-search']/input[@class='search']")
	private WebElement txtSearchObject;
	
	@FindBy(xpath=".//*[@id='minimap-show']")
	private WebElement btnThreedPrivew;
	@FindBy(xpath=".//*[@id='minimap-3D-header-button-container']/button[contains(@class,'ui-icon-plusthick')]")
	private WebElement btnPlus;
	
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
	

	//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]
	
	//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div[contains(@class,'odd')]/div/div[contains(@class,'col4')]
	
	
	Common common = new Common(driver);

	public EventIndexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public EventCreationPage clickOnNewEvent()
	{
		btnNewEvent.click();
		common.pause(5);
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage fillEventInfo(String criteria,String eventName,String eventType,boolean isAttendeeEnable)
	{
		if(criteria.equalsIgnoreCase("Add") && isAttendeeEnable)
		{
			common.pause(2);
			log("<b>Event Name: </b>"+eventName);
			common.type(txtEventName, eventName);
			txtEventName.sendKeys(Keys.TAB);
			common.pause(2);
			common.selectFromComboByVisibleElement(driver.findElement(By.id("s-chairtype")), eventType);
			//WebElement eventTypeButton=driver.findElement(By.xpath("//div[@class='event-type-container']/div/input[@value='"+eventType+"']"));
			//eventTypeButton.click();
			common.pause(2);
			usesMatricUnitCheckBox.click();
			btnDoneInEventInfo.click();
			common.pause(2);
		}
		else
		{
			common.type(txtEventName, eventName);
			common.pause(2);
			btnDoneInEventInfo.click();
			common.pause(2);
		}
		
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage fillRoomSettingsForm(String criteria)
	{
		common.pause(2);
		if(criteria.equalsIgnoreCase("clone"))
		{
			common.clickOn(driver, btnDoneInRoomSetting);
			common.pause(3);
		}
		else
		{
			common.clickOn(driver, btnDoneInRoomSetting);
			common.pause(3);
		}
		
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage searchRoomInEvent(String searchString)
	{
		common.type(txtSerachRoom, searchString);
		btnSearch.click();
		common.pause(2);
		return new EventCreationPage(driver);
	}
	
	public void clickOnCutomizeRoomTab()
	{
		CustomizeRoomTab.click();
		common.pause(2);
	}
	
	public DashboardPage customizeRoomSetting(String roomName)
	{
		common.type(txtRoomName, roomName);
		List<WebElement> allFloorPlan = driver.findElements(By.xpath("//button[contains(@class,'bg-button')]"));
		Random r = new Random();
		allFloorPlan.get(r.nextInt(13)).click();
		common.pause(2);
		btnDoneInRoomSetting.click();
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public DashboardPage clickOnSearchedRoom()
	{
		List<WebElement> eles = driver.findElements(By.xpath("//div[@class='listing-name']"));
		eles.get(0).click();
		common.pause(2);
		btnDoneInRoomSetting.click();
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public DashboardPage clickOnLogo()
	{
		common.clickOn(driver, logo);
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public EventCreationPage clickOnEvent()
	{
		try{
			common.pause(2);
			List<WebElement> allEles = driver.findElements(By.xpath("//div[contains(@class,'events-table-name-row-cell') and not(contains(.,'Name'))]/div/div/div/span"));
			
			common.highlightElement(driver, allEles.get(0));
			allEles.get(0).click();
			common.pause(2);
		}
		catch(Exception e)
		{
			log("There is no existing event available to view");
		}
		common.pause(2);
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("Total Available Windows Are-->"+windowHandles.size());
		System.out.println(windowHandles);
		for(String winHandle: windowHandles)
		{
			System.out.println("Driver switch on new window:---->"+winHandle);
			driver.switchTo().window(winHandle);
			common.pause(2);
		}
		return new EventCreationPage(driver);
	}
	
	public DashboardPage clickOnDeleteEvent()
	{
		try{
		 List<WebElement> allDelEles = driver.findElements(By.xpath("//i[contains(@class,'event-delete-icon')]"));
		 common.highlightElement(driver, allDelEles.get(0));
		 common.pause(2);
		 allDelEles.get(0).click();
		}
		catch(Exception e)
		{
			log("There is no existing event available to delete");
		}
		common.pause(2);
		
		driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public DashboardPage filteration(String criteria,String value)
	{
		if(criteria.equalsIgnoreCase("Author"))
		{
			driver.findElement(By.xpath("//div[@class='Select-option'][contains(.,'"+value+"')]")).click();
		}
		else if(criteria.equalsIgnoreCase("Category"))
		{
			driver.findElement(By.xpath("//div[@class='Select-option'][contains(.,'"+value+"')]")).click();
		}
		else
		{
			driver.findElement(By.xpath("//div[@class='Select-option'][contains(.,'"+value+"')]")).click();
		}
		
		return new DashboardPage(driver);
	}
	
	public DashboardPage searchEvent(String searchString)
	{
		common.type(txtSearch, searchString);
		return new DashboardPage(driver);
	}
	
	public EventCreationPage clickOnCloneIcone()
	{
		List<WebElement> allDelEles = driver.findElements(By.xpath("//i[contains(@class,'event-duplicate-icon')]"));
		//common.highlightElement(driver, allDelEles.get(0));
		common.pause(2);
		allDelEles.get(0).click();
		driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
		common.pause(2);
		Set<String> windowHandles = driver.getWindowHandles();
		for(String winHandle: windowHandles)
		{
			System.out.println("Driver switch on new window:---->"+winHandle);
			driver.switchTo().window(winHandle);
			common.pause(2);
		}
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage searchObjects(String objectName)
	{
		common.type(txtSearchObject, objectName);
		common.pause(2);
		return new EventCreationPage(driver);
	}
	
	public EventThreeDPage clickOnViewThreeDIcone()
	{
		
		try{
			 List<WebElement> allDelEles = driver.findElements(By.xpath("//i[contains(@class,'event-3d-icon')]"));
			 common.highlightElement(driver, allDelEles.get(0));
			 common.pause(2);
			 allDelEles.get(0).click();
			 common.pause(3);
			}
			catch(Exception e)
			{
				log("There is no existing event available to view in 3D");
			}
		Set<String> windowHandles = driver.getWindowHandles();
		for(String winHandle: windowHandles)
		{
			System.out.println("Driver switch on new window:---->"+winHandle);
			driver.switchTo().window(winHandle);
			common.pause(2);
		}
		return new EventThreeDPage(driver);
	}
	
	public DashboardPage clickOnUpcomingEvent()
	{
		btnUpcommingEvent.click();
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public DashboardPage clickOnPreviousEvent()
	{
		btnPreviousEvent.click();
		common.pause(2);
		return new DashboardPage(driver);
	}
	
	public DashboardPage clickOnDashboardTutorial()
	{
		linkDashboardTutorial.click();
		common.pause(1);
		return new DashboardPage(driver);
	}
	
	public EventCreationPage clickOnEventSettings()
	{
		btnEventSettings.click();
		common.pause(2);
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage clickOnDuplicateButton(String roomName)
	{
		btnDuplicateRoom.click();
		common.pause(2);
		driver.findElement(By.xpath("//div[@class='dialog-buttons']/button[contains(.,'OK')]")).click();
		common.pause(2);
		common.type(txtRoomName, roomName);
		btnDoneInRoomSetting.click();
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage addRoomInEvent(String roomName)
	{
		btnAddRoom.click();
		common.pause(2);
		common.type(txtRoomName, roomName);
		btnDoneInRoomSetting.click();
		
		return new EventCreationPage(driver);
	}

	public EventCreationPage clickOnShareEvent()
	{
		btnShareEvent.click();
		common.pause(2);
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage shareEvent(String email)
	{
		common.type(txtSendto, email);
		driver.findElement(By.xpath(".//*[@id='opt1']")).click();
		common.pause(2);
		common.type(txtMessage, "Test Automation");
		btnShare.click();
		common.pause(5);
		try{
		WebElement aleatMessgae = driver.findElement(By.xpath("//div[@class='dialog-inner']"));
		log("<b> Confirmation Aleart: </b>"+aleatMessgae.getText());
		driver.findElement(By.xpath("//div[@class='dialog-buttons']/button")).click();
		}
		catch(Exception e)
		{
			log("Confirmation aleart is not available");
		}
		
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage clickOnUserDropdown()
	{
		common.highlightElement(driver, userNameDropDown);
		userNameDropDown.click();
		common.pause(3);
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage clickOnCreatedRoom(String eventName)
	{
		driver.findElement(By.xpath(".//*[@id='sortable-rooms']/li/a[contains(.,'"+eventName+"')]")).click();
		common.pause(2);
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage clickOnDeleteButton()
	{
		btnDeleteRoom.click();
		common.pause(2);
		driver.findElement(By.xpath("//div[@class='dialog-buttons']/button[contains(.,'OK')]")).click();
		common.pause(2);
		return new EventCreationPage(driver);
	}
	
	
	
	public AttendeeManagerPage clickOnGuestTab()
	{
		guestTab.click();
		common.pause(2);
		cliclOnAttendees.click();
		common.pause(2);
		return new AttendeeManagerPage(driver);
	}
	
	public AttendeeManagerPage addGuest(String fName,String lName,String title,String numOfGuest)
	{
		common.type(txtFirstName, fName);
		common.pause(1);
		common.type(txtLastName, lName);
		common.pause(1);
		common.type(txtTitle, title);
		common.pause(1);
		common.type(txtOrganization, "Auto-QA");
		if(!numOfGuest.equalsIgnoreCase("0"))
		{
			common.selectFromComboByVisibleElement(selectNumOfGuest, numOfGuest);
			common.pause(2);
		}
		btnAddAttendee.click();
		common.pause(2);
		
		return new  AttendeeManagerPage(driver);
	}
	
	public AttendeeManagerPage deleteGuest()
	{
		try{
			WebElement deleteButton =driver.findElement(By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div[1]/div/div[contains(@class,'cell-delete')]"));
			common.scrollTo(deleteButton);
			common.pause(2);
			deleteButton.click();
			common.pause(2);
			driver.findElement(By.xpath("//div[@class='dialog-buttons']/button[contains(.,'OK')]")).click();
			common.pause(2);
		}
		catch(Exception e)
		{
			log("<b>No guest available in grid or delete button not found</b>");
		}
		return new AttendeeManagerPage(driver);
	}
	
	public AttendeeManagerPage enterTag(String tagName)
	{
		tabAddTag.click();
		common.pause(2);
		common.type(txtTagName, tagName);
		common.pause(2);
		txtTagName.sendKeys(Keys.RETURN);
		common.pause(2);
		return new AttendeeManagerPage(driver);
	}
	
	public AttendeeManagerPage assignTagToAttendee(String name)
	{
		WebElement checkBox = driver.findElement(By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div[1]/div/div[contains(@class,'koCellCheckBox')]/div/div[contains(@class,'kgCheckbox')]"));
		common.highlightElement(driver, checkBox);
		checkBox.click();
		common.pause(2);
		driver.findElement(By.xpath("//li[contains(@class,'tags')]/ul/li/a[contains(.,'"+name+"')]")).click();
		//driver.findElement(By.xpath("//li[contains(@class,'tags')]/ul/li[1]/input[@class='glm-tag-color']")).click();
		common.pause(2);
		//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div[1]/div/div[contains(.,'sas')]
		return new AttendeeManagerPage(driver);
	}
	
	public AttendeeManagerPage enterMeal(String mealName)
	{
		tabAddMeal.click();
		common.pause(2);
		common.type(txtMealName, mealName);
		common.pause(2);
		txtMealName.sendKeys(Keys.RETURN);
		common.pause(2);
		
		return new AttendeeManagerPage(driver);
	}
	
	public AttendeeManagerPage assignMealToAttendee(String name)
	{
		WebElement checkBox = driver.findElement(By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div[1]/div/div[contains(@class,'koCellCheckBox')]/div/div[contains(@class,'kgCheckbox')]"));
		common.highlightElement(driver, checkBox);
		checkBox.click();
		common.pause(2);
		List<WebElement> ele = driver.findElements(By.xpath("//li[contains(@class,'meals')]/ul/li/a[contains(.,'"+name+"')]"));
		ele.get(0).click();
		//driver.findElement(By.xpath("//li[contains(@class,'tags')]/ul/li[1]/input[@class='glm-tag-color']")).click();
		common.pause(2);
		//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div[1]/div/div[contains(.,'sas')]
		return new AttendeeManagerPage(driver);
	}
	
	public AttendeeManagerPage groupUnGroupAttendee(String scenario)
	{
		List<WebElement> allEles = driver.findElements((By.xpath("//div[@id='glmContainer']/div[contains(@class,'glm-grid')]/div[@class='kgNoSelect']/div[@class='kgViewport']/div[@class='kgCanvas']/div/div/div/div[contains(@class,'koCellCheckBox')]/div/div[contains(@class,'kgCheckbox')]")));
		for(WebElement ele:allEles)
		{
			ele.click();
			common.pause(2);
		}
		if(scenario.equalsIgnoreCase("ungroup"))
		{
			tabUngroup.click();
		}
		else
		{
			tabGroup.click();
			common.pause(2);
			driver.findElement(By.id("addGuestGroup")).click();
		}
		common.pause(2);
		return new AttendeeManagerPage(driver);
	}
	
	public EventCreationPage clickOnObjectEditingTutorial()
	{
		objectEditingTutorialLink.click();
		common.pause(2);
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage clickOnRoomSettingTutorial()
	{
		roomSettingsTutorialLink.click();
		common.pause(2);
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage clickOnThreeDPreview()
	{
		btnThreedPrivew.click();
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage clickOnPlusButton()
	{
		btnPlus.click();
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage clickOnHelpDropDown()
	{
		btnHelpDropdown.click();
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage openSupportCenter()
	{
		Actions action = new Actions(driver);
		btnHelpDropdown.click();
		common.pause(2);
		btnSupportCenter.click();
		common.pause(2);
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("Total Available Windows Are-->"+windowHandles.size());
		System.out.println(windowHandles);
		for(String winHandle: windowHandles)
		{
			System.out.println("Driver switch on new window:---->"+winHandle);
			driver.switchTo().window(winHandle);
			common.pause(2);
		}

		return new EventCreationPage(driver);
	}
	
	public EventCreationPage openVideoTutorial()
	{
		btnHelpDropdown.click();
		common.pause(2);
		btnVideoTutorial.click();
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage openTraining()
	{
		btnHelpDropdown.click();
		common.pause(2);
		btnTraining.click();
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage clickOnWalkThrough()
	{
		btnHelpDropdown.click();
		common.pause(2);
		btnWalkThrough.click();
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage openFeedback()
	{
		btnHelpDropdown.click();
		common.pause(2);
		btnFeedback.click();
		return new EventCreationPage(driver);
	}
	
	public EventCreationPage openLiveChat()
	{
		btnHelpDropdown.click();
		common.pause(2);
		btnLiveChat.click();
		return new EventCreationPage(driver);
	}
	
}
