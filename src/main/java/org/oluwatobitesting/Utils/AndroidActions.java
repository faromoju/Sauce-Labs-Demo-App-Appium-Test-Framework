package org.oluwatobitesting.Utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumUtilities{
    AndroidDriver driver;

    public AndroidActions(AndroidDriver driver) {
        super(driver);

        this.driver = driver;
    }

    public void startActivity(String appActivity) {
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity",
                ImmutableMap.of("intent", appActivity));
    }

    public void scrollToText(String text) {
        driver.findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(text(\""+text+"\"));"));
    }

    public void scrollToElement(WebElement element) {
        driver.findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+element+");"));
    }

    public void tapAndroidBackButton() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void scrollToTop() {
        driver.findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector()).scrollToBeginning(4);"));
    }

    public void tapCoordinates() {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "x", 468 , "y" , 1530
        ));
    }
}
