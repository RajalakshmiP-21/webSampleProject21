package apiTesting;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUpdateBookingTest {
	String strToken = "";
	
  @Test
  public void createNewBooking() {
	  File jsonFile = new File("src//test//resources//TestData//booking.json");
	  RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
	  
	  Response resp = RestAssured.given()
			  .accept("application/json")
			  .contentType("application/json")
			  .body(jsonFile)
			  .post("/booking");
	  System.out.println(resp.getStatusCode());
	  Assert.assertEquals(resp.getStatusCode(),200);
	  System.out.println("Assert validation Done -200 respone code");
	  System.out.println(resp.getStatusLine());
	  System.out.println(resp.getBody().prettyPrint());
	  JsonPath jsonPathEvaluator = resp.jsonPath();
	  String fname = jsonPathEvaluator.get("booking.firstname");
	  Assert.assertEquals(fname, "Rajalakshmi");  
  }
  
  @Test
  public void updateBooking() {
	  File bookingFile = new File("src//test//resources//TestData//booking.json");
	  RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
	  File jsonFile = new File("src//test//resources//TestData//user.json");
	  Response resp = RestAssured.given()
			  .accept("application/json")
			  .contentType("application/json")
			  .body(jsonFile)
			  .post("/auth");
	  
	  resp.getBody().jsonPath().prettyPrint();
	  strToken = resp.getBody().jsonPath().getString("token") ;
	 System.out.println("Token is ....."+strToken);
	 
	 Response putResp = RestAssured.given()
			  .accept("application/json")
			  .contentType("application/json")
			  .cookie("token",strToken)
			  .body(bookingFile)
			  .put("/booking/1681");
	  
	 System.out.println(putResp.getStatusCode());
	 Assert.assertEquals(putResp.getStatusCode(),200);
	 System.out.println(putResp.getBody().prettyPrint());
	 JsonPath jsonPathEvaluator = putResp.jsonPath();
	 String fname = jsonPathEvaluator.get("firstname");
	 Assert.assertEquals(fname, "Rajalakshmi");
	 
	  }
  
  @Test
  public void deleteBooking() {
	 // File bookingFile = new File("src//test//resources//TestData//booking.json");
	  RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
	  File jsonFile = new File("src//test//resources//TestData//user.json");
	  Response resp = RestAssured.given()
			  .accept("application/json")
			  .contentType("application/json")
			  .body(jsonFile)
			  .post("/auth");
	  
	  resp.getBody().jsonPath().prettyPrint();
	  strToken = resp.getBody().jsonPath().getString("token") ;
	 System.out.println("Token is ....."+strToken);
	 
	 Response delResp = RestAssured.given()
			   .contentType("application/json")
			  .cookie("token",strToken)
			  .delete("/booking/1821");
	  
	 System.out.println(delResp.getStatusCode());
	 System.out.println(delResp.getStatusLine());
	 Assert.assertEquals(delResp.getStatusCode(),201);

//	 System.out.println(delResp.getBody().prettyPrint());
//	 JsonPath jsonPathEvaluator = delResp.jsonPath();
//	 String fname = jsonPathEvaluator.get("firstname");
//	 Assert.assertEquals(fname, "Rajalakshmi");
	 
	  }
}
