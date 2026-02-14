package tests.api;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiAllure;

import static io.restassured.RestAssured.given;

public class VerifyLoginApiNegativeTest extends BaseApiTest {

    @Test(description = "API: Verify login with missing fields (negative)")
    @Description("Verify login endpoint returns error when required params are missing")
    public void verifyLoginMissingEmailOrPassword() {

        Response response = given()
                .spec(rspec())
                .formParam("email", "")
                .formParam("password", "")
                .when()
                .post("/verifyLogin");

        String body = response.asString();
        ApiAllure.attachRequest("POST /verifyLogin | email='', password=''");
        ApiAllure.attachResponse(body);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        try {
            int apiCode = response.jsonPath().getInt("responseCode");
            String msg = response.jsonPath().getString("message");

            Assert.assertTrue(apiCode != 200, "Expected non-200 responseCode but got: " + apiCode);
            Assert.assertTrue(msg != null && !msg.trim().isEmpty(), "Message is empty");
        } catch (Exception e) {
            Assert.assertTrue(body.toLowerCase().contains("message") || body.toLowerCase().contains("response"),
                    "Response doesn't contain expected fields: " + body);
        }

    }
}
