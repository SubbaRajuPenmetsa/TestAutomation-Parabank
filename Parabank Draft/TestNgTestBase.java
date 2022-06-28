package Parabank.Selenium;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase {

	protected static String baseUrl;
	protected static WebDriver driver;
	protected static String driverpath;
	
	/*Defining common Variables*/
	@BeforeSuite									//Usage of TestNG annotations
	public void initTestSuite() throws IOException {
		SuiteConfiguration config = new SuiteConfiguration();
		baseUrl = config.getProperty("site.url");
		driverpath = config.getProperty("driverpath");
	}

	/*Method to launch chrome browser*/
	public static void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", driverpath);		//Getting chrome driver path from Properties file
		driver = new ChromeDriver();  														
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	/*Method to close chrome browser*/
	public static void closeBrowser() {
		driver.quit();
		System.out.println("Browser Closed");
	}

	/*Getters and Setters for global variables to include encapsulation*/
	public static String getBaseUrl() {
		return baseUrl;
	}

	public static void setBaseUrl(String baseUrl) {
		TestNgTestBase.baseUrl = baseUrl;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		TestNgTestBase.driver = driver;
	}

	public static String getDriverpath() {
		return driverpath;
	}

	public static void setDriverpath(String driverpath) {
		TestNgTestBase.driverpath = driverpath;
	}
}
