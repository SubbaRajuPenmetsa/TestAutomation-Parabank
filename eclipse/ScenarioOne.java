package Parabank.Selenium;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/*Scenario one for including */
public class ScenarioOne extends TestNgTestBase {
	//For launching browser
	@BeforeMethod
	public static void launchBrowserOne() {
		System.out.println("launching browser for regesitering");
		launchBrowser();
		System.out.println("Browser has been launched");
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public static void moduleOne() throws IOException {
		SuiteConfiguration prop = new SuiteConfiguration();
		
		driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[2]/a")).click(); //click on register
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*Enter the informantion for regestering*/
		driver.findElement(By.xpath("//*[@id=\"customer.firstName\"]")).sendKeys(prop.getProperty("FirstName"));
		driver.findElement(By.id("customer.lastName")).sendKeys(prop.getProperty("LastName"));
		driver.findElement(By.cssSelector("input[id=\"customer.address.street\"]")).sendKeys(prop.getProperty("Address"));
		driver.findElement(By.name("customer.address.city")).sendKeys(prop.getProperty("City"));
		driver.findElement(By.xpath("//*[@id=\"customer.address.state\"]")).sendKeys(prop.getProperty("State"));
		driver.findElement(By.xpath("//*[@id=\"customer.address.zipCode\"]")).sendKeys(prop.getProperty("ZipCode"));
		driver.findElement(By.xpath("//*[@id=\"customer.phoneNumber\"]")).sendKeys(prop.getProperty("PhoneNumber"));
		driver.findElement(By.xpath("//*[@id=\"customer.ssn\"]")).sendKeys(prop.getProperty("SSN"));
		driver.findElement(By.xpath("//*[@id=\"customer.username\"]")).sendKeys(prop.getProperty("UserName"));
		
		/*Using  string reverse function*/
		String password = prop.getProperty("Password");
	    StringBuilder passwordReversed = new StringBuilder();
	    passwordReversed.append(password);
	    passwordReversed.reverse();
		driver.findElement(By.xpath("//*[@id=\"customer.password\"]")).sendKeys(passwordReversed);
		driver.findElement(By.xpath("//*[@id=\"repeatedPassword\"]")).sendKeys(passwordReversed);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);									//Usage of Explicit wait
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")));
		driver.findElement(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	//for closing the browser
	@AfterMethod
	public static void closeBrowserOne() {
		System.out.println("Closing Browser");
		closeBrowser();
		System.out.println("Module One Success");
	}

}
