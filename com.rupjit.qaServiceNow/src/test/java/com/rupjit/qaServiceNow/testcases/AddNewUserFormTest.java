package com.rupjit.qaServiceNow.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.qaServiceNow.base.TestBase;
import com.rupjit.qaServiceNow.pages.AddNewUserForm;
import com.rupjit.qaServiceNow.pages.LoginPage;
import com.rupjit.qaServiceNow.pages.OrganisationUsers;
import com.rupjit.qaServiceNow.pages.HomePage.HomePageOptions;

public class AddNewUserFormTest extends TestBase {
	AddNewUserForm addNewUserForm;

	@BeforeMethod 
	public void setup() throws Exception { 
		addNewUserForm=((OrganisationUsers) new LoginPage().login(prop.getProperty("username"),
				prop.getProperty("password")).clickAndMoveTo(HomePageOptions.ORGANISATION_USERS)).clickAndMoveTOAddNewUser();
		
	 }
	
	@Test
	public void createUser() {
		new OrganisationUsers().deleteUser("rupjit11");
		addNewUserForm.fillFormDetailsAndSubmit("rupjit12", "rupjit12", "das", "rup123");
		boolean userAvailable=new OrganisationUsers().isUserAvailable("rupjit12");
		Assert.assertEquals(true, userAvailable,"User rupjit09 not available");
	}

}
