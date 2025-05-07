package Java_Appli_testing;

import io.restassured.RestAssured;


import io.restassured.http.ContentType;

import io.restassured.response.Response;

import org.json.JSONObject;

import org.testng.Assert;

import org.testng.annotations.Test;
 
public class RegisterUserTests {
 
    private final String REGISTER_URL = "http://localhost:9002/auth/register";
 
    @Test(enabled = false)

    public void register_user_valid() {

        JSONObject data = new JSONObject();

        data.put("username", "user_" + System.currentTimeMillis());

        data.put("password", "validPassword123");
 
        Response response = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body(data.toString())

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 200);

    }
 
    @Test(enabled = false)

    public void register_user_specialCharactersPassword() {

        JSONObject data = new JSONObject();

        data.put("username", "user_" + System.currentTimeMillis());

        data.put("password", "P@ssw0rd!@#");
 
        Response response = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body(data.toString())

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 201);

    }
 
    @Test(enabled = false)

    public void register_user_missingUsername() {

        JSONObject data = new JSONObject();

        data.put("password", "validPassword123");
 
        Response response = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body(data.toString())

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 400);

    }
 
    @Test(enabled = false)

    public void register_user_missingPassword() {

        JSONObject data = new JSONObject();

        data.put("username", "user_" + System.currentTimeMillis());
 
        Response response = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body(data.toString())

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 400);

    }
 
    @Test(enabled = false)

    public void register_user_existingUsername() {

        JSONObject data = new JSONObject();

        data.put("username", "existingUser"); // Replace with a real one if needed

        data.put("password", "validPassword123");
 
        Response response = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body(data.toString())

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 409); // Adjust if needed

    }
 
    @Test(enabled = false)

    public void register_user_emptyBody() {

        Response response = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body("{}")

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 400);

    }
 
    @Test(enabled = false)

    public void register_user_invalidContentType() {

        JSONObject data = new JSONObject();

        data.put("username", "user_" + System.currentTimeMillis());

        data.put("password", "password");
 
        Response response = RestAssured

            .given()

                .contentType(ContentType.XML) // Intentionally wrong

                .body(data.toString())

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 415); // Unsupported Media Type

    }
 
    @Test(enabled = false)

    public void register_user_emptyPassword() {

        JSONObject data = new JSONObject();

        data.put("username", "xyz");

        data.put("password", "");
 
        Response response = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body(data.toString())

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 400);

    }
 
    @Test(enabled = false)

    public void register_user_specialCharUsername() {

        JSONObject data = new JSONObject();

        data.put("username", "&&");

        data.put("password", "()");
 
        Response response = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body(data.toString())

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 400);

    }
 
    @Test(enabled = false)

    public void register_user_spaceUsername() {

        JSONObject data = new JSONObject();

        data.put("username", " ");

        data.put("password", " 123");
 
        Response response = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body(data.toString())

            .when()

                .post(REGISTER_URL);
 
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    
    @Test(enabled = false)

    public void fetch_existing_user_and_validate() {

        String expectedUsername = "user_"; // Replace with a real existing user

        String expectedPassword = "validPassword123"; // Match the correct password set earlier
     
        Response response = RestAssured

            .given()

                .accept(ContentType.JSON)

            .when()

                .get("http://localhost:9002/auth/user/" + expectedUsername);
     
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to fetch user");
     
        JSONObject responseBody = new JSONObject(response.getBody().asString());
     
        Assert.assertEquals(responseBody.getString("username"), expectedUsername, "Username mismatch");

        Assert.assertEquals(responseBody.getString("password"), expectedPassword, "Password mismatch");

    }
    
    @Test(enabled = true)

    public void fetch_existing_user_with_auth_and_validate() {

        String username = "existingUser"; // should be pre-registered

        String password = "validPassword123"; // valid password used during registration
     
        // Step 1: Login to get token

        JSONObject loginPayload = new JSONObject();

        loginPayload.put("username", username);

        loginPayload.put("password", password);
     
        Response loginResponse = RestAssured

            .given()

                .contentType(ContentType.JSON)

                .body(loginPayload.toString())

            .when()

                .post("http://localhost:9002/auth/login");
     
        Assert.assertEquals(loginResponse.getStatusCode(), 200, "Login failed");
     
        String token = new JSONObject(loginResponse.getBody().asString()).getString("token");
     
        // Step 2: Fetch user with token

        Response fetchResponse = RestAssured

            .given()

                .header("Authorization", "Bearer " + token)

                .accept(ContentType.JSON)

            .when()

                .get("http://localhost:9002/auth/user/" + username);
     
        Assert.assertEquals(fetchResponse.getStatusCode(), 200, "Fetch user failed");
     
        JSONObject userData = new JSONObject(fetchResponse.getBody().asString());
     
        // Step 3: Validate user data (excluding password if not returned)

        Assert.assertEquals(userData.getString("username"), username, "Username mismatch");
     
        // If password is returned (unlikely), validate it too:

        // Assert.assertEquals(userData.getString("password"), password, "Password mismatch");

    }

     

     
}

 