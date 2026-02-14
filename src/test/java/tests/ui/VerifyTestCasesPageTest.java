package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.TestCasesPage;

public class VerifyTestCasesPageTest extends BaseTest {

    @Test(description = "Verify that Test Cases page is accessible from Home page")
    public void verifyTestCasesPageNavigation() {

        HomePage homePage = new HomePage();
        homePage.clickTestCases();

        TestCasesPage testCasesPage = new TestCasesPage();
        boolean isVisible = testCasesPage.isTestCasesPageVisible();

        Assert.assertTrue(isVisible, "Test Cases page is not displayed!");
    }
}
