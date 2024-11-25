package org.oluwatobitesting.Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.oluwatobitesting.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LogInPage extends AndroidActions {
    //Declare AndroidDriver
    AndroidDriver driver;

    //Class Constructor
    public LogInPage(AndroidDriver driver) {
        super(driver);

        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Locators
    @AndroidFindBy(accessibility = "Username input field")
    private WebElement usernameField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Username is required\"]")
    private WebElement usernameErrorMessage;

    @AndroidFindBy(accessibility = "Password input field")
    private WebElement passwordField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Password is required\"]")
    private WebElement passwordErrorMessage;

    @AndroidFindBy(accessibility = "Login button")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Provided credentials do not match any user in this service.\"]")
    private WebElement invalidCredentialsErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"bob@example.com\"]")
    private WebElement validUsername;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"10203040\"]")
    private WebElement validPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"alice@example.com (locked out)\"]")
    private WebElement lockedOutUsername;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sorry, this user has been locked out.\"]")
    private WebElement lockedOutUserErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Products\"]")
    public List<WebElement> productsPageLogo;

    //Actions
    public String getUsernameErrorMessage() {
        return usernameErrorMessage.getText();
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void clearUsername() {
        usernameField.clear();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clearPassword() {
        passwordField.clear();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getInvalidCredentialsErrorMessage() {
        return invalidCredentialsErrorMessage.getText();
    }

    public String getValidUsername() {
        return validUsername.getText();
    }

    public String getValidPassword() {
        return validPassword.getText();
    }

    public String getLockedOutUsername() {
        return lockedOutUsername.getText().trim().split(" ")[0];
    }

    public String getLockedOutUserErrorMessage() {
        return lockedOutUserErrorMessage.getText();
    }
}
