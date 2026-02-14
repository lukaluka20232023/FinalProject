package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class VerifySubscriptionHomeTest extends BaseTest {

    @Test(description = "Verify subscription on Home page works")
    public void verifyHomeSubscription() {

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isSubscriptionSectionVisible(), "Subscription section not visible on Home page!");

        String email = "sub" + System.currentTimeMillis() + "@mail.com";
        homePage.subscribeWithEmail(email);

        String msg = homePage.getSubscriptionSuccessMessage();
        Assert.assertTrue(msg.toLowerCase().contains("successfully subscribed"),
                "Subscription success message not shown! Actual: " + msg);
    }
}
