package org.oluwatobitesting.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.oluwatobitesting.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AndroidActions {
    //Declare AndroidDriver
    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);

        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Locators
    @AndroidFindBy(accessibility = "cart badge")
    private WebElement cartBadge;

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"cart screen\"]/android.view.ViewGroup/android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView")
    private WebElement cartPageTitle;

    @AndroidFindBy(accessibility = "total number")
    private WebElement productTotalNumber;

    @AndroidFindBy(accessibility = "total price")
    private WebElement productTotalAmount;

    @AndroidFindBy(accessibility = "Proceed To Checkout button")
    private WebElement checkoutButton;

    //Actions
    public void tapCartBadge() {
        cartBadge.click();
    }

    public String getCartPageTitle() {
        return cartPageTitle.getText();
    }

    public void removeProductFromCart(String product) {
        scrollToText(product);
        WebElement removeButton = driver.findElement(AppiumBy
                .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + product + "\"]/following-sibling::android.view.ViewGroup[@content-desc=\"remove item\"]"));
        removeButton.click();
    }

    public String getRemovedProduct(String product) {
        scrollToText(product);
        WebElement removedProduct = driver.findElement(AppiumBy
                .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + product + "\"]"));

        return removedProduct.getText();
    }

    public void reduceProductFromCart(String product, int numberOfProductsLeft) {
        scrollToText(product);
        WebElement numberOfProduct = driver.findElement(AppiumBy
                .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + product + "\"]/following-sibling::android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView"));

        WebElement counterMinusButton = driver.findElement(AppiumBy
                .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + product + "\"]/following-sibling::android.view.ViewGroup[@content-desc=\"counter minus button\"]"));

        int numberOfProducts = Integer.parseInt(numberOfProduct.getText());

        for (int i = numberOfProducts; i > numberOfProductsLeft; i--) {
            waitUntilClickable(counterMinusButton);
            counterMinusButton.click();
        }
    }

    public int getNumberOfProduct(String product) {
        scrollToText(product);
        WebElement numberOfProduct = driver.findElement(AppiumBy
                .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + product + "\"]/following-sibling::android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView"));

        return Integer.parseInt(numberOfProduct.getText());
    }

    public void addProductToCart(String product, int numberOfProductsAdded) {
        scrollToText(product);
        WebElement numberOfProduct = driver.findElement(AppiumBy
                .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + product + "\"]/following-sibling::android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView"));

        WebElement counterPlusButton = driver.findElement(AppiumBy
                .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + product + "\"]/following-sibling::android.view.ViewGroup[@content-desc=\"counter plus button\"]"));

        int numberOfProducts = Integer.parseInt(numberOfProduct.getText());

        for (int i = numberOfProducts; i < numberOfProductsAdded; i++) {
            waitUntilClickable(counterPlusButton);
            counterPlusButton.click();
        }
    }

    public int totalProductNumber(String[] productNames) {
        int productsSum = 0;

        for (String productCounter : productNames) {
            scrollToText(productCounter);

            WebElement numberOfProduct = driver.findElement(AppiumBy
                    .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + productCounter + "\"]/following-sibling::android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView"));

            productsSum = productsSum + Integer.parseInt(numberOfProduct.getText());
        }

        return productsSum;
    }

    public double totalProductAmount(String[] productNames) {
        double productsAmountSum = 0;

        for (String productCounter : productNames) {
            scrollToText(productCounter);

            WebElement productAmounts = driver.findElement(AppiumBy
                    .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + productCounter + "\"]/following-sibling::android.widget.TextView[@content-desc=\"product price\"]"));

            WebElement numberOfProduct = driver.findElement(AppiumBy
                    .xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + productCounter + "\"]/following-sibling::android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView"));

            double productAmount = Double.parseDouble(productAmounts.getText().split("\\$")[1]);
            double totalItem = Integer.parseInt(numberOfProduct.getText());

            productsAmountSum = productsAmountSum + (productAmount * totalItem);
        }

        return productsAmountSum;
    }

    public int getTotalProductNumber() {
        return Integer.parseInt(productTotalNumber.getText().trim().split(" ")[0]);
    }

    public Double getTotalProductAmount() {
        return Double.parseDouble(productTotalAmount.getText().trim().split("\\$")[1]);
    }

    public void tapCheckoutButton() {
        checkoutButton.click();
    }
}
