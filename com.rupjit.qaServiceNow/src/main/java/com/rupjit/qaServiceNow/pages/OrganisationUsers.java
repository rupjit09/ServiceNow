package com.rupjit.qaServiceNow.pages;

import org.openqa.selenium.support.PageFactory;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.TestBase;

public class OrganisationUsers extends TestBase{

	public OrganisationUsers() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public String getOrganisationUsersPageTitle() {
		DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		return DriverFactory.getInstance().getDriver().getTitle();
	}
}
