package UserManagement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidation {
	
	 @Test
	  public void validateJsonSchema()
	  {
		 File schema = new File("resources/ExpectedJsonSchema.json");
		   given()
		  .header("Content-Type","application/json")
		  .when()
		  .get("https://reqres.in/api/users?page=2")
		  .then()
		  .statusCode(200)
		  .body(JsonSchemaValidator.matchesJsonSchema(schema));
		 
	  }

}
