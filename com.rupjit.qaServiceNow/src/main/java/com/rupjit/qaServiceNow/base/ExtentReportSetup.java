package com.rupjit.qaServiceNow.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportSetup {
	static ExtentReports reports;
public static ExtentReports setupExtentReport() {
	ExtentSparkReporter sparkReport = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/ExtentReport.html");
	reports=new ExtentReports();
	reports.attachReporter(sparkReport);
	reports.setSystemInfo("Hostname", "RupjitDesktop");
		return reports;
	}
}
