package com.weborders.pages;

import com.weborders.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBasePage {
    // it meant to be extended for all page classes.(subclasses)
    protected WebDriver driver = Driver.getDriver();
    // driver accesible in every page classes
    protected WebDriverWait wait = new WebDriverWait(driver,20);
    // We have to apply wait within page classes not in test classes.
    // We implement waits, elements, locators everything in page classes not in test classses ! ! !
    // POM means correspond page classes for test cases.
    // reverse enginering easily
    // encapsulation about design not security
    // kitchen and dining room example (encapsulation) non eseential parts hiding from user.


    // this element present in every page thats why we put here
    @FindBy(tagName = "h1")
    protected WebElement pageLogo ;

    public String getPageLogText(){
        return pageLogo.getText();
    }

    public void NaviagaTo(String component){
        String locator = "//a[text()='"+component+"']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).click();

    }


    public AbstractBasePage(){
        PageFactory.initElements(driver,this);
        //  to use and find @Findby in all subclasses


    }
}
