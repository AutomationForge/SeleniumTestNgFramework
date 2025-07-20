package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

//Once we make the class as abstract then we cannot create the object of that class. Then HomePage class which 
//extends BrowserUtility class will call the constructor using superkeyword.
public class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(String browserName) {

		logger.info("Launching Browser for " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {

			driver.set(new ChromeDriver());

		} else if (browserName.equalsIgnoreCase("edge")) {

			driver.set(new EdgeDriver());

		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver.set(new FirefoxDriver());

		}

		else {
			logger.error("Invalid browser Name. Please select chrome or edge or firefox");
			System.err.print("Invalid browser Name. Please select chrome or edge or firefox");
		}
	}

//	public BrowserUtility(Browser browserName) {
//
//		logger.info("Launching Browser for " + browserName);
//
//		if (browserName == Browser.CHROME) {
//
//			driver.set(new ChromeDriver());
//
//		} else if (browserName == Browser.EDGE) {
//
//			driver.set(new EdgeDriver());
//
//		} else if (browserName == Browser.FIREFOX) {
//
//			driver.set(new FirefoxDriver());
//
//		}
//
//		driver.get().manage().window().maximize();
//	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {

		logger.info("Launching Browser for " + browserName);

		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");// headless
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			}

			else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {

			if (isHeadless) {

				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");// headless
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			}

			else {
				driver.set(new EdgeDriver());
			}
		} else if (browserName == Browser.FIREFOX) {

			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");// headless
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}

		}

		driver.get().manage().window().maximize();
	}

	public void goToWebsite(String url) {

		logger.info("Visiting the Website" + " " + url);

		driver.get().get(url);

	}

	public void maximizeWindow() {

		logger.info("Maximizing the browser window");

		driver.get().manage().window().maximize();

	}

	public void clickOn(By locator) {

		logger.info("Finding element with the locator" + " " + locator);

		WebElement element = driver.get().findElement(locator);

		logger.info("Element found now performing click" + " " + locator);

		element.click();
	}

	public void enterText(By locator, String textoEnter) {

		logger.info("Finding element with the locator" + " " + locator);

		WebElement element = driver.get().findElement(locator);

		logger.info("Element found now entering text" + " " + textoEnter);

		element.sendKeys(textoEnter);

	}

	public String getVisibleText(By locator) {

		logger.info("Finding element with the locator" + " " + locator);

		WebElement element = driver.get().findElement(locator);

		logger.info("Element found now returning visible text" + " " + element.getText());

		return element.getText();

	}

	public String takeScreenShot(String name) {

		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = System.getProperty("user.dir") + "//screenshots//" + name + " - " + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return path;
	}

	public void quit() {
		if (driver.get() != null) {
			driver.get().quit();
		}
	}
}