package org.oluwatobitesting.Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.oluwatobitesting.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingPage extends AndroidActions {
    //Declare AndroidDriver
    AndroidDriver driver;

    //Class Constructor
    public LandingPage(AndroidDriver driver) {
        super(driver);

        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Locators
    @AndroidFindBy(accessibility = "longpress reset app")
    public List<WebElement> demoAppLogo;

    @AndroidFindBy(accessibility = "open menu")
    private WebElement menuButton;

    @AndroidFindBy(accessibility = "menu item log in")
    private WebElement logInTab;

    //Actions
    public void navigateToLogInPage() {
        menuButton.click();
        logInTab.click();
    }
}
