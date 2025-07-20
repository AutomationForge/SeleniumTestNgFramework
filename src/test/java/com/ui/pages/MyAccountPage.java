package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.utility.BrowserUtility;

public class MyAccountPage extends BrowserUtility {

	private static final By USERNAME_LOCATOR = By.xpath("//a[@title='View my customer account']");

	public MyAccountPage(WebDriver driver) {
		super(driver);

	}

	public String getUserName() {

		return getVisibleText(USERNAME_LOCATOR);
	}

	

}
