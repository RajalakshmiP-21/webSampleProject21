package testScripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import commonUtils.Utility1;

public class RemoteWebDriverStandaloneTest {
	
	WebDriver driver;
	ExtentReports extentReports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;

	
	@BeforeMethod
	public void setup() throws MalformedURLException
	{
		 ChromeOptions options = new ChromeOptions();
		  options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		  
		  String strHub = "http://10.0.12.21:4444";
		  
		  driver = new RemoteWebDriver(new URL(strHub),options);
		  
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
  public void test()  {
	  extentTest =extentReports.createTest("WebGrid Test");
	  WebElement gender = driver.findElement(By.id("male"));
	  if(!gender.isSelected())
	  {
		  gender.click();
	  }
		/*
		 * SoftAssert softassert= new SoftAssert();
		 * softassert.assertFalse(gender.isSelected());
		 */
	  Assert.assertFalse(gender.isSelected());
	 
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
  
}
