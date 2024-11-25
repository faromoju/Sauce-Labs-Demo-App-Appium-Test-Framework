package org.oluwatobitesting;

import org.oluwatobitesting.Pages.LandingPage;
import org.oluwatobitesting.Pages.LogInPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LogInIT extends ProjectSetup{
    static String validUsername;
    static String validPassword;
    static String lockedOutUsername;

    @BeforeClass(alwaysRun = true)
    public static void preLogInPageTest() {
        //Page Class Objects
        landingPageObject = new LandingPage(driver);
        logInPageObject = new LogInPage(driver);

        //Navigate to Log In Page
        landingPageObject.navigateToLogInPage();

        //Get Credentials from Log In Page
        validUsername = logInPageObject.getValidUsername();
        validPassword = logInPageObject.getValidPassword();
        lockedOutUsername = logInPageObject.getLockedOutUsername();
    }

    @Test(priority = 1)
    public void launchTest() {
        //Assertion for Landing Page
        Assert.assertFalse(landingPageObject.demoAppLogo.isEmpty());
    }

    @Test(priority = 2, dataProvider = "testData")
    public void logInWithoutUsername(String username, String password) {
        //Enter Password and Click Log In
        logInPageObject.enterPassword(password);
        logInPageObject.clickLoginButton();

        //Assertion for Username Error Message
        Assert.assertEquals(logInPageObject.getUsernameErrorMessage(), "Username is required");

        //Clear Password
        logInPageObject.clearPassword();
    }

    @Test(priority = 3, dataProvider = "testData")
    public void logInWithoutPassword(String username, String password) {
        //Enter Username and Click Log In
        logInPageObject.enterUsername(username);
        logInPageObject.clickLoginButton();

        //Assertion for Password Error Message
        Assert.assertEquals(logInPageObject.getPasswordErrorMessage(), "Password is required");

        //Clear Username
        logInPageObject.clearUsername();
    }

    @Test(priority = 4, dataProvider = "testData")
    public void logInWithInvalidCredentials(String username, String password) {
        //Enter Invalid Credentials and Click Log In
        logInPageObject.enterUsername(username);
        logInPageObject.enterPassword(password);
        logInPageObject.clickLoginButton();

        //Assertion for Invalid Credentials Error Message
        Assert.assertEquals(logInPageObject.getInvalidCredentialsErrorMessage(), "Provided credentials do not match any user in this service.");

        //Clear Username and Password
        logInPageObject.clearUsername();
        logInPageObject.clearPassword();
    }

    @Test(priority = 5, dataProvider = "testData")
    public void logInWithInvalidUsername(String username, String password) {
        //Enter Invalid Username and Click Log In
        logInPageObject.enterUsername(username);
        logInPageObject.enterPassword(validPassword);
        logInPageObject.clickLoginButton();

        //Assertion for Invalid Username Error Message
        Assert.assertEquals(logInPageObject.getInvalidCredentialsErrorMessage(), "Provided credentials do not match any user in this service.");

        //Clear Username and Password
        logInPageObject.clearUsername();
        logInPageObject.clearPassword();
    }

    @Test(priority = 6, dataProvider = "testData")
    public void logInWithInvalidPassword(String username, String password) {
        //Enter Invalid Password and Click Log In
        logInPageObject.enterUsername(validUsername);
        logInPageObject.enterPassword(password);
        logInPageObject.clickLoginButton();

        //Assertion for Invalid Password Error Message
        Assert.assertEquals(logInPageObject.getInvalidCredentialsErrorMessage(), "Provided credentials do not match any user in this service.");

        //Clear Username and Password
        logInPageObject.clearUsername();
        logInPageObject.clearPassword();
    }

    @Test(priority = 7, dataProvider = "testData")
    public void logInWithLockedOutUser(String username, String password) {
        //Enter Valid Credentials and Click Log In
        logInPageObject.enterUsername(lockedOutUsername);
        logInPageObject.enterPassword(validPassword);
        logInPageObject.clickLoginButton();

        //Assertion for Locked Out User Error Message
        Assert.assertEquals(logInPageObject.getLockedOutUserErrorMessage(), "Sorry, this user has been locked out.");

        //Clear Username and Password
        logInPageObject.clearUsername();
        logInPageObject.clearPassword();
    }

    @Test(priority = 8, dataProvider = "testData")
    public static void logInWithValidCredentials(String username, String password) {
        //Enter Valid Credentials and Click Log In
        logInPageObject.enterUsername(validUsername);
        logInPageObject.enterPassword(validPassword);
        logInPageObject.clickLoginButton();

        //Assertion for Valid User Log In
        Assert.assertFalse(logInPageObject.productsPageLogo.isEmpty());
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                {
                    "test", "password"
                }
        };
    }
}
