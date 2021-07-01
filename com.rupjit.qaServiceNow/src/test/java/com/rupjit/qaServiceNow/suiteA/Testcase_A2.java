package com.rupjit.qaServiceNow.suiteA;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rupjit.qaServiceNow.base.MyLogger;
import com.rupjit.qaServiceNow.pages.PageSuiteA;
import com.rupjit.qaServiceNow.utils.TestUtil;


public class Testcase_A2  extends PageSuiteA{

	String dataSetRunmodes[]=null;
	static int count=-1;
	static boolean skip_testdata=false;
	static boolean fail_testdata=false;
	static boolean isTestcasePass=true;

	//Check runmode of testccase
		@BeforeTest
		public void checkTestcaseSkip() {
			MyLogger.debug("Checking the runmode of TestCase "+this.getClass().getSimpleName());
			if(!TestUtil.isCaseRunnable(Asuitexls, this.getClass().getSimpleName())) {
				TestUtil.dataSetResult(Asuitexls,"Testcases", TestUtil.getRowNum(Asuitexls, this.getClass().getSimpleName()),"SKIP");
				MyLogger.debug("Skipping TestCase as runmode is No for testcase "+this.getClass().getSimpleName());
				throw new SkipException("Runmode is set to no for TestCase "+this.getClass().getSimpleName());
			}
			dataSetRunmodes=TestUtil.getDataSetRunmodeTest(Asuitexls, this.getClass().getSimpleName());
		}
	@Test(dataProvider="getTestData")
	public void testcasesA2(String d1,String d2,String d3,String d4) {
		//Check runmodes of dataset and skip if it is set to no
		count++;
		if(!dataSetRunmodes[count].equalsIgnoreCase("y")) {
			skip_testdata=true;
			MyLogger.debug("Runmode of the dataset "+(count+1)+" is set to No, so skipping");
			throw new SkipException("Runmode of the dataset \"+(count+1)+\" is set to No, so skipping");
		}
		MyLogger.debug("Executing testcaseA2");
		MyLogger.debug(d1+"---"+d2+"---"+d3+"---"+d4);
		String expectedValue="true";
		String ActualValue="false";
		Assert.assertTrue(expectedValue.equalsIgnoreCase(ActualValue), "Actual Value not matching with Expceted Value");
		
	}
	
	@AfterMethod
	public void reporterdataSetResult(){
		if(skip_testdata)
			TestUtil.dataSetResult(Asuitexls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail_testdata){
			TestUtil.dataSetResult(Asuitexls, this.getClass().getSimpleName(), count+2, "FAIL");
		isTestcasePass=false;
		}
		else
			TestUtil.dataSetResult(Asuitexls, this.getClass().getSimpleName(), count+2, "PASS");
			
		skip_testdata=false;
		fail_testdata=false;
	}
	
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestcasePass){
			TestUtil.dataSetResult(Asuitexls,"Testcases", TestUtil.getRowNum(Asuitexls, this.getClass().getSimpleName()),"PASS");
		}else
			TestUtil.dataSetResult(Asuitexls,"Testcases", TestUtil.getRowNum(Asuitexls, this.getClass().getSimpleName()),"FAIL");
		}
		
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(Asuitexls, this.getClass().getSimpleName());
	}
}
