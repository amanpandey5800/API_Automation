package Learning_Day_6;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class L1_JsonSchemeValidation {
	
	@Test
	public void validationjson() {
		given()
			
		.when()
			.get("http://localhost:3000/students")
		.then()
		
			// JsonSchemaValidator is pre-existing library which can be used for schema validation.
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("student.json"));
	}
}

