package org.oluwatobitesting;

import org.oluwatobitesting.Pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutIT extends ProjectSetup{
    @BeforeClass(alwaysRun = true)
    public void preCheckoutPageTest() {
        checkoutPageObject = new CheckoutPage(driver);
    }

    @Test(priority = 1)
    public void checkoutPageVerification() {
        //Assertion for Checkout Page
        Assert.assertFalse(checkoutPageObject.checkoutPageTitle.isEmpty());
    }

    @Test(priority = 2)
    public void proceedToPaymentWithoutFullName() {
        //Tap To Payment Button
        checkoutPageObject.tapToPaymentButton();

        //Assertion for To Payment without Full Name
        Assert.assertEquals(checkoutPageObject.getFullNameErrorMessage(), "Please provide your full name.");
    }

    @Test(priority = 3)
    public void proceedToPaymentWithoutAddressLine1() {
        //Tap To Payment Button
        checkoutPageObject.tapToPaymentButton();

        //Assertion for To Payment without Address Line
        Assert.assertEquals(checkoutPageObject.getAddressLine1ErrorMessage(), "Please provide your address.");
    }

    @Test(priority = 4)
    public void proceedToPaymentWithoutCity() {
        //Tap To Payment Button
        checkoutPageObject.tapToPaymentButton();

        //Assertion for To Payment without City
        Assert.assertEquals(checkoutPageObject.getCityErrorMessage(), "Please provide your city.");
    }

    @Test(priority = 5)
    public void proceedToPaymentWithoutZipCode() {
        //Tap To Payment Button
        checkoutPageObject.tapToPaymentButton();

        //Assertion for To Payment without Zip Code
        Assert.assertEquals(checkoutPageObject.getZipCodeErrorMessage(), "Please provide your zip code.");
    }

    @Test(priority = 6)
    public void proceedToPaymentWithoutCountry() {
        //Tap To Payment Button
        checkoutPageObject.tapToPaymentButton();

        //Assertion for To Payment without Country
        Assert.assertEquals(checkoutPageObject.getCountryErrorMessage(), "Please provide your country.");
    }

    @Test(priority = 7, dataProvider = "testData")
    public void proceedToPaymentWithValidDetails(
            String firstName, String lastName, String addressLine1, String addressLine2,
            String city, String state, String zipCode, String country) {

        //Enter Required Details
        checkoutPageObject.enterFullName(firstName, lastName);
        checkoutPageObject.enterAddressLine1(addressLine1);
        checkoutPageObject.enterAddressLine2(addressLine2);
        checkoutPageObject.enterCity(city);
        checkoutPageObject.enterState(state);
        checkoutPageObject.enterZipCode(zipCode);
        checkoutPageObject.enterCountry(country);

        //Tap To Payment Button
        checkoutPageObject.tapToPaymentButton();

        //Assertion for Tap To Payment Successfully
        Assert.assertEquals(checkoutPageObject.getReviewOrderText(), "Review Order");
    }

    @Test(priority = 8)
    public void checkSameBillingAddressCheckbox() {
        //Tap To Enable Same As Shipping Address Checkbox
        checkoutPageObject.tapSameAsShippingAddressCheckbox();

        //Assertion for Checkbox
        Assert.assertTrue(checkoutPageObject.shippingAddressCheckboxValue.isEmpty());

        //Tap To Disable Same As Shipping Address Checkbox
        checkoutPageObject.tapSameAsShippingAddressCheckbox();

        //Assertion for Checkbox
        Assert.assertFalse(checkoutPageObject.shippingAddressCheckboxValue.isEmpty());
    }

    @Test(priority = 9)
    public void reviewOrderWithoutFullName() {
        //Tap Review Order Button
        checkoutPageObject.tapReviewOrder();

        //Assertion for Review Order without Full Name
        Assert.assertEquals(checkoutPageObject.getReviewOrderFullNameErrorMessage(), "Value looks invalid.");
    }

    @Test(priority = 10)
    public void reviewOrderWithInvalidCardNumber() {
        //Enter an Invalid Card Number
        checkoutPageObject.enterCardNumber("5281901287651243");

        //Tap Another Field
        checkoutPageObject.tapCoordinates();

        //Assertion for Review Order with Invalid Card Number
        Assert.assertEquals(checkoutPageObject.getCardNumberErrorMessage(), "Value looks invalid.");

        //Clear Invalid Card Number
        checkoutPageObject.clearCardNumber();
    }

    @Test(priority = 11)
    public void reviewOrderWithInvalidExpirationDate() {
        //Enter an Invalid Expiration Date
        checkoutPageObject.enterExpirationDate("01");

        //Tap Another Field
        checkoutPageObject.tapCoordinates();

        //Assertion for Review Order with Invalid Expiration Date
        Assert.assertEquals(checkoutPageObject.getExpirationDateErrorMessage(), "Value looks invalid.");

        //Clear Invalid Expiration Date
        checkoutPageObject.clearExpirationDate();
    }

    @Test(priority = 12)
    public void reviewOrderWithInvalidSecurityCode() {
        //Enter an Invalid Security Code
        checkoutPageObject.enterSecurityCode("49");

        //Tap Another Field
        checkoutPageObject.tapCoordinates();

        //Assertion for Review Order with Invalid Security Code
        Assert.assertEquals(checkoutPageObject.getSecurityCodeErrorMessage(), "Value looks invalid.");

        //Clear Invalid Security Code
        checkoutPageObject.clearSecurityCode();
    }

    @Test(priority = 13)
    public void checkSecurityCodeInfoButton() {
        //Tap To Display Security Code Information
        checkoutPageObject.tapSecurityCodeInfo();

        //Assertion for Security Code Information Pop Up
        Assert.assertFalse(checkoutPageObject.securityCodeInfoMessage.isEmpty());

        //Tap To stop Displaying Security Code Information
        checkoutPageObject.tapSecurityCodeInfo();

        //Assertion for Security Code Information Pop Up
        Assert.assertTrue(checkoutPageObject.securityCodeInfoMessage.isEmpty());
    }

    @Test(priority = 14, dataProvider = "testData")
    public void reviewOrderWithValidDetails(
            String firstName, String lastName, String addressLine1, String addressLine2,
            String city, String state, String zipCode, String country) {

        //Enter Required Details
        checkoutPageObject.enterFullName(firstName, lastName);
        checkoutPageObject.enterCardNumber("5123450000000008");
        checkoutPageObject.enterExpirationDate("0139");
        checkoutPageObject.enterSecurityCode("452");

        //Tap Review Order Button
        checkoutPageObject.tapReviewOrder();

        //Assertion for Tap Review Order Successfully
        checkoutPageObject.scrollToDeliveryService("DHL Standard Delivery");
        Assert.assertFalse(checkoutPageObject.deliveryService.isEmpty());
    }

    @Test(priority = 15)
    public void completedOrderValidation() {
        //Tap Place Order Button
        checkoutPageObject.tapPlaceOrderButton();

        //Assertion for Completed Order Page
        Assert.assertFalse(checkoutPageObject.checkoutCompletePageTitle.isEmpty());

        //Tap Continue Shopping Button
        checkoutPageObject.tapContinueShoppingButton();

        //Assertion for Continue Shopping Button
        Assert.assertTrue(checkoutPageObject.cartBadgeNumber.isEmpty());
    }

    @DataProvider()
    public Object[][] testData() {
        return new Object[][] {
            {
                "Test", "User", "New Place Avenue", "Lake City Villa", "Citeh", "Lagos", "121096", "Nigeria"
            }
        };
    }
}
