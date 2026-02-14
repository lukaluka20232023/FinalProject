package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

public class VerifyProductsPageTest extends BaseTest {

    @Test
    public void verifyProductsPageLoadsSuccessfully() {

        HomePage home = new HomePage();
        home.clickProducts();

        ProductsPage products = new ProductsPage();

        Assert.assertTrue(products.isProductsPageLoaded(),
                "Products page title not visible");

        Assert.assertTrue(products.productsExist(),
                "No products displayed");
    }
}
