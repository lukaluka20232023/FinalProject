package tests.api;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiAllure;

import static io.restassured.RestAssured.given;

public class SearchProductApiTest extends BaseApiTest {

    @Test(description = "API: Search product by keyword")
    @Description("Verify search endpoint returns results for a keyword")
    public void searchProduct() {

        String keyword = "dress";

        Response response = given()
                .spec(rspec())
                .formParam("search_product", keyword)
                .when()
                .post("/searchProduct");

        ApiAllure.attachRequest("POST /searchProduct | formParam search_product=" + keyword);
        ApiAllure.attachResponse(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        Assert.assertTrue(response.asString().toLowerCase().contains(keyword),
                "Response does not seem to contain the keyword: " + keyword);
    }
}
