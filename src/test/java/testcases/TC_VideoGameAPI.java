package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class TC_VideoGameAPI {
	
	@Test(priority=1)
	public void test_getAllVideoGames() {
		
		given()
		
		.when()
		    .get("https://reqres.in/api/users")
		.then()
		    .statusCode(200)
		    .log().all();
	}
	@Test(priority=2)
	public void test_addVIdeoGame() {
		
		HashMap data= new HashMap();
		data.put("name", "Ravi");
		data.put("job", "Developer");
		
		given()
		       .contentType("application/json")
		       .body(data)
		.when()
               .post("https://reqres.in/api/users")
        .then()
               .statusCode(201)
               .log().all()
               .extract().response();
	}
	@Test(priority=3)
	public void test_getVideoGame()
	{
		given()
		.when()
		      .get("https://reqres.in/api/users?page=2")
		.then()
		      .statusCode(200)
		      .body("data.id[0]", equalTo(7))
		      .body("data.first_name[0]", equalTo("Michael"))
		      .body("data.last_name[0]", equalTo("Lawson"))
		      .log().all();
	}
	@Test(priority=4)
	public void test_updateVideoGame()
	{
		HashMap data= new HashMap();
		data.put("name", "Tom");
		data.put("job", "developer");
		
		given()
		       .contentType("application/json")
		       .body(data)
		.when()
		       .put("https://reqres.in/api/users/2")
		.then()
		        .statusCode(200)
		        .body("name", equalTo("Tom"))
		        .and()
		        .body("job", equalTo("developer"))
		        .log().all();
	}
	@Test(priority=5)
	public void test_deleteVideoGame() {
		given()
		.when()
		    .delete("https://reqres.in/api/users/2")
		.then()
		    .statusCode(204)
		    .log().all();		
	}

}
