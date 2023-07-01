package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

	//created to perform CRUD ( Create, Retrieve, Update, Delete) requests to the user module urls
public class UserEndPoints {
	
	public static  Response createUser (User payload) {
		Response response = given()
		//both the headers are included in swagger with -H 
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			//referred url from Routes class
			.post(Routes.post_url);
		
		return response;
	}
	
	public static  Response readUser (String username) {
		Response response = given()
				.pathParam("username", username)
		.when()
			//referred url from Routes class
			.get(Routes.get_url);
		
		return response;
	}
	
	public static  Response updateeUser (String username, User payload) {
		Response response = given()
		//both the headers are included in swagger with -H 
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			//referred url from Routes class
			.put(Routes.update_url);
		
		return response;
	}
	
	
	public static  Response deleteUser (String username) {
		Response response = given()
		.pathParam("username", username)
		.when()
			//referred url from Routes class
			.delete(Routes.delete_url);
		
		return response;
	}
	
	
}
