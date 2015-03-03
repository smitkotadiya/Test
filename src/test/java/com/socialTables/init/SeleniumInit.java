
package com.socialTables.init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.opera.core.systems.internal.ImplicitWait;
import com.socialTables.HomePage.Verifications.HomeVerificationPage;
import com.socialTables.HomePage.indexPages.HomePageIdexPage;
import com.socialTables.events.IndexPages.EventIndexPage;
import com.socialTables.events.verifications.DashboardPage;
import com.socialTables.events.verifications.EventCreationPage;
import com.socialTables.general.GeneralIndexPage;



public class SeleniumInit implements ILoggerStatus {


	public String testName="";
	/* Minimum requirement for test configuration */
	protected String testUrl; // Test url
	protected String seleniumHub; // Selenium hub IP
	protected String seleniumHubPort; // Selenium hub port
	protected String targetBrowser; // Target browser
	protected static String test_data_folder_path = null;

	// Variables For Login 
	public String userName_Owner="nishil.patel@kiwiqa.com";
	public String userName_Admin="nishilpatel81@gmail.com";
	public String userName_Planner="piyush.patel@kiwiqa.com";
	public String userName_LPlanner="smit.kotadiya@kiwiqa.com";
	public String password_Owner="patel22781";
	
	// screen-shot folder
	protected static String screenshot_folder_path = null;
	public static String currentTest; // current running test

	protected static Logger logger = Logger.getLogger("testing");
	protected WebDriver driver;
	Common common = new Common(driver);

	/* Page's declaration */
	public GeneralIndexPage generalIndexPage;
	
	//Home Page Module
	public HomePageIdexPage homePageIndexPage;
	public HomeVerificationPage homeVerificationPage;
	
	//Event Module
	public DashboardPage dashboardPage;
	public EventIndexPage eventIndexPage;
	public EventCreationPage eventCreationPage;

	
	
	
//	protected RegisteredPage registeredPage;
	// And many more ...

	/**
	 * Fetches suite-configuration from XML suite file.
	 * 
	 * @param testContext
	 */
	@BeforeTest(alwaysRun = true)
	public void fetchSuiteConfiguration(ITestContext testContext) 
	{

		System.out.println("-------------------------------------------------------in fetch config");
		testUrl = testContext.getCurrentXmlTest().getParameter("selenium.url");
		System.out.println("======"+testUrl+"=========");
		seleniumHub = testContext.getCurrentXmlTest().getParameter(
				"selenium.host");
		seleniumHubPort = testContext.getCurrentXmlTest().getParameter(
				"selenium.port");
		targetBrowser = testContext.getCurrentXmlTest().getParameter(
				"selenium.browser");

	}
	//new code====
	

	/**
	 * WebDriver initialization
	 * 
	 * @return WebDriver object
	 * @throws IOException 
	 * @throws InterruptedException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) throws IOException, InterruptedException {

		
		/*Runtime runtime = Runtime.getRuntime();
		runtime.exec("java -jar C:\\Users\\KHAN\\selenium-server-standalone-2.33.0.jar -role hub");
		System.out.println("==================Server started=================");
		Thread.sleep(2000);


		Runtime runtime2 = Runtime.getRuntime();
		runtime2.exec("java -jar C:\\Users\\KHAN\\selenium-server-standalone-2.33.0.jar -role node -port 5554");
		System.out.println("=======================Node started====================");
		Thread.sleep(2000);*/
		String path="";
		if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X"))
	     {
			path="/Users/Nishil/developer/test-automation";
	     }
		else
		{
		path="c:\\Downloads_new";
		}
		File theDir = new File(path);
		  // if the directory does not exist, create it
		  if (!theDir.exists()) {
		    System.out.println("creating directory: ");
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		     } catch(SecurityException se){
		        //handle it
		     }        
		     if(result) {    
		       System.out.println("DIR created");  
		     }
		  }
		
		currentTest = method.getName(); // get Name of current test.

		URL remote_grid = new URL("http://" + seleniumHub + ":"
				+ seleniumHubPort + "/wd/hub");

		String SCREENSHOT_FOLDER_NAME = "screenshots";
		String TESTDATA_FOLDER_NAME = "test_data";

		test_data_folder_path = new File(TESTDATA_FOLDER_NAME)
				.getAbsolutePath();
		screenshot_folder_path = new File(SCREENSHOT_FOLDER_NAME)
				.getAbsolutePath();

		DesiredCapabilities capability = null;
		if (targetBrowser == null || targetBrowser.contains("firefox")) {


			FirefoxProfile profile = new FirefoxProfile();
			if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X"))
		    {
				path="/Users/Nishil/developer/test-automation";
		    }
		    else
		    {
		    	 path="c:\\Downloads_new";
		    }
		
			profile.setPreference("dom.max_chrome_script_run_time", "999");
			profile.setPreference("dom.max_script_run_time", "999");
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", path);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/msword,application/csv,text/csv,image/png,image/jpeg,application/pdf,text/html,text/plain,application/octet-stream");
			
			profile.setPreference("browser.download.manager.showWhenStarting",false);
			profile.setPreference("browser.download,manager.focusWhenStarting",false);
			//profile.setPreference("browser.download.useDownloadDir",true);
			profile.setPreference("browser.helperApps.alwaysAsk.force",false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen",false);
			profile.setPreference("browser.download.manager.closeWhenDone",false);
			profile.setPreference("browser.download.manager.showAlertOnComplete",false);
			profile.setPreference("browser.download.manager.useWindow",false);
			profile.setPreference("browser.download.manager.showWhenStarting",false);
			profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting",false);
			profile.setPreference("pdfjs.disabled", true);
			
			profile.setEnableNativeEvents(false);
			profile.setPreference("network.http.use-cache", false);

			capability = DesiredCapabilities.firefox();
			capability.setJavascriptEnabled(true);
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			System.out.println("========="+"firefox Driver "+"==========");
		} else if (targetBrowser.contains("ie8")) {

			capability = DesiredCapabilities.internetExplorer();
			capability.setPlatform(Platform.ANY);
			capability.setBrowserName("internet explorer");
			// capability.setVersion("8.0");
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capability.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					true);
			capability.setJavascriptEnabled(true);
		} else if (targetBrowser.contains("chrome")) {

			capability = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver",
					"C:\\chromedriver.exe");
			// driver = new RemoteWebDriver(new
			// URL("http://localhost:4444/wd/hub"), capability);
			capability.setBrowserName("chrome");
			capability.setJavascriptEnabled(true);
		} else if (targetBrowser.contains("ie9")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capability.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					true);
			capability.setJavascriptEnabled(true);
		}else if (targetBrowser.contains("safari")) {
			
		//System.setProperty("webdriver.safari.driver","/Users/jesus/Desktop/SafariDriver.safariextz");
		//driver = new SafariDriver();
		SafariDriver profile = new SafariDriver();	
		
		capability = DesiredCapabilities.safari();
	
			capability.setJavascriptEnabled(true);
			capability.setBrowserName("safari");
//			//capability.setCapability(SafariDriver.CLEAN_SESSION_CAPABILITY, profile);
			 this.driver = new SafariDriver(capability);
		}		
		
	
		
		driver = new RemoteWebDriver(remote_grid, capability);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(testUrl);
		driver.manage().window().maximize();
		
		//Initialization of object.
		generalIndexPage = new GeneralIndexPage(driver);
		homePageIndexPage = new HomePageIdexPage(driver);
		homeVerificationPage = new HomeVerificationPage(driver);
		eventIndexPage = new EventIndexPage(driver);
		eventCreationPage = new EventCreationPage(driver);
		dashboardPage = new DashboardPage(driver);
	
	} 

	/**
	 * After Method
	 * 
	 * @param testResult
	 */
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult) {
		try {

			 testName = testResult.getName();

			if (!testResult.isSuccess()) {

				/* Print test result to Jenkins Console */
				System.out.println();
				System.out.println("TEST FAILED - " + testName);
				System.out.println();
				System.out.println("ERROR MESSAGE: "
						+ testResult.getThrowable());
				System.out.println("\n");
				Reporter.setCurrentTestResult(testResult);

				/* Make a screenshot for test that failed */
				String screenshotName = common.getCurrentTimeStampString()
						+ testName;
				Reporter.log("<br> <b>Please look to the screenshot - </b>");
				common.makeScreenshot(driver, screenshotName);
			} 
			else 
			{
				log("<br></br> Performoing logout...");
				try
				{
					common.pause(2);
					driver.findElement(By.xpath(".//*[@id='top']/div/ul/li[3]/a")).click();
					common.pause(4);
					driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click();
					common.pause(4);
				}
				catch(Exception e)
				{
					log("<br></br> Not able to perform logout");
				}
				
				System.out.println("TEST PASSED - " + testName + "\n"); // Print
																		// test
																		// result
																		// to
																		// Jenkins
																		// Console
			}
			System.out.println("here is test status--------------------"+testResult.getStatus());

			driver.manage().deleteAllCookies();
			driver.quit();
			

		} catch (Throwable throwable) {
				System.out.println("message from tear down"+throwable.getMessage());
		}
	}

	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg
	 *            Message/Log to be reported.
	 */
	public static void logMessage(String msg) {
		Reporter.log(msg + "<br/>");
	}

	public static void log(String msg, final int logger_status) {

		switch (logger_status) {

		case ILoggerStatus.NORMAL:
			Reporter.log("<br>" + msg + "</br>");
			break;

		case ILoggerStatus.ITALIC:
			log("<i>" + msg + "</i>");
			break;

		case ILoggerStatus.STRONG:
			Reporter.log("<strong>" + msg + "</br>");
			break;
		}
	}

	public static void logStatus(final int test_status) {

		switch (test_status) {

		case ITestStatus.PASSED:
			log("<font color=238E23>--Passed</font>");
			break;

		case ITestStatus.FAILED:
			log("<font color=#FF0000>--Failed</font>");
			break;

		case ITestStatus.SKIPPED:
			log("<font color=#FFFF00>--Skipped</font>");
			break;

		default:
			break;
		}

	}

	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg
	 *            Message/Log to be reported.
	 */
	public static void log(String msg) {
		Reporter.log("<br></br>"+msg);
	}

}
