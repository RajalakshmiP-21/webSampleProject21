package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import commonUtils.Utility1;

public class CheckInputTest {

	WebDriver driver;
	ExtentReports extentReports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;

	@BeforeMethod
	public void setup()
	{
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get("https://testautomationpractice.blogspot.com/");
	}
	
	@BeforeTest
	public void setupExtend()
	{
		extentReports = new ExtentReports();
		spark = new ExtentSparkReporter("test-output/SparkReport.html");
		extentReports.attachReporter(spark);
	}
	
  @Test
  public void radioButtonTest() {
	  extentTest =extentReports.createTest("Radio Button Test");
	  WebElement gender = driver.findElement(By.id("female"));
	  if(!gender.isSelected()) {
		  gender.click();
		  gender.click();
		Assert.assertTrue(gender.isSelected());
	  }
  }
  @Test(retryAnalyzer =MyRetry.class)
  public void checkBoxTest()
  {
	  extentTest =extentReports.createTest("Checkbox Test");
	  WebElement sunday = driver.findElement(By.id("sunday"));
	  WebElement monday = driver.findElement(By.id("monday"));
	  sunday.click();
	  SoftAssert softassert = new SoftAssert();
	  softassert.assertTrue(sunday.isSelected());
	  System.out.println("Value of Sunday..."+sunday.getDomAttribute(""));
	  
	  monday.click();
	  if(sunday.isSelected()) {
		  sunday.click();
	  }
	  Assert.assertFalse(sunday.isSelected());
	 // Assert.assertTrue(sunday.isSelected());
  }

@Test
  public void dropdownTest()
  {
	  extentTest =extentReports.createTest("Drop Down Test");
	  Select sinSel = new Select(driver.findElement(By.id("country")));
	  sinSel.selectByVisibleText("Canada");
	  
	  Select mulSel =new Select (driver.findElement(By.id("animals")));
	  mulSel.selectByIndex(2);
	  mulSel.selectByValue("elephant");
	  mulSel.selectByVisibleText("Giraffe");
	  System.out.println(mulSel.getAllSelectedOptions());
  }
 
	  @AfterMethod
	  public void tearDown(ITestResult result) { 
		 if( result.getStatus() == ITestResult.FAILURE)
		 {
			 extentTest.log(Status.FAIL, result.getThrowable().getMessage());
			 String path = Utility1.getScreenshotPath(driver);
			 extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		 }
		  driver.quit();
		  }  
	  
	  @AfterTest
	  public void finishExtend() {
		  extentReports.flush();
	  }
}