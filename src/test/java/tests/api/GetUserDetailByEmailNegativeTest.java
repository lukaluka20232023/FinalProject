package tests.api;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiAllure;

import static io.restassured.RestAssured.given;

public class GetUserDetailByEmailNegativeTest extends BaseApiTest {

    @Test(description = "API: Get user detail by email (negative)")
    @Description("Verify endpoint behavior for non-existing email")
    public void getUserDetailByEmailNonExisting() {

        String email = "no_such_user_999999@mail.com";

        Response response = given()
                .spec(rspec())
                .queryParam("email", email)
                .when()
                .get("/getUserDetailByEmail");

        ApiAllure.attachRequest("GET /getUserDetailByEmail?email=" + email);
        ApiAllure.attachResponse(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        Assert.assertTrue(response.asString().toLowerCase().contains("not found")
                        || response.asString().toLowerCase().contains("error")
                        || response.asString().toLowerCase().contains("message"),
                "Response does not indicate not-found/error for non-existing email");
    }
}
