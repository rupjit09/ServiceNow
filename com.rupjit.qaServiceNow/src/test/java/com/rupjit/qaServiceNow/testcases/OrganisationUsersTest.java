package com.rupjit.qaServiceNow.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.qaServiceNow.base.TestBase;
import com.rupjit.qaServiceNow.pages.LoginPage;
import com.rupjit.qaServiceNow.pages.OrganisationUsers;
import com.rupjit.qaServiceNow.pages.HomePage.HomePageOptions;

public class OrganisationUsersTest extends TestBase{
	OrganisationUsers organisationUsersPage;
	
	@BeforeMethod
	public void setup() throws Exception {
		organisationUsersPage=(OrganisationUsers) new LoginPage().login(prop.getProperty("username"),
				prop.getProperty("password")).clickAndMoveTo(HomePageOptions.ORGANISATION_USERS);
	}
	
	@Test
	public void createUser() {
		new OrganisationUsers().deleteUser("rupjit11");
		organisationUsersPage.clickAndMoveTOAddNewUser().fillFormDetailsAndSubmit("rupjit11", "rupjit11", "das", "rup123");
		boolean userAvailable=new OrganisationUsers().isUserAvailable("rupjit11");
		Assert.assertEquals(true, userAvailable,"User rupjit11 not available");
	}
	
	@Test
	public void deleteUser() {
		new OrganisationUsers().deleteUser("rupjit12");
		organisationUsersPage.clickAndMoveTOAddNewUser().fillFormDetailsAndSubmit("rupjit12", "rupjit12", "das", "rup123");
		boolean userAvailable=new OrganisationUsers().isUserAvailable("rupjit12");
		Assert.assertEquals(userAvailable,true,"User rupjit12 not available");
		new OrganisationUsers().deleteUser("rupjit12");
		userAvailable=new OrganisationUsers().isUserAvailable("rupjit12");
		Assert.assertEquals(userAvailable,false,"Delete User failed, User rupjit12 still available");
		
	}
}
