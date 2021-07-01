package com.rupjit.qaServiceNow.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.TestBase;
import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularModel;

public class HomePage extends TestBase{
	
	@ByAngularModel.FindBy(rootSelector = "butter" , model="filterTextValue")
	WebElement filterBar;
	
	public HomePage() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public void filterBar(String filter) {
		filterBar.sendKeys(filter);
	}

}
