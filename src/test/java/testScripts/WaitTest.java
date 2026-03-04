package testScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WaitTest 
{
  @Test
  public void explicitWaitTest() {
	  	  WebDriver driver=new ChromeDriver();
	 	  driver.manage().window().maximize();
	 	  WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(18));
	 	  driver.navigate().to("http://uitestingplayground.com/ajax");
	 	  driver.findElement(By.id("ajaxButton")).click();	  
	 	  wait.until(ExpectedConditions
	 			  .textToBePresentInElement(driver.findElement(By.cssSelector("div#content")),
	 			   "Data loaded with AJAX get request."));
	 	  String strTxt=driver.findElement(By.cssSelector("div#content")).getText();
	 	  Assert.assertEquals(strTxt, "Data loaded with AJAX get request.");
	 	  System.out.println(strTxt);
  }  
  }
  