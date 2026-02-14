package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;
import utils.WaitUtils;

import java.util.List;

public class ProductsPage {

    private By productsTitle = By.cssSelector(".title.text-center");
    private By productCards = By.cssSelector(".product-image-wrapper");
    private By firstAddToCartBtn = By.xpath("(//a[contains(@class,'add-to-cart')])[1]");
    private By viewCartBtn = By.xpath("//u[contains(text(),'View Cart')]");
    private By searchedProductsTitle = By.xpath("//*[contains(translate(normalize-space(.),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'SEARCHED PRODUCTS')]");
    private By productNameTexts = By.cssSelector(".productinfo p");
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");



    public boolean isProductsPageLoaded() {
        DriverFactory.hideAdIframes();

        try {
            WaitUtils.waitForVisible(searchInput);
            return true;
        } catch (Exception ignored) {}

        return WaitUtils.waitForVisible(productsTitle).isDisplayed();
    }



    public int getNumberOfProducts() {
        List<WebElement> products = DriverFactory.getDriver().findElements(productCards);
        return products.size();
    }

    public boolean productsExist() {
        return getNumberOfProducts() > 0;
    }

    public void addFirstProductToCart() {
        WebElement el = WaitUtils.waitForClickable(firstAddToCartBtn);
        WaitUtils.scrollToElement(el);

        DriverFactory.hideAdIframes();

        try {
            el.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            DriverFactory.hideAdIframes();
            ((org.openqa.selenium.JavascriptExecutor) DriverFactory.getDriver())
                    .executeScript("arguments[0].click();", el);
        }
    }

    public void clickViewCart() {
        WebElement el = WaitUtils.waitForClickable(viewCartBtn);
        WaitUtils.scrollToElement(el);
        el.click();
    }

    public void searchForProduct(String keyword) {

        try {
            new org.openqa.selenium.support.ui.WebDriverWait(DriverFactory.getDriver(), java.time.Duration.ofSeconds(10))
                    .until(d -> d.getCurrentUrl().contains("/products"));
        } catch (Exception ignored) {}

        DriverFactory.hideAdIframes();

        By searchInputStable = By.cssSelector("#search_product");

        WebElement input = WaitUtils.waitForClickable(searchInputStable);
        WaitUtils.scrollToElement(input);
        input.clear();
        input.sendKeys(keyword);

        WaitUtils.scrollToElement(input);
        input.clear();
        input.sendKeys(keyword);

        WebElement btn = WaitUtils.waitForClickable(searchButton);
        WaitUtils.scrollToElement(btn);
        btn.click();
    }


    public boolean isSearchedProductsSectionVisible() {
        DriverFactory.hideAdIframes();
        return WaitUtils.waitForVisible(searchedProductsTitle).isDisplayed();
    }

    public boolean atLeastOneResultContains(String keyword) {
        String k = keyword.trim().toLowerCase();

        for (WebElement el : DriverFactory.getDriver().findElements(productNameTexts)) {
            String txt = el.getText().trim().toLowerCase();
            if (!txt.isEmpty() && txt.contains(k)) {
                return true;
            }
        }
        return false;
    }

    private By firstViewProductBtn = By.xpath("(//a[contains(text(),'View Product')])[1]");

    public void clickFirstViewProduct() {
        DriverFactory.hideAdIframes();

        WebElement el = WaitUtils.waitForClickable(firstViewProductBtn);
        WaitUtils.scrollToElement(el);
        el.click();
    }


}
