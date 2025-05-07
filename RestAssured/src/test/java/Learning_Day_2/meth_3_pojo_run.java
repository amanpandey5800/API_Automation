package Learning_Day_2;

import org.testng.annotations.Test;

import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class meth_3_pojo_run  {
	@Test
	public void pojo() {
		
		method_3_pojo data=new  method_3_pojo();
		
		data.setName("Abc");
		data.setLocation("France");
		String x[] = {"C","C++"};
		
		data.setCourses(x);
		
		
		given()
		.config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
		.contentType("application/json")
		.body(data)
		.when()	
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.log().all();
		
	}
}
