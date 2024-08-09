package api.endpoints;

import api.payloads.User;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndpoints2 {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes"); //routes.properties file is loaded into here
        return routes;
    }

    static String user_post_url=getURL().getString("user_post_url");
    static String user_get_url=getURL().getString("user_get_url");
    static String user_put_url=getURL().getString("user_put_url");
    static String user_delete_url=getURL().getString("user_delete_url");

    public static Response createUser(User payload) {
//        String user_post_url=getURL().getString("user_post_url");
        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload)
                        .when()
                        .post(user_post_url);

        return response;
    }

    public static Response getUser(String userName) {

        Response response = given()
                .pathParam("username", userName)
                .when()
                .get(user_get_url);

        return response;
    }

    public static Response updateUser (String userName, User payload) {
        Response response = given()
                .pathParam("username", userName)
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .when()
                .put(user_put_url);

        return response;
    }

    public static Response deleteUser (String userName) {
        Response response = given()
                .pathParam("username", userName)
                .when()
                .delete(user_delete_url);

        return response;
    }


}
