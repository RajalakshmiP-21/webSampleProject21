package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IFrameToolTipTest {
  @Test
  public void tooltipTest() {
	  
 WebDriver driver=new ChromeDriver();
	 Actions action =new Actions(driver) ;
	  driver.manage().window().maximize();
	 
	  driver.navigate().to("https://jqueryui.com/tooltip/");
	  
	  action.scrollByAmount(10,900);
	  
	  driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
	  
	  WebElement inPage=driver.findElement(By.id("age"));
	  action.moveToElement(inPage).perform();
	  String altTxt= driver.findElement(By.cssSelector("#ui-id-1")).getText();
	  Assert.assertEquals(altTxt, "We ask for your age only for statistical purposes.");
	  
	  driver.switchTo().defaultContent();
	  String strTxt=driver.findElement(By.cssSelector("div.demo-description")).getText();
	  System.out.println(strTxt);
	  
	  
  }
  
  
}