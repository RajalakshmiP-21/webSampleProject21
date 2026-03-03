package testScripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebTableTest {
  @Test
  public void getAllValues() {
	  WebDriver driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://the-internet.herokuapp.com/tables");
	  
	  //findElement method is used for single value, if no values returned then no such element exception thrown
	  //String strTxt = driver.findElement(By.
		//	  xpath("//table[@id='table1']//td[contains(text(),'Jason')]//following-sibling::td"))
		//	  .getText();
	  
	  //findElements method is used for List, if no values returned then it will return 0
	  List<WebElement> items = driver.findElements(By.xpath("//table[@id='table1']//td[contains(text(),'Jason')]//following-sibling::td"));
	  System.out.println(items.size());
	  
	  for (WebElement item :items)	  {
		  System.out.println (item.getText());
	  }
	driver.close();  
  }
}
