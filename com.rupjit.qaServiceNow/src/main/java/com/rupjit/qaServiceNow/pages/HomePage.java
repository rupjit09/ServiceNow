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
		new TestBase().getNgWebDriverInstance(DriverFactory.getInstance().getDriver()).waitForAngularRequestsToFinish();
		filterBar.sendKeys(filterString);
	}
	
	public Object clickAndMoveTo(HomePageOptions option) {
		switch (option.toString().toUpperCase()) {
		case "ORGANISATION_USERS":
			new HomePage().filterBar("Organization");
			new TestBase().waitForVisibilityOfElement(Organisation_Users,DriverFactory.getInstance().getDriver());
			Organisation_Users.click();
			return new OrganisationUsers();
		
		case "CREATE_NEW_INCIDENT":
			new HomePage().filterBar("Incident");
			new TestBase().waitForVisibilityOfElement(Incident_CreateNew,DriverFactory.getInstance().getDriver());
			Incident_CreateNew.click();
			return new Incident_CreateNew();
		case "SELFSERVICE_MYAPPROVAL":
			new HomePage().filterBar("Self-Service");
			new TestBase().waitForVisibilityOfElement(SelfService_MyApproval,DriverFactory.getInstance().getDriver());
			SelfService_MyApproval.click();
			return new SelfService_MyApproval();
		default :
			MyLogger.debug("Incorrect HomePageOptions passed");
			return null;
		}
		
	}

}
