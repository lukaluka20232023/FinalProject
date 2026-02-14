package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;

public class RemoveProductFromCartTest extends BaseTest {

    @Test(description = "Add product to cart then remove it and verify cart is empty")
    public void removeProductFromCart() {

        HomePage home = new HomePage();
        home.clickProducts();

        ProductsPage products = new ProductsPage();
        Assert.assertTrue(products.isProductsPageLoaded(), "Products page did not load");

        products.addFirstProductToCart();
        products.clickViewCart();

        CartPage cart = new CartPage();
        Assert.assertTrue(cart.hasItems(), "Cart has no items after adding product");

        cart.removeFirstItem();

        Assert.assertTrue(cart.isCartEmpty(), "Cart is not empty after removing item");
    }
}
