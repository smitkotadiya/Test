package com.socialTables.TeamMemberVanue.IndexPage;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.TeamMemberVanue.Verifications.TeamMemberAndVenueVerificationPage;
import com.socialTables.TeamMemberVanue.Verifications.VenueCreationPage;
import com.socialTables.events.verifications.DashboardPage;
import com.socialTables.general.AbstractPage;
import com.socialTables.general.GeneralVerificationPage;
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
	
	Common common = new Common(driver);
	public TeamMemberAndVenueIndexPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public VenueCreationPage clickOnNewVenue()
	{
		btnNewVenue.click();
		common.pause(2);
		return new VenueCreationPage(driver);
	}
	
	public VenueCreationPage fillVenueDetail(String venueName) throws InterruptedException
	{
		txtVenueName.sendKeys(venueName);
		txtVenueAddress.sendKeys("auto-address");
		txtVenueCity.sendKeys("auto-city");
		String state = common.selectRandomOptionFromCombo(By.id("state"), driver);
		common.selectFromComboByVisibleElement(selectState, state);
		txtZip.sendKeys(RandomStringUtils.randomNumeric(5));
		txtMaxoccupacy.sendKeys(RandomStringUtils.randomNumeric(2));
		btnVenueNext.click();
		
		return new VenueCreationPage(driver);
	}
	
	public VenueCreationPage selectAnyBackgroud()
	{
		List<WebElement> allBackgrounds = driver.findElements(By.xpath("//div[@class='pattern-container']/button"));
		Random randomGenerator = new Random();
		allBackgrounds.get(randomGenerator.nextInt(5)).click();
		btnVenueNext.click();
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
		common.selectFromComboByVisibleElement(selectMemberRole, role);
		btnDone.click();
		
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public TeamMemberAndVenueVerificationPage verifyEmail(String email)
	{
		common.openUrlInNewTab(driver, "http://mailinator.com/");
		driver.findElement(By.id("inboxfield")).sendKeys(email);
		driver.findElement(By.xpath("//btn[contains(.,'Check it')]")).click();
		common.pause(2);
		driver.findElement(By.xpath(".//*[@id='mailcontainer']/li[1]/a/div[2]")).click();
		common.pause(2);
		driver.findElement(By.xpath("//a[contains(.,'Join Team')]")).click();
		common.pause(2);
		driver.findElement(By.tagName("body")).sendKeys(Keys.COMMAND+"3");
		
		return new TeamMemberAndVenueVerificationPage(driver);
	}
	
	public DashboardPage fillJoinForm()
	{
		txtJobtitle.sendKeys("Auto-"+org.apache.commons.lang.RandomStringUtils.randomAlphabetic(3));
		txtName.sendKeys("Auto "+RandomStringUtils.randomAlphabetic(4));
		txtPassword.sendKeys("patel22781");
		txtConfirmPassword.sendKeys("patel22781");
		btnStartPlanningButton.click();
		
		return new DashboardPage(driver);
	}
	
	
	
}
