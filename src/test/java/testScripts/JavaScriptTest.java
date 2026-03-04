package testScripts;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class JavaScriptTest {
  @Test
  public void javaScriptExecutorTest() {
	  	  WebDriver driver=new ChromeDriver();
	 	  driver.manage().window().maximize();
	 	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	 	  driver.navigate().to("https://testautomationpractice.blogspot.com/");
	 	  JavascriptExecutor js= (JavascriptExecutor)driver;
	 	  js.executeScript("window.scrollTo(10,document.body.scrollHeight)");
	 	  String strTitle= (String)js.executeScript("return document.title ");
	 	  System.out.println(strTitle); 
  }
}