package com.weborders.pages;

import com.weborders.utilities.ConfigurationReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends  AbstractBasePage{

    @FindBy(id = "ctl00_MainContent_username")
    private WebElement username ;

    @FindBy (id = "ctl00_MainContent_password")
    private WebElement password ;


    public void login(){
        String usernamevalue = ConfigurationReader.getProperty("username");
        String passwordvalue = ConfigurationReader.getProperty("password");
        username.sendKeys(usernamevalue);
        password.sendKeys(passwordvalue);
    } // specified in configuration.properties

    public void login(String usernameValue, String passwordValue){
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);
    } // it allows us to specify different credentials
      // overloading example in Real project..
      // same action different method signature
    // POM cares elements are located in page classes
}
