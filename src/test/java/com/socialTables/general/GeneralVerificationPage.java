package com.socialTables.general;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GeneralVerificationPage extends AbstractPage
{

	@FindBy(xpath="//h2[contains(.,'Team Venues')]")
	private WebElement verifyTeamMemberAndVenue;
	@FindBy(xpath="//h2[contains(.,'Team Settings')]")
	private WebElement verifyTeamSettings;
	@FindBy(xpath="//h2[contains(.,'Account Statistics')]")
	private WebElement verifyStatistics;
	
	
	public GeneralVerificationPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean verifySorting(ArrayList<String> sortedRecordByMethod,ArrayList<String> sortedRecordBySystem)
	{
		boolean ans=false;
		System.out.println(sortedRecordByMethod+"==="+sortedRecordBySystem);
		if(sortedRecordByMethod.size() == sortedRecordBySystem.size())
		{
		
			for(int i=0;i<sortedRecordByMethod.size();i++)
			{	
				if(sortedRecordByMethod.get(i).equalsIgnoreCase(sortedRecordBySystem.get(i)))
				{
					ans=true;
				}
				else
				{
					System.out.println(sortedRecordByMethod.get(i)+ " not equals "+sortedRecordBySystem.get(i));
					ans=false;
					break;
				}
			}
		
		}
		return ans;
	}
	
	public boolean verifyTeamMemberAndVenuePage()
	{
		if(verifyTeamMemberAndVenue.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyTeamSettings()
	{
		if(verifyTeamSettings.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyStatistics()
	{
		if(verifyStatistics.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
