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

public class US13_SendAppreciation_Hakan {


    // We need these in different places so they are here
    WebDriver driver;
    String webUsername;


    @BeforeMethod
    public void setUp() throws IOException {

        // Setup for using configuration.properties
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("configuration.properties");
        properties.load(file);

        // Open driver from utilities
        driver = WebDriverFactory.getDriver(properties.getProperty("browser"));
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // Go to our environment
        driver.get(properties.getProperty("env"));


        // We need username in different places, so we created a variable for it
        String username = properties.getProperty("username");


        // Login using crm_login utilities
        CRM_Utilities.crm_login(driver, username, getProperty("password"));



        // Locate more button and click it
        WebElement moreButton = driver.findElement(By.xpath("//span[@id='feed-add-post-form-link-text']"));
        moreButton.click();



        // Locate appreciation button and click it
        WebElement appreciationButton = driver.findElement(By.xpath("//span[.='Appreciation']/span[@class='menu-popup-item-text']"));
        appreciationButton.click();



        // I used this variable for finding usernames website version (yes they are not same as credentials)
        webUsername = username.substring(0,1).toUpperCase() + username.substring(1,username.indexOf('@'))
                      +" "+username.substring(username.indexOf('c'),username.indexOf('y')).toUpperCase()
                      +username.substring(username.indexOf('y'),username.indexOf('.'));
        // This returns us a webUsername like Hr79 Cydeo

        // Print the website version of username
        System.out.println("Web Username = "+webUsername);



        //  Some template locators
        //  //WebElement notificationNameCheck = driver.findElement(By.xpath("//div[@class='feed-wrap']/div[@class='feed-item-wrap']//a[.='"+webUsername+"']"));
        //  //div[@class='feed-wrap']//div[@class='feed-item-wrap']//a[.='Hr38 Cydeo']

    }



    @Test
    public void testCase1_sendAndSeeMessage(){

        // There is a iFrame, so we have switch to that frame
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']")));


        // Locate and fill the appreciation input box
        WebElement appreciationInputBox = driver.findElement(By.xpath("//body[@contenteditable='true']"));

        // We have to reuse it, so I created a variable for it
        String message = "Great Job Team!!!";

        appreciationInputBox.sendKeys(message);


        // We should switch to parent frame in order to use oder buttons
        driver.switchTo().parentFrame();


        // Locate and click send button
        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendButton.click();



        // We need our date in order to make sure if the message is correct message (in this version "11:30 am")
        SimpleDateFormat formatter= new SimpleDateFormat("hh:mm aa");
        Date date = new Date(System.currentTimeMillis());

        // Converted it to our version (in this version "11:30 am")
        String currentTime = formatter.format(date).toLowerCase();
        // Print to check if it is correct
        System.out.println("Current Time --> "+currentTime);



        // Located 3 different element from our sent message (avoiding from bugs we need all 3)
        // To check who sent the appreciation message
        WebElement notificationNameCheck = driver.findElement(By.xpath("//div[contains(@class, 'feed-post-cont-wrap sonet-log-item-createdby') and contains(@class, '-post sonet-')]//a[.='"+webUsername+"']"));
        // To check if content is as expected
        WebElement notificationContentCheck = driver.findElement(By.xpath("//div[contains(@class, 'feed-post-cont-wrap sonet-log-item-createdby') and contains(@class, '-post sonet-')]//div[.='"+message+"']"));
        // To check if the notification is correct one
        // Because once you sent a message if you try to send again the same message you can't send. So our code find old message. To avoid this bug we need time
        WebElement notificationTimeCheck = driver.findElement(By.xpath("//div[contains(@class, 'feed-post-cont-wrap sonet-log-item-createdby') and contains(@class, '-post sonet-')]//div[contains(text(), '"+currentTime+"')]"));


        /*

        These are some locators that I used

        //  Old dynamic locator --> //div[@class='feed-wrap']/div[@class='feed-item-wrap']//a[.='"+webUsername+"']
        //  New dynamic locator --> //div[contains(@class, 'feed-post-cont-wrap sonet-log-item-createdby') and contains(@class, '-post sonet-')]//a[.='"+webUsername+"']
        //  New dynamic locator --> //div[contains(@class, 'feed-post-cont-wrap sonet-log-item-createdby') and contains(@class, '-post sonet-')]//div[.='"+message+"']
        //  Old dynamic locator --> //div[contains(@class, 'feed-post-cont-wrap sonet-log-item-createdby') and contains(@class, '-post sonet-')]//div[@class='feed-time']
        //  New dynamic locator --> //div[contains(@class, 'feed-post-cont-wrap sonet-log-item-createdby') and contains(@class, '-post sonet-')]//div[contains(text(), '"+formatter.format(date)+"')]

        */



        // Print to check if the texts are correct
        System.out.println("notificationNameCheck.getText() = " + notificationNameCheck.getText());
        System.out.println("notificationContentCheck.getText() = " + notificationContentCheck.getText());
        System.out.println("notificationTimeCheck.getText() = " + notificationTimeCheck.getText());




        // Check if username is correct
        Assert.assertEquals( notificationNameCheck.getText(), webUsername );
        // Check if message content is correct
        Assert.assertEquals( notificationContentCheck.getText(), message );
        // Check if the time is correct
        Assert.assertTrue( notificationTimeCheck.getText().contains(currentTime));


        /*


        //  Old locators that i used
        //  //div[@class='feed-wrap']/div[@class='feed-item-wrap']//div[contains(@class, 'feed-post-cont-wrap sonet-log-item-createdby-post sonet') and contains(@class, "-post sonet-")]
        //  //div[contains(@class, "feed-post-cont-wrap sonet-log-item-createdby")]


        /// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ///  *** These are locators for different types of messages (appreciations / poll / important) ***

        ///  reusable locator for posts     -->   //div[contains(@class, "feed-post-cont-wrap sonet-log-item-createdby") and contains(@class, "-post sonet-")]
        ///  reusable locator for votes     -->   //div[contains(@class, "feed-post-cont-wrap sonet-log-item-createdby") and contains(@class, "-vote sonet-")]
        ///  reusable locator for important -->   //div[contains(@class, "feed-post-cont-wrap sonet-log-item-createdby") and contains(@class, "-important sonet-")]

        ///  ***

        /// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


        /// I checked these for finding the locators
        /// class="feed-post-cont-wrap sonet-log-item-createdby-1621 sonet-log-item-where-U-1621-all sonet-log-item-where-U-1621-blog-post sonet-log-item-where-U-1621-blog"
        /// class="feed-post-cont-wrap sonet-log-item-createdby-1543 sonet-log-item-where-U-1543-all sonet-log-item-where-U-1543-blog-post sonet-log-item-where-U-1543-blog"
        /// class="feed-post-cont-wrap sonet-log-item-createdby-1673 sonet-log-item-where-U-1673-all sonet-log-item-where-U-1673-blog-post-vote sonet-log-item-where-U-1673-blog"
        /// class="feed-post-cont-wrap sonet-log-item-createdby-1673 sonet-log-item-where-U-1673-all sonet-log-item-where-U-1673-blog-post-vote sonet-log-item-where-U-1673-blog"
        /// class="feed-post-cont-wrap sonet-log-item-createdby-1561 sonet-log-item-where-U-1561-all sonet-log-item-where-U-1561-blog-post-important sonet-log-item-where-U-1561-blog"
        /// class="feed-post-cont-wrap sonet-log-item-createdby-1673 sonet-log-item-where-U-1673-all sonet-log-item-where-U-1673-blog-post-vote sonet-log-item-where-U-1673-blog"
        /// class="feed-post-cont-wrap sonet-log-item-createdby-1722 sonet-log-item-where-U-1722-all sonet-log-item-where-U-1722-blog-post sonet-log-item-where-U-1722-blog"


        ///  then i modified the locator that i need in my test for reusability
        ///  reusable locator for posts     -->   //div[contains(@class, "feed-post-cont-wrap sonet-log-item-createdby") and contains(@class, "-post sonet-")]//a[.='"+webUsername+"']


         */



    }



    @Test
    public void testCase2_seeWarningMessage(){

        // Directly find and click send button (message input box is empty)
        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendButton.click();

        // Locate warning message
        WebElement warningMessage = driver.findElement(By.xpath("//div[@class='feed-add-error']/span[@class='feed-add-info-text']"));

        // Expected warning message
        String expectedErrorMessage = "The message title is not specified";

        // Check if actual warning message is same as expected
        Assert.assertEquals(warningMessage.getText(),expectedErrorMessage);



    }



//  This method is commented because we want to check manually if everything worked as expected

//
//    @AfterMethod
//    public void tearDown() {
//        driver.close();
//    }
//


}
