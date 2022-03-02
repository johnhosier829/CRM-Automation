package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.Security;
import java.util.concurrent.TimeUnit;

public class US7_User_poll_and_answer_Aniqa {

    //1TC for login
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


        WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
        userName.sendKeys(ConfigurationReader.getProperty("username"));
        //     * 3-write password
        WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
        password.sendKeys(ConfigurationReader.getProperty("password"));
        //     * 4-click login button
        WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        BrowserUtils.sleep(3);
        loginBtn.click();
        // 5 verify title
        String expectedTitle = "Portal";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

/*
    @AfterMethod
    public void tearDown() {
        BrowserUtils.sleep(3);
        driver.close();
    }

 */



    //User_poll_and_answer

    @Test

    public void user_poll_and_answer() throws InterruptedException {
        //1. locate poll
        WebElement findPoll = driver.findElement(By.xpath("//div[@class=\"bx-vote-body\"]"));

        WebElement voteAgain=driver.findElement(By.xpath("//button[@data-bx-vote-button=\"showVoteForm\"]"));
        if (voteAgain.isDisplayed()){
            voteAgain.click();
        }


        //2. locate first answer choice
       WebElement answerChoice = driver.findElement(By.xpath("//span[@class=\"bx-vote-block-inp-substitute\"]"));

       // 3. click first answer choice
        Thread.sleep(3);
        answerChoice.click();


        //4. locate second answer choice
        WebElement answerChoice2 = driver.findElement(By.xpath("//table[@class=\"bx-vote-answer-list\"]//tr[2]"));


        //5. click second answer choice
        BrowserUtils.sleep(3);
        answerChoice2.click();

        //7. locate vote

        WebElement locateVote = driver.findElement(By.xpath("//form[@name=\"vote-form-nSLjLH47\"]//button[@class=\"ui-btn ui-btn-lg ui-btn-primary\"]"));
        Boolean localVoteDisplayed=locateVote.isDisplayed();
        System.out.println("localVoteDisplayed = " + localVoteDisplayed);

        //8. click on vote
       locateVote.click();


    }
}


