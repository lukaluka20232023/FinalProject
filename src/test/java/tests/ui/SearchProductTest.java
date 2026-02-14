package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

public class SearchProductTest extends BaseTest {

    @Test(description = "Search for a product and verify results are shown")
    public void searchProductAndVerifyResults() {

        HomePage home = new HomePage();
        home.clickProducts();

        ProductsPage products = new ProductsPage();
        Assert.assertTrue(products.isProductsPageLoaded(), "Products page did not load");

        String keyword = "dress";
        products.searchForProduct(keyword);

        Assert.assertTrue(products.isSearchedProductsSectionVisible(),
                "Searched Products section is not visible");

        Assert.assertTrue(products.productsExist(),
                "No products are displayed after search");

        Assert.assertTrue(products.atLeastOneResultContains(keyword),
                "No result product name contains the search keyword: " + keyword);
    }
}
