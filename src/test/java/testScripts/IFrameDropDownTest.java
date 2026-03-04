package testScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class IFrameDropDownTest {
  public final String expValue="JavaScript";
	
	@Test  
	  public void iframe2Test() throws InterruptedException {
		  
		  WebDriver driver=new ChromeDriver();
		 	 Actions action =new Actions(driver) ;
		 	  driver.manage().window().maximize();
		 	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 	 
		 	  driver.navigate().to("https://jqueryui.com/autocomplete/");
		 	  
		 	  //action.scrollByAmount(10,900);
		 	  
		 	 driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		 	 WebElement inPage=driver.findElement(By.id("tags"));
			 //action.moveToElement(inPAge).perform();
			 inPage.sendKeys("as");
			
			 //Thread.sleep(2000);
			 List<WebElement> items= driver.findElements(By.cssSelector("ul#ui-id-1 >li"));
			 
			System.out.println("No: of matching items ...."+ items.size());
			for (WebElement item: items) {
				
				System.out.println(item.getText());
				if (item.getText().equalsIgnoreCase(expValue)){
					item.click();
					break;
				}
			}
			 
  }
}