package tests.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class BaseApiTest {

    private RequestSpecification requestSpec;

    @BeforeClass(alwaysRun = true)
    public void setupApi() {

        useRelaxedHTTPSValidation();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://automationexercise.com")
                .setBasePath("/api")
                .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
                .build();
    }

    protected RequestSpecification rspec() {
        return requestSpec;
    }
}
