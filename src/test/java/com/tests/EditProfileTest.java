package com.tests;

import org.apache.commons.io.FileUtils;
import org.example.pages.DashBoard;
import org.example.pages.LoginPage;
import org.example.pages.SettingPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CredsUtil;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class EditProfileTest extends BaseTest {

    @Test(dataProvider = "creds")
    public void runTest(String userName, String Pass){
        driver.get("https://www.naukri.com/");
        LoginPage lp = new LoginPage(driver);
        DashBoard db = lp.login(userName,Pass);
        String expectedUrl = "https://www.naukri.com/mnjuser/homepage";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("homepage"));

        String actualUrl = driver.getCurrentUrl();

        // Verification
        Assert.assertEquals(actualUrl, expectedUrl, "The user was not redirected to the correct page!");

        SettingPage sp= db.navigateToSetting();
        sp.editHeadLine();
        TakesScreenshot aa=(TakesScreenshot) driver;
        File source = aa.getScreenshotAs(OutputType.FILE);

        File dest = new File("");
        FileUtils.copyFile(source,dest);
    }

    @DataProvider(name = "creds")
    public Object [][] dataPr() throws IOException {
        return CredsUtil.getxl();
    }



}
