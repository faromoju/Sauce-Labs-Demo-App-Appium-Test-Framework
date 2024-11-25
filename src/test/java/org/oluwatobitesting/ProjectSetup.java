package org.oluwatobitesting;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.oluwatobitesting.Pages.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class ProjectSetup {
    static AppiumDriverLocalService service;
    public static AndroidDriver driver;
    static String packageName;
    static String iPAddress;
    static String port;

    //Page Objects
    static CheckoutPage checkoutPageObject;
    static CartPage cartPageObject;
    static ProductsPage productsPageObject;
    static LogInPage logInPageObject;
    static LandingPage landingPageObject;


    @BeforeSuite(alwaysRun = true)
    public static void StartAppium() throws URISyntaxException, IOException {
        //Get Properties Data
        File propertiesFile = new File("src/main/java/org/oluwatobitesting/Resources/data.properties");
        Properties property = new Properties();
        FileInputStream propertiesFileData = new FileInputStream(propertiesFile.getAbsoluteFile());
        property.load(propertiesFileData);

        iPAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : property.getProperty("iPAddress");
        port = System.getProperty("port") != null ? System.getProperty("port") : property.getProperty("port");
        String androidDeviceName = System.getProperty("androidDeviceName") != null ? System.getProperty("androidDeviceName") : property.getProperty("androidDeviceName");
        String androidUdId = System.getProperty("androidUdId") != null ? System.getProperty("androidUdId") : property.getProperty("androidUdId");

        //Appium Service
        service = new AppiumServiceBuilder()
                .withIPAddress(iPAddress).usingPort(Integer.parseInt(port))
                .withArgument(() -> "--allow-insecure", "chromedriver_autodownload").build();

        //Start Appium Server
        service.start();

        //Set Test Apk File Path
        File testApk = new File("app/Android-MyDemoAppRN.1.3.0.build-244.apk");

        //Set Device Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(androidDeviceName);
        options.setApp(testApk.getAbsolutePath());
        options.setUdid(androidUdId);

        //Instantiate Android Driver
        driver = new AndroidDriver(new URI("http://127.0.0.1:4728/").toURL(), options);

        //Get Package Name
        packageName = driver.getCurrentPackage();

        //Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterSuite(alwaysRun = true)
    public static void shutdownAppium() {
        //Close App
        driver.quit();

        //Shutdown Appium Server
        service.stop();
    }
}
