package UserManagement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import utils.JsonReader;
import utils.PropertyReader;
import utils.SoftAssertionUtil;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;

import static io.restassured.RestAssured.given;


public class getUsers {
	
	
	//SoftAssertionUtil softAssertion=new SoftAssertionUtil();
	   
		@Test(groups= {"SmokeTest"})
	    public void validateGetResponseBody() {
	        // Set base URI for the API
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

	        // Send a GET request and validate the response body using 'then'
	                 given()
	                .when()
	                .get("/todos/1")
	                .then()
	                .assertThat()
	                .statusCode(200)
	                .body(not(isEmptyString()))
	                .body("title", equalTo("delectus aut autem"))
	                .body("userId", equalTo(1));
	    }
		
		 
		    @Test (groups= {"SmokeTest"})
		    public void validateResponseHasItems() {
		        // Set base URI for the API
		        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		        // Send a GET request and store the response in a variable
		        Response response = given()
		                .when()
		                .get("/posts")
		                .then()
		                .extract()
		                .response();
		        // Use Hamcrest to check that the response body contains specific items
		        assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
		    }
		 
		 @Test (groups= {"SmokeTest"})
		 public void validateResponseHasSize() {
		        // Set base URI for the API
		        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		        // Send a GET request and store the response in a variable
		        Response response = given()
		                .when()
		                .get("/comments")
		                .then()
		                .extract()
		                .response();
		         // Use Hamcrest to check that the response body has a specific size

		        assertThat(response.jsonPath().getList(""), hasSize(500));
		    }
		 
		 @Test (groups= {"RegressionTest"})
		 public void validateResponseInOrder() {
		        // Set base URI for the API
		        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		        // Send a GET request and store the response in a variable
		        Response response = given()
		                .when()
		                .get("/comments?postId=1")
		                .then()
		                .extract()
		                .response();
		         // Use Hamcrest to check that the response body has a specific size
                List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz","Jayne_Kuhic@sydney.com","Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz");
		        assertThat(response.jsonPath().getList("email"), contains(expectedEmails.toArray(new String[0])));
		    }
		 
		 @Test (groups= {"RegressionTest"})
		 public void validateDataInResponse()
		 {
			 RestAssured.baseURI="https://reqres.in/api";
			 
			Response response= given()
			 .queryParam("page",2)
			 .when()
			 .get("/users")
			 .then()
			 .extract()
			 .response();
			
			response.then().body("data[0].id",equalTo(7));
			response.then().body("data[0].email",equalTo("michael.lawson@reqres.in"));
			response.then().body("data[0].first_name",equalTo("Michael"));
			response.then().body("data[0].last_name",equalTo("Lawson"));
			response.then().body("data[0].avatar",equalTo("https://reqres.in/img/faces/7-image.jpg"));
		 }
		 

		 @Test (groups= {"RegressionTest"})
		 public void testGetUsersWithMultipleQueryParams() {
		    Response response =
		            given()
		                    .queryParam("page", 2)
		                    .queryParam("per_page", 3)
		                    .queryParam("rtqsdr", 4)
		                    .when()
		                    .get("https://reqres.in/api/users")
		                    .then()
		                    .statusCode(200)
		                    .extract()
		                    .response();
		 }



		
		    @Test
		    public void testCreateUserWithFormParam() {
			

		        Response response = given()
		                .contentType("application/x-www-form-urlencoded")
		                .formParam("name", "John Doe")
		                .formParam("job", "Developer")
		                .when()
		                .post("https://reqres.in/api/users")
		                .then()
		                .statusCode(415)
		                .extract()
		                .response();

		        // Assert that the response contains the correct name and job values
//		        response.then().body("name", equalTo("John Doe"));
//		        response.then().body("job", equalTo("Developer"));
		    }
		
		  @Test
		  public void testSingleHeader()
		  {
			   given()
			  .header("Content-Type","application/json")
			  .when()
			  .get("https://reqres.in/api/users?page=2")
			  .then()
			  .statusCode(200)
			  .body("page",equalTo(2))
			  .body("data[0].email",is("michael.lawson@reqres.in"));
		  }
		  
		  @Test
		  public void testMultipleHeader()
		  {
			   given()
			  .header("Content-Type","application/json")
			  .header("Authorization","bearer eugbsdnbdskjngldsknlkewgnklnglsd glskd vlsdn lkdsngsdv dsn")
			  .when()
			  .get("https://reqres.in/api/users?page=2")
			  .then()
			  .statusCode(200)
			  .body("page",equalTo(2))
			  .body("data[0].email",is("michael.lawson@reqres.in"));
		  }

		
		 @Test
		 public void testMultipleHeaderUsingMap()
		  {
			  
			  Map<String,String> headers= new HashMap<String,String>();
			  headers.put("Content-Type","application/json");
			  headers.put("Authorization","bearer eugbsdnbdskjngldsknlkewgnklnglsd glskd vlsdn lkdsngsdv dsn");
			  
			   given()
			  .headers(headers)
			  .when()
			  .get("https://reqres.in/api/users?page=2")
			  .then()
			  .statusCode(200)
			  .body("page",equalTo(2))
			  .body("data[0].email",is("michael.lawson@reqres.in"));
		  }
		  
		  @Test
		  public void validateHeadersFromResponse()
		  {
//			  Map<String,String> headers= new HashMap<String,String>();
//			  headers.put("Content-Type","application/json");
//			  headers.put("Authorization","bearer eugbsdnbdskjngldsknlkewgnklnglsd glskd vlsdn lkdsngsdv dsn");
			  Response response= given()
			  .when()
			  .get("https://reqres.in/api/users?page=2")
			  .then()
			  .extract()
			  .response();
			  
			  Headers headerResponese= response.getHeaders();
			  
			  for(Header h : headerResponese)
			  {
				  if(h.getName().contains("Vary"))
				  {
					  System.out.println(h.getName()+" "+h.getValue());
					  assertEquals(h.getValue(),"Accept-Encoding");
				  }
				 
			  }
				  
			  
			  
		  }
		  
		
		  @Test
		  public void verifyStatusCodeDelete() {


			  Response resp = given().delete("https://reqres.in/api/users/2");
			  assertEquals(resp.getStatusCode(),StatusCode.NO_CONTENT.code);

              }
		  
		     @Test
			 public void testGetUsersWithMultipleQueryParamsWithPropertiesFiles() {
			  
			    String serverAddress=PropertyReader.propertyReader("config.properties", "server");
			    Response response =
			            given()
			                    .queryParam("page", 2)
			                    .queryParam("per_page", 3)
			                    .queryParam("rtqsdr", 4)
			                    .when()
			                    .get(serverAddress)
			                    .then()
			                    .statusCode(200)
			                    .extract()
			                    .response();
			 }
		 
		    
		     @Test
			 public void testGetUsersWithMultipleQueryParamsWithPropertiesFilesandTestDataComnined() throws IOException, ParseException {
			  
			    String serverAddress=PropertyReader.propertyReader("config.properties", "serverAddress");
			    String endPoint=JsonReader.getTestData("endpoint");
			    String URL = serverAddress + endPoint;
			    System.out.println(URL);
			    Response response =
			            given()
			                    .queryParam("page", 2)
			                    .queryParam("per_page", 3)
			                    .queryParam("rtqsdr", 4)
			                    .when()
			                    .get(URL)
			                    .then()
			                    .statusCode(200)
			                    .extract()
			                    .response();
			 }
		  
		    @Test()
			 public void validateDataInResponsewithSoftAssertions()
			 {
				 RestAssured.baseURI="https://reqres.in/api";
				 
				Response response= given()
				 .queryParam("page",2)
				 .when()
				 .get("/users")
				 .then()
				 .extract()
				 .response();
				
				SoftAssertionUtil.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "Status code not Matching");
				SoftAssertionUtil.assertAll();
				
			 }
		  
		  @DataProvider(name = "testdata")
		  public Object[][] testData() {
		      return new Object[][] {
		          {"1", "John"},
		          {"2", "Jane"},
		          {"3", "Bob"}
		      };
		  }
		  
		  @Test(dataProvider="testdata")
		  @Parameters({"id","name"})
		  public void validateWithDataProvider(String id, String name)
		  {
			  given()
			  .queryParam(id)
			  .queryParam(name)
			  .when()
			  .get("https://reqres.in/api/users")
			  .then()
			  .statusCode(200);
		  }
		  
		     @Test
			 public void getArrayDataFromJsonReader() throws IOException, ParseException
			 {
			                 JsonReader.getJsonArrayData("languages", 0);
			                 JSONArray jsonObject=JsonReader.getJsonArray("contact");
			                 Iterator<String> iterator = jsonObject.iterator();
			                 while(iterator.hasNext()) {
			                    System.out.println(iterator.next());
			                 }
			 }
		  
		  
			 
}



