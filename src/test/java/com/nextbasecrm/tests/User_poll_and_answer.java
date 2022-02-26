package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
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

public class User_poll_and_answer {

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
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.sleep(3);
        driver.close();
    }

    //positive scenario
    @BeforeMethod
    public void login_with_valid_credentials_with_login_btn() {
        // 2-write username

    @Test
    public void login_with_valid_credentials_with_enter_btn() {
        // 2-write username
        WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
        userName.sendKeys(ConfigurationReader.getProperty("username"));
        //     * 3-write password
        WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
        password.sendKeys(ConfigurationReader.getProperty("password") + Keys.ENTER);
        //     * 4-click login button
        // 5 verify title
        String expectedTitle = "Portal";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

 */

    //User_poll_and_answer

    @Test
            public void user_poll_and_answer() throws InterruptedException {
        //1. locate poll
        WebElement findPoll = driver.findElement(By.xpath("//div[@class=\"bx-vote-body\"]"));

        //2. locate first answer choice
        //WebElement answerChoice = driver.findElement(By.xpath("//table[@class=\"bx-vote-answer-list\"]//tr[1]"));

//        if(answerChoice.isDisplayed()){
//            System.out.println("is displayed");
//        }else{
//            WebElement voteAgain=driver.findElement(By.xpath("//button[@data-bx-vote-button=\"showVoteForm\"]"));
//            voteAgain.click();
//        }

        //2. locate first answer choice
        WebElement answerChoice = driver.findElement(By.xpath("//table[@class=\"bx-vote-answer-list\"]//tr[1]"));

        //3. click first answer choice
        Thread.sleep(3);
        answerChoice.click();

        //4. locate second answer choice
        WebElement answerChoice2 = driver.findElement(By.xpath("//table[@class=\"bx-vote-answer-list\"]//tr[2]"));

        //5. click second answer choice
        Thread.sleep(3);
        answerChoice2.click();
        System.out.println(answerChoice2.isSelected());
        //6. verify second answer choice is selected after clicking.
        if (answerChoice2.isSelected()) {
            System.out.println("Second Button is selected. Verification PASSED!");
        } else {
            System.out.println("Second Button is not selected. Verification Failed!");

    }
        //7. locate vote

        WebElement locateVote = driver.findElement(By.xpath(" button[@class=\"ui-btn ui-btn-lg ui-btn-primary\"]"));

        //8. click on vote
       //locateVote.click();

    }

/*
        //negative scenario
        // valid username invalid password
        @Test
        public void login_with_valid_username_invalid_password(){
            // 2-write valid username
            WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
            userName.sendKeys(ConfigurationReader.getProperty("username"));
            //  3-write invalid password
            WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
            password.sendKeys("abcd");
            //  4-click login button
            WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
            BrowserUtils.sleep(3);
            loginBtn.click();
            //  5 verify error message
            String expectedErrorMessage="Incorrect login or password";
            String actualErrorMessage=driver.findElement(By.xpath("//div[@class='errortext']")).getText();
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);


 */
        }


