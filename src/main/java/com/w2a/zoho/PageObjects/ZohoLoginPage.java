package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoLoginPage extends BasePage{
	
	public ZohoLoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[@id=\"login_id\"]")
	public WebElement email;
	
	@FindBy(xpath="//*[@id=\"nextbtn\"]")
	public WebElement next;
	
	@FindBy(xpath="//*[@id=\"password\"]")
	public WebElement password;
	
	@FindBy(xpath="//*[@id=\"nextbtn\"]")
	public WebElement signIn;
	
	
	public ZohoLoginPage doLoginInvalidUser(String username, String password) {
		type(email, username, "User Name");
		click(next, "Clicking Next");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		type(this.password, password, "Password");
		click(signIn, "Sign In");
		
		return this;
	}
	
	public ZohoAppPage doLoginValidUser(String username, String password) {
		type(email, username, "User Name");
		click(next, "Clicking Next");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		type(this.password, password, "Password");
		click(signIn, "Sign In");
		
		return (ZohoAppPage) openPage(ZohoAppPage.class);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(email);
	}
}
