package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US9_AccesCorrectModulle_sueda {

    WebDriver driver;


    @BeforeClass
    public void setupMethod() {

        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
    }

    @BeforeMethod
    public void login_to_page(){

        WebElement login = driver.findElement(By.xpath("//input[@type='text']"));

        login.sendKeys(ConfigurationReader.getProperty("username"));

        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));

        password.sendKeys(ConfigurationReader.getProperty("password")+ Keys.ENTER);

//        WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));
//        submit.click();
    }

    @Test
    public void StreamActivity(){
        List<String> expectedTittles = new ArrayList<>(Arrays.asList("Portal", "Site map", "Chat and Calls", "Workgroups and projects", "Site map", "Site map", "Contact Center", "Absence Chart", "Company Structure", "Meeting Rooms", "Company"));
        List<String> allTitles = new ArrayList<>();
        WebElement activityStream = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_live_feed']"));
        activityStream.click();
        allTitles.add(driver.getTitle());
        WebElement tasks = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_tasks']"));
        tasks.click();
        allTitles.add(driver.getTitle());
        WebElement chatAndCalls = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_im_messenger']"));
        chatAndCalls.click();
        allTitles.add(driver.getTitle());
        WebElement closeButton = driver.findElement(By.cssSelector(".bx-im-fullscreen-popup-back-link"));
        closeButton.click();
        WebElement workgroups = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_all_groups']"));
        workgroups.click();
        allTitles.add(driver.getTitle());
        WebElement drive = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_files']"));
        drive.click();
        allTitles.add(driver.getTitle());
        WebElement calendar = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_calendar']"));
        calendar.click();
        allTitles.add(driver.getTitle());
        WebElement contactCenter = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_contact_center']"));
        contactCenter.click();
        allTitles.add(driver.getTitle());
        WebElement timeAndReport = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_timeman_sect']"));
        timeAndReport.click();
        allTitles.add(driver.getTitle());
        WebElement employees = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_company']"));
        employees.click();
        allTitles.add(driver.getTitle());
        WebElement services = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_services_sect']"));
        services.click();
        allTitles.add(driver.getTitle());
        WebElement company = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_about_sect']"));
        company.click();
        allTitles.add(driver.getTitle());

        Assert.assertEquals(allTitles,expectedTittles,"Title didn't match");
    }
}



