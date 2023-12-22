package UserManagement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.PostRequestBody;
import pojo.cityRequest;

public class PostUsers {
	
	
	private static FileInputStream getJsonFilePath(String fileName) 
	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(System.getProperty("user.dir") + "/resources/TestData/"+fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fis;
	}
	
	@Test
    public void validatePostRequestBodyWithString() {        //Sending Request Body as Json String
        // Set base URI for the API
      

        // Send a GET request and validate the response body using 'then'
                Response response= given()
                .header("Content-Type","application/json")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("https://reqres.in/api/users");
                assertEquals(response.statusCode(),StatusCode.CREATED.code);
                System.out.println(response.getBody().asString());

    }
	
	@Test
    public void validatePutRequestBodyWithString() {
        // Set base URI for the API
      

        // Send a GET request and validate the response body using 'then'
                Response response= given()
                .header("Content-Type","application/json")
                .body("{\"name\":\"Vaibhav\",\"job\":\"SeniorSDET\"}")
                .when()
                .put("https://reqres.in/api/users/2");
                assertEquals(response.statusCode(),StatusCode.SUCCESS.code);
                System.out.println(response.getBody().asString());

    }
	
	@Test
    public void validatePatchRequestBodyWithString() {
        // Set base URI for the API
      

        // Send a GET request and validate the response body using 'then'
                Response response= given()
                .header("Content-Type","application/json")
                .body("{\"name\":\"morpheus\",\"job\":\"zionresident\"}")
                .when()
                .patch("https://reqres.in/api/users/2");
                assertEquals(response.statusCode(),StatusCode.SUCCESS.code);
                System.out.println(response.getBody().asString());

    }
	
	@Test
    public void validatePostWithJsonFile() throws IOException  {   //Sending request body as Json File
        // Set base URI for the API
      

        // Send a GET request and validate the response body using 'then'
                Response response= given()
                .header("Content-Type","application/json")
                .body(IOUtils.toString(getJsonFilePath("postRequestBody.json")))
                .when()
                .post("https://reqres.in/api/users/2");
                assertEquals(response.statusCode(),StatusCode.CREATED.code);
                System.out.println(response.getBody().asString());

    }
	
	@Test
    public void validatePatchWithJsonFile() throws IOException  {
        // Set base URI for the API
      

        // Send a GET request and validate the response body using 'then'
                Response response= given()
                .header("Content-Type","application/json")
                .body(IOUtils.toString(getJsonFilePath("patchRequestBody.json")))
                .when()
                .patch("https://reqres.in/api/users/2");
                assertEquals(response.statusCode(),StatusCode.SUCCESS.code);
                System.out.println(response.getBody().asString());

    }
	
	@Test
    public void validatePutWithJsonFile() throws IOException {
        // Set base URI for the API
      

        // Send a GET request and validate the response body using 'then'
                Response response= given()
                .header("Content-Type","application/json")
                .body(IOUtils.toString(getJsonFilePath("putRequestBody.json")))
                .when()
                .put("https://reqres.in/api/users/2");
                assertEquals(response.statusCode(),StatusCode.SUCCESS.code);
                System.out.println(response.getBody().asString());

    }
	
	@Test
	public void validatePostWithPOJO() {    //simple pojo             //Sending Request Body as Pojo
        // Set base URI for the API
      

                PostRequestBody postRequest = new PostRequestBody();
                postRequest.setName("morpheus");
                postRequest.setJob("leader");                
                Response response= given()
                .header("Content-Type","application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
                assertEquals(response.statusCode(),StatusCode.CREATED.code);
                System.out.println(response.getBody().asString());

    }
     
	
	@Test
	public void validatePostWithPOJOAsList() {   //list pojo
        // Set base URI for the API
                 List<String> Languages = new ArrayList<String>();
                 Languages.add("Java");
                 Languages.add("Python");
                PostRequestBody postRequest = new PostRequestBody();
                postRequest.setName("morpheus");
                postRequest.setJob("leader");  
                postRequest.setLanguages(Languages);
                Response response= given()
                .header("Content-Type","application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
                assertEquals(response.statusCode(),StatusCode.CREATED.code);
                System.out.println(response.getBody().asString());

    }
	
	@Test
	public void validatePutWithPOJO() {
        // Set base URI for the API
      

                PostRequestBody putRequest = new PostRequestBody();
                putRequest.setName("Vaibhav");
                putRequest.setJob("SeniorSDET");                
                Response response= given()
                .header("Content-Type","application/json")
                .body(putRequest)
                .when()
                .put("https://reqres.in/api/users/2");
                assertEquals(response.statusCode(),StatusCode.SUCCESS.code);
                System.out.println(response.getBody().asString());

    }
	
	@Test
    public void validatePatchRequestBodyWithPOJO() {
       
		  PostRequestBody patchRequest = new PostRequestBody();
		  patchRequest.setName("morpheus");
		  patchRequest.setJob("zion resident");  

        // Send a GET request and validate the response body using 'then'
                Response response= given()
                .header("Content-Type","application/json")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");
                assertEquals(response.statusCode(),StatusCode.SUCCESS.code);
                System.out.println(response.getBody().asString());

    }
	
	
	
	/*
	 * Test Body for below method
	 * {
    "name": "morpheus",
    "job": "leader",
    "City":[
        
        {
              "Name" : "Bengaluru",
              "Temperature" : "30"
        },
        {
             "Name" : "Chennai",
              "Temperature" : "40"
        }
        
        
        ]
}
	 */
	
	@Test
	public void validatePostWithPOJOAsListObject() {   //pojo with list of objects
        // Set base URI for the API 
                 List<String> Languages = new ArrayList<String>();
                 Languages.add("Java");
                 Languages.add("Python");
                 
                 cityRequest city1= new cityRequest();
                 city1.setCity("Bangalore");
                 city1.setTemperature("30");
                 cityRequest city2= new cityRequest();
                 city2.setCity("Chennai");
                 city2.setTemperature("40");              
                 List<cityRequest> cities = new ArrayList<>();
                 cities.add(city1);
                 cities.add(city2);
                PostRequestBody postRequest = new PostRequestBody();
                postRequest.setName("morpheus");          //here just like languages we need to create object of cities and pass them as as list to
                                                          // postRequest
                postRequest.setJob("leader");  
                postRequest.setLanguages(Languages);
                postRequest.setCityRequestBody(cities);
                Response response= given()
                .header("Content-Type","application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
                assertEquals(response.statusCode(),StatusCode.CREATED.code);
                System.out.println(response.getBody().asString());

    }
	
	
	@Test
    public void validatePatchResponseBodyWithPOJO() {          //Deserialization
       
		  PostRequestBody patchRequest = new PostRequestBody();
		  patchRequest.setName("morpheus");
		  patchRequest.setJob("zion resident");  

        // Send a GET request and validate the response body using 'then'
                Response response= given()
                .header("Content-Type","application/json")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");
                PostRequestBody responseBody = response.as(PostRequestBody.class);
                assertEquals(responseBody.getName(),"morpheus");
                assertEquals(response.statusCode(),StatusCode.SUCCESS.code);
                System.out.println(response.getBody().asString());

    }
	
	
	@Test
	public void validatePostWithResponsePOJOAsListObjectComplex() {   //pojo with list of objects
        // Set base URI for the API 
                 List<String> Languages = new ArrayList<String>();
                 Languages.add("Java");
                 Languages.add("Python");
                 
                 cityRequest city1= new cityRequest();
                 city1.setCity("Bangalore");
                 city1.setTemperature("30");
                 cityRequest city2= new cityRequest();
                 city2.setCity("Chennai");
                 city2.setTemperature("40");              
                 List<cityRequest> cities = new ArrayList<>();
                 cities.add(city1);
                 cities.add(city2);
                PostRequestBody postRequest = new PostRequestBody();
                postRequest.setName("morpheus");          //here just like languages we need to create object of cities and pass them as as list to
                                                          // postRequest
                postRequest.setJob("leader");  
                postRequest.setLanguages(Languages);
                postRequest.setCityRequestBody(cities);	
                Response response= given()
                .header("Content-Type","application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
                PostRequestBody responseBody= response.as(PostRequestBody.class);
                System.out.println(responseBody.getLanguages());
                System.out.println(responseBody.getCityRequestBody().get(0).getCity());
                System.out.println(responseBody.getCityRequestBody().get(0).getTemperature());
                System.out.println(responseBody.getCityRequestBody().get(1).getCity());
                System.out.println(responseBody.getCityRequestBody().get(1).getTemperature());
                assertEquals(response.statusCode(),StatusCode.CREATED.code);
             //   System.out.println(response.getBody().asString());

    }
     

}
