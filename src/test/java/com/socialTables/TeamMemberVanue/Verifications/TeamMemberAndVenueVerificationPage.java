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
	
	
	public boolean verifyAddMemberFields()
	{
		if(txtMemberEmail.isDisplayed() && selectMemberRole.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyJoinTeamForm()
	{
		if(verifyJoinNowForm.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
