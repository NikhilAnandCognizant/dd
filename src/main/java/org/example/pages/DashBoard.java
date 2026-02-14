package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashBoard {
    private By burgerButton = By.cssSelector(".nI-gNb-drawer");
    private WebDriver wd ;

    public DashBoard(WebDriver wd){
        this.wd = wd;


    }
    public SettingPage navigateToSetting(){
        WebDriverWait wait = new WebDriverWait(this.wd, Duration.ofSeconds(2));

        WebElement br = wait.until(ExpectedConditions.visibilityOfElementLocated(this.burgerButton));
        br.click();
        WebElement linkTOsetting =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nI-gNb-info__sub-link")));
        linkTOsetting.click();
        return new SettingPage(this.wd);


    }

}
