package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;
import utils.WaitUtils;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By testCasesBtn = By.xpath("//a[@href='/test_cases']");

    public void clickTestCases() {
        WebElement el = WaitUtils.waitForClickable(testCasesBtn);
        WaitUtils.scrollToElement(el);
        el.click();
    }


    private By productsBtn = By.xpath("//a[@href='/products']");

    public void clickProducts() {
        DriverFactory.hideAdIframes();   // <-- add this

        WebElement el = WaitUtils.waitForClickable(productsBtn);
        WaitUtils.scrollToElement(el);
        el.click();
    }

    private By contactUsBtn = By.xpath("//a[@href='/contact_us']");

    public void clickContactUs() {
        DriverFactory.hideAdIframes();

        WebElement el = WaitUtils.waitForClickable(contactUsBtn);
        WaitUtils.scrollToElement(el);
        el.click();
    }

    private By cartBtn = By.xpath("//a[@href='/view_cart']");

    // Subscription (footer)
    private By subscriptionHeader = By.xpath("//*[contains(text(),'Subscription')]");
    private By subscriptionEmailInput = By.id("susbscribe_email");
    private By subscribeBtn = By.id("subscribe");
    private By subscribeSuccessMsg = By.cssSelector("#success-subscribe div");

    public void clickCart() {
        DriverFactory.hideAdIframes();

        WebElement el = WaitUtils.waitForClickable(cartBtn);
        WaitUtils.scrollToElement(el);
        el.click();
    }

    public boolean isSubscriptionSectionVisible() {
        return WaitUtils.waitForVisible(subscriptionHeader).isDisplayed();
    }

    public void subscribeWithEmail(String email) {
        DriverFactory.hideAdIframes();

        WebElement emailEl = WaitUtils.waitForVisible(subscriptionEmailInput);
        WaitUtils.scrollToElement(emailEl);
        emailEl.clear();
        emailEl.sendKeys(email);

        WebElement btn = WaitUtils.waitForClickable(subscribeBtn);
        WaitUtils.scrollToElement(btn);
        btn.click();
    }

    public String getSubscriptionSuccessMessage() {
        return WaitUtils.waitForVisible(subscribeSuccessMsg).getText();
    }


}
