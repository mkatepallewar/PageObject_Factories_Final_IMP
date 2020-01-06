package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoAppPage extends BasePage{
	
	@FindBy(xpath="//*[contains(text(),'Cliq')]")
	public WebElement cliq;
	
	@FindBy(xpath="//div[contains(text(),'CRM')]")
	public WebElement crm;
	
	@FindBy(xpath="//div[contains(text(),'Creator')]")
	public WebElement creator;
	
	@FindBy(xpath="//div[contains(text(),'Campaigns')]")
	public WebElement campaign;
	
	@FindBy(xpath="//div[contains(text(),'Meeting')]")
	public WebElement meeting;
	
	@FindBy(xpath="//div[contains(text(),'Mail')]")
	public WebElement mail;
	
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(cliq);
	}
	
	public ZohoCRMPage gotoCRM() {
		click(crm, "CRM Link");
		return (ZohoCRMPage) openPage(ZohoCRMPage.class);
	}
	
	public ZohoCliqPage gotoCliq() {
		click(cliq, "CliqLink");
		return (ZohoCliqPage) openPage(ZohoCliqPage.class);
	}
	
	public ZohoCreatorPage gotoCreator() {
		click(creator, "Creator Link");
		return (ZohoCreatorPage) openPage(ZohoCreatorPage.class);
	}
	
	public ZohoMailPage gotoMail() {
		click(mail, "Mail Link");
		return (ZohoMailPage) openPage(ZohoMailPage.class);
	}
	
	public ZohoCampaignPage gotoCampaigns() {
		click(campaign, "Campaign Link");
		return (ZohoCampaignPage) openPage(ZohoCampaignPage.class);
	}
	

}
