package api_EndPoints;

import api_Payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class User_EndPoints2 {
    private static ResourceBundle getUrl(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(getUrl().getString("post_url"));
        return response;
    }

    public static Response readUser(String userName){
        Response response = given()
                .pathParam("username",userName)
                .when()
                .get(getUrl().getString("get_url"));
        return response;
    }

    public static Response updateUser(User payload, String userName){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(getUrl().getString("update_url"));
        return response;
    }

    public static Response deleteUser(String userName){
        Response response = given()
                .pathParam("username",userName)
                .when()
                .delete(getUrl().getString("delete_url"));
        return response;
    }
}
