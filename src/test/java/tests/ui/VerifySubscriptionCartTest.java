package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class VerifySubscriptionCartTest extends BaseTest {

    @Test(description = "Verify subscription works on Cart page")
    public void verifyCartSubscription() {

        HomePage homePage = new HomePage();
        homePage.clickCart();

        Assert.assertTrue(homePage.isSubscriptionSectionVisible(), "Subscription section not visible on Cart page!");

        String email = "cartsub" + System.currentTimeMillis() + "@mail.com";
        homePage.subscribeWithEmail(email);

        String msg = homePage.getSubscriptionSuccessMessage();
        Assert.assertTrue(msg.toLowerCase().contains("successfully subscribed"),
                "Subscription success message not shown! Actual: " + msg);
    }
}
