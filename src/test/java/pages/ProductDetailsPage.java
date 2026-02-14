package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;
import utils.WaitUtils;

public class ProductDetailsPage {

    private By productInfo = By.cssSelector(".product-information");
    private By reviewHeader = By.xpath("//*[contains(text(),'Write Your Review')]");
    private By nameInput = By.id("name");
    private By emailInput = By.id("email");
    private By reviewTextarea = By.id("review");
    private By submitReviewBtn = By.id("button-review");
    private By reviewSuccessMsg = By.cssSelector(".alert-success");

    @Step("Verify Product Details page loaded")
    public boolean isLoaded() {
        return WaitUtils.waitForVisible(productInfo).isDisplayed();
    }

    @Step("Submit product review")
    public void submitReview(String name, String email, String review) {
        DriverFactory.hideAdIframes();

        WebElement header = WaitUtils.waitForVisible(reviewHeader);
        WaitUtils.scrollToElement(header);

        WebElement nameEl = WaitUtils.waitForVisible(nameInput);
        nameEl.clear();
        nameEl.sendKeys(name);

        WebElement emailEl = WaitUtils.waitForVisible(emailInput);
        emailEl.clear();
        emailEl.sendKeys(email);

        WebElement reviewEl = WaitUtils.waitForVisible(reviewTextarea);
        reviewEl.clear();
        reviewEl.sendKeys(review);

        WebElement btn = WaitUtils.waitForClickable(submitReviewBtn);
        WaitUtils.scrollToElement(btn);
        btn.click();
    }

    @Step("Get review success message")
    public String getReviewSuccessMessage() {
        return WaitUtils.waitForVisible(reviewSuccessMsg).getText();
    }
}
