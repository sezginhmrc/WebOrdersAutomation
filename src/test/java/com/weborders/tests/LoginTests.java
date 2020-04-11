package com.weborders.tests;

import com.weborders.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
// everything will be imported

public class LoginTests extends AbstractBaseTest{

    @Test // test annotion to make it runnable
    public void logintest(){
      extentTest =  extentReports.createTest("Verify Page Logo");
      // we have to create report at the begining
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        assertEquals(loginPage.getPageLogText(),"Web Orders");
        extentTest.pass("Logo verified");
        // if test pass it will executed
        // if test fails screenschot will be taken and sent the report
    }
}
