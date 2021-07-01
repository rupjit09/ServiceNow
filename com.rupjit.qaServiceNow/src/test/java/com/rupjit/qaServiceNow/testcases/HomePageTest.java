package com.rupjit.qaServiceNow.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.qaServiceNow.base.TestBase;
import com.rupjit.qaServiceNow.pages.HomePage;
import com.rupjit.qaServiceNow.pages.LoginPage;

public class HomePageTest extends TestBase{

	HomePage homepage;
	@BeforeMethod
	public void setup() throws Exception {
		homepage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void filterBarTest() {
		homepage.filterBar("user");
	}
}
