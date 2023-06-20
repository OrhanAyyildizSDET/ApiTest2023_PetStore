package api_Test;

import api_EndPoints.User_Endpoints;
import api_Payloads.User;
import api_Utilities.Data_Provider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDT_Test {
    User user = new User();

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = Data_Provider.class)
    public void postUser(String id, String userName, String firstName, String lastName, String email, String password, String phone){
        user.setId(Integer.parseInt(id));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        Response response = User_Endpoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = Data_Provider.class)
    public void deleteUser(String userName){

        Response response = User_Endpoints.deleteUser(userName);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }



}
