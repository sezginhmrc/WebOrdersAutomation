package com.weborders.tests;

// extent report libraries
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.weborders.utilities.BrowserUtilities;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public abstract class AbstractBaseTest {

    // essential setup and teardown for all tests

    protected WebDriver driver = Driver.getDriver();

    protected static ExtentReports extentReports ;

    protected static ExtentHtmlReporter extentHtmlReporter ;

    protected static ExtentTest extentTest ;
    // static to prevent nullPointerException

    @BeforeTest
    public void beforeTest() {
        extentReports = new ExtentReports();
        String reportPath = "";
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            reportPath = System.getProperty("user.dir") + "\\test-output\\report.html";
            // for window
        } else {
            reportPath = System.getProperty("user.dir") + "/test-output/report.html";
            // for mac
        }
        extentHtmlReporter  = new ExtentHtmlReporter(reportPath);
        // to html
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setReportName("WebOrders Automation");
    }

    @AfterTest
    public void afterTest(){
            extentReports.flush();
    }


    @BeforeMethod
    public void setup(){
        driver.get(ConfigurationReader.getProperty("url"));
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void teardown(ITestResult iTestResult){
        if(iTestResult.getStatus()==iTestResult.FAILURE){
            String screenshotLocation =  BrowserUtilities.getScreenshot(iTestResult.getName());
        }
        BrowserUtilities.wait(3);
        Driver.closeDriver();
    }
}
