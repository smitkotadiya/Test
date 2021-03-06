
package com.socialTables.init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.internal.Utils;

import com.socialTables.HomePage.Verifications.HomeVerificationPage;
import com.socialTables.HomePage.indexPages.HomePageIdexPage;
import com.socialTables.TeamMemberVanue.Index.TeamMemberAndVenueIndex;
import com.socialTables.TeamMemberVanue.IndexPage.TeamMemberAndVenueIndexPage;
import com.socialTables.TeamMemberVanue.Verifications.TeamMemberAndVenueVerificationPage;
import com.socialTables.TeamMemberVanue.Verifications.VenueCreationPage;
import com.socialTables.events.IndexPages.EventIndexPage;
import com.socialTables.events.verifications.AttendeeManagerPage;
import com.socialTables.events.verifications.DashboardPage;
import com.socialTables.events.verifications.EventCreationPage;
import com.socialTables.events.verifications.EventThreeDPage;
import com.socialTables.general.GeneralIndexPage;
import com.socialTables.general.GeneralVerificationPage;
import com.socialTables.tableDesigner.IndexPage.TableDesignerIndexPage;
import com.socialTables.tableDesigner.verifications.TableDesignerPage;
import com.socialTables.teamSettings.Index.TeamSettingIndex;
import com.socialTables.teamSettings.IndexPages.TeamSettingsIndexPage;
import com.socialTables.teamSettings.verifications.TeamSettingsPage;

public class SeleniumInit  {


	public String suiteName="";
	public String testName="";
	/* Minimum requirement for test configuration */
	protected String testUrl; // Test url
	protected String seleniumHub; // Selenium hub IP
	protected String seleniumHubPort; // Selenium hub port
	protected String targetBrowser; // Target browser
	protected static String test_data_folder_path = null;
	public static String currentWindowHandle = "";//Get Current Window handle
	public static String browserVersion = "";
	public static String osName="";
	

	// Variables For Login 
	public String userName_Owner="piyush.patel@kiwiqa.com";
	public String userName_Admin="nishil.patel@kiwiqa.com";
	public String userName_Planner="viral.patel@kiwiqa.com";
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
	public GeneralVerificationPage generalVerificationPage;
	
	//Home Page Module
	public HomePageIdexPage homePageIndexPage;
	public HomeVerificationPage homeVerificationPage;
	
	//Event Module
	public DashboardPage dashboardPage;
	public EventIndexPage eventIndexPage;
	public EventCreationPage eventCreationPage;
	public EventThreeDPage eventThreeDPage;
	public AttendeeManagerPage attendeeManagerPage;
	
	//Team Member and venue
	public TeamMemberAndVenueIndexPage teamMemberAndVenueIndexPage;
	public TeamMemberAndVenueVerificationPage teamMemberAndVenueVerificationPage;
	public VenueCreationPage venueCreationPage;
	
	//Team settings

	public TeamSettingsIndexPage teamSettingsIndexPage;
	public TeamSettingsPage teamSettingsPage;
	
	//Table Designer
	public TableDesignerIndexPage tableDesignerIndexPage;
	public TableDesignerPage tableDesignerPage;

	/**
	 * Fetches suite-configuration from XML suite file.
	 * 
	 * @param testContext
	 */
	@BeforeTest(alwaysRun = true)
	public void fetchSuiteConfiguration(ITestContext testContext) 
	{
		testUrl = testContext.getCurrentXmlTest().getParameter("selenium.url");
		System.out.println("======"+testUrl+"=========");
		seleniumHub = testContext.getCurrentXmlTest().getParameter(
				"selenium.host");
		seleniumHubPort = testContext.getCurrentXmlTest().getParameter(
				"selenium.port");
		targetBrowser = testContext.getCurrentXmlTest().getParameter(
				"selenium.browser");
	}

	/**
	 * WebDriver initialization
	 * 
	 * @return WebDriver object
	 * @throws IOException 
	 * @throws InterruptedException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method,ITestContext testContext) throws IOException, InterruptedException {

		
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
		if (targetBrowser == null || targetBrowser.contains("firefox")) 
		{
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
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("security.OCSP.enabled", 0);
			profile.setEnableNativeEvents(false);
			profile.setPreference("network.http.use-cache", false);

			capability = DesiredCapabilities.firefox();
			capability.setJavascriptEnabled(true);
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			browserVersion = capability.getBrowserName();
			
			osName = Platform.getCurrent().name();
			
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
			browserVersion = capability.getBrowserName();
			osName = capability.getPlatform().name();
		} else if (targetBrowser.contains("chrome")) {

			capability = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver",
					"C:\\chromedriver.exe");
			// driver = new RemoteWebDriver(new
			// URL("http://localhost:4444/wd/hub"), capability);
			capability.setBrowserName("chrome");
			capability.setJavascriptEnabled(true);
			browserVersion = capability.getVersion();
			osName = capability.getPlatform().name();
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
			browserVersion = capability.getBrowserName();
			osName = capability.getPlatform().name();
		}else if (targetBrowser.contains("safari")) {
			
		//System.setProperty("webdriver.safari.driver","/Users/jesus/Desktop/SafariDriver.safariextz");
		//driver = new SafariDriver();
			SafariDriver profile = new SafariDriver();	
		
			capability = DesiredCapabilities.safari();
	
			capability.setJavascriptEnabled(true);
			capability.setBrowserName("safari");
			browserVersion = capability.getBrowserName();
			osName = capability.getPlatform().name();
			//capability.setCapability(SafariDriver.CLEAN_SESSION_CAPABILITY, profile);
			 this.driver = new SafariDriver(capability);
		}		
		
		driver = new RemoteWebDriver(remote_grid, capability);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(testUrl);
		driver.manage().window().maximize();
		currentWindowHandle = driver.getWindowHandle();
		System.out.println("Current Window Handle ID:--->"+currentWindowHandle);
		
		suiteName  = testContext.getSuite().getName();
		System.out.println("Current Xml Suite is:---->"+suiteName);
		
		//Page Objetc Initialization According To Its Test Suite.
		
		if(suiteName.equalsIgnoreCase("Social Tables Automation - Events Module"))
		{
			eventIndexPage = new EventIndexPage(driver);
			eventCreationPage = new EventCreationPage(driver);
			attendeeManagerPage = new AttendeeManagerPage(driver);
			eventThreeDPage = new  EventThreeDPage(driver);
		}
		else if(suiteName.equalsIgnoreCase("Social Tables Automation - Table Designer Module"))
		{
			tableDesignerIndexPage = new TableDesignerIndexPage(driver);
			tableDesignerPage = new TableDesignerPage(driver);
		}
		else if(suiteName.equalsIgnoreCase("Social Tables Automation - Team Member And Venue Module"))
		{
			teamMemberAndVenueIndexPage = new TeamMemberAndVenueIndexPage(driver);
			teamMemberAndVenueVerificationPage = new TeamMemberAndVenueVerificationPage(driver);
			venueCreationPage = new VenueCreationPage(driver);
		}
		else if(suiteName.equalsIgnoreCase("Social Tables Automation - Team Settings Module"))
		{
			teamSettingsIndexPage = new TeamSettingsIndexPage(driver);
			teamSettingsPage = new TeamSettingsPage(driver);
		}
		
		homePageIndexPage = new HomePageIdexPage(driver);
		homeVerificationPage = new HomeVerificationPage(driver);
		generalIndexPage = new GeneralIndexPage(driver);
		generalVerificationPage = new GeneralVerificationPage(driver);
		dashboardPage = new DashboardPage(driver);
		
	} 
	
	/**
	 * Log For Failure Test Exception.
	 * @param tests
	 */
	private void getShortException(IResultMap tests) {

        for (ITestResult result : tests.getAllResults()) {
            
            Throwable exception = result.getThrowable();
            List<String> msgs = Reporter.getOutput(result);
            boolean hasReporterOutput = msgs.size() > 0;
            boolean hasThrowable = exception != null;
            if (hasThrowable) {
                boolean wantsMinimalOutput = result.getStatus() == ITestResult.SUCCESS;
                if (hasReporterOutput) {
                    log("<h3>"
                            + (wantsMinimalOutput ? "Expected Exception"
                                    : "Failure Reason:") + "</h3>");
                }

                // Getting first line of the stack trace
                String str = Utils.stackTrace(exception, true)[0];
                Scanner scanner = new Scanner(str);
                String firstLine = scanner.nextLine();
                log(firstLine);
            }
        }
    }
	
	/**
	 * After Method
	 * 
	 * @param testResult
	 */
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult) {
		
		ITestContext ex = testResult.getTestContext();
		
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
				//Reporter.log(testResult.getThrowable().getMessage());
				getShortException(ex.getFailedTests());
			} 
			else 
			{
				log("<br></br> Performoing logout...");
				try
				{
					common.pause(2);
					driver.findElement(By.xpath(".//*[@id='main-header']/div/div[2]/div[1]/a/span")).click();
					common.pause(2);
					driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click();
					common.pause(2);
				}
				catch(Exception e)
				{
					log("<br></br> Not able to perform logout");
				}
				
				System.out.println("TEST PASSED - " + testName + "\n"); // Print test resule to Jenkins Console
			}
			System.out.println("here is test status--------------------"+testResult.getStatus());

			driver.manage().deleteAllCookies();
			driver.close();
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
	public static void log(String msg) {
		Reporter.log("<br></br>"+msg);
	}

}
