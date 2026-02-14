package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

public class AddReviewOnProductTest extends BaseTest {

    @Test(description = "Verify user can add a review on a product")
    public void addReviewOnProduct() {

        HomePage homePage = new HomePage();
        homePage.clickProducts();

        ProductsPage productsPage = new ProductsPage();
        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products page not loaded!");

        productsPage.clickFirstViewProduct();

        ProductDetailsPage detailsPage = new ProductDetailsPage();
        Assert.assertTrue(detailsPage.isLoaded(), "Product Details page not loaded!");

        String email = "review" + System.currentTimeMillis() + "@mail.com";
        detailsPage.submitReview("Test User", email, "Nice product, good quality!");

        String msg = detailsPage.getReviewSuccessMessage();
        Assert.assertTrue(msg.toLowerCase().contains("thank you"),
                "Review success message not shown! Actual: " + msg);
    }
}
