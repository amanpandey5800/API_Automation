package Learning_Day_1;

import org.testng.annotations.Test;





import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class HttpRequests {
	
/*
 given()
  	content type, set cookies, add auth, add param, set headers , info etc....
  	
 when()
 	get,post,put,patch,delete etc requests come under this.
 
 then()
 	validation status code, extract response, extract headers , cookies, & response body.
  
 * 
 */	
	// for get request
	@Test
	public void test1() {
		given()
		.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation())) 
		.when()
			.get("https://reqres.in/api/users?page=2")		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	// for post request , creating the user
	@Test
	public void test2() {
		
		HashMap hm=new HashMap<String, String>();
		hm.put("name","aman");
		hm.put("Job","Engineer");
		
		
		given()
			.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
			.contentType("application/json")
			.body(hm)
		.when()
			.post("https://reqres.in/api/users")
		
			// to store id for further operations we will store it in an id
		.then()
			.statusCode(201)
			.log().all();
	}
	
}
