package Learning_Day_1;

import java.util.HashMap;

import org.testng.annotations.Test;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Store_id {
	
	int id;
	
	@Test(priority=1)
	public void postreq() {
		HashMap data=new HashMap<String,String>();
		
		data.put("Name","Aman");
		data.put("Job", "Engineer");
		
		id=given()
			.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");		
	}	
	
	//updation of user	
	@Test(priority=2,dependsOnMethods= {"postreq"})
	public void updation() {
		HashMap hm = new HashMap<String,String>();
		hm.put("Job","Designer");
		
		
		given()
			.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
			.contentType("application/json")
			.body(hm)
			
		.when()
			.patch("https://reqres.in/api/users/id")
		.then()
			.statusCode(200)
			.log().all();
	}	
		
	//Deletion of user
	@Test(priority=3,dependsOnMethods={"updation"})
	public void delete() {
		given()
			.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
		.when()
			.delete("https://reqres.in/api/users/id")
		.then()
			.statusCode(204)
			.log().all();
	}
}
