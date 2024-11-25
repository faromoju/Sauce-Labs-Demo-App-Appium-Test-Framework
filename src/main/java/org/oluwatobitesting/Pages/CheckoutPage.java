package org.oluwatobitesting.Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.oluwatobitesting.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AndroidActions {
    //Declare AndroidDriver
    AndroidDriver driver;

    public CheckoutPage(AndroidDriver driver) {
        super(driver);

        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Locators
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Checkout\"]")
    public List<WebElement> checkoutPageTitle;

    @AndroidFindBy(accessibility = "To Payment button")
    private WebElement toPaymentButton;

    @AndroidFindBy(accessibility = "Full Name* input field")
    private WebElement fullNameField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Full Name*-error-message\"]/android.widget.TextView")
    private WebElement fullNameErrorMessage;

    @AndroidFindBy(accessibility = "Address Line 1* input field")
    private WebElement addressLine1Field;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Address Line 1*-error-message\"]/android.widget.TextView")
    private WebElement addressLine1ErrorMessage;

    @AndroidFindBy(accessibility = "Address Line 2 input field")
    private WebElement addressLine2Field;

    @AndroidFindBy(accessibility = "City* input field")
    private WebElement cityField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"City*-error-message\"]/android.widget.TextView")
    private WebElement cityErrorMessage;

    @AndroidFindBy(accessibility = "State/Region input field")
    private WebElement stateField;

    @AndroidFindBy(accessibility = "Zip Code* input field")
    private WebElement zipCodeField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Zip Code*-error-message\"]/android.widget.TextView")
    private WebElement zipCodeErrorMessage;

    @AndroidFindBy(accessibility = "Country* input field")
    private WebElement countryField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Country*-error-message\"]/android.widget.TextView")
    private WebElement countryErrorMessage;

    @AndroidFindBy(accessibility = "Review Order button")
    private WebElement reviewOrderButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Review Order\"]")
    private WebElement reviewOrderButtonText;

    @AndroidFindBy(accessibility = "checkbox for My billing address is the same as my shipping address.")
    private WebElement sameAsShippingAddressCheckbox;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"checkbox for My billing address is the same as my shipping address.\"]/android.view.ViewGroup/android.widget.ImageView")
    public List<WebElement> shippingAddressCheckboxValue;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Full Name*-error-message\"]/android.widget.TextView")
    private WebElement reviewOrderFullNameErrorMessage;

    @AndroidFindBy(accessibility = "Card Number* input field")
    private WebElement cardNumberField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Card Number*-error-message\"]/android.widget.TextView")
    private WebElement cardNumberErrorMessage;

    @AndroidFindBy(accessibility = "Expiration Date* input field")
    private WebElement expirationDateField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Expiration Date*-error-message\"]/android.widget.TextView")
    private WebElement expirationDateErrorMessage;

    @AndroidFindBy(accessibility = "Security Code* input field")
    private WebElement securityCodeField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Security Code*-error-message\"]/android.widget.TextView")
    private WebElement securityCodeErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"?\"]")
    private WebElement securityCodeInfoButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CVV is the last three digits on the back of your credit card.\"]")
    public List<WebElement> securityCodeInfoMessage;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"DHL Standard Delivery\")")
    public List<WebElement> deliveryService;

    @AndroidFindBy(accessibility = "Place Order button")
    private WebElement placeOrderButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Checkout Complete\"]")
    public List<WebElement> checkoutCompletePageTitle;

    @AndroidFindBy(accessibility = "Continue Shopping button")
    private WebElement continueShoppingButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.TextView")
    public List<WebElement> cartBadgeNumber;

    //Actions
    public void tapToPaymentButton() {
        toPaymentButton.click();
    }

    public void enterFullName(String firstName, String lastName) {
        fullNameField.sendKeys(firstName + " " + lastName);
    }

    public String getFullNameErrorMessage() {
        return fullNameErrorMessage.getText();
    }

    public void enterAddressLine1(String addressLine1) {
        addressLine1Field.sendKeys(addressLine1);
    }

    public String getAddressLine1ErrorMessage() {
        return addressLine1ErrorMessage.getText();
    }

    public void enterAddressLine2(String addressLine2) {
        addressLine2Field.sendKeys(addressLine2);
    }

    public void enterCity(String city) {
        cityField.sendKeys(city);
    }

    public String getCityErrorMessage() {
        return cityErrorMessage.getText();
    }

    public void enterState(String state) {
        stateField.sendKeys(state);
    }

    public void enterZipCode(String zipCode) {
        zipCodeField.sendKeys(zipCode);
    }

    public String getZipCodeErrorMessage() {
        return zipCodeErrorMessage.getText();
    }

    public void enterCountry(String country) {
        countryField.sendKeys(country);
    }

    public String getCountryErrorMessage() {
        return countryErrorMessage.getText();
    }

    //Review Order Page (Still Checkout)
    public void tapReviewOrder() {
        reviewOrderButton.click();
    }

    public String getReviewOrderText() {
        return reviewOrderButtonText.getText();
    }

    public void tapSameAsShippingAddressCheckbox() {
        sameAsShippingAddressCheckbox.click();
    }

    public String getReviewOrderFullNameErrorMessage() {
        return reviewOrderFullNameErrorMessage.getText();
    }

    public void enterCardNumber(String cardNumber) {
        cardNumberField.sendKeys(cardNumber);
    }

    public String getCardNumberErrorMessage() {
        return cardNumberErrorMessage.getText();
    }

    public void clearCardNumber() {
        cardNumberField.clear();
    }

    public void enterExpirationDate(String expirationDate) {
        expirationDateField.sendKeys(expirationDate);
    }

    public String getExpirationDateErrorMessage() {
        return expirationDateErrorMessage.getText();
    }

    public void clearExpirationDate() {
        expirationDateField.clear();
    }

    public void enterSecurityCode(String securityCode) {
        securityCodeField.sendKeys(securityCode);
    }

    public String getSecurityCodeErrorMessage() {
        return securityCodeErrorMessage.getText();
    }

    public void clearSecurityCode() {
        securityCodeField.clear();
    }

    public void tapSecurityCodeInfo() {
        securityCodeInfoButton.click();
    }

    public void scrollToDeliveryService(String deliveryService) {
        scrollToText(deliveryService);
    }

    public void tapPlaceOrderButton() {
        placeOrderButton.click();
    }

    public void tapContinueShoppingButton() {
        continueShoppingButton.click();
    }
}
