package com.rupjit.qaServiceNow.base;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
//Singleton method with factory design pattern
	private DriverFactory() {
			}
	
	private static DriverFactory instance=new DriverFactory();
	public static DriverFactory getInstance() {
		return instance;
	}
	

	ThreadLocal<WebDriver> tdriver=new ThreadLocal<WebDriver>();
	public WebDriver getDriver() {
		return tdriver.get();
	}
	
	public void setDriver(WebDriver driverParam) {
		tdriver.set(driverParam);
	}
	
	public void closeBrowser() {
		tdriver.get().close();
		tdriver.remove();
	}
}
