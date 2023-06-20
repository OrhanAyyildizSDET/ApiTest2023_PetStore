package api_Test;

import api_EndPoints.User_EndPoints2;
import api_Payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_Test_Properties {
    Faker faker = new Faker();
    User user;
    public Logger logger;

    @BeforeClass
    public void setupData(){
        user = new User(faker.idNumber().hashCode(),
                faker.name().username(),
                faker.name().firstName(),
                faker.address().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.phoneNumber().cellPhone(),
                faker.number().randomDigit());

        logger = LogManager.getLogger(this.getClass());
    }
    @Test(priority = 1)
    public void postUser(){
        logger.info("************ Creating User **************");
        Response response = User_EndPoints2.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("************ User is Created **************");

    }

    @Test(priority = 2)
    public void getUser(){
        logger.info("************ Getting User **************");

        Response response = User_EndPoints2.readUser(user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("************ User is Displayed **************");

    }

    @Test(priority = 3)
    public void updateUser(){
        logger.info("************ Updating User **************");

        user.setLastName(faker.name().lastName());
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setEmail(faker.internet().emailAddress());

        Response response = User_EndPoints2.updateUser(user, user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        Response responseAfterUpdate = User_EndPoints2.readUser(user.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        logger.info("************ User is Updated **************");

    }

    @Test(priority = 4)
    public void deleteUser(){
        logger.info("************ Deleting User **************");

        Response response = User_EndPoints2.deleteUser(user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        Response afterDelete = User_EndPoints2.readUser(user.getUsername());
        Assert.assertEquals(afterDelete.getStatusCode(), 404);
        System.out.println(afterDelete.body().prettyPrint());

        logger.info("************ User is Deleted **************");

    }
}
