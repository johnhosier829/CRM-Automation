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

        //Click on Username block to open dropdown menu
        WebElement usernameBlock = driver.findElement(By.id("user-block"));
        usernameBlock.click();

        //Check if 'My Profile' button is displayed
        WebElement myProfileButton = driver.findElement(By.xpath("//span[.='My Profile']"));
        System.out.println("myProfileButton.isDisplayed() = " + myProfileButton.isDisplayed());

        Assert.assertTrue(myProfileButton.isDisplayed(),"My Profile button is not displayed");

        //Check if 'Edit Profile Settings' button is displayed
        WebElement editProfileSettingsButton = driver.findElement(By.xpath("//span[.='Edit Profile Settings']"));
        System.out.println("editProfileSettingsBlock = " + editProfileSettingsButton.isDisplayed());

        Assert.assertTrue(editProfileSettingsButton.isDisplayed(),"Edit Profile Settings button is not displayed");

        //Check if 'Themes' button is displayed
        WebElement themesButton = driver.findElement(By.xpath("//*[@id=\"popup-window-content-menu-popup-user-menu\"]/div/div/span[1]/span[2]"));
        System.out.println("themesButton = " + themesButton.isDisplayed());

        Assert.assertTrue(themesButton.isDisplayed(), "Themes button is not displayed");

        //Check if 'Configure Notifications' button is displayed
        WebElement configureNotificationsButton = driver.findElement(By.xpath("//*[@id=\"popup-window-content-menu-popup-user-menu\"]/div/div/span[2]/span[2]"));
        System.out.println("configureNotificationsButton = " + configureNotificationsButton.isDisplayed());

        Assert.assertTrue(configureNotificationsButton.isDisplayed(), "Configure Notifications button is not displayed");

        //Check if 'Log Out' button is displayed
        WebElement logOutButton = driver.findElement(By.xpath("//span [.='Log out']"));
        System.out.println("logOutButton = " + logOutButton.isDisplayed());

        Assert.assertTrue(logOutButton.isDisplayed(), "Log out button is not displayed");

    }

//Close all browsers after Test Method is run
    @AfterMethod
    public void exitBrowser(){
        driver.quit();
    }









}
