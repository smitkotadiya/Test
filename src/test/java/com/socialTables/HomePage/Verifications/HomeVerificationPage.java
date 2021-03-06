package com.socialTables.HomePage.Verifications;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.socialTables.general.AbstractPage;
import com.socialTables.init.Common;

public class HomeVerificationPage extends AbstractPage
{
	@FindBy(xpath="//div[@class='contact-form']/div[contains(.,'Sorry, we could not find any account with that email in our         records. Please try again.')]")
	private WebElement verifyInvalidLoginMsg;
	@FindBy(xpath=".//*[contains(text(),'We have sent you an email')]")
	private WebElement verifyValidPassResetMsg;
	@FindBy(xpath=".//*[contains(text(),'Sorry, we could not find any account')]")
	private WebElement verifyInvalidPassResetMsg;
	@FindBy(xpath=".//*[@id='mailcontainer']/li[1]/a/div[2]")
	private WebElement verifyresetemail;
	@FindBy(xpath=".//*[contains(text(),'Please fill this field out.')]")
	private WebElement verifyNullPassResetMsg;
	@FindBy (xpath=".//*[contains(text(),'Passwords don\'t match')]")
	private WebElement verifypasschange;
	@FindBy(xpath="//p[contains(@class,'first_name') and contains(@class,'required')]")
	private WebElement verifyValidation;
	@FindBy(xpath="//input[@value='Create Your Free Account']")
	private WebElement verifyAccountCreationPage;
	@FindBy(xpath="//h1[ contains(.,'What kind of event matters to you?')]")
	private WebElement verifyTryWithoutCreatAccount;
	

	Common common = new Common(driver);
	
	public HomeVerificationPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyInvalidLogin()
	{
		return verifyInvalidLoginMsg.isDisplayed();
	}
	
	public boolean verifyValidPassReset()
	 {
		return verifyValidPassResetMsg.isDisplayed();
	 }
	 
	 public boolean verifyInvalidPassReset()
	 {
		 log("Message:"+verifyInvalidLoginMsg.getText());
		 return verifyInvalidPassResetMsg.isDisplayed();
	 }
	 
	 public boolean verifyNullPassReset()
	 {
		 log("<b>Validation Message:</b>"+verifyNullPassResetMsg.getText());
		 return verifyNullPassResetMsg.isDisplayed();
	 }
	 
	 public boolean verifyResetMail()
	 {
		 log("<b>Validation Message</b>"+verifyresetemail.getText());
		 return verifyresetemail.isDisplayed();
	 }
	 public boolean verifyPassChng()
	 {
		 return common.isElementDisplayed(verifypasschange);
	 }
	 
	 public boolean verifyCreateAccountValidations()
	 {
		 return verifyValidation.isDisplayed();
	 }
	 
	 public boolean verifyAccountCreationPage()
	 {
		 return verifyAccountCreationPage.isDisplayed();
	 }
	 
	 public boolean verifyTryWithoutCreateEvent()
	 {
		 return verifyTryWithoutCreatAccount.isDisplayed();
	 }
}
