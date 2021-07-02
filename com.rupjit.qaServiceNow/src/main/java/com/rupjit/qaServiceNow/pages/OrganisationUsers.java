package com.rupjit.qaServiceNow.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.TestBase;

public class OrganisationUsers extends TestBase{
	
	@FindBy(xpath="//button[text()='New']")
	WebElement addnewUser;

	public OrganisationUsers() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public String getOrganisationUsersPageTitle() {
		DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		return DriverFactory.getInstance().getDriver().getTitle();
	}
	
	public AddNewUserForm clickAndMoveTOAddNewUser() {
		DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		addnewUser.click();
		return new AddNewUserForm();
	}
	
	
}
