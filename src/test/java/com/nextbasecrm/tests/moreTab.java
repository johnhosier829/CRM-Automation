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

public class moreTab {
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

    @Test
    public void more_tab_options() {

        //1.locate MORE tab
        WebElement findMoreTab = driver.findElement(By.xpath("//span[@id=\"feed-add-post-form-link-text\"]"));

         //2. click MORE tab
        findMoreTab.click();
        BrowserUtils.sleep(1);

        //3. locate "file" option

        WebElement fileTab=driver.findElement(By.xpath("//*[text()='File']"));
        //4. check if "file" is displayed
        System.out.println("fileTab.isDisplayed() = " + fileTab.isDisplayed());

        //5. locate "Appreciation" option
        WebElement appreciationTab=driver.findElement(By.xpath("//*[text()='Appreciation']"));

        //6. check if "Appreciation" is displayed
        System.out.println("appreciationTab.isDisplayed() = " + appreciationTab.isDisplayed());

        //7. locate "Announcement" option
        WebElement announcementTab= driver.findElement(By.xpath("//*[text()='Announcement']"));

        //4. check if "file" is displayed
        System.out.println("announcementTab.isDisplayed() = " + announcementTab.isDisplayed());

        //8. locate "Workflow" option
        WebElement workflowTab= driver.findElement(By.xpath("//*[text()='Workflow']"));

        //4. check if "Workflow" is displayed
        System.out.println("workflowTab.isDisplayed() = " + workflowTab.isDisplayed());


    }
}
