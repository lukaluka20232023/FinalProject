package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils {

    private static final int TIMEOUT = 15;

    public static WebElement waitForVisible(By locator) {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) DriverFactory.getDriver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }
}
