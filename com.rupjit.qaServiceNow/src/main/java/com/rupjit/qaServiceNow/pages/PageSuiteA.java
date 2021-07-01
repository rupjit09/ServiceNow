package com.rupjit.qaServiceNow.pages;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.rupjit.qaServiceNow.base.MyLogger;
import com.rupjit.qaServiceNow.base.TestBase;
import com.rupjit.qaServiceNow.utils.TestUtil;


public class PageSuiteA extends TestBase{

	@BeforeSuite
	//Runmode of 
	public void checkSuiteSkip() throws IOException, InterruptedException {
		initialize();
		MyLogger.debug("Checking the runmode of SuiteA");
		if(!TestUtil.isSuiteRunnable(suitexls, "A Suite")) {
			MyLogger.debug("Skipping Suite A as runmode is No");
			throw new SkipException("Runmode is set to no for Suite A");
		}

	}
}
