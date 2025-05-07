package Learning_Day_4;

import org.testng.Assert;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.matcher.RestAssuredMatchers.*;
public class L_1_Parsingjsonresponse {
		
	@Test(enabled=false)
	public void parsingjsonresponse() {
		// Approach 1
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/students")
		.then()
			.statusCode(200)
//			.header("Content-Type","application/json")
//		.body("x.students[14].name",equalTo("Abc"));
			.body("[0].name", equalTo("arun"))
			.body("[0].location",Matchers.equalTo("Delhi"));
	}
	

	
	@Test(enabled=false)
	public void parsingresponse()
	{
		Response res=given()
				.contentType(ContentType.JSON)
			.when()
				.get("http://jsonplaceholder.typicode.com/posts");
		Assert.assertEquals(res.getStatusCode(),200);
//		Assert.assertEquals(res.getHeader("Content-Type"),"application/json");
		System.out.println(res.asString());

		
		String loc=res.jsonPath().get("[0].title").toString();// it returns value in object format so we will use .toString() 
		System.out.println(loc);
		Assert.assertEquals(loc,"sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
	}
	
	// parsing response body data 
	
	@Test(enabled=true)
	public void jsonresponsebodydata() {
		
		Response res=given()
				.contentType(ContentType.JSON)
				.when()
					.get("http://jsonplaceholder.typicode.com/posts");
		
//		JSONObject jo=new JSONObject(res.toString()); //converting json response to json object type
//		for(int i=0; i<jo.getJSONArray("posts").length(); i++) {
//			String title=jo.getJSONArray("posts").getJSONObject(i).get("title").toString();
//			System.out.println(title);
//		}
		
		 // Convert response into JSONArray since it's an array
	    JSONArray ja = new JSONArray(res.asString());

	    for (int i = 0; i < ja.length(); i++) {
	        JSONObject obj = ja.getJSONObject(i); // Get individual JSON object
	        String title = obj.get("title").toString();
	        System.out.println(title);

	    }
		
		
	}
}				
	
	

