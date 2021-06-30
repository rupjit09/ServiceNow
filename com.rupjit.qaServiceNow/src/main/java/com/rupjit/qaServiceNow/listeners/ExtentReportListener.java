package com.rupjit.qaServiceNow.listeners;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.ExtentFactory;
import com.rupjit.qaServiceNow.base.ExtentReportSetup;
import com.rupjit.qaServiceNow.base.TestBase;

import io.qameta.allure.Attachment;

public class ExtentReportListener extends TestBase implements ITestListener{

	static ExtentReports report;
	   ExtentTest test;
	   
public void onTestStart(ITestResult result) {
	//before each test case
	test = report.createTest(result.getMethod().getMethodName());
	ExtentFactory.getInstance().setExtent(test);
}

public void onTestSuccess(ITestResult result) {
	ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+ " is Passed.");
	//ExtentFactory.getInstance().removeExtentObject();
	//add screenshot for failed test.
		String screenshotPath="";
		try {
			long nanos = java.lang.System.nanoTime();
			long threadId = Thread.currentThread().getId();
			screenshotPath=getScreenhot(DriverFactory.getInstance().getDriver(), result.getMethod().getMethodName()+"_"+nanos+"_"+threadId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ExtentFactory.getInstance().getExtentTest().addScreenCaptureFromPath(screenshotPath, "Test case success screenshot");
			ExtentFactory.getInstance().removeExtentObject();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	//allure
		WebDriver driver = DriverFactory.getInstance().getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(result) + " Success and screenshot taken!");	
}

public void onTestFailure(ITestResult result) {
	ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
	ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, result.getThrowable());
	
	//add screenshot for failed test.
	String screenshotPath="";
	try {
		long nanos = java.lang.System.nanoTime();
		long threadId = Thread.currentThread().getId();
		screenshotPath=getScreenhot(DriverFactory.getInstance().getDriver(), result.getMethod().getMethodName()+"_"+nanos+"_"+threadId);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		ExtentFactory.getInstance().getExtentTest().addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
		ExtentFactory.getInstance().removeExtentObject();

	} catch (IOException e) {
		e.printStackTrace();
	}
	
//allure
	WebDriver driver = DriverFactory.getInstance().getDriver();
	// Allure ScreenShotRobot and SaveTestLog
	if (driver instanceof WebDriver) {
		saveScreenshotPNG(driver);
	}
	// Save a log on allure.
	saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");	
}

public void onTestSkipped(ITestResult result) {
	ExtentFactory.getInstance().getExtentTest().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
	ExtentFactory.getInstance().removeExtentObject();
}

public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
}

public void onTestFailedWithTimeout(ITestResult result) {
}

public void onStart(ITestContext context) {
	try {
		 report = ExtentReportSetup.setupExtentReport();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	//allure
	context.setAttribute("WebDriver", DriverFactory.getInstance().getDriver());
}

public void onFinish(ITestContext context) {
	//close extent
	report.flush();
}

//below methods are for AllureReport
private static String getTestMethodName(ITestResult iTestResult) {
	return iTestResult.getMethod().getConstructorOrMethod().getName();
}

// Text attachments for Allure
@Attachment(value = "Page screenshot", type = "image/png")
public byte[] saveScreenshotPNG(WebDriver driver) {
	return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
}

// Text attachments for Allure
@Attachment(value = "{0}", type = "text/plain")
public static String saveTextLog(String message) {
	return message;
}

// HTML attachments for Allure
@Attachment(value = "{0}", type = "text/html")
public static String attachHtml(String html) {
	return html;
}
}
