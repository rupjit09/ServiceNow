package com.rupjit.qaServiceNow.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.rupjit.qaServiceNow.base.DriverFactory;
import com.rupjit.qaServiceNow.base.TestBase;

import io.qameta.allure.Attachment;

public class AllureListener extends TestBase implements ITestListener{
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

	public void onStart(ITestContext iTestContext) {
		//System.out.println("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", DriverFactory.getInstance().getDriver());
	}

	public void onFinish(ITestContext iTestContext) {
		System.out.println("Test method " + iTestContext.getName()+ " completed");
	}

	public void onTestStart(ITestResult iTestResult) {
		System.out.println("Test method " + getTestMethodName(iTestResult) + " started");
	}

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("Test method " + getTestMethodName(iTestResult) + " success");
		
		Object testClass = iTestResult.getInstance();
		WebDriver driver = DriverFactory.getInstance().getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " Success and screenshot taken!");	
	}

	public void onTestFailure(ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
		WebDriver driver = DriverFactory.getInstance().getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");	
	}

	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("Skipped method " + getTestMethodName(iTestResult) + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
}
