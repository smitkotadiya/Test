package com.socialTables.TeamMemberVanue.Verifications;

import java.util.List;

import org.bouncycastle.asn1.cmp.OOBCertHash;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class TeamMemberAndVenueVerificationPage extends AbstractPage
{

	@FindBy(name="email")
	private WebElement txtMemberEmail;
	@FindBy(name="role")
	private WebElement selectMemberRole;
	@FindBy(xpath=".//*[@id='join-team']/div/div/div/h2")
	private WebElement verifyJoinNowForm;
	@FindBy(xpath="//input[@name='email']")
	private WebElement verifyCancelButtton;
	@FindBy(xpath="//a[contains(.,'Draw Venue')]")
	private WebElement verifyDrawVenue;
	@FindBy(xpath="//div[@class='contextual-links']/a[contains(.,'Team Settings')]")
	private WebElement verifyTeamSettingsNotDisplay;
	@FindBy(xpath="//div[@class='contextual-links']/a[contains(.,'Corporate Dashboard')]")
	private WebElement verifyDashboardNotDisplay;
	
	
	Common common = new Common(driver);
	
	public TeamMemberAndVenueVerificationPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyAddedVenue(int numOfVenueBefore,String venueName)
	{
		List<WebElement> eles = driver.findElements(By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')]/div"));
		int numOfVenueAfter = eles.size();
		boolean bool= false;
		if(numOfVenueBefore==numOfVenueAfter-1)
		{
			for(WebElement ele:eles)
			{
				if(ele.getText().equalsIgnoreCase(venueName))
				{
					bool = true;
					break;
				}
				else
				{
					bool = false;
				}
			}
		}
		return bool;
	}
	
	public boolean verifyDeletedVenue(int numOfVenueBefore)
	{
		driver.navigate().refresh();
		common.pause(2);
		By by = By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')]/div");
		int numOfVenueAfter = common.getNumOfElements(driver, by);
		if(numOfVenueAfter==numOfVenueBefore-1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyEditedVenue(String venueName)
	{
		common.pause(2);
		boolean bool= false;
		List<WebElement> eles = driver.findElements(By.xpath("//div[contains(@class,'listings-container')]/div[contains(@class,'floorplan')]/div"));
		for(WebElement ele:eles)
		{
			if(ele.getText().equalsIgnoreCase(venueName))
			{
				bool = true;
				break;
			}
			else
			{
				bool = false;
			}
		}
		return bool;
	}
	
	public boolean verifySearchVenue(String venueName)
	{
		boolean bool = false;
		List<WebElement> searchedVenues = driver.findElements(By.xpath("//div[@class='listing-name']"));
		for(WebElement ele: searchedVenues)
		{
			if(venueName.equalsIgnoreCase(ele.getText()))
			{
				bool = true;
			}
			else
			{
				bool = false;
				break;
			}
		}
		return bool;
	}
	
	public boolean verifyDeleteMember(int numOfMemberBefore)
	{
		By by = By.xpath(".//*[@id='members-table']/tbody/tr/td[1]");
		int numOfMemberAfter = common.getNumOfElements(driver, by);
		if(numOfMemberAfter == numOfMemberBefore-1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyCancelButtonInMember()
	{
		common.pause(2);
		try{
			verifyCancelButtton.isDisplayed();
			return false;
		}
		catch(Exception e)
		{
			return true;
		}
	}
	
	public boolean verifyAddMemberFields()
	{
		return txtMemberEmail.isDisplayed() && selectMemberRole.isDisplayed();
	}
	
	public boolean verifyDrawVenueNotDisplay()
	{
		try{
			verifyDrawVenue.isDisplayed();
			return false;
		}
		catch(Exception e)
		{
			return true;
		}
	}
	
	public boolean verifyJoinTeamForm()
	{
		return verifyJoinNowForm.isDisplayed();
	}
	
	public boolean verifyPlannerAccess()
	{
		try{
			verifyTeamSettingsNotDisplay.isDisplayed();
			verifyDashboardNotDisplay.isDisplayed();
			return false;
		}
		catch(Exception e)
		{
			return true;
		}
	}
	
	public boolean verifyInvitationEmail(String email)
	{
		common.openUrlInNewTab(driver, "http://mailinator.com/");
		driver.findElement(By.id("inboxfield")).sendKeys(email);
		driver.findElement(By.xpath("//btn[contains(.,'Check it')]")).click();
		common.pause(2);
		List<WebElement> eles = driver.findElements(By.xpath("//li[contains(@class,'message')]"));
		if(eles.size()==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyResendEmailFunctionality()
	{
		common.pause(2);
		List<WebElement> eles = driver.findElements(By.xpath("//li[contains(@class,'message')]"));
		if(eles.size()==2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
