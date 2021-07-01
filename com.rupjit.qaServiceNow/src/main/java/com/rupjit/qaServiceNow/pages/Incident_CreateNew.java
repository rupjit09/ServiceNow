package com.rupjit.qaServiceNow.pages;

import org.openqa.selenium.support.PageFactory;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.TestBase;

public class Incident_CreateNew extends TestBase{

	public Incident_CreateNew() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public String getIncident_CreateNewPageTitle() {
		DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		return DriverFactory.getInstance().getDriver().getTitle();
	}
	
}
