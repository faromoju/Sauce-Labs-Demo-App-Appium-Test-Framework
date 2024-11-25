package org.oluwatobitesting.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.jspecify.annotations.Nullable;
import org.oluwatobitesting.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends AndroidActions {
    //Declare AndroidDriver
    AndroidDriver driver;

    //Class Constructor
    public ProductsPage(AndroidDriver driver) {
        super(driver);

        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Locators
    @AndroidFindBy(accessibility = "sort button")
    private WebElement sortButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"store item text\"]")
    List<WebElement> productNames;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Thank you for submitting your review!\"]")
    public List<WebElement> rateModalText;

    @AndroidFindBy(accessibility = "Close Modal button")
    private WebElement closeModalButton;

    @AndroidFindBy(accessibility = "counter plus button")
    private WebElement counterPlusButton;

    @AndroidFindBy(accessibility = "Add To Cart button")
    private WebElement addToCartButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView")
    private WebElement counterAmount;

    @AndroidFindBy(accessibility = "counter minus button")
    private WebElement counterMinusButton;

    //Actions
    public void clickSortButton() {
        sortButton.click();
    }

    public void selectSortType(String sortType) {
        driver.findElement(AppiumBy.accessibilityId(sortType)).click();
    }

    public String firstProductName() {
        return productNames.get(0).getText();
    }

    public void rateProducts(String[] productName, String starNumber) {
        for (String productCounter : productName) {
            scrollToText(productCounter);
            for (int i = 0; i < productNames.size(); i++) {
                WebElement productNameElement = productNames.get(i);
                List<WebElement> rateProduct = driver.findElements(AppiumBy
                        .xpath("//android.view.ViewGroup[@content-desc=\"review star " + starNumber + "\"]"));

                if (productNameElement.getText().contains(productCounter)) {
                    rateProduct.get(i).click();
                    closeModalButton.click();
                    break;
                }
            }
        }
    }

    public void rateProduct(String starNumber) {
        List<WebElement> rateProduct = driver.findElements(AppiumBy
                .xpath("//android.view.ViewGroup[@content-desc=\"review star " + starNumber + "\"]"));

        rateProduct.get(0).click();
    }

    public void closeRateModal() {
        closeModalButton.click();
    }

    public void addProductsToCart(String[] productName, int numberOfProducts) {
        scrollToTop();
        for (String productCounter : productName) {
            scrollToText(productCounter);
            for (int i = 0; i < productNames.size(); i++) {
                WebElement productNameElement = productNames.get(i);
                WebElement product = driver.findElement(AppiumBy
                        .xpath("//android.widget.TextView[@content-desc=\"store item text\" and @text=\"" + productCounter + "\"]"));

                if (productNameElement.getText().contains(productCounter)) {
                    product.click();
                    for (int j = 1; j < numberOfProducts; j++) {
                        counterPlusButton.click();
                    }
                    addToCartButton.click();
                    driver.pressKey(new KeyEvent(AndroidKey.BACK));
                    break;
                }
            }
        }
    }

    public String getCounterAmount() {
        return counterAmount.getText();
    }

    public void addProductToCart(int numberOfProducts) {
        productNames.get(0).click();
        for (int j = 1; j < numberOfProducts; j++) {
            counterPlusButton.click();
        }
    }

    public void removeFromProductsCounter(String productName) {
        scrollToText(productName);
        WebElement product = driver.findElement(AppiumBy
                .xpath("//android.widget.TextView[@content-desc=\"store item text\" and @text=\""+productName+"\"]"));
        product.click();
        counterMinusButton.click();
    }

    public @Nullable String getAddToCartAttribute() {
        return addToCartButton.getAttribute("enabled");
    }

    //Remove Later
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]")
    private WebElement cartBadge;
    public void tapCartBadge() {
        cartBadge.click();
    }
}
