package parallelScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class SampleTestTwo {
	WebDriver driver;
	
  @Test
  public void testOne() {
	  driver=new EdgeDriver();
	  long value1=Thread.currentThread().getId();
	  System.out.println("Test Result Class 2 from testOne: "+value1);
	  driver.close();
  }
  
  @Test
  public void testTwo() {
	  driver=new EdgeDriver();
	  long value1=Thread.currentThread().getId();
	  System.out.println("Test Result Class 2 from testTwo: "+value1);
	  driver.close();
  }
  
  @Test
  public void testThree() {
	  driver=new EdgeDriver();
	  long value1=Thread.currentThread().getId();
	  System.out.println("Test Result Class 2 from testThree: "+value1);
	  driver.close();
  }
}
