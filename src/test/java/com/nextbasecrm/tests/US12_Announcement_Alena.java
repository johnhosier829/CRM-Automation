package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.WebDriverFactory;
import org.apache.hc.core5.http.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US12_Announcement_Alena {

    WebDriver driver;

    @BeforeMethod
    public void setupMethod() {
        // Go to https://login2.nextbasecrm.com/
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com/");
    }

    @Test
    public void sendAnnouncement() {

        WebElement inputUsername = driver.findElement(By.xpath("//input[@name = 'USER_LOGIN']"));
        inputUsername.sendKeys("helpdesk79@cydeo.com");

        WebElement inputPassword = driver.findElement(By.xpath("//input[@name = 'USER_PASSWORD']"));
        inputPassword.sendKeys("UserUser");


        WebElement loginButton = driver.findElement(By.xpath("//input[@value ='Log In']"));
        loginButton.click();


        WebElement moreDropdown = driver.findElement(By.id("feed-add-post-form-link-text"));
        moreDropdown.click();

        WebElement announcement = driver.findElement(By.xpath("//span[.='Announcement']"));
        announcement.click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']")));

        WebElement announcementMessage = driver.findElement(By.xpath("//body[@contenteditable='true']"));
        String message = "Hi CRM testers!";
        announcementMessage.sendKeys(message);

        driver.switchTo().defaultContent();

        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendButton.click();


        //  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // WebElement sendButton = driver.findElement(By.id("blog-submit-button-save"));
        // sendButton.click();

        WebElement feed = driver.findElement(By.xpath("//div[starts-with(@id,'blog_post_body')]"));
        Assert.assertEquals(feed.getText(), "Hello Alena", "Message did not appear!");
    }

        @Test
                public void emptyAnnouncement(){
        WebElement announcement = driver.findElement(By.xpath("//span[.='Announcement']"));
        announcement.click();

        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendButton.click();

            WebElement errorMessage = driver.findElement(By.xpath("//span[.='The message title is not specified']"));
            String expectedResult = "The message title is not specified";
            String actualResult = errorMessage.getText();

            Assert.assertEquals(actualResult,expectedResult, "Warning message did not appear");



    }
}



























