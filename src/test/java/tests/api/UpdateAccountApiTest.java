package tests.api;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiTestData;

import static io.restassured.RestAssured.given;

public class UpdateAccountApiTest extends BaseApiTest {

    @Test(description = "API: Update Account",
            priority = 4,
            dependsOnMethods = "tests.api.GetUserDetailByEmailPositiveTest.getUserDetailByEmail")
    public void updateAccount() {

        ApiTestData.initOnce();

        Response response = given()
                .spec(rspec())
                .formParam("name", ApiTestData.name)
                .formParam("email", ApiTestData.email)
                .formParam("password", ApiTestData.password)
                .formParam("firstname", "UpdatedFirst")
                .formParam("lastname", "UpdatedLast")
                .formParam("address1", "Updated Address 123")
                .formParam("mobile_number", "6666666666")
                .when()
                .put("/updateAccount")
                .then()
                .extract()
                .response();

        attachResponse(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().toLowerCase().contains("updated")
                        || response.asString().toLowerCase().contains("success"),
                "Expected update success message");
    }

    @Step("Attach API response")
    @Attachment(value = "API Response", type = "application/json")
    public String attachResponse(String response) { return response; }
}
