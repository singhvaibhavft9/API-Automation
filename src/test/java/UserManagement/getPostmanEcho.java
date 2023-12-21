package UserManagement;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import core.StatusCode;
import io.restassured.response.Response;
import utils.JsonReader;

public class getPostmanEcho {
	 
	  @Test
	  public void validateResponseBodyGetDigestAuth() throws IOException, ParseException
	  {
		  System.out.println(JsonReader.getTestData("username"));
		  System.out.println(JsonReader.getTestData("password"));
		  Response resp= given()
				 .auth()
				 .digest(JsonReader.getTestData("username"),JsonReader.getTestData("password"))
				 .when()
				 .get("https://postman-echo.com/digest-auth");
		 
		 int actualStatusCode=resp.getStatusCode();
		 System.out.println(actualStatusCode);
		 
		 assertEquals(actualStatusCode,StatusCode.SUCCESS.code);
		  
		  
	  }
	  

}
