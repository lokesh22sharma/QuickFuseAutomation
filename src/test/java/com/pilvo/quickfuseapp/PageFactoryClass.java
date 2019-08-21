package com.pilvo.quickfuseapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by lokesh.sharma03 on 21/08/19.
 */
public class PageFactoryClass {

    WebDriver driver;
    QuickFuseApp quickFuseApp;

    @BeforeSuite
    public void initialized() {
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://quickfuseapps.com/");
            quickFuseApp = new QuickFuseApp(driver);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test(priority = 0, enabled = true)
    public void createNewApp() throws Exception {
        try {
            quickFuseApp.createNewApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void teardown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }


}
