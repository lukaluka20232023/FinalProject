package tests.api;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiTestData;

import static io.restassured.RestAssured.given;

public class VerifyLoginApiPositiveTest extends BaseApiTest {

    @Test(description = "API: Verify Login with valid email/password",
            priority = 2,
            dependsOnMethods = "tests.api.CreateAccountApiTest.createAccount")
    public void verifyLoginValid() {

        ApiTestData.initOnce();

        Response response = given()
                .spec(rspec())
                .formParam("email", ApiTestData.email)
                .formParam("password", ApiTestData.password)
                .when()
                .post("/verifyLogin")
                .then()
                .extract()
                .response();

        attachResponse(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().toLowerCase().contains("success")
                        || response.asString().toLowerCase().contains("user exists")
                        || response.asString().toLowerCase().contains("verified"),
                "Expected login success message");
    }

    @Step("Attach API response")
    @Attachment(value = "API Response", type = "application/json")
    public String attachResponse(String response) { return response; }
}
