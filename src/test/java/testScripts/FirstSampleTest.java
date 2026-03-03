package testScripts;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class FirstSampleTest {
  @Test
  public void loginTest() {
	 Map<String, Object> prefs = new HashMap<String, Object>();
	 //Disable the "save password" pop-up
	 prefs.put("credentials_enabled_service", false);
	 prefs.put("profile.password_managed_enabled", false);
	 //Disable the change your password pop-up related to data breach
	 prefs.put("profile.password_manager_leak_detection", false);
	 
	 //Create ChromeOptions object
	 ChromeOptions options = new ChromeOptions();
	 options.setExperimentalOption("prefs", prefs);
	 WebDriver driver = new ChromeDriver(options);
	 
	 driver.manage().window().maximize();
	 
	 //get is for dynamic web page and navigate to is for static web page
	 //driver.get("https://the-internet.herokuapp.com/login");
	 
	 driver.navigate().to("https://the-internet.herokuapp.com/login");
	 
	 WebElement inpUser = driver.findElement(By.id("username"));
	 inpUser.sendKeys("tomsmith");
	 driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
	 //driver.findElement(By.className("radius")).click();
	 //driver.findElement(By.tagName("button")).click();
	 driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
	 
	 //to locate the hyperlink: we cannot use href, we have to use only displayed text
	 //driver.findElement(By.linkText("Elemental Selenium")).click();
	 //driver.findElement(By.partialLinkText("Elemental")).click();
	 //driver.wait();
	 //driver.close();
	 
	 driver.navigate().back();
	 System.out.println(driver.getCurrentUrl());
	 String Title =driver.getTitle();
	 Assert.assertEquals(Title, "The Internet");
	 driver.close();
  }
}
