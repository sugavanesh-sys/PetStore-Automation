package api.test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

//    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testCreateUserDD(String userId, String userName, String firstName, String lastName, String email, String password, String phone, String status){

        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response= UserEndpoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

//    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testGetUserDD(String userName){
        System.out.println("This is the username to be queried: "+userName);
        Response response=UserEndpoints.getUser(userName);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

  //  @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserDD(String userName){
        System.out.println("This is the username to be deleted: "+userName);
        Response response=UserEndpoints.deleteUser(userName);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

}


