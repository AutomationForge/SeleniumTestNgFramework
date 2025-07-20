////// So the driver flows like this:
////
////Test Class ➝ Page Class ➝ BrowserUtility
////         (creates)       (passes)         (uses)
//HomePage extends BrowserUtility:
//
//public class HomePage extends BrowserUtility {
//    public HomePage(WebDriver driver) {
//        super(driver); // <-- Passes WebDriver to BrowserUtility
//    }
//}
//why there is a need to BrowserUtility  class as abstarct ?
//An abstract class in Java:
//
//Cannot be instantiated directly.
//
//Can contain both abstract methods (without implementation) and concrete methods (with implementation).
//
//Is typically used as a base class that other classes inherit from.
//
//
//Possible Reasons (If Interntional):
//To Enforce Inheritance Only:
//
//You're saying: "This is a base utility class — it's meant to be extended, not used directly."
//
//This is common in frameworks where the base class should only be used through subclasses (e.g., Page Object classes).
//
//To Avoid Instantiation:
//
//Maybe you don’t want anyone to use this class directly.
//
//It might be designed to force page classes to extend it instead.
//*******************IMP NOTES**************************************
//Login test(this class extends TestBase)
//so whenever your run the LoginTest class
//It automatically calls the methods(@Before or @After) present TestBase class)
//In the setUp method present in TestBase , so according to isLambdaTest , isHeadless flag
//the respective condition is called 
//isLambdaTest > intialiazeLambdaTestSession method is called
//not isLambdaTest > in local system test run
//Whenever we create homePage = new HomePage(CHROME, isHeadless);
// It call the constructor(HomePage(WebDriver driver)) present in HomePage class  which will launch the app URL

package com.ui.tests;

import static com.constants.Browser.*;

import org.testng.Assert;
import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners({ com.ui.listeners.TestListeners.class })
public class LoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	@Test(description = "Verifies with valid user is able to login into Application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginJsonTestDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyser.class)
	public void loginJsonTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Saman Sahu");
	}

	@Test(description = "Verifies with valid user is able to login into Application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginCSVTestDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyser.class)
	public void loginCSVTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Saman Sahu");
	}

	@Test(description = "Verifies with valid user is able to login into Application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginExcelTestDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyser.class)
	public void loginExcelTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Saman Sahu");

	}
}
