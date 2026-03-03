package parallelScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SampleTestThree {
	WebDriver driver;
	
  @Test
  public void testOne() {
	  driver=new FirefoxDriver();
	  long value1=Thread.currentThread().getId();
	  System.out.println("Test Result Class 3 from testOne: "+value1);
	  driver.close();
  }
  
  @Test
  public void testTwo() {
	  driver=new FirefoxDriver();
	  long value1=Thread.currentThread().getId();
	  System.out.println("Test Result Class 3 from testTwo: "+value1);
	  driver.close();
  }
  
  @Test
  public void testThree() {
	  driver=new FirefoxDriver();
	  long value1=Thread.currentThread().getId();
	  System.out.println("Test Result Class 3 from testThree: "+value1);
	  driver.close();
  }
}
