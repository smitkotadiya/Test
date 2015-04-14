package com.socialTables.init;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/**
 * Define Common Webdriver
 */
public class Common{

	Date date = new Date();
	protected Wait<WebDriver> wait;
	protected WebDriver driver;

	public Common(WebDriver driver) 
	{

		this.driver = driver;
	}

	/* "findElement" method finds element by it is identifier */
	/**
	 * Find web-element for given locator.
	 * 
	 * @param elementName
	 * @return
	 */
	public WebElement findElement(String elementName) {

		String locator;

		locator = elementName;

		int count = 0;
		while (count < 4) {
			try {
				if (locator.startsWith("link=") || locator.startsWith("LINK=")) {
					locator = locator.substring(5); // remove "link=" from
													// locator
					try {
						if (locator.contains(" "))
							return driver.findElement(By
									.partialLinkText(locator));

						return driver.findElement(By.linkText(locator));
					} catch (Exception e) {
						return null;
					}
				}
				if (locator.startsWith("id=")) {
					locator = locator.substring(3); // remove "id=" from locator
					try {
						return driver.findElement(By.id(locator));
					} catch (Exception e) {
						return null;
					}
				} else if (locator.startsWith("//")) {
					try {
						return driver.findElement(By.xpath(locator));
					} catch (Exception e) {
						return null;
					}
				} else if (locator.startsWith("css=")) {

					locator = locator.substring(4); // remove "css=" from
													// locator
					try {
						return driver.findElement(By.cssSelector(locator));
					} catch (Exception e) {
						return null;
					}
				} else if (locator.startsWith("name=")) {

					locator = locator.substring(5); // remove "name=" from
													// locator
					try {
						return driver.findElement(By.name(locator));
					} catch (Exception e) {
						return null;
					}
				} else {
					try {
						return driver.findElement(By.id(locator));
					} catch (Exception e) {
						return null;
					}

				}
			} catch (StaleElementReferenceException e) {
				e.toString();

				count = count + 1;
				// System.out.println("Trying["+
				// count+"] to recover from a stale element :" +
				// e.getMessage());
			}
			count = count + 4;
		}
		return null;

	}

	/**
	 * Checks checkbox or toggle element.
	 * 
	 * @param element
	 *            Checkbox element.
	 */
	
	public void scrollTo(WebElement element)
	{
		
		Actions dragger=new Actions(driver);
		  WebElement draggablePartOfScrollbar = element;

	         // drag downwards
	      int numberOfPixelsToDragTheScrollbarDown = 50;
	      for (int i=10;i<500;i=i+numberOfPixelsToDragTheScrollbarDown)
	      {
	          try
	          {  
	         // this causes a gradual drag of the scroll bar, 10 units at a time
	         dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(numberOfPixelsToDragTheScrollbarDown,0).release().perform();
	         Thread.sleep(1000L);
	          } catch(Exception e1){
	        	  
	          }
	       }

	}
	
	public void checkChkBox(WebElement element) {

		boolean isCheckBoxSet;

		isCheckBoxSet = element.isSelected();

		if (!isCheckBoxSet) {

			element.click();

		}

	}

	public void openMailinator(String emailAddress) {
		pause(3);
		String emailParsed[] = emailAddress.split("@");
		String url = "http://" + emailParsed[0] + ".mailinator.com";
		open(url);
	}

	/**
	 * Gets current time in the following format Month, Date, Hours, Minutes,
	 * Seconds, Millisecond.
	 * 
	 * @return Current date.
	 */
	public String getCurrentTimeStampString() {

		java.util.Date date = new java.util.Date();

		SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmssSS");
		TimeZone timeZone = TimeZone.getDefault();
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(timeZone
				.getOffset(date.getTime()), "GMT"));
		sd.setCalendar(cal);
		return sd.format(date);
	}

	/**
	 * Takes screenshot and adds it to TestNG report.
	 * 
	 * @param driver
	 *            WebDriver instance.
	 */
	public void makeScreenshot(WebDriver driver, String screenshotName) {

		WebDriver augmentedDriver = new Augmenter().augment(driver);

		/* Take a screenshot */
		File screenshot = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);
		String nameWithExtention = screenshotName + ".png";

		/* Copy screenshot to specific folder */
		try {
			/*String reportFolder = "target" + File.separator
		     + "failsafe-reports" + File.separator + "firefox"
		     + File.separator; */
		    String reportFolder = "test-output" + File.separator;
			String screenshotsFolder = "screenshots";
			File screenshotFolder = new File(reportFolder + screenshotsFolder);
			if (!screenshotFolder.getAbsoluteFile().exists()) {
				screenshotFolder.mkdir();
			}
			FileUtils.copyFile(screenshot, new File(screenshotFolder+ File.separator + nameWithExtention).getAbsoluteFile());
		} catch (IOException e) {
			this.log("Failed to capture screenshot: " + e.getMessage());
		}
		log(getScreenshotLink(nameWithExtention, nameWithExtention)); // add
																		// screenshot
																		// link
																		// to
																		// the
																		// report
	}

	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg
	 *            Message/Log to be reported.
	 */
	public void log(String msg) {
		
		Reporter.log(msg);
	}

	/**
	 * Generates link for TestNG report.
	 * 
	 * @param screenshot_name
	 *            Screenshot name.
	 * @param link_text
	 *            Link text.
	 * @return Formatted link for TestNG report.
	 */
	public String getScreenshotLink(String screenshot_name, String link_text) {

		log("<br><Strong><font color=#FF0000>--Failed</font></strong>");
		return "<a href='../test-output/screenshots/" + screenshot_name + "' target='_new'>"
				+ link_text + "</a>";
	}

	/**
	 * Checks whether the needed WebElement is displayed or not.
	 * 
	 * @param element
	 *            Needed element
	 * 
	 * @return true or false.
	 */
	public boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementNotDisplayed(WebElement element) {
		try {
			return !element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Assertion to check that given element is not being displayed.
	 * 
	 * @param locator
	 *            Locator of element.
	 */
	public void assertElementIsNotDisplayed(String locator) {

		Assert.assertFalse(isElementDisplayed(locator));
	}

	/**
	 * Wait(max. 1 minute) till given element does not disappear from page.
	 * 
	 * @param by
	 *            Locator of element.
	 * @return
	 * 
	 * @throws InterruptedException
	 */
	public boolean waitForElementIsNotDisplayed(By by)
			throws InterruptedException {

		for (int second = 0;; second++) {
			if (second >= 60) {

				break;
			}
			try {
				if (!isElementDisplayed(by))
					break;
			} catch (Exception e) {
			}
			pause(1000);
		}
		return false;
	}

	/**
	 * Wait(max. 1 minute) till given element does not disappear from page.
	 * @param by
	 *            Locator of element.
	 * @return
	 * @throws InterruptedException
	 */
	public boolean waitForElementIsDisplayed(By by) throws InterruptedException {

		for (int second = 0;; second++) {
			if (second >= 60) {

				break;
			}
			try {
				if (isElementDisplayed(by))
					break;
			} catch (Exception e) {
			}
			pause(1000);
		}
		return false;
	}

	/**
	 * Checks if given elements is checked.
	 * 
	 * @param locator
	 *            Locator of element.
	 * 
	 * @return true if checked else false.
	 */
	public boolean isChecked(String locator) {

		return this.findElement(locator).isSelected();

	}

	/**
	 * Checks whether the needed WebElement is displayed or not.
	 * 
	 * @param elementLocator
	 * @return
	 */
	public boolean isElementDisplayed(By elementLocator) {
		try {
			return driver.findElement(elementLocator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	/**
	 * Checks whether the visibility of Element Located
	 * 
	 * @param by
	 * @return
	 */
	public ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {
		return new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(by);
				return element.isDisplayed() ? element : null;
			}
		};
	}

	/**
	 * Wait up to String locator present
	 * 
	 * @param selector
	 */
	public void waitForElement(String xpath) {
		wait = new WebDriverWait(driver, 50);
		try {
			wait.until(visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
		}
	}

	/**
	 * Finds handle to second window other than given handle to current window
	 * and switches to as well.
	 * 
	 * @param handleCurrentWindow
	 * @return handleSecondWindow
	 */
	public String findAndSwitchToSecondWindow(String handleCurrentWindow) {

		pause(1000);
		Set<String> windows = driver.getWindowHandles();
		String handleSecondWindow = null;
		for (String window : windows) {
			if (!window.contains(handleCurrentWindow)) {
				handleSecondWindow = window;
			}
		}

		// Switch to the second window.
		try {

			pause(2000);

			driver.switchTo().window(handleSecondWindow);

		} catch (Throwable failure) { // If there is problem in switching
										// window, then re-try.

			pause(1000);

			driver.switchTo().window(handleSecondWindow);

		}

		return handleSecondWindow;

	}
	
	public void selectFromCombo(WebElement element,String value)
	{
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	public void selectFromComboByVisibleElement(WebElement element,String value)
	{
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}


	/**
	 * Wait up to By element present
	 * 
	 * @param element
	 */
	public void waitForElement(By element) {

		try {
			wait = new WebDriverWait(driver, 60);
			wait.until(visibilityOfElementLocated(element));
		} catch (Exception e) {
		}
	}

	/**
	 * Clicks on visible or not visible element.
	 * 
	 * @param element
	 *            Web element.
	 */
	public void jsClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"return arguments[0].click();", element);
		// this.waitForAjax("0");
	}

	public void clickOn(WebDriver driver,WebElement element)
	{
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
		element.click();
	}
	
	/**
	 * Generates random symbols;
	 * 
	 * @param length
	 *            Length of the generated symbols.
	 * 
	 * @return StringBuffer object.
	 */
	public static String generateRandomChars(int length) {
		String random=RandomStringUtils.random(length);
		return random;
	}

	/**
	 * Generate Random Number in Length
	 * 
	 * @param length
	 * @return
	 */
	public static int generateRandomNumber(int length) {

		Random rand = new Random();
		int num = rand.nextInt(length);
		int numNoRange = rand.nextInt();
		return numNoRange;

	}

	/**
	 * Mouse Hover in Web element
	 * 
	 * @param element
	 */
	public void mouseOver(WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();

	}

	/**
	 * Get text in a given element.
	 * 
	 * @param elementName
	 *            Locator of element.
	 * 
	 * @return text in given element.
	 */
	public String getText(String elementName) {

		String text;

		try {
			text = this.findElement(elementName).getText();

		} catch (Exception e) {

			text = "Element was not found";
		}

		return text;
	}

	public void open(String url) {

		driver.get(url);
	}

	/**
	 * Get value of given element dynamically.
	 * 
	 * @param locator
	 *            Locator of element.
	 * 
	 * @return Dynamic value.
	 */
	public String getValue(String locator) {

		return this.findElement(locator).getAttribute("value");
	}

	/**
	 * Assertion to check that two values are equal.
	 * 
	 * @param value1
	 *            Value-1.
	 * @param value2
	 *            Value-2.
	 */
	public void assertTwoValuesAreEqual(Object value1, Object value2) {

		Assert.assertEquals(value1, value2);
	}

	/**
	 * Checks if given element is being displayed on page.
	 * 
	 * @param elementName
	 *            Locator of element.
	 * 
	 * @return true if displayed else false.
	 */
	public boolean isElementDisplayed(String elementName) {

		WebElement webElement;
		try {
			webElement = this.findElement(elementName);
			return webElement.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Wait till given element is present.
	 * 
	 * @param locator
	 *            Locator of element.
	 */
	public void waitForConditionIsElementPresent(String locator) {

		for (int second = 0;; second++) {
			if (second >= 10) {
				break;
			}

			try {
				if (isElementPresent(locator))
					break;
			} catch (Throwable failure) {
			}

			pause(1000);
		}

	}

	/**
	 * Checks if element loaded in browser memory.
	 * 
	 * @param locator
	 *            Locator of element.
	 * @return true if loaded else false.
	 */
	public boolean isElementPresent(String locator) {

		WebElement webElement = this.findElement(locator);
		if (webElement != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Assertion to check that given element is not present.
	 * 
	 * @param locator
	 *            Locator of element.
	 */
	public void assertElementNotPresent(String locator) {

		Assert.assertFalse(isElementPresent(locator));
	}

	/**
	 * Assertion to check that given element ispresent.
	 * 
	 * @param locator
	 *            Locator of element.
	 */
	public void assertElementPresent(String locator) {

		Assert.assertTrue(isElementPresent(locator));
	}

	/**
	 * Pauses for given seconds.
	 * 
	 * @param secs
	 */
	public void pause(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException interruptedException) {

		}
	}

	/**
	 * Get random numeric of given lenth.
	 * 
	 * @param length
	 *            desired length.
	 * @return
	 */
	public int randomNumericValueGenerate(int length) {

		Random randomGenerator = new Random();

		int randomInt = randomGenerator.nextInt(length);
		return randomInt;
	}

	/**
	 * Clears and type new value into given text-box.
	 * 
	 * @param Web Element
	 *            Locator of element.
	 * 
	 * @param value
	 *            New text/value.
	 */
	public void type(WebElement webElement, String value) 
	{
		webElement.clear();
		webElement.sendKeys(value);

	}

	/**
	 * Wait till all ajax calls finish.
	 * 
	 * @param num
	 *            Number of ajax calls to finish.
	 */
	public void waitForAjax(String num) {

		String ajax;

		ajax = this.ajaxFinised(num);

		for (int second = 0;; second++) {
			if (second >= 20) {
				break;
			} else if (ajax.equals("true")) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Wait till ajax call finish.
	 * 
	 * @throws InterruptedException
	 */
	public void waitForAjax() throws InterruptedException {

		String ajax;
		ajax = this.ajaxFinised("1");

		for (int second = 0;; second++) {
			if (second >= 15) {
				break;
			} else if (ajax.equals("true")) {
				break;
			}
			Thread.sleep(1000);
		}

	}

	/**
	 * Checks that all ajax calls are completed on page.
	 * 
	 * @param num
	 *            Number of ajax calls to wait for completion.
	 * 
	 * @return "true" if completed else "false".
	 */
	public String ajaxFinised(String num) {

		Object isAjaxFinished;

		JavascriptExecutor js = (JavascriptExecutor) driver;

		isAjaxFinished = js.executeScript("return jQuery.active == " + num);

		return isAjaxFinished.toString();

	}

	/**
	 * Clears and type new value into given text-box.
	 * 
	 * @param locator
	 *            Locator of element.
	 * 
	 * @param value
	 *            New text/value.
	 */
	public void typeNew(String locator, String newValue) {

		this.findElement(locator).clear();
		this.findElement(locator).sendKeys(newValue);

	}
	
	public String selectRandomOptionFromCombo(By by,WebDriver driver) throws InterruptedException
	{
		String selectedOption = "";
		WebElement selectCombo = driver.findElement(by);
		Thread.sleep(2);
		List<WebElement> getAllOption = selectCombo.findElements(By.xpath("option"));
		ArrayList<String> arrayOfAllOption=new ArrayList<String>();
		for(WebElement ele:getAllOption)
		{
			if(!ele.getText().startsWith("All"))
			{
				arrayOfAllOption.add(ele.getText());
			}
			
		}
		int index = new Random().nextInt(arrayOfAllOption.size());
		if(Integer.signum(index)==-1)
		{
			index = -index;
			//index=Math.abs(index);
		}
		selectedOption=arrayOfAllOption.get(index);
		System.out.println("Selected Option Is---->"+selectedOption);
		return selectedOption;
	}
	
	public int getNumOfElements(WebDriver driver,By by)
	{
		int i=0;
		List<WebElement> ele = driver.findElements(by);
		i=ele.size();
		System.out.println("Total Number Of Elements Are >>> "+i);
		return i;
	}
	
	/**
	 * Refresh Current Page
	 * @param driver
	 */
	public void refresh(WebDriver driver)
	{
		driver.navigate().refresh();
	}
	
	/**
	 * Open URL in New Window
	 * @param driver
	 * @param url
	 */
	public void openUrlInNewTab(WebDriver driver,String url)
	{
		if(System.getProperty("os.name").equalsIgnoreCase("windows"))
		{
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"t");
		}
		else
		{
			driver.findElement(By.tagName("body")).sendKeys(Keys.COMMAND+"t");
		}
		driver.get(url);
	}
	
	/**
	 * Close Current Tab In Web Browser
	 * @param driver
	 */
	public void closeCurrentTab(WebDriver driver)
	{
		if(System.getProperty("os.name").equalsIgnoreCase("windows"))
		{
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"w");
		}
		else
		{
			driver.findElement(By.tagName("body")).sendKeys(Keys.COMMAND+"w");
		}
		
	}
	
	/**
	 * Highlight Element
	 * @param driver
	 * @param element
	 */
	public void highlightElement(WebDriver driver, WebElement element) {
		  /*
		   * for (int i = 0; i < 2; i++) { JavascriptExecutor js =
		   * (JavascriptExecutor) driver;
		   * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		   * element, "color: yellow; border: 2px solid yellow;");
		   * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		   * element, ""); }
		   */

		  // draw a border around the found element

		  ((JavascriptExecutor) driver).executeScript(
		    "arguments[0].style.border = '3px solid yellow'", element);
		  pause(2);
	}
	
	

	
}
