package com.rupjit.qaServiceNow.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.TestBase;

public class OrganisationUsers extends TestBase{
	
	@FindBy(xpath="//button[text()='New']")
	WebElement addnewUser;
	
	@FindBy(xpath="//input[@aria-label='Search column: user id'][1]")
	WebElement userIdSearchBox;
	
	//@FindBy(xpath="//tbody[@class='list2_body']/descendant::a[text()='rupjit09']")

	public OrganisationUsers() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public String getOrganisationUsersPageTitle() {
		DriverFactory.getInstance().getDriver().switchTo().defaultContent();
		DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		return DriverFactory.getInstance().getDriver().getTitle();
	}
	
	public AddNewUserForm clickAndMoveTOAddNewUser() {
		DriverFactory.getInstance().getDriver().switchTo().defaultContent();
		DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		System.out.println("Clicking on Add NEW user button");
		waitForElementToBeClickable(addnewUser, DriverFactory.getInstance().getDriver());
		addnewUser.click();
		return new AddNewUserForm();
	}
	
	public boolean isUserAvailable(String userId) {
		DriverFactory.getInstance().getDriver().switchTo().defaultContent();
		DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		String isSearchBarHidden=DriverFactory.getInstance().getDriver().findElement(By.xpath("//th[@name='search']//button")).getAttribute("aria-expanded");
		if(isSearchBarHidden.equalsIgnoreCase("false"))
			DriverFactory.getInstance().getDriver().findElement(By.xpath("//th[@name='search']//button")).click();
		userIdSearchBox.clear();
		userIdSearchBox.sendKeys(userId,Keys.ENTER);
		DriverFactory.getInstance().getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		boolean userAvailable = false;
		try {
		userAvailable=DriverFactory.getInstance().getDriver().findElement(By.xpath("//tbody[@class='list2_body']/descendant::a[text()='"+userId+"']")).isDisplayed();
		}catch (NoSuchElementException e) {
			System.out.println("User Not Found");
		}
		return userAvailable;
		
	}
	
	public void deleteUser(String userId) {
		//System.out.println("User Availability "+new OrganisationUsers().isUserAvailable(userId));
		if(new OrganisationUsers().isUserAvailable(userId)) {
			System.out.println(userId+" is available so deleting");
			DriverFactory.getInstance().getDriver().switchTo().defaultContent();
			DriverFactory.getInstance().getDriver().switchTo().frame("gsft_main");
		WebElement userRowCheckbox=DriverFactory.getInstance().getDriver().findElement(By.xpath("//tbody[@class='list2_body']/descendant::a[text()='"+userId+"']/parent::td/preceding-sibling::td[@class='list_decoration_cell col-control col-small col-center ']/descendant::label"));
		userRowCheckbox.click();
		Select select=new Select(DriverFactory.getInstance().getDriver().findElement(By.xpath("//select[contains(@id,'labelAction')]")));
		select.selectByVisibleText("Delete");
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//button[@id='ok_button']")).click();
		} else
			System.out.println(userId+" not available");
	}
	
	
}
