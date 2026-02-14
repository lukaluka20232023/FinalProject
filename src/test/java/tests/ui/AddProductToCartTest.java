package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.CartPage;

public class AddProductToCartTest extends BaseTest {

    @Test
    public void verifyUserCanAddProductToCart() {

        HomePage home = new HomePage();
        home.clickProducts();

        ProductsPage products = new ProductsPage();
        Assert.assertTrue(products.isProductsPageLoaded(), "Products page did not load");

        products.addFirstProductToCart();
        products.clickViewCart();

        CartPage cart = new CartPage();
        Assert.assertTrue(cart.isCartPageLoaded(), "Cart page not opened");
        Assert.assertTrue(cart.hasItems(), "Cart is empty");
    }
}
