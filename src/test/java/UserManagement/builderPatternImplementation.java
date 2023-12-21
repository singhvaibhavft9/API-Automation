package UserManagement;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;


public class builderPatternImplementation {
	
	private RequestSpecification requestSpec;
	private ResponseSpecification responseSpec;
	public class RestAssuredNormalApproachTest {

	    @Test
	    public void testRestAssuredNormalApproach() {
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

	                 given()
	                .contentType(ContentType.JSON)
	                .queryParam("userId", "1")
	                .when()
	                .get("/posts")
	                .then()
	                .assertThat()
	                .statusCode(200);
	    }
	}
	
	@Test
	public void testRestAssuredWithBuilderPattern()
	{
		      requestSpec=getRequestSpecification("1","application/json");
              given().spec(requestSpec)
              .when()
              .get("/posts")
              .then()
              .spec(setRequestSpecification(200,"application/json"));

	}
	
	private RequestSpecification getRequestSpecification(String queryParameter, String contentType)
	{
		         requestSpec=new RequestSpecBuilder()
		        		 .setBaseUri("https://jsonplaceholder.typicode.com")
		                 .setContentType(contentType)
		                 .addQueryParam("userId", queryParameter)
		                 .build();
		         
		         return requestSpec;

	}
	
	private ResponseSpecification setRequestSpecification(int statusCode,String contentType)
	{
		responseSpec=new ResponseSpecBuilder()
		                  .expectStatusCode(statusCode)
		                 .expectContentType(contentType)
		                 .build();
		                 
		         return responseSpec;

	}

}
