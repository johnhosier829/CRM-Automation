package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class User_poll_and_answer {
    //1TC for poll and answer

    /**
     * 1-go to login page
     * 2-write username
     * 3-write password
     * 4-click login button
     */
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //  1-go to login page
        driver.get(ConfigurationReader.getProperty("env"));
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.sleep(3);
        driver.close();
    }

    @Test
    public void login_with_valid_credentials_with_login_btn(){
        // 2-write username

        //     * 3-write password
        //     * 4-click login button
    }


}
