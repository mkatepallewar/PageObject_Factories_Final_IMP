package com.w2a.zoho.rough;

import org.testng.annotations.Test;

import com.w2a.zoho.PageObjects.ZohoHomePage;
import com.w2a.zoho.PageObjects.ZohoLoginPage;
import com.w2a.zoho.testcases.BaseTest;
import com.w2a.zoho.utilities.DataProviders;
import com.w2a.zoho.utilities.DriverManager;

public class TestCase2 extends BaseTest{
	
	
	@Test 
	public void dologin() {
		
		openBrowser("firefox");
		System.out.println(DriverManager.getDriver());
		ZohoHomePage home=new ZohoHomePage();
		ZohoLoginPage login= home.gotoLogin();
		login.doLoginInvalidUser("manishtest006@gmail.com", "Password_1");
		
		quit();
	}
}
