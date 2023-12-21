package UserManagement;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import core.BaseTest;
import io.restassured.response.Response;
import utils.ExtentReport;

public class getErgast  {
	
	 @Test 
	 public void validateResponseBodyGetPathParam() {

		
	   
	    Response resp = given().pathParam("raceSeason",2017)
	            .when()
	            .get("http://ergast.com/api/f1/{raceSeason}/circuits.json"); //RestAssured


	    int actualStatusCode = resp.statusCode();  //RestAssured
	    assertEquals(actualStatusCode, 200); //Testng
	    System.out.println(resp.body().asString());


	 }


}
