package com.socialTables.TeamMemberVanue.IndexPage;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.bcel.generic.Select;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import ch.qos.logback.core.joran.action.Action;

import com.socialTables.TeamMemberVanue.Verifications.TeamMemberAndVenueVerificationPage;
import com.socialTables.TeamMemberVanue.Verifications.VenueCreationPage;
import com.socialTables.events.verifications.DashboardPage;
import com.socialTables.events.verifications.EventCreationPage;
import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class TeamMemberAndVenueIndexPage extends AbstractPage
{

	@FindBy(xpath="//a[contains(.,'New Venue')]")
	private WebElement btnNewVenue;
	@FindBy(id="name")
	private WebElement txtVenueName;
	@FindBy(id="address")
	private WebElement txtVenueAddress;
	@FindBy(id="city")
	private WebElement txtVenueCity;
	@FindBy(id="state")
	private WebElement selectState;
	@FindBy(id="zip")
	private WebElement txtZip;
	@FindBy(id="maxOccupancy")
	private WebElement txtMaxoccupacy;
	@FindBy(xpath="//input[@type='number']")
	private WebElement selectPerimeter;
	
	@FindBy(xpath="//button[contains(.,'Next')]")
	private WebElement btnVenueNext;
	@FindBy(xpath="//button[contains(.,'Cancel')]")
	private WebElement btnVenueCancel;
	@FindBy(id="fpEditHeight")
	private WebElement txtVenueCeilingHeight;
	@FindBy(xpath="//button[contains(.,'Save')]")
	private WebElement btnSave;
	
	@FindBy(xpath="//button[contains(.,'New Member')]")
	private WebElement btnNewMember;
	@FindBy(name="email")
	private WebElement txtMemberEmail;
	@FindBy(name="role")
	private WebElement selectMemberRole;
	@FindBy(xpath="//button[contains(.,'Done')]")
	private WebElement btnDone;
	
	//Join Form
	@FindBy(name="name")
	private WebElement txtName;
	@FindBy(name="job_title")
	private WebElement txtJobtitle;
	@FindBy(name="password")
	private WebElement txtPassword;
	@FindBy(name="confirm_password")
	private WebElement txtConfirmPassword;
	@FindBy(xpath="//input[contains(@value,'START PLANNING')]")
	private WebElement btnStartPlanningButton;
	
	@FindBy(id="search")
	private WebElement txtSearch;
	@FindBy(xpath="//button[contains(.,'Search')]")
	private WebElement btnSearch;
	@FindBy(xpath="//button[contains(.,'Cancel')]")
	private WebElement btnCancel;
	@FindBy(xpath="//button[contains(.,'My Account')]")
	private WebElement btnMyAccount;
	@FindBy(xpath="//a[contains(.,'Back to Venue Details')]")
	private WebElement backToVenueDetailLink;
	@FindBy(xpath="//a[contains(.,'Back to Background Details')]")
	private WebElement backToBackgroundLink;
	
	@FindBy(xpath="//button[contains(.,'Resend Email') and not(contains(@style,'display: none;'))]")
	private WebElement btnResendButton;
	
	
	Common common = new Common(driver);
	public TeamMemberAndVenueIndexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public VenueCreationPage newOrEditVenue(boolean isEditable)
	{
		if(!isEditable)
		{
			btnNewVenue.click();
			common.pause(2);
		}
		else
		{
			Actions action = new Actions(driver);
			WebElement firstRow = driver.findElement(By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')][1]/div"));
			action.moveToElement(firstRow).build().perform();
			common.pause(2);
			WebElement editIcon = driver.findElement(By.xpath("//div[contains(@class,'listings-container')]/div[1]/div[3]/a/img "));
//			action.moveToElement(editIcon).click().perform();
			common.jsClick(editIcon);
			System.out.println("Click Done on 'Edit' button");
		}
		
		return new VenueCreationPage(driver);
	}
	
	
	public VenueCreationPage fillVenueDetail(String venueName,boolean isEditable) throws InterruptedException
	{
		if(!isEditable)
		{
			common.type(txtVenueName, venueName);
			txtVenueAddress.sendKeys("auto-address");
			txtVenueCity.sendKeys("auto-city");
			String state = common.selectRandomOptionFromCombo(By.id("state"), driver);
			common.selectFromComboByVisibleElement(selectState, state);
			txtZip.sendKeys(RandomStringUtils.randomNumeric(5));
			txtMaxoccupacy.sendKeys(RandomStringUtils.randomNumeric(2));
			btnVenueNext.click();
			common.pause(2);
		}
		else
		{
			common.type(txtVenueName, venueName);
			txtVenueAddress.sendKeys("auto-address");
			txtVenueCity.sendKeys("auto-city");
			String state = common.selectRandomOptionFromCombo(By.id("state"), driver);
			common.selectFromComboByVisibleElement(selectState, state);
			txtZip.sendKeys(RandomStringUtils.randomNumeric(5));
			txtMaxoccupacy.sendKeys(RandomStringUtils.randomNumeric(2));
			btnVenueNext.click();
			common.pause(2);
		}
		
		return new VenueCreationPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage deleteVenue()
	{
		Actions action = new Actions(driver);
		WebElement firstRow = driver.findElement(By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')][1]/div"));
		action.moveToElement(firstRow).build().perform();
		common.pause(2);
		WebElement deleteIcon = driver.findElement(By.xpath("//div[@class='delete-button']"));
		//action.moveToElement(firstRow).click(deleteIcon).perform();
		common.jsClick(deleteIcon);
		common.pause(4);
		try{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		catch(Exception e)
		{
			log("Problem with handle error");
		}
		common.pause(3);
		System.out.println("Click Done on 'Delete' button");
		
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public EventCreationPage createEventForVenue()
	{
		Actions action = new Actions(driver);
		WebElement firstRow = driver.findElement(By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')][1]/div"));
		action.moveToElement(firstRow).build().perform();
		common.pause(2);
		WebElement eventButton = driver.findElement(By.xpath("//div[@class='new-event-button']/span"));
		//action.moveToElement(firstRow).click(deleteIcon).perform();
		common.jsClick(eventButton);
		return new EventCreationPage(driver);
	}
	
	public VenueCreationPage selectAnyBackgroud()
	{
		List<WebElement> allBackgrounds = driver.findElements(By.xpath("//div[@class='pattern-container']/button"));
		Random randomGenerator = new Random();
		allBackgrounds.get(randomGenerator.nextInt(5)).click();
		btnVenueNext.click();
		common.pause(2);
		return new VenueCreationPage(driver);
	}
	
	public VenueCreationPage selectAnyWallTexture()
	{
		List<WebElement> allBackgrounds = driver.findElements(By.xpath(".//*[@class='wallTexture']"));
		Random randomGenerator = new Random();
		allBackgrounds.get(randomGenerator.nextInt(2)).click();
		txtVenueCeilingHeight.sendKeys("50");
		btnSave.click();
		
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
		common.pause(2);
		return new VenueCreationPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage clickOnNewMember()
	{
		btnNewMember.click();
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage fillNewMemberDetail(String email, String role)
	{
		txtMemberEmail.sendKeys(email);
		common.pause(2);
		org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//select[@name='role']")));
		select.selectByVisibleText(role);
		common.pause(2);
		btnDone.click();
		
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage verifyEmail(String email)
	{
		common.openUrlInNewTab(driver, "http://mailinator.com/");
		driver.findElement(By.id("inboxfield")).sendKeys(email);
		driver.findElement(By.xpath("//btn[contains(.,'Check it')]")).click();
		common.pause(2);
		try{
			
		driver.findElement(By.xpath(".//*[@id='mailcontainer']/li[1]/a/div[2]")).click();
		common.pause(2);
		
		}
		catch(Exception e)
		{
			log("<b> Member may not get 'Confirmation Email' or 'xpath' changed</b>");
		}
		try{
			common.pause(2);
			driver.switchTo().frame(driver.findElement(By.name("rendermail")));
			common.pause(2);
			common.highlightElement(driver, driver.findElement(By.xpath("html/body/div[1]/div/div/table[2]/tbody/tr[2]/td[2]/a/font")));
			System.out.println("========Switch on iframe========");
			driver.findElement(By.xpath("html/body/div[1]/div/div/table[2]/tbody/tr[2]/td[2]/a/font")).click();
			//common.jsClick(driver.findElement(By.xpath("html/body/div[1]/div/div/table[2]/tbody/tr[2]/td[2]/a/font")));
			System.out.println("========Switch on Mail Window========");
			driver.switchTo().window(currentWindowHandle);
			common.pause(2);
			//System.out.println("=============="+"Click Performed"+driver.findElement(By.xpath("//[@id='content']/tbody/tr[2]/td[2]/a/font")).getText());
		}
		catch(Exception e)
		{
			log("<b> Not able to click on 'Join Team'</b>");
		}
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("Total Available Windows Are-->"+windowHandles.size());
		System.out.println(windowHandles);
		for(String winHandle: windowHandles)
		{
//			if(!winHandle.equalsIgnoreCase(currentWindowHandle))
//			{gfgg
				System.out.println("Driver switch on new window:---->"+winHandle);
				driver.switchTo().window(winHandle);
				common.pause(2);
				//break;
			//}
		}
	
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage searchVenue(String searchString)
	{
		common.type(txtSearch, searchString);
		btnSearch.click();
		common.pause(2);
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public DashboardPage fillJoinForm()
	{
		common.pause(2);
		txtJobtitle.sendKeys("Auto-"+org.apache.commons.lang.RandomStringUtils.randomAlphabetic(3));
		txtName.sendKeys("Auto "+RandomStringUtils.randomAlphabetic(4));
		txtPassword.sendKeys(password_Owner);
		txtConfirmPassword.sendKeys(password_Owner);
		btnStartPlanningButton.click();
		common.pause(2);
		
		return new DashboardPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage removeMember()
	{
		List<WebElement> allEles = driver.findElements(By.xpath(".//*[@id='members-table']/tbody/tr[contains(.,'Auto')]/td[4]/button[contains(.,'Remove')]"));
		try{
		allEles.get(0).click();
		}
		catch(Exception e)
		{
			log("<b> There is no record available which is generated by automation script</b>");
		}
		Alert aleart = driver.switchTo().alert();
		aleart.accept();
		common.pause(2);
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage clickOnCancel()
	{
		btnCancel.click();
		common.pause(2);
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage clickOnMyAccount()
	{
		btnMyAccount.click();
		common.pause(2);
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public VenueCreationPage clickOnBackToVenueDetail()
	{
		backToVenueDetailLink.click();
		common.pause(2);
		return new VenueCreationPage(driver);
	}

	public VenueCreationPage clickOnBackToBackground()
	{
		backToBackgroundLink.click();
		common.pause(2);
		return new VenueCreationPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage clickOnResendEmail()
	{
		btnResendButton.click();
		common.pause(2);
		return new TeamMemberAndVenueVerificationPage(driver);
	}
}
