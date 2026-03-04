package testScripts;

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

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UploadImageExtentReportTest {
	
	 WebDriver driver;
	 ExtentReports extentReports ;
	 ExtentSparkReporter spark;
	 ExtentTest extentTest;
 
	@BeforeMethod
	
	public void setup() {
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		   
	}
	
	
	@BeforeTest
	
	public void setupExtent() {
		extentReports=new ExtentReports();
		spark=new ExtentSparkReporter("test-output/SparkReport.html");
		extentReports.attachReporter(spark);	
	}
	
@Test
  public void fileuploadTest() {	 
	  extentTest=extentReports.createTest("File upload test");
	  driver.navigate().to("https://the-internet.herokuapp.com/upload");
	  WebElement addFile =driver.findElement(By.id("file-upload"));
	  String strpath=System.getProperty("user.dir")+ "\\screenshot\\" + "1771485762552.png";
	  addFile.sendKeys(strpath);
	  Assert.assertTrue(driver.findElement(By.id("file-submit")).isDisplayed());
	  driver.findElement(By.id("file-submit")).click();  
	}

@Test (retryAnalyzer=MyRetry.class)
public void downloadTest() {
	  extentTest=extentReports.createTest("File download test"); 
	  driver.navigate().to("https://the-internet.herokuapp.com/download"); 
	  WebElement txtFile= driver.findElement(By.xpath("//a[contains(text(),'sampleFile.txt')]"));
	  Assert.assertFalse(driver.findElement(By.xpath("//a[contains(text(),'sampleFile.txt')]")).isDisplayed());
	  txtFile.click();
	  }


@Test(enabled=true)
public void shaddowDOMTest() {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.navigate().to("https://testautomationpractice.blogspot.com/");
	WebElement shadowHost=driver.findElement(By.id("shadow_host"));
	SearchContext cont= shadowHost.getShadowRoot();
	cont.findElement(By.cssSelector("input[type='text']")).sendKeys("ShadowInput");
}



@Test (enabled=false)
public void softAssert()
{
	  WebDriver driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.navigate().to("https://testautomationpractice.blogspot.com/");
	  WebElement sunday= driver.findElement(By.id("sunday"));
	  WebElement monday= driver.findElement(By.id("monday"));
	  sunday.click();
	  SoftAssert softAssert= new SoftAssert();
	  softAssert.assertFalse(sunday.isSelected());
	  System.out.println("Value of Sunday ...." +sunday.getDomAttribute("value"));
	  System.out.println("Value of a property ...." +sunday.getDomProperty("tagName"));
	  
	  if (sunday.isSelected()) {
		  sunday.click();
	  }
	  softAssert.assertFalse(sunday.isSelected());
	  monday.click();
	  softAssert.assertAll();
}

@AfterMethod 
public void teardown(ITestResult result ) {
	if (result.getStatus()==result.FAILURE) {
		extentTest.log(Status.FAIL, result.getThrowable().getMessage());
		String path =Utility1.getScreenshotPath(driver);
		extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());}
}


@AfterTest 
public void finishExtent() {
	extentReports.flush();	
}
}