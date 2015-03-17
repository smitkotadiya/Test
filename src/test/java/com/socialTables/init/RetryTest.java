package com.socialTables.init;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer
{

	public int retryCount = 0;
	 public int forceRetry = 1;
	
	public boolean retry(ITestResult result) 
	{
		if(retryCount<forceRetry)
		{
			System.out.println("Force Retry");
			retryCount++;
			return false;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
