package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsFormTest extends BaseTest {

    @Test(description = "Submit Contact Us form and verify success message")
    public void submitContactUsFormSuccessfully() {

        HomePage home = new HomePage();
        home.clickContactUs();

        ContactUsPage contact = new ContactUsPage();
        Assert.assertTrue(contact.isContactUsPageLoaded(), "Contact Us page did not load");

        contact.fillForm(
                "Test User",
                "testuser12345@mail.com",
                "Final Project Automation",
                "Submitting Contact Us form via Selenium + TestNG POM."
        );

        contact.uploadFileFromResources("test_upload.txt");

        contact.submitAndAcceptAlert();

        Assert.assertTrue(contact.isSuccessMessageVisible(), "Success message not visible after submit");
    }
}
