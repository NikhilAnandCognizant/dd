package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingPage {
    private By editButton = By.xpath("//div[@class=\"widgetHead\"]/span[@class=\"edit icon\"]");
    private By textar = By.cssSelector(".resumeHeadlineTxt.materialize-textarea");
    private WebDriver wb ;
    private By save = By.xpath("//div[@class=\"action s12\"]/button");
    public SettingPage(WebDriver wd){
        this.wb = wd;

    }

    public void editHeadLine(){
        WebDriverWait wt = new WebDriverWait(this.wb , Duration.ofSeconds(3));
        WebElement editel = wt.until(ExpectedConditions.visibilityOfElementLocated(this.editButton));
        editel.click();
        WebElement textArea = wt.until(ExpectedConditions.visibilityOfElementLocated(this.textar));


        textArea.sendKeys( "Next js");

        WebElement save = wt.until(ExpectedConditions.visibilityOfElementLocated(this.save));
        save.click();



    }

}
