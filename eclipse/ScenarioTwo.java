package Parabank.Selenium;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ScenarioTwo extends TestNgTestBase{

	@BeforeMethod
	public static void launchBrowserTwo() {
		System.out.println("launching browser for login");
		launchBrowser();
		System.out.println("Browser has been launched");
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public static void moduleTwo() throws IOException, InterruptedException {
		SuiteConfiguration prop = new SuiteConfiguration();
		
		//Logging in
		driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input")).sendKeys(prop.getProperty("UserName"));
		String password = prop.getProperty("Password");
		StringBuilder passwordReversed = new StringBuilder();
		passwordReversed.append(password);
		passwordReversed.reverse();
		driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input")).sendKeys(passwordReversed);
		driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input")).click();
		
		//Opening savings account
		driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Select accountType = new Select(driver.findElement(By.xpath("//*[@id=\"type\"]")));
		accountType.selectByVisibleText("SAVINGS");
		Select accountNum = new Select(driver.findElement(By.xpath("//*[@id=\"fromAccountId\"]")));
		accountNum.selectByIndex(0);
		Thread.sleep(5000);
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input"));
		Actions act = new Actions(driver);
		act.moveToElement(ele).build().perform();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		/*-----Account Verification and Storing Account Number-----*/
		WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/p[1]"));
		String text= ele1.getText();
		System.out.println(text);
		//Usage of try catch block
		try {
			text.contains("Congratulations, your account is now open");
		} 
		catch(Exception e){
			System.out.println("Exception occurred *** " +e.getMessage());
		}
		System.out.println("Account Verification sucessfull");
		
		WebElement accNum = driver.findElement(By.xpath("//*[@id=\"newAccountId\"]"));
		String number = accNum.getText();
		System.out.println(number);
		String path = System.getProperty("user.dir")+prop.getProperty("ExcelPath");
		FileInputStream inputStream = new FileInputStream(path);
		try (Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet sheet = workbook.getSheet("Account");
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue("Account Number");
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(number);
			FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+prop.getProperty("ExcelPath"));
			workbook.write(fos);
			fos.close();
		}
		System.out.println("Account Number has been Updated in excel sheet");

	}


		@AfterMethod
		public static void closeBrowserOne() {
			System.out.println("Closing Browser");
			closeBrowser();
			System.out.println("Module Two Success");
		}
}
