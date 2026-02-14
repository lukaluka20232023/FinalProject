package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;
import utils.ScreenshotUtils;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();

        driver.get("https://automationexercise.com/");

        DriverFactory.closePopupIfPresent();
        DriverFactory.hideAdIframes();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.takeScreenshot();
        }

        DriverFactory.quitDriver();
    }
}
