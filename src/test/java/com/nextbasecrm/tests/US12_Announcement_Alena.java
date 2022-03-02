package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US12_Announcement_Alena {

     WebDriver driver;

    @BeforeMethod
    public void setupMethod(){
        // Go to https://login2.nextbasecrm.com/
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com/");
    }
        @Test
        public void crm_login() {

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

            WebElement sendMessage = driver.findElement(By.xpath("//body[starts-with( 'true')]"));
            sendMessage.sendKeys("Hello Alena");







        }




            //Send message in announcement tab
            public void iframeMessage(String hello_from_alena) {
                WebElement sendMessage = driver.findElement(By.id("bx-html-editor-iframe-cnt-idPostFormLHE_blogPostForm"));
                sendMessage.sendKeys("Hello Alena");


                WebElement sendButton = driver.findElement(By.id("blog-submit-button-save"));

                sendMessage.sendKeys("");
                sendButton.click();
                WebElement errorMessage = driver.findElement(By.xpath("//span[.='The message title is not specified']"));
                String expectedResult = "The message title is not specified";
                String actualResult = errorMessage.getText();

                if (actualResult.equals(expectedResult)){
                    System.out.println("Test is passed");
                }else {
                    System.out.println("Test is failed");
                }


            }


        }

