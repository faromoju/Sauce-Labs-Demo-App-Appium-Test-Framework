package org.oluwatobitesting.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.oluwatobitesting.Utils.AppiumUtilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {
    ExtentReports report = ExtentReporter.getReporterObject();
    ExtentTest test;
    AndroidDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        test = report.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "TEST PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //To Fail Test and Return Error Logs in Reporter
        test.fail(result.getThrowable());

        //Get driver for each Test
        try {
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Add Screenshot to Reporter. Gotten from Appium Utilities
        try {
            test.addScreenCaptureFromPath(AppiumUtilities.getScreenshot(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }
}
