package testScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class DataDrivenLogin {
	WebDriver driver;
	@BeforeTest
	public void setup()
	{
		Map<String, Object> prefs = new HashMap<String, Object>();
		 //Disable the "save password" pop-up
		 prefs.put("credentials_enabled_service", false);
		 prefs.put("profile.password_managed_enabled", false);
		 //Disable the change your password pop-up related to data breach
		 prefs.put("profile.password_manager_leak_detection", false);
		 //Create ChromeOptions object
		 ChromeOptions options = new ChromeOptions();
		 options.setExperimentalOption("prefs", prefs);
		 driver = new ChromeDriver(options);
	  	 driver.manage().window().maximize();
	}
  @Test(dataProvider = "logindata")
  public void loginTest(String strUser, String strPwd) {
	  	 
		 driver.get("https://www.saucedemo.com/");
		 WebElement inpUser = driver.findElement(By.id(readObjPath("username")));
		 inpUser.sendKeys(strUser);
		// inpUser.sendKeys("standard_user");
		 WebElement pwd= driver.findElement(By.name(readObjPath("password")));
		 pwd.sendKeys(strPwd);
		 driver.findElement(By.id(readObjPath("loginBtn"))).click();
		 WebElement header = driver.findElement(By.cssSelector(readObjPath("headerMsg")));
		 Assert.assertTrue(header.isDisplayed());
  }
  
  @DataProvider(name="logindata")
  public Object[][] getData() throws IOException, CsvValidationException
  {
	  String path=System.getProperty("user.dir")+"//src//test//resources//TestData/logindata.csv";
	  CSVReader reader = new CSVReader (new FileReader(path));
	  String cols[];
	  ArrayList<Object> dataList= new ArrayList <Object>();
	  while ((cols = reader.readNext())!=null){
		  Object record[]= {cols[0],cols[1]};
		  dataList.add(record);
		  }
		  reader.close();
		  return dataList.toArray(new Object[dataList.size()][]);
	  }
	  
	
  //To use Excel - ObjRepo
  public String readObjPath(String ObjName) {
	  String ObjPath ="";
	  String path= System.getProperty("user.dir")+"//src//test//resources//TestData//loginRepo.xlsx";
	  FileInputStream fin;
	  
	  //HSSF for .xls
	  //XSSF for .xlsx
	  
	  XSSFWorkbook workbook = null;
	  try {
		fin= new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  XSSFSheet loginSheet=workbook.getSheet("loginPage");
	  int numRows=loginSheet.getLastRowNum();
	  for (int i=1; i<=numRows;i++)
	  {
		  XSSFRow row = loginSheet.getRow(i);
		  if(row.getCell(0).getStringCellValue().equalsIgnoreCase(ObjName)) {
			  ObjPath= row.getCell(1).getStringCellValue();
		  }
		  
	  }
	  return ObjPath;
  }
  
  
  }
