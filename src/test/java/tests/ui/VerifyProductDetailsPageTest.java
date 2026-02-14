package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

public class VerifyProductDetailsPageTest extends BaseTest {

    @Test(description = "Verify product details page is accessible from Products page")
    public void verifyProductDetailsOpens() {

        HomePage homePage = new HomePage();
        homePage.clickProducts();

        ProductsPage productsPage = new ProductsPage();
        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products page not loaded!");

        productsPage.clickFirstViewProduct();

        ProductDetailsPage detailsPage = new ProductDetailsPage();
        Assert.assertTrue(detailsPage.isLoaded(), "Product Details page not loaded!");
    }
}
