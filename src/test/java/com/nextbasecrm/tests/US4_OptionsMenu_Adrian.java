package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.nextbasecrm.utilities.ConfigurationReader.getProperty;

public class US4_OptionsMenu_Adrian {

    WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {

        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("configuration.properties");
        properties.load(file);


        driver = WebDriverFactory.getDriver(properties.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(getProperty("env"));

        CRM_Utilities.crm_login(driver, getProperty("username"), getProperty("password"));
    }



    @Test
    public void usernameBlock(){
        WebElement usernameBlock = driver.findElement(By.id("user-block"));
        usernameBlock.click();

        WebElement myProfileButton = driver.findElement(By.xpath("//span[.='My Profile']"));
        System.out.println("myProfileButton.isDisplayed() = " + myProfileButton.isDisplayed());

        Assert.assertTrue(myProfileButton.isDisplayed(),"My Profile button is not displayed");

    }











}
