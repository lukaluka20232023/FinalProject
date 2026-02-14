package tests.api;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiAllure;
import utils.ApiTestData;

import static io.restassured.RestAssured.given;

public class DeleteAccountApiTest extends BaseApiTest {

    @Test(description = "API: Delete Account",
            priority = 5,
            dependsOnMethods = "tests.api.UpdateAccountApiTest.updateAccount")
    public void deleteAccount() {

        ApiTestData.initOnce();

        Response response = given()
                .spec(rspec())
                .formParam("email", ApiTestData.email)
                .formParam("password", ApiTestData.password)
                .when()
                .delete("/deleteAccount")
                .then()
                .extract()
                .response();

        ApiAllure.attachRequest("GET /deleteAccount");
        ApiAllure.attachResponse(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().toLowerCase().contains("deleted")
                        || response.asString().toLowerCase().contains("success"),
                "Expected delete success message");
    }

    @Step("Attach API response")
    @Attachment(value = "API Response", type = "application/json")
    public String attachResponse(String response) { return response; }
}
