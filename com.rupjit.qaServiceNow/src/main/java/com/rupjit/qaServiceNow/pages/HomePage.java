package com.rupjit.qaServiceNow.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.TestBase;
import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularModel;

public class HomePage extends TestBase{
	public enum HomePageOptions{
		ORGANISATION_USERS,SELFSERVICE_MYAPPROVAL,CREATE_NEW_INCIDENT
	}
	
	@ByAngularModel.FindBy(rootSelector="body",model="filterTextValue")
	WebElement filterBar;
	
	@FindBy(xpath="//ul[@aria-label='Modules for Application: Organization']//div[text()='Users']")
	WebElement Organisation_Users;
	
	@FindBy(xpath="//ul[@aria-label='Modules for Application: Incident']//div[text()='Create New']")
	WebElement Incident_CreateNew;
	
	@FindBy(xpath="//ul[@aria-label='Modules for Application: Self-Service']//div[text()='My Approvals']")
	WebElement SelfService_MyApproval;
	
	public HomePage() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public String getHomePageTitle() {
		return DriverFactory.getInstance().getDriver().getTitle();
	}
	
	public void filterBar(String filterString) {
		getNgWebDriverInstance().waitForAngularRequestsToFinish();
		filterBar.sendKeys(filterString);
	}
	
	public Object clickAndMoveTo(HomePageOptions option) {
		switch (option.toString().toUpperCase()) {
		case "ORGANISATION_USERS":
			waitForVisibilityOfElement(Organisation_Users);
			Organisation_Users.click();
			return new OrganisationUsers();
		
		case "CREATE_NEW_INCIDENT":
			waitForVisibilityOfElement(Incident_CreateNew);
			Incident_CreateNew.click();
			return new Incident_CreateNew();
		case "SELFSERVICE_MYAPPROVAL":
			waitForVisibilityOfElement(SelfService_MyApproval);
			SelfService_MyApproval.click();
			return new SelfService_MyApproval();
		default :
			MyLogger.debug("Incorrect HomePageOptions passed");
			return null;
		}
		
	}

}
