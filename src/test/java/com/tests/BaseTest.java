package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

        protected WebDriver driver;

        @BeforeTest
        public void prepareModule() {

            System.out.println("Module Setup: Config files loaded.");
        }

        @BeforeMethod
        public void setupBrowser() {
            // Logic: Open browser for every test case
            driver = new ChromeDriver();

        }

        @AfterMethod
        public void closeBrowser() {
            if (driver != null) {
//                driver.quit();
            }
        }

        @AfterTest
        public void cleanupModule() {
            // Logic: Disconnect from DB or clear test data
            System.out.println("Module Cleanup: Connections closed.");
        }

}
