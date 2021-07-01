package com.rupjit.qaServiceNow.pages;

import org.openqa.selenium.support.PageFactory;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.TestBase;

public class SelfService_MyApproval extends TestBase{
	
	public SelfService_MyApproval() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}

	public String getSelfService_MyApprovalPageTitle() {
		DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		return DriverFactory.getInstance().getDriver().getTitle();
	}
}
