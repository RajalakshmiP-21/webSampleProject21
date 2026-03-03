package apiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchBookingDetailsTest {
  @Test
public void getBookingDetails() {
	  
	  RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
	  
	  Response response = RestAssured.get("/booking/2");
	  int statusCode = response.getStatusCode();
	  Assert.assertEquals(statusCode, 200);
	  System.out.println("status is 200");
	  Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	  
	  System.out.println(response.getBody().asString());
	  JsonPath jsonPathEvaluator = response.jsonPath();
	  String fname = jsonPathEvaluator.get("firstname");
	  Assert.assertEquals(fname, "Jim");
	  System.out.println("name assertion works fine");
	  Assert.assertEquals(jsonPathEvaluator.get("additionalneeds"), "Breakfast");
	  
  }
}
