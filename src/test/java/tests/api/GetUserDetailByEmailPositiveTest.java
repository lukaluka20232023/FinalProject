package tests.api;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiTestData;

import static io.restassured.RestAssured.given;

public class GetUserDetailByEmailPositiveTest extends BaseApiTest {

    @Test(description = "API: Get user detail by email (positive)",
            priority = 3,
            dependsOnMethods = "tests.api.VerifyLoginApiPositiveTest.verifyLoginValid")
    public void getUserDetailByEmail() {

        ApiTestData.initOnce();

        Response response = given()
                .spec(rspec())
                .queryParam("email", ApiTestData.email)
                .when()
                .get("/getUserDetailByEmail")
                .then()
                .extract()
                .response();

        attachResponse(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().toLowerCase().contains(ApiTestData.email.toLowerCase())
                        || response.asString().toLowerCase().contains("user"),
                "Expected response to contain user details / email");
    }

    @Step("Attach API response")
    @Attachment(value = "API Response", type = "application/json")
    public String attachResponse(String response) { return response; }
}
