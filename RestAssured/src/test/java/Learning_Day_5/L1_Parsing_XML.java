package Learning_Day_5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.equalTo;


import org.testng.annotations.Test;

import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;


public class L1_Parsing_XML {

	@Test 
	public void testxmlresponse() {
		// aproach one - without storing response 
		
		given()
		.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
		
		.when()
			.get("https://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type","application/xml")
			.body("TravelerinformationResponse.page",equalTo("1"));
		
	}
	

}
