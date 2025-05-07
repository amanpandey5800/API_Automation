package Learning_Day_2;

import io.restassured.config.RestAssuredConfig;

import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;
public class WaysToCreatePostReqBody {
	
	
	// 1). Post request using hashmap
	
	@Test
	public void usingHashmap() {
		
		HashMap hm=new HashMap<String,String>();
		
		hm.put("Name","Scott");
		hm.put("location","France");
		
		String carr[] = {"Rust","Python"};
		hm.put("courses", carr);
		
		
		given()
			.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
			.contentType("application/json")
			.body(hm)
			
		.when()
			//.get("http://localhost:3000/students")
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			
			// to verify 
			.body("Name", equalTo("Scott"))
			.body("location",equalTo("France"))
			.body("courses[0]",equalTo("Rust"))
			.body("courses[1]",equalTo("Python"))
			//.header("Content-Type","application/json; charset=utf-8")
			
			// to execute all 
			.log().all();
	}
	
	@Test(dependsOnMethods= {"usingHashMap"})
	public void delete() {
		
		given()
			.config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
		.when()
			.delete("http://localhost:3000/students")
		.then()
			.log().all();
	}

}
