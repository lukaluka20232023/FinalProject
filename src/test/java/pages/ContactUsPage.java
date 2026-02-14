package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;
import utils.WaitUtils;

import java.nio.file.Paths;

public class ContactUsPage {

    private By getInTouchHeader = By.xpath("//*[contains(translate(normalize-space(.),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'GET IN TOUCH')]");
    private By nameInput = By.name("name");
    private By emailInput = By.name("email");
    private By subjectInput = By.name("subject");
    private By messageTextArea = By.id("message");
    private By uploadInput = By.name("upload_file");
    private By submitBtn = By.name("submit");
    private By successMsg = By.cssSelector(".status.alert.alert-success");

    @Step("Verify Contact Us page is opened")
    public boolean isContactUsPageLoaded() {
        DriverFactory.hideAdIframes();
        return WaitUtils.waitForVisible(getInTouchHeader).isDisplayed();
    }

    @Step("Fill Contact Us form")
    public void fillForm(String name, String email, String subject, String message) {

        WebElement n = WaitUtils.waitForVisible(nameInput);
        WaitUtils.scrollToElement(n);
        n.clear();
        n.sendKeys(name);

        WebElement e = WaitUtils.waitForVisible(emailInput);
        WaitUtils.scrollToElement(e);
        e.clear();
        e.sendKeys(email);

        WebElement s = WaitUtils.waitForVisible(subjectInput);
        WaitUtils.scrollToElement(s);
        s.clear();
        s.sendKeys(subject);

        WebElement m = WaitUtils.waitForVisible(messageTextArea);
        WaitUtils.scrollToElement(m);
        m.clear();
        m.sendKeys(message);
    }

    @Step("Upload file to Contact Us form")
    public void uploadFileFromResources(String fileName) {
        String path = Paths.get("src", "test", "resources", fileName).toAbsolutePath().toString();

        WebElement up = WaitUtils.waitForVisible(uploadInput);
        WaitUtils.scrollToElement(up);
        up.sendKeys(path);
    }

    @Step("Submit Contact Us form and accept alert")
    public void submitAndAcceptAlert() {

        DriverFactory.hideAdIframes();

        WebElement btn = WaitUtils.waitForClickable(submitBtn);
        WaitUtils.scrollToElement(btn);
        btn.click();

        try {
            Alert alert = DriverFactory.getDriver().switchTo().alert();
            alert.accept();
        } catch (Exception ignored) {}
    }

    @Step("Verify success message is visible")
    public boolean isSuccessMessageVisible() {
        DriverFactory.hideAdIframes();
        return WaitUtils.waitForVisible(successMsg).isDisplayed();
    }
}
