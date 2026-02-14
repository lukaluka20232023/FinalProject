package pages;

import org.openqa.selenium.By;
import utils.DriverFactory;
import utils.WaitUtils;

public class CartPage {

    private By cartTitle = By.xpath("//li[contains(text(),'Shopping Cart')]");
    private By cartRows = By.cssSelector(".cart_info tbody tr");
    private By removeButtons = By.cssSelector(".cart_quantity_delete");
    private By emptyCartText = By.xpath(
            "//*[contains(translate(normalize-space(.),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CART IS EMPTY')]"
    );

    public boolean isCartPageLoaded() {
        return WaitUtils.waitForVisible(cartTitle).isDisplayed();
    }

    public int getCartItemCount() {
        return DriverFactory.getDriver().findElements(cartRows).size();
    }

    public boolean hasItems() {
        return getCartItemCount() > 0;
    }

    public void removeFirstItem() {

        DriverFactory.hideAdIframes();

        var btn = WaitUtils.waitForClickable(removeButtons);
        WaitUtils.scrollToElement(btn);
        btn.click();
    }

    public boolean isCartEmpty() {

        DriverFactory.hideAdIframes();

        try {
            return new org.openqa.selenium.support.ui.WebDriverWait(
                    DriverFactory.getDriver(),
                    java.time.Duration.ofSeconds(10)
            ).until(d -> d.findElements(cartRows).size() == 0);
        } catch (Exception e) {
            return DriverFactory.getDriver().findElements(emptyCartText).size() > 0;
        }
    }

}
