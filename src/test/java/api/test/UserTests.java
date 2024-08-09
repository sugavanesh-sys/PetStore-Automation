package api.test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker fake;
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void testDataSetup(){
        fake=new Faker();
        userPayload=new User();
        logger = LogManager.getLogger(this.getClass());

        userPayload.setId(fake.idNumber().hashCode());
        userPayload.setUsername(fake.name().username());
        userPayload.setFirstName(fake.name().firstName());
        userPayload.setLastName(fake.name().lastName());
        userPayload.setEmail(fake.internet().safeEmailAddress());
        userPayload.setPassword(fake.internet().password(6,14));
        userPayload.setPhone(fake.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testCreateUser(){

        logger.info("******* Creating a new user **********");
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();
        logger.info("******* A new user created **********");
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGetUser(){
        Response response = UserEndpoints.getUser(this.userPayload.getUsername());
        response.then().log().all();
        logger.info("******* Fetched the user **********");
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUser() {

        // Updating the below data to new values
        userPayload.setFirstName(fake.name().firstName());
        userPayload.setLastName(fake.name().lastName());
        userPayload.setEmail(fake.internet().safeEmailAddress());

        // Update the user with the new data
        Response updateResponse = UserEndpoints.updateUser(this.userPayload.getUsername(), this.userPayload);
        updateResponse.then()
                .log().all()
                .statusCode(200); // Validate update response
        logger.info("******* Updating the user **********");

        // Get the updated user details
        Response getUserResponse = UserEndpoints.getUser(this.userPayload.getUsername());
        getUserResponse.then()
                .log().all()
                .statusCode(200); // Validate get user response
        // You can also use assertions on the user data from getUserResponse
        logger.info("******* Fetched the Updated user **********");
    }

    @Test(priority = 4)
    public void testDeleteUser(){
        logger.info("******* Deleting the user **********");
        Response response= UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("******* User Deleted **********");

    }
}
