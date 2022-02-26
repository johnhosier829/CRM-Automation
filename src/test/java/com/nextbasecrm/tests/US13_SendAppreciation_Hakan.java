package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US13_SendAppreciation_Hakan {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        CRM_Utilities.crm_login(driver, "hr79@cydeo.com", "UserUser");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement moreButton = driver.findElement(By.xpath("//span[@id='feed-add-post-form-link-text']"));
        moreButton.click();

        WebElement appreciationButton = driver.findElement(By.xpath("//span[.='Appreciation']/span[@class='menu-popup-item-text']"));
        appreciationButton.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }

    @Test
    public void testCase1_sendAndSeeMessage(){

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']")));

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement appreciationInputBox = driver.findElement(By.xpath("//body[@contenteditable='true']"));
        appreciationInputBox.sendKeys("Thank You! This is a auto-message!");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.switchTo().parentFrame();

        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendButton.click();
    }

    @Test
    public void testCase2_seeWarningMessage(){

        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendButton.click();

    }

}
