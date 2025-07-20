package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtil;

import static com.utility.JSONUtility.*;

import static com.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static final By SIGN_IN_LINK_LOCATOR = By.linkText("Sign in");

	public HomePage(Browser browser, boolean isHeadless) {
		super(browser, isHeadless); // call the parent class constructor using super keyword.
		goToWebsite(readJSON(QA).getUrl());
		// goToWebsite(PropertiesUtil.readProperty(DEV, "URL"));

	}

	public HomePage(WebDriver driver) {
		super(driver); // call the parent class constructor using super keyword.
		goToWebsite(readJSON(QA).getUrl());
	}

	public LoginPage goToLoginPage() {

		logger.info("Performing click to go to sign in Page");

		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());

		return loginPage;
	}

}
