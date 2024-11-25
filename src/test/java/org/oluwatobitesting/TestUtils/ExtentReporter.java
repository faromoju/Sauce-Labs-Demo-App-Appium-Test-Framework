package org.oluwatobitesting.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporter {
    static ExtentReports report;

    public static ExtentReports getReporterObject() {
        //ExtentSparkReporter
        File reportsPath = new File("reports");
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportsPath.getAbsolutePath() + "/index.html");
        reporter.config().setReportName("Appium Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        //ExtentReports
        report = new ExtentReports();
        report.attachReporter(reporter);
        return report;
    }
}
