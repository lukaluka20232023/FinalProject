package tests.api;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiAllure;
import utils.ApiTestData;

import static io.restassured.RestAssured.given;

public class CreateAccountApiTest extends BaseApiTest {

    @Test(description = "API: Create Account", priority = 1)
    public void createAccount() {

        ApiTestData.initOnce();

        Response response = given()
                .spec(rspec())
                .formParam("name", ApiTestData.name)
                .formParam("email", ApiTestData.email)
                .formParam("password", ApiTestData.password)
                .formParam("title", ApiTestData.title)
                .formParam("birth_date", ApiTestData.birth_date)
                .formParam("birth_month", ApiTestData.birth_month)
                .formParam("birth_year", ApiTestData.birth_year)
                .formParam("firstname", ApiTestData.firstname)
                .formParam("lastname", ApiTestData.lastname)
                .formParam("company", ApiTestData.company)
                .formParam("address1", ApiTestData.address1)
                .formParam("address2", ApiTestData.address2)
                .formParam("country", ApiTestData.country)
                .formParam("zipcode", ApiTestData.zipcode)
                .formParam("state", ApiTestData.state)
                .formParam("city", ApiTestData.city)
                .formParam("mobile_number", ApiTestData.mobile_number)
                .when()
                .post("/createAccount")
                .then()
                .extract()
                .response();

        ApiAllure.attachRequest("GET /createAccount");
        ApiAllure.attachResponse(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().toLowerCase().contains("account created")
                        || response.asString().toLowerCase().contains("created"),
                "Expected 'account created' message");
    }

    @Step("Attach API response")
    @Attachment(value = "API Response", type = "application/json")
    public String attachResponse(String response) { return response; }
}
