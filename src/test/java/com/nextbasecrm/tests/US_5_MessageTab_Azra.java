package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US_5_MessageTab_Azra {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // go to env
        driver.get(ConfigurationReader.getProperty("env"));

        //write username
        WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
        userName.sendKeys(ConfigurationReader.getProperty("username"));

        //write password
        WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
        password.sendKeys(ConfigurationReader.getProperty("password"));

        //click login button
        WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        BrowserUtils.sleep(3);
        loginBtn.click();
    }

    @Test
    public void click_message_tab_enterText_send(){

        //find message tab element and click on it
        WebElement messageTab= driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-tab-message\"]/span"));
        messageTab.click();
        BrowserUtils.sleep(3);

        //find message text box, switch frame as text box is iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"bx-html-editor-iframe-cnt-idPostFormLHE_blogPostForm\"]/iframe")));
        BrowserUtils.sleep(3);

        //write the text message
        WebElement textBox= driver.findElement(By.xpath("/html/body"));
        String textMessage="Hello Group27 :)";
        textBox.sendKeys(textMessage);
        BrowserUtils.sleep(3);

        //switch back to main(parent), find send button and click
        driver.switchTo().parentFrame();
        WebElement sendButton= driver.findElement(By.xpath("//*[@id=\"blog-submit-button-save\"]"));
        sendButton.click();
        BrowserUtils.sleep(3);

        // verify the text message in Activity Stream
        WebElement verifyTextMessage= driver.findElement(By.xpath(""));


    }

    @Test
    public void click_message_tab_noText_send() {

        //find message tab element and click on it
        WebElement messageTab = driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-tab-message\"]/span"));
        messageTab.click();
        BrowserUtils.sleep(3);

        //find message text box- its iframe so I have to switch to it
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"bx-html-editor-iframe-cnt-idPostFormLHE_blogPostForm\"]/iframe")));
        BrowserUtils.sleep(3);

        //write the text message
        //WebElement textBox= driver.findElement(By.xpath("/html/body"));
        //String textMessage="Hello B25 ";
        //textBox.sendKeys(textMessage);
        //BrowserUtils.sleep(3);

        //switch back to main frame, then find "send" button and click
        driver.switchTo().parentFrame();
        WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"blog-submit-button-save\"]"));
        sendButton.click();
        BrowserUtils.sleep(3);

        //find the displayed warning message
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-notice-blockblogPostForm\"]/div/span[2]"));
        String expectedWarningMessage = "The message title is not specified";
        String actualWarningMessage = errorMessage.getText();
        System.out.println("[+] ActualMessage: " + actualWarningMessage);
        Assert.assertEquals(expectedWarningMessage, actualWarningMessage);
        BrowserUtils.sleep(3);

    }





    @AfterMethod
    public void tearDown() {
        BrowserUtils.sleep(3);
        driver.close();
    }

}
