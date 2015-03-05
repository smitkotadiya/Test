package com.socialTables.general;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class GeneralVerificationPage extends AbstractPage
{

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
}
