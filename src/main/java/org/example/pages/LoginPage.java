package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {

    By loginButton = By.id("login_Layer");
    By usenameField = new By.ByXPath("//div[@class=\"form-row\"]/input[@type='text']");
    By passwordField = new By.ByXPath("//div[@class=\"form-row\"]/input[@type='password']");
    By loginSubmit = By.cssSelector(".btn-primary.loginButton");
    WebDriver  wd;
    public  LoginPage(WebDriver wd){
        this.wd = wd;


    }
    public DashBoard login(String userName, String password){
        System.out.println(userName+" "+password);
        WebDriverWait wt = new WebDriverWait(this.wd , Duration.ofSeconds(2));
        WebElement lb = wt.until(ExpectedConditions.visibilityOfElementLocated(this.loginButton));

        assert lb != null;
        lb.click();


        WebElement usernameField = wt.until(ExpectedConditions.visibilityOfElementLocated(this.usenameField));
        usernameField.clear();
        usernameField.sendKeys(userName);

        WebElement pass = wt.until(ExpectedConditions.presenceOfElementLocated(this.passwordField));
        pass.clear();
        pass.sendKeys(password);

         WebElement loginsb = wt.until(ExpectedConditions.presenceOfElementLocated(this.loginSubmit));
         loginsb.click();


        return new DashBoard(this.wd);
    }


}
