package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class US12_Announcement_Alena {

    public WebDriver driver;

    @BeforeMethod
    public void setupMethod(){
        // Go to https://login2.nextbasecrm.com/
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com/");
    }

        public void crm_login(){

            WebElement inputUsername = driver.findElement(By.xpath("//input[@name = 'USER_LOGIN']"));
            inputUsername.sendKeys("helpdesk79@cydeo.com");

            WebElement inputPassword = driver.findElement(By.xpath("//input[@name = 'USER_PASSWORD']"));
            inputPassword.sendKeys("UserUser");


            WebElement loginButton = driver.findElement(By.xpath("//input[@value ='Log In']"));
            loginButton.click();
    }

            //Find dropdown "More"
            WebElement moreDropdown = driver.findElement(By.id("feed-add-post-form-link-text"));




}
