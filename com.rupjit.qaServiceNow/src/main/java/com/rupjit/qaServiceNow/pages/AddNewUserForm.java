package com.rupjit.qaServiceNow.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.TestBase;

public class AddNewUserForm extends TestBase{
	@FindBy(xpath="//input[@id='sys_user.user_name']")
	WebElement user_Id_textInputBox;
	
	@FindBy(xpath="//input[@id='sys_user.first_name']")
	WebElement firstName_textInputBox;
	
	@FindBy(xpath="//input[@id='sys_user.last_name']")
	WebElement lastName_textInputBox;
	
	@FindBy(xpath="//input[@id='sys_user.user_password']")
	WebElement password_textInputBox;
	
	@FindBy(xpath="//input[@id='sys_user.email']")
	WebElement email_textInputBox;
	
	@FindBy(xpath="//button[@id='sysverb_insert_bottom']")
	WebElement submit_Button;
	
	public AddNewUserForm() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public String getAddNewUserFormTitle() {
		return DriverFactory.getInstance().getDriver().getTitle();
	}
	
	public void fillFormDetailsAndSubmit(String userName,String firstname,String lastName,String password) {
		DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		user_Id_textInputBox.sendKeys(userName);
		firstName_textInputBox.sendKeys(firstname);
		lastName_textInputBox.sendKeys(lastName);
		password_textInputBox.sendKeys(password);
		submit_Button.click();
	}

}
