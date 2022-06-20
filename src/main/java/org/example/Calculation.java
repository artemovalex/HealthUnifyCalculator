package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

public class Calculation {
    WebDriver driver;
    String weight;

    @BeforeClass
    public void setPathToWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator");
    }

    @Test
    public void kilogramsToPounds() {
        driver.findElement(By.name("wg")).sendKeys("100");
        Select drpWeight = new Select(driver.findElement(By.name("opt1")));
        drpWeight.selectByVisibleText("pounds");
        weight = driver.findElement(By.name("wg")).getText();
        Assert.assertEquals(weight, "220");

    }
}