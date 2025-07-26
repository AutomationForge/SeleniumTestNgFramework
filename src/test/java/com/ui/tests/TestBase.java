package com.ui.tests;

import static com.constants.Browser.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

//Testbase is parent of all test classes like LoginTest

public class TestBase {

	protected HomePage homePage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;

	@Parameters({ "browser", "isLambdaTest", "isHeadless" })
	@BeforeMethod(description = "Load the home page of this website")
	public void setUp(@Optional("chrome") String browser, @Optional("false") boolean isLambdaTest,
			@Optional("true") boolean isHeadless, ITestResult result) {

		this.isLambdaTest = isLambdaTest;

		WebDriver lambdaDriver;

		if (isLambdaTest) {

			lambdaDriver = LambdaTestUtility.intialiazeLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);

		} else {
			// Running test on local machine
			logger.info("Load the home page of this website");
			logger.info("Browser parameter received: '" + browser + "'");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);

		}
	}

	public BrowserUtility getInstance() {
		return homePage; // (Java concept here we are returning object of child class that is
							// homepage(Which extend browserutility) and the return
							// type of method is parent class BrowserUtility(parent class reference))
	}

	@AfterMethod(description = "Tear down the browser")
	public void tearDown() {

		if (isLambdaTest) {
			LambdaTestUtility.quitSession(); // Quit the browser session on lambda test.
		} else {
			homePage.quit();

		}
	}
}
