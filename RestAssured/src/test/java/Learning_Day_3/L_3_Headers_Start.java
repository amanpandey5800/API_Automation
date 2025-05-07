package Learning_Day_3;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class L_3_Headers_Start {
	
	
	@Test(enabled=false)
	void testheaders() {
		given()
		
		.when()
			.get("https://www.google.com")
		
		.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.and()	// not necessarily requires but you can add them
			.header("Content-Encoding","gzip")
			.and()
			.header("Server","gws");
	}
	
	
	// value of headers 
	@Test
	public void getheadervalues() {
		Response res=given()
				.when()
					.get("https://www.google.com");
				
		// single header info
		String headervalue=res.header("Content-Type");
		System.out.println("header value is = " + headervalue);
		
	}
	
	
	// all header values
	@Test
	public void allheaders() {
		Response r=given()
				.when()	
					.get("https://www.google.com");
		
		// all values
		Headers allheaders=r.headers();
		for(Header hd:allheaders ) {
			System.out.println("HeaderName = "+ hd.getName() + ", value = "+ hd.getValue()); // this is not very useful 
		}
					
	}
}
