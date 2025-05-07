package Java_Appli_testing;
import static io.restassured.RestAssured.*;


import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class login_user {
	public String token;
	
	@Test
	public void loginuser() {
		JSONObject data=new JSONObject();
		data.put("username","Raman");
		data.put("password", "abc");
		
		Response res=given()
					.contentType(ContentType.JSON)
					.body(data.toString())
				.when()	
					.post("http://localhost:9002/auth/login");
		Assert.assertEquals(res.getStatusCode(), 200,"FAiled");
		  token=res.path("token");
		System.out.println("token = ="+token);
	}
	
}
