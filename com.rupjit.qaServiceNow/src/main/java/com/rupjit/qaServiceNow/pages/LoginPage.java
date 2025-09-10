package com.rupjit.qaServiceNow.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.MyLogger;
import com.rupjit.qaServiceNow.base.TestBase;


public class LoginPage extends TestBase{

	@FindBy(xpath="//input[@id='user_name']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='user_password']")
	WebElement password;
	
	@FindBy(xpath="//button[text()='Log in']")
	WebElement loginButton;
	
	public LoginPage() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public String getLoginPageTitle() {
		return DriverFactory.getInstance().getDriver().getTitle();
	}
	
	public HomePage login(String un,String pwd) throws Exception{
		DriverFactory.getInstance().getDriver();//.switchTo().frame("gsft_main");
		MyLogger.debug("login from pageclass method");
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginButton=DriverFactory.getInstance().getDriver().findElement(By.xpath("//button[text()='Log in']"));
		new TestBase().waitForVisibilityOfElement(loginButton,DriverFactory.getInstance().getDriver());
		loginButton.click();
		DriverFactory.getInstance().getDriver().switchTo().defaultContent();
		return new HomePage();
	}
	
}
