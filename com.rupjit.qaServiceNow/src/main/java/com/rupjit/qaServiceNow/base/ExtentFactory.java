package com.rupjit.qaServiceNow.base;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {

	private ExtentFactory() {
		
	}
	private static ExtentFactory instance=new ExtentFactory();
	
	public static ExtentFactory getInstance() {
		return instance;
	}
	
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();

	public ExtentTest getExtentTest() {
		return extentTest.get();
	}
	
	public void setExtent(ExtentTest extentTestObject) {
		extentTest.set(extentTestObject);
	}
	
	public void removeExtentObject() {
		extentTest.remove();
	}
}
