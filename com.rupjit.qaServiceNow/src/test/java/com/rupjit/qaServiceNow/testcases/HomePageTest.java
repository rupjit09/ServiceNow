package com.rupjit.qaServiceNow.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.qaServiceNow.base.TestBase;
import com.rupjit.qaServiceNow.pages.HomePage;
import com.rupjit.qaServiceNow.pages.HomePage.HomePageOptions;
import com.rupjit.qaServiceNow.pages.Incident_CreateNew;
import com.rupjit.qaServiceNow.pages.LoginPage;
import com.rupjit.qaServiceNow.pages.OrganisationUsers;
import com.rupjit.qaServiceNow.pages.SelfService_MyApproval;

public class HomePageTest extends TestBase{

	HomePage homepage;
	@BeforeMethod
	public void setup() throws Exception {
		homepage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	//@Test
	public void homePageTitleTest() {
		String title=homepage.getHomePageTitle();
		System.out.println("title="+title);
		Assert.assertEquals(title, "ServiceNow","Login page title not matching");
	}
	
	@Test
	public void navigateToOrganisation_USERSpage() throws InterruptedException {
		homepage.filterBar("user");
		homepage.clickAndMoveTo(HomePageOptions.ORGANISATION_USERS);
		String title=new OrganisationUsers().getOrganisationUsersPageTitle();
		Assert.assertEquals(title, "Users | ServiceNow","Page title not matching");
		}
	
	@Test
	public void navigateToIncident_CreateNewpage() throws InterruptedException {
		homepage.filterBar("Incident");
		homepage.clickAndMoveTo(HomePageOptions.CREATE_NEW_INCIDENT);
		String title=new Incident_CreateNew().getIncident_CreateNewPageTitle();
		Assert.assertTrue(title.contains("| Incident | ServiceNow"),"Page title not matching");
	}
	
	@Test
	public void navigateToSelfService_MyApprovalpage() throws InterruptedException {
		homepage.filterBar("Self-Service");
		homepage.clickAndMoveTo(HomePageOptions.SELFSERVICE_MYAPPROVAL);
		String title=new SelfService_MyApproval().getSelfService_MyApprovalPageTitle();
		Assert.assertEquals(title, "Approvals | ServiceNow","Page title not matching");
	}
}
