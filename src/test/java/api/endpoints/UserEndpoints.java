package api.endpoints;

import api.payloads.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndpoints {

    public static Response createUser(User payload) {
        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload)
                        .when()
                        .post(Routes.user_post_url);

        return response;
    }

    public static Response getUser(String userName) {

        Response response = given()
                .pathParam("username", userName)
                .when()
                .get(Routes.user_get_url);

        return response;
    }

    public static Response updateUser (String userName, User payload) {
        Response response = given()
                .pathParam("username", userName)
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .when()
                .put(Routes.user_put_url);

        return response;
    }

    public static Response deleteUser (String userName) {
        Response response = given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.user_delete_url);

        return response;
    }


}
