package testScripts;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseActionTest 
{
  @Test
  public void actionTest() {
	  
	  WebDriver driver=new ChromeDriver();
	  Actions action = new Actions(driver);
	  driver.manage().window().maximize();
      driver.navigate().to("https://testautomationpractice.blogspot.com/");
      WebElement btnPoint=driver.findElement(By.cssSelector("button.dropbtn"));
	  action.moveToElement(btnPoint).perform();
	  WebElement link= driver.findElement(By.linkText("Laptops"));
	  action.click(link).perform();
	  
	  action.scrollToElement(driver.findElement(By.xpath("//button[contains(text(),'Copy Text')]"))).perform();
	  action.doubleClick(driver.findElement(By.xpath("//button[contains(text(),'Copy Text')]"))).perform();
	  action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable"))).perform();//self addition
	  action.contextClick(driver.findElement(By.id("field2"))).perform();
	 
	  
  }
}