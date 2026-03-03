package parallelScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SampleTestOne {
	WebDriver driver;
	
  @Test
  public void testOne() {
	  driver=new ChromeDriver();
	  long value1=Thread.currentThread().getId();
	  System.out.println("Test Result Class 1 from testOne: "+value1);
	  driver.close();
  }
  
  @Test
  public void testTwo() {
	  driver=new ChromeDriver();
	  long value1=Thread.currentThread().getId();
	  System.out.println("Test Result Class 1 from testTwo: "+value1);
	  driver.close();
  }
  
  @Test
  public void testThree() {
	  driver=new ChromeDriver();
	  long value1=Thread.currentThread().getId();
	  System.out.println("Test Result Class 1 from testThree: "+value1);
	  driver.close();
  }
}
