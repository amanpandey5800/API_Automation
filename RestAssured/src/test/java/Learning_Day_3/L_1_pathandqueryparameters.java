package Learning_Day_3;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;

public class L_1_pathandqueryparameters {
	// https://jsonplaceholder.typicode.com/posts?id=5
	
	
	@Test 
	public void testqueryandpathparams() {
		given()
			.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
			//.pathParam("mypath1", "api")  // path parameters
			.pathParam("mypath2","posts")  // path parameters
//			.queryParam("page",2)
			.queryParam("id",5)
			
		.when()
			.get("https://jsonplaceholder.typicode.com/{mypath2}")
			
		.then()
			.statusCode(200)
			.log().all();
		
	}
}
