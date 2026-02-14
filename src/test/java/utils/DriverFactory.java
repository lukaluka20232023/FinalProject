package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver.set(new ChromeDriver(options));

        getDriver().manage().timeouts().implicitlyWait(Duration.ZERO);

        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver d = driver.get();
        if (d != null) {
            d.quit();
            driver.remove();
        }
    }

    public static void closePopupIfPresent() {
        try {
            WebElement closeBtn = getDriver().findElement(By.cssSelector(".close-modal"));
            closeBtn.click();
        } catch (Exception ignored) {}
    }

    public static void hideAdIframes() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript(
                    "document.querySelectorAll(" +
                            "'iframe[id^=\"aswift\"], iframe[src*=\"doubleclick\"], iframe[title=\"Advertisement\"], " +
                            "ins.adsbygoogle, div[id^=\"google_ads\"], div[id*=\"ad_position\"], div[id*=\"ad\"]'" +
                            ").forEach(e => {" +
                            "e.style.display='none'; e.style.visibility='hidden'; e.style.height='0px'; e.style.width='0px';" +
                            "});"
            );
        } catch (Exception ignored) {}
    }
}
