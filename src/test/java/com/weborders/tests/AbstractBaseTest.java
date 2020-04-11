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

import java.io.IOException;

public abstract class AbstractBaseTest {

    // essential setup and teardown for all tests

    protected WebDriver driver;

    // static Extent reports class
    // to prevent nullPointerException
    protected static ExtentTest extentTest ;
    protected static ExtentReports extentReports ;

    protected static ExtentHtmlReporter extentHtmlReporter ;


    @BeforeTest
    // it will create report before for all test cases in class
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
    // will be executed after for all test cases run
    public void afterTest(){
            extentReports.flush();
    }


    @BeforeMethod
    // will be executed at the begining for each test case (@Test yazan heryerde)
    public void setup(){
        driver = Driver.getDriver();
        driver.get(ConfigurationReader.getProperty("url"));
        driver.manage().window().maximize();

    }

    @AfterMethod
    // will be executed at the end for each test cases run (@Test yazan heryerde)
    // reporting the test results and close driver for each test case.
    public void teardown(ITestResult iTestResult){
        // if test fails screen shot will be sent to the reoprt
        if(iTestResult.getStatus()==iTestResult.FAILURE){
            String screenshotLocation =  BrowserUtilities.getScreenshot(iTestResult.getName());
            try {
                extentTest.fail(iTestResult.getName()); // test name that failed in report
                extentTest.addScreenCaptureFromPath(screenshotLocation); // screenshot as an evidence in report
                extentTest.fail(iTestResult.getThrowable()); // error message (output) in report
            } catch (IOException e) {
                e.printStackTrace();
                throw  new RuntimeException("Failed to attach screenshot");
                // if screen shot not attached to the report exception will thrown
                // i have to fix the issue in order to flow of program
            }
        } else if ( iTestResult.getStatus() == iTestResult.SUCCESS){
            extentTest.pass(iTestResult.getName()); // if test pass  test name in report
        } else if (iTestResult.getStatus()== ITestResult.SKIP){
            extentTest.skip(iTestResult.getName()); // if test skpis test name in report
            // test name is method name (public void verifyWarningMessage)
        }
        BrowserUtilities.wait(3);
        Driver.closeDriver();
    }
}
