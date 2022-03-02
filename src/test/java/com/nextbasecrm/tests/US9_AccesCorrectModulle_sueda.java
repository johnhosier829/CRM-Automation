package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static com.nextbasecrm.utilities.ConfigurationReader.getProperty;

public class US9_AccesCorrectModulle_sueda {


    WebDriver driver;




    @BeforeMethod
    public void login_to_page(){

        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));


        // Login using crm_login utilities

        CRM_Utilities.crm_login(driver, getProperty("username"), getProperty("password"));


    }

    @Test
    public void StreamActivity(){

        //creat expectedTittles ArrayList
        List<String> expectedTittles = new ArrayList<>(Arrays.asList("Portal", "My tasks", "Chat and Calls", "Workgroups and projects",
                "Site map", "Site map", "Contact Center", "Absence Chart", "Company Structure", "Meeting Rooms", "Company"));

        //creat actualAllTittles ArrayList
        List<String> actualAllTitles = new ArrayList<>();

        // activityStream Locate
        WebElement activityStream = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_live_feed']"));
        activityStream.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //driver Locate
        WebElement tasks = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_tasks']"));
        tasks.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //chatAndCalls Locate
        WebElement chatAndCalls = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_im_messenger']"));
        chatAndCalls.click();

        ////add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //close the popup
        WebElement closeButton = driver.findElement(By.cssSelector(".bx-im-fullscreen-popup-back-link"));
        closeButton.click();

        //workgroups Locate
        WebElement workgroups = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_all_groups']"));
        workgroups.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //drive Locate
        WebElement drive = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_files']"));
        drive.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //calender Locate
        WebElement calendar = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_calendar']"));
        calendar.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //contactCenter Locate
        WebElement contactCenter = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_contact_center']"));
        contactCenter.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //timeandReport Locate
        WebElement timeAndReport = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_timeman_sect']"));
        timeAndReport.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //Employess Locate
        WebElement employees = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_company']"));
        employees.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //service Locate
        WebElement services = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_services_sect']"));
        services.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //company Locate
        WebElement company = driver.findElement(By.xpath("//li[@id='bx_left_menu_menu_about_sect']"));
        company.click();

        //add actualAllTitles List
        actualAllTitles.add(driver.getTitle());

        //check the Actual Tittle Equals the Expected Tittles
        Assert.assertEquals(actualAllTitles,expectedTittles,"Actual Tittle  didn't match the Expected Tittle.");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }





    }




