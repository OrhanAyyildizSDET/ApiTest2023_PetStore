package api_EndPoints;

import api_Payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class User_Endpoints {
    public static Response createUser(User payload){
        Response response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(Routes.post_url_user);
        return response;
    }

    public static Response readUser(String userName){
        Response response = given()
                    .pathParam("username",userName)
                .when()
                    .get(Routes.get_url_user);
        return response;
    }

    public static Response updateUser(User payload, String userName){
        Response response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .pathParam("username", userName)
                    .body(payload)
                .when()
                    .put(Routes.update_url_user);
        return response;
    }

    public static Response deleteUser(String userName){
        Response response = given()
                    .pathParam("username",userName)
                .when()
                    .delete(Routes.delete_url_user);
        return response;
    }
}
