package com.rupjit.qaServiceNow.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.paulhammant.ngwebdriver.NgWebDriver;
import com.rupjit.qaServiceNow.utils.Xls_Reader;


public class TestBase {

	public static MyLogger log;
	//public static Properties config;
	public static Xls_Reader suitexls;
	public static Xls_Reader Asuitexls;
	public static Xls_Reader Bsuitexls;
	public static Xls_Reader Csuitexls;
	public static boolean isInitialized=false;


	public static Properties prop;
	BrowserFactory bf=new BrowserFactory();


	public void initialize() throws IOException, InterruptedException {
		if(!isInitialized) {
		
		//initialize config.properties file
		log.debug("Loading Config.properties file...");
		prop=new Properties();
		FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\qaServiceNow\\config\\config.properties");
		prop.load(ip);
		log.debug("Config.properties file loaded");
		
		//Initializing XLS file
		log.debug("Loading XLS file...");
		//suitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\Suite.xlsx");
		//Asuitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\A suite.xlsx");
		//Bsuitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\B suite.xlsx");
		//Csuitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\C suite.xlsx");
		log.debug("XLS files loaded");
		isInitialized=true;
		}}
		
		@BeforeMethod
		public void LaunchApplication() throws IOException, InterruptedException {
			initialize();
			String browser=prop.getProperty("browser");
			String url=prop.getProperty("url");
			DriverFactory.getInstance().setDriver(new BrowserFactory().createBrowserInstance(browser));
			WebDriver driver=DriverFactory.getInstance().getDriver();
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("page_load_timeout")), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("implicit_wait")), TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.navigate().to(url);
		getNgWebDriverInstance().waitForAngularRequestsToFinish();
	}
		
		@AfterMethod
		public void tearDown() {
			DriverFactory.getInstance().closeBrowser();
		}


	public JavascriptExecutor getJSExecutorInstance() {
		JavascriptExecutor js=(JavascriptExecutor) DriverFactory.getInstance().getDriver();
		return js;
	}
	
	public NgWebDriver getNgWebDriverInstance() {
		NgWebDriver ngDriver=new NgWebDriver(getJSExecutorInstance());
		return ngDriver;
	}
	
	public void moveMouseTo(WebElement element) {
		Actions action1=new Actions(DriverFactory.getInstance().getDriver());
		action1.moveToElement(element).build().perform();
	}

	public void dragAndDrop(WebElement source,int xOffset,int yOffset) {
		Actions action2=new Actions(DriverFactory.getInstance().getDriver());
		action2.moveToElement(source).build().perform();
		action2.dragAndDropBy(source, xOffset, yOffset).build().perform();
	}

	public void dragAndDrop(WebElement source,WebElement target) {
		Actions action3=new Actions(DriverFactory.getInstance().getDriver());
		action3.moveToElement(source).build().perform();
		action3.dragAndDrop(source, target).build().perform();
	}

	public void waitForVisibilityOfElement(WebElement element) {
		WebDriverWait wait1=new WebDriverWait(DriverFactory.getInstance().getDriver(), 30);
		wait1.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait2=new WebDriverWait(DriverFactory.getInstance().getDriver(), 30);
		wait2.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitForInvisibilityOfElement(WebElement element) {
		WebDriverWait wait3=new WebDriverWait(DriverFactory.getInstance().getDriver(), 30);
		wait3.until(ExpectedConditions.invisibilityOf(element));
	}
	public void javaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}

	
	public static String getScreenhot(WebDriver driver,String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
                //after execution, you could see a folder "TestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/target/TestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
