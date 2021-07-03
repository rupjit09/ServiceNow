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
		new OrganisationUsers().deleteUser("rupjit10");
		organisationUsersPage.clickAndMoveTOAddNewUser().fillFormDetailsAndSubmit("rupjit10", "rupjit10", "das", "rup123");
		boolean userAvailable=new OrganisationUsers().isUserAvailable("rupjit10");
		Assert.assertEquals(true, userAvailable,"User rupjit10 not available");
	}
}
