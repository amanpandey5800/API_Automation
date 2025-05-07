package Learning_Day_2;
import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.config.RestAssuredConfig;
import static org.hamcrest.Matchers.*;
import io.restassured.config.SSLConfig;


public class method_2_orgjson {
	
	//post request body creation using org.json library
	
	@Test
	public void postusingorgjson() {
		JSONObject data=new JSONObject();
		data.put("Name","Vinod");
		data.put("location","Dubai");
		String c[] = {"Devops","Cloud"};
		data.put("courses",c);
		
		given()
			.config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("Name",equalTo("Vinod"))
			.body("location",equalTo("Dubai"))
			.body("courses[0]",equalTo("Devops"))
			.body("courses[1]",equalTo("Cloud"))
			.header("Content-Type","application/json")
			.log().all();
	}
	
	
	@Test(priority=2)
	public void delete() {
		given()
		.config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
		.when()
			.delete("http://localhost:3000/students")
		.then()
			.statusCode(200)
			.log().all();
	}
}
