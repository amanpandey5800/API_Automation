package Java_Appli_testing;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class fetch_updated_user  {
	
	String username ="Raman";
	
	@Test
	public void gettoken() {
		JSONObject data=new JSONObject();
		data.put("username","Raman");
		data.put("password", "abc");
		Response res=given()
				.contentType(ContentType.JSON)
				.body(data.toString())
			.when()
				.post("http://localhost:9002/auth/login");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
	String token=res.jsonPath().getString("token");
	System.out.println(token);
	

		Response res1=given()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer " + token)
			.when()
				.get("http://localhost:9002/auth/user/"+username);
		
		Assert.assertEquals(res1.getStatusCode(),200);
		res1.prettyPrint();
	}
}
	
	
