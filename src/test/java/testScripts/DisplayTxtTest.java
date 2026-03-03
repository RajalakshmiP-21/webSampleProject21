package testScripts;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DisplayTxtTest {
  @Test
  public void verifyDisplayTest() throws IOException, InterruptedException {
	  WebDriver driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://automationbookstore.dev/");
	  driver.findElement(By.id("searchBar")).sendKeys("Education");
	  
	  TakesScreenshot scr = (TakesScreenshot)driver;
	  	  
	  // for screenshot - we have downloaded and added dependency from mvnrepository - Apache common io
	  File scrFile = scr.getScreenshotAs(OutputType.FILE);
	  String path = System.getProperty("user.dir")	
			  +"/screenshot/"+ System.currentTimeMillis() + ".png";
	  FileUtils.copyFile(scrFile, new File(path));
	  
	  
	  WebElement closeIcon =  driver.findElement(By.cssSelector("a[title*='text']"));
	  Boolean checkTest = closeIcon.isDisplayed();
	  
	  if (checkTest) 	  
	  {		 
		  closeIcon.click();
		  System.out.println ("Performed Close operation");
	  } 
	  
	 
		
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  WebElement bookICon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pid5_thumb")));
		 
		/* WebElement bookICon =driver.findElement(By.id("pid5_thumb"));
		 * synchronized(bookICon) { bookICon.wait(10); }
		 */
	  
		  File scrFile1 = bookICon.getScreenshotAs(OutputType.FILE); String path1 =
		  System.getProperty("user.dir") +"/screenshot/"+ System.currentTimeMillis() +
		  ".png"; FileUtils.copyFile(scrFile1, new File(path1));
		 
	 driver.close();
  }
}
