package Learning_Day_3;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class L_2_CookiesandHeaders {
	
	@Test(enabled=false)
	public void testcookies()
	{
		given()
//			.config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
		.when()	
			.get("https://www.google.com")
		.then()
			.cookie("AEC","djdvjdcdhbvdbvfbcdcbdhbdjsbcdjschududdjcfcudb")
			.log().all();
	}
	
	@Test(enabled=false)
	public void getcookiesinfo() {
		
		Response res= given() // response will be stored in this variable
				
			.when()
				.get("https://www.google.com"); // if we dont put ";" then it wont store any response  
		
		// single cookie info
		String cookie_value=res.getCookie("AEC");  // this will give the value of cookie
		System.out.println("Value of cookie is =========== "+cookie_value);
	}
	
	@Test(enabled=true)
		public void getallcookiesinfo() {
		
		Response s = given()
			.when()
				.get("https://www.google.com");
		
		// info for all the cookies 
		Map<String,String> every_info=s.getCookies();
		System.out.println(every_info.keySet());  // it will give all the keys 
		
		// it will give cookies and its values as well
		for(String i:every_info.keySet()) {
			System.out.println("Cookie = "+ i + " , value = "+ every_info.get(i));
		}
	
	}
}
