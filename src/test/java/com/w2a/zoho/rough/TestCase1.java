package com.w2a.zoho.rough;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.w2a.zoho.testcases.BaseTest;
import com.w2a.zoho.utilities.DriverManager;

public class TestCase1 extends BaseTest {
	
	@Test
	public void doLogin() {
		openBrowser("firefox");
		
		DriverManager.getDriver().findElement(By.cssSelector("body > div.main-container-wrapper > div.zh-header-wrap > div > a.zh-login")).click();
		DriverManager.getDriver().findElement(By.xpath("//*[@id=\"login_id\"]")).sendKeys("manishtest006@gmail.com");
		
		DriverManager.getDriver().findElement(By.xpath("//*[@id=\"nextbtn\"]")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		quit();
	}
}
