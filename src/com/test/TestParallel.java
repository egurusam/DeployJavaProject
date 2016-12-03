package com.test;
 
import java.net.MalformedURLException;
import java.net.URL;
 
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
public class TestParallel extends BaseClass {
 
    @Test
    public void test_01() throws InterruptedException, MalformedURLException{
        try{
            getDriver().get("http://www.w3schools.com/");
 
            getDriver().findElement(By.xpath("html/body/div[2]/div/a[4]")).click();
 
            //Wait intentially added to show parallelism execution
            Thread.sleep(10000);
 
            getDriver().findElement(By.xpath("//*[@id='gsc-i-id1']")).sendKeys("test");
            Thread.sleep(5000);
 
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}