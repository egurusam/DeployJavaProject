package com.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class GridExampleTest {
	
	public RemoteWebDriver driver;
	public static String appURL = "http://www.google.com";
	
	@BeforeClass
	public void setUp() throws MalformedURLException {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testGooglePageTitleInIEBrowser() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		System.out.println("*** Navigation to Application ***");
		driver.navigate().to(appURL);
		String strPageTitle = driver.getTitle();
		System.out.println("*** Verifying page title ***");
		Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"));
		System.out.println("***Click Gmail Link ****");
		driver.findElement(By.linkText("Gmail")).click();
		Thread.sleep(8000);
		//driver.findElement(By.linkText("Create account")).click();
		List<WebElement> allLInk=driver.findElements(By.tagName("a"));
		for (WebElement link : allLInk) {
			System.out.println("Link Value " +link.getText());
		if(link.getText().equals("Create account"))
		{
			System.out.println("***Inside Create Account***");
			link.click();
			Thread.sleep(5000);
			break;
		}
		}
	
		Set<String> winHandles=driver.getWindowHandles();
		for (String window : winHandles) {
			
			System.out.println(driver.getTitle());
			
			if(driver.getTitle().equals("Create your Google Account"))
			{
				System.out.println("Create Account page is Opened");
				driver.findElement(By.name("FirstName")).sendKeys("FirefoxDriver");
				driver.findElement(By.name("LastName")).sendKeys("WebDriver");
				driver.findElement(By.name("GmailAddress")).clear();
				driver.findElement(By.name("GmailAddress")).sendKeys("FirefoxDriver.WebDriver");
				driver.findElement(By.name("Passwd")).sendKeys("FirefoxDriver.WebDriver@gmail.com");
				WebElement PasswrodAgin=driver.findElement(By.name("PasswdAgain"));
				PasswrodAgin.sendKeys("FirefoxDriver.WebDriver@gmail.com");
				driver.findElement(By.xpath("//div[@role='listbox']")).sendKeys("March");
				driver.findElement(By.name("BirthDay")).sendKeys("03");
		 	    driver.findElement(By.name("BirthYear")).sendKeys("1985");
		 		driver.findElement(By.xpath("//div[@aria-activedescendant=':d']")).sendKeys("Male");
		        driver.findElement(By.name("RecoveryPhoneNumber")).sendKeys("9994278924");
		        driver.findElement(By.name("RecoveryEmailAddress")).sendKeys("ga.ezhil@gmail.com");
		 		 Thread.sleep(8000);
				
			}
		}
	}
	
	@AfterClass
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}