package Java_Appli_testing;
import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Create_User {
	
	@Test
	public void creat_user() {
		JSONObject data=new JSONObject();
		data.put("username","anil");
		data.put("password", "abc");
		
		Response res=given()
					.contentType(ContentType.JSON)
					.body(data.toString())
				.when()	
					.post("http://localhost:9002/auth/register");
		
		Assert.assertEquals(res.getStatusCode(),200);
	}
}
