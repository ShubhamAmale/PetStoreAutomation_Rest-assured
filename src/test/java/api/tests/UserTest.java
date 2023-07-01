package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPaylaod;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker=new Faker();
		userPaylaod = new User();
		
		//randomly generate values for variables from faker class in insert it into User class parallely
		userPaylaod.setId(faker.idNumber().hashCode());
		userPaylaod.setUsername(faker.name().username());	
		userPaylaod.setFirstName(faker.name().firstName());	
		userPaylaod.setLastName(faker.name().lastName());	
		userPaylaod.setEmail(faker.internet().safeEmailAddress());	
		userPaylaod.setPassword(faker.internet().password(5, 10));
		userPaylaod.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
				
				
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("**************Creating User***************");
		Response response = UserEndPoints.createUser(userPaylaod);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************User Created***************");
	}
	
	
	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("**************Reading User Info***************");
		Response response = UserEndPoints.readUser(this.userPaylaod.getUsername());
		response.then().log().all();
		//response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************User Info is Displayed***************");
		
	}
	
	@Test(priority = 3)
	public void updateUserByName() {
		
		logger.info("**************Updating User***************");
		//update user by payload
		userPaylaod.setFirstName(faker.name().firstName());	
		userPaylaod.setLastName(faker.name().lastName());	
		userPaylaod.setEmail(faker.internet().safeEmailAddress());		
		Response response = UserEndPoints.updateeUser(this.userPaylaod.getUsername(), userPaylaod);
		response.then().log().body().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Check data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPaylaod.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("**************User is updated***************");
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("**************Deleting User***************");
		
		Response response = UserEndPoints.deleteUser(this.userPaylaod.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************User is deleted***************");
		
		
	}
	
	
	
	
	
	
	
}
