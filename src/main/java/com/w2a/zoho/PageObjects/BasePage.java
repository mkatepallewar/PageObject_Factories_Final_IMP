package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.w2a.zoho.extentListners.ExtentListeners;
import com.w2a.zoho.utilities.DriverManager;

public abstract class BasePage<T> {
	protected WebDriver driver;
	
	public BasePage() {
		this.driver=DriverManager.getDriver();
	}
	public T openPage(Class<T> clazz) {
		/*
		 * Initializinf page factory elements
		 * Defining page load conditions
		 * 
		 */
		
		T page=null;
		driver= DriverManager.getDriver();
		
		AjaxElementLocatorFactory ajaxElementFactory=new AjaxElementLocatorFactory(driver, 10);
		
		page=PageFactory.initElements(driver, clazz);
		PageFactory.initElements(ajaxElementFactory,page);
		
		ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
		waitForPagetoLoad(pageLoadCondition);

		
		return page;
	}
	private void waitForPagetoLoad(ExpectedCondition pageLoadCondition) {
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(pageLoadCondition);
		
	}
	protected abstract ExpectedCondition getPageLoadCondition();
	
	
	public void click(WebElement element, String elementName) {
		element.click();
		ExtentListeners.testReport.get().info("Clicking on : "+elementName);
	}
	
	public void type(WebElement element, String value, String elementName) {
		element.sendKeys(value);
		ExtentListeners.testReport.get().info("Entering value :" +value+ " in : "+elementName);
	}
}
