package tests.api;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiAllure;

import static io.restassured.RestAssured.given;

public class GetAllBrandsTest extends BaseApiTest {

    @Test(description = "API: GET all brands list")
    @Description("Verify brands list endpoint returns 200 and contains data")
    public void getAllBrands() {

        Response response = given()
                .spec(rspec())
                .when()
                .get("/brandsList");

        ApiAllure.attachRequest("GET /brandsList");
        ApiAllure.attachResponse(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        Assert.assertTrue(response.asString().toLowerCase().contains("brands"),
                "Response does not contain 'brands'");
    }
}
