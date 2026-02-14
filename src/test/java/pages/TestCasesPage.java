package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.WaitUtils;

public class TestCasesPage {

    private WebDriver driver;

    private By anyHeaderWithTestCasesText = By.xpath("//*[contains(translate(normalize-space(.),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'TEST CASES')]");

    public TestCasesPage() {
        this.driver = DriverFactory.getDriver();
    }

    @Step("Verify Test Cases page is displayed")
    public boolean isTestCasesPageVisible() {

        DriverFactory.hideAdIframes();

        boolean urlOk = driver.getCurrentUrl().contains("/test_cases");
        if (urlOk) return true;

        try {
            return WaitUtils.waitForVisible(anyHeaderWithTestCasesText).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
