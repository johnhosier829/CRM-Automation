package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class US1_LoginFunctionality_Maxim {

    WebDriver driver;
    public static String[] user = {"hr79@cydeo.com", "hr80@cydeo.com", "hr81@cydeo.com", "helpdesk79@cydeo.com", "helpdesk80@cydeo.com", "helpdesk81@cydeo.com", "marketing79@cydeo.com", "marketing80@cydeo.com", "marketing81@cydeo.com"};
    public static String password = "UserUser";

    @BeforeMethod
    public void setUp() throws IOException {

        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("configuration.properties");
        properties.load(file);
        driver = WebDriverFactory.getDriver(properties.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com/");
    }

    @Test(priority = 0)
    public void pageTitleVerification() {

        String expectedTitle = "Authorization";
        Assert.assertEquals(driver.getTitle(), expectedTitle);

    }


    @Test(priority = 1)
    public void isCheckBoxDisplayed() {

        WebElement checkBox = driver.findElement(By.xpath("//input[@id='USER_REMEMBER']"));

        Assert.assertTrue(checkBox.isDisplayed());

    }

    @Test(priority = 2)
    public void errorTextVerify() throws InterruptedException {

        String expectedErrorText = "Incorrect username or password";

        // call utility method with invalid credentials
        CRM_Utilities.crm_login(driver, "Wrong", "Wrong");

        // Timeout to see alert

        Thread.sleep(5000);


        //locate alert message
        WebElement alertMessage = driver.findElement(By.xpath("//div[@class='errortext']"));

        //verifying alert text
        Assert.assertEquals(alertMessage.getText(), expectedErrorText);


    }


    @Test(priority = 3)
    public void loginWithInvalidCredentials() throws InterruptedException {

// #1. Login with Wrong credentials
        CRM_Utilities.crm_login(driver, "Wrong", "Wrong");
        WebElement alertMessage = driver.findElement(By.xpath("//div[@class='errortext']"));
        Assert.assertTrue(alertMessage.isDisplayed());
        driver.close();

        // #2.   Login with Wrong username and correct password
        driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://login2.nextbasecrm.com/");
        CRM_Utilities.crm_login(driver, "Wrong", password);
        alertMessage = driver.findElement(By.xpath("//div[@class='errortext']"));
        Assert.assertTrue(alertMessage.isDisplayed());
        driver.close();


        // #3.  Login with correct username and wrong password
        driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://login2.nextbasecrm.com/");
        CRM_Utilities.crm_login(driver, user[1], "Wrong");
        alertMessage = driver.findElement(By.xpath("//div[@class='errortext']"));
        Assert.assertTrue(alertMessage.isDisplayed());
        driver.close();

        // #4.  Login with blank credentials
        driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://login2.nextbasecrm.com/");
        CRM_Utilities.crm_login(driver, "", "");
        alertMessage = driver.findElement(By.xpath("//div[@class='errortext']"));
        Assert.assertTrue(alertMessage.isDisplayed());
        driver.close();


    }


    @Test(priority = 3)
    public void loginWithValidCredentialsAndClickBTN() throws InterruptedException {
        driver.close();


        String expectedURL = "https://login2.nextbasecrm.com/stream/?login=yes";

        for (String s : user) {

            driver = WebDriverFactory.getDriver("chrome");
            driver.get("https://login2.nextbasecrm.com/");
            CRM_Utilities.crm_login(driver, s, password);

            Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

            driver.manage().deleteAllCookies();
            driver.close();

        }


    }

    //Method for Key Enter
    public static void crm_login_EnterKey(WebDriver driver, String username, String password) {

        WebElement inputUsername = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        inputUsername.sendKeys(username);


        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        inputPassword.sendKeys(password);

        inputPassword.sendKeys(Keys.ENTER);


    }

    @Test(priority = 4)
    public void loginWithValidCredentialsAndEnterKey() throws InterruptedException {
        driver.close();

        String expectedURL = "https://login2.nextbasecrm.com/stream/?login=yes";

        for (String s : user) {

            driver = WebDriverFactory.getDriver("chrome");
            driver.get("https://login2.nextbasecrm.com/");
            crm_login_EnterKey(driver, s, password);

            Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

            driver.manage().deleteAllCookies();
            driver.close();

        }


    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
