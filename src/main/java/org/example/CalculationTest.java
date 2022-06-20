package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculationTest {
    WebDriver driver;

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
    //проверка перевода фунтов в кг
    public void kilogramsToPounds() {
        driver.findElement(By.name("wg")).sendKeys("100");
        Select drpWeight = new Select(driver.findElement(By.name("opt1")));
        drpWeight.selectByVisibleText("pounds");
        String weight = driver.findElement(By.name("wg")).getAttribute("value");
        Assert.assertEquals(weight, "220");
    }

    @Test
    //проверка перевода футов в см
    public void ftToSm() {
        Select drpFt = new Select(driver.findElement(By.name("opt2")));
        drpFt.selectByValue("5");
        String height = driver.findElement(By.name("ht")).getAttribute("value");
        Assert.assertEquals(height, "152");
    }

    @Test
    //проверка перевода футов и дюймов в см
    public void ftAndInchToSm() {
        Select drpFt = new Select(driver.findElement(By.name("opt2")));
        drpFt.selectByValue("3");
        Select drpInch = new Select(driver.findElement(By.name("opt3")));
        drpInch.selectByValue("10");
        String height = driver.findElement(By.name("ht")).getAttribute("value");
        Assert.assertEquals(height, "117");
    }

    @Test
    //проверка расчета SI Units:
    public void siCalculation() {
        driver.findElement(By.name("wg")).sendKeys("80");
        Select drpFt = new Select(driver.findElement(By.name("opt2")));
        drpFt.selectByValue("3");
        Select drpInch = new Select(driver.findElement(By.name("opt3")));
        drpInch.selectByValue("10");
        driver.findElement(By.cssSelector("input[value='Calculate']")).click();
        String height = driver.findElement(By.name("si")).getAttribute("value");
        Assert.assertEquals(height, "58.44");
    }

    @Test
    //проверка расчета US Units:
    public void usCalculation() {
        driver.findElement(By.name("wg")).sendKeys("80");
        Select drpFt = new Select(driver.findElement(By.name("opt2")));
        drpFt.selectByValue("3");
        Select drpInch = new Select(driver.findElement(By.name("opt3")));
        drpInch.selectByValue("10");
        driver.findElement(By.cssSelector("input[value='Calculate']")).click();
        String height = driver.findElement(By.name("us")).getAttribute("value");
        Assert.assertEquals(height, "59.42");
    }

    @Test
    //проверка расчета UK Units:
    public void ukCalculation() {
        driver.findElement(By.name("wg")).sendKeys("80");
        Select drpFt = new Select(driver.findElement(By.name("opt2")));
        drpFt.selectByValue("3");
        Select drpInch = new Select(driver.findElement(By.name("opt3")));
        drpInch.selectByValue("10");
        driver.findElement(By.cssSelector("input[value='Calculate']")).click();
        String height = driver.findElement(By.name("uk")).getAttribute("value");
        Assert.assertEquals(height, "371.09");
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}