package org.oluwatobitesting.Utils;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AppiumUtilities {
    AndroidDriver driver;
    WebDriverWait wait;

    public AppiumUtilities(AndroidDriver driver) {
        this.driver = driver;
    }

    public void waitUntilClickable(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String getScreenshot(String testCaseName, AndroidDriver driver) throws IOException {
        File screenshotFolder = new File("reports");
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        String screenshotFile = screenshotFolder.getAbsolutePath() + "/Screenshots/" + testCaseName + ".png";
        FileUtils.copyFile(screenshot, new File(screenshotFile));
        return screenshotFile;
    }
}
