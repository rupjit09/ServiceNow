package com.rupjit.qaServiceNow.testcases;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.qaServiceNow.base.MyLogger;
import com.rupjit.qaServiceNow.base.TestBase;
import com.rupjit.qaServiceNow.pages.LoginPage;



public class LoginPageTest extends TestBase{

	LoginPage loginpage;
	
	@BeforeMethod
	public void setup() {
		loginpage=new LoginPage(); 
	}
	
	@Test
	public void loginPageTitleTest() {
		String title=loginpage.validateLoginPageTitle();
		MyLogger.debug("executing first testcase");
		Assert.assertEquals(title, "ServiceNow","Login page title not matching");
	}
	
	@Test
	public void loginTest() throws Exception {
		MyLogger.debug("executing second testcase");
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void loginFailTest() throws Exception {
		MyLogger.debug("executing third testcase");
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals("A", "B");
	}
}
