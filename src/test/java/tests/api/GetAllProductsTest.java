package tests.api;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiAllure;

import static io.restassured.RestAssured.given;

public class GetAllProductsTest extends BaseApiTest {

    @Test(description = "Verify GET all products API returns 200 and contains products list")
    public void verifyGetAllProducts() {

        Response response = sendGetProductsRequest();

        attachResponse(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200!");

        Assert.assertTrue(response.asString().contains("products"),
                "Response does not contain 'products' field!");

        ApiAllure.attachRequest("GET /brandsList");
        ApiAllure.attachResponse(response.asPrettyString());
    }

    @Step("Send GET request to /productsList")
    public Response sendGetProductsRequest() {
        return given()
                .spec(rspec())
                .when()
                .get("/productsList")
                .then()
                .extract()
                .response();
    }



    @Attachment(value = "API Response", type = "application/json")
    public String attachResponse(String response) {
        return response;
    }
}
