package Learning_Day_2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
public class methods_4_extrfile {
	
	
	
	@Test
	public void externalfile() throws FileNotFoundException 
	{
		File f= new File(".\\body.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		
		JSONObject data=new JSONObject(jt);
		
		given()
			.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.log().all();
	}

}
