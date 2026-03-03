package testScripts;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class AlertTestConfigProp {
	WebDriver driver;
	WebDriverWait wait;
	Properties prop;
	
	  @BeforeTest
	  public void setup() throws IOException {
		  String path =	System.getProperty("user.dir")+"//src//test//resources//configFiles//config.properties";
		  FileInputStream fin = new FileInputStream(new File(path));
		  prop = new Properties();
		  prop.load(fin);
		  String strBrowser = prop.getProperty("browser");
		  String strURL = prop.getProperty("url");
		  if(strBrowser.equalsIgnoreCase("chrome"))
		  { 
			  driver=new ChromeDriver();
			  wait= new WebDriverWait(driver, Duration.ofSeconds(10));
			  driver.manage().window().maximize();	
			  driver.get(strURL);
		  }
		  else if(strBrowser.equalsIgnoreCase("edge"))
		  { 
			  driver = new EdgeDriver();
			  wait= new WebDriverWait(driver, Duration.ofSeconds(10));
			  driver.manage().window().maximize();	
			  driver.get(strURL);
		  }
	  }

  @Test
  public void f() {
  }

}
