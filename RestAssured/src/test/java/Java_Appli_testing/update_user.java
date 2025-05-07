package Java_Appli_testing;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class update_user extends login_user{
	
	@Test
	public void update() {
//		JSONObject updateData = new JSONObject();
//        updateData.put("username", "Aman");
//        updateData.put("password", "xyz");
//        updateData.put("role","admin");
        
        
        Response res=given()
        		.contentType(ContentType.JSON)
        		 .header("Authorization", "Bearer " + token)
//        		.body(updateData.toString())
        	.when()	
        		.put("http://localhost:9002/auth/user/Raman/role?newRole=admin");
//        		.put("http://localhost:9002/swagger-ui/index.html#/auth-controller/updateRole");
//        		.put("http://localhost:9002/auth/user/Aman/role?newRole=user");
        Assert.assertEquals(res.getStatusCode(), 200,"UpdateFailed");
        
       
        		
	}
	
}
