package AutomationFramework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationFramework.Pageobjects.LandingPage;
import AutomationFramework.Pageobjects.MyCartPage;
import AutomationFramework.Pageobjects.OrderConfirmationpage;
import AutomationFramework.Pageobjects.Paymentpage;
import AutomationFramework.Pageobjects.ProductCatalogue;
import AutomationFramework.TestComponents.BaseTest;
import AutomationFramework.TestComponents.RetryAnalysis;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = RetryAnalysis.class)
	public void incorrectCreds() throws IOException {

		String prdname = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingpage.loginapplication("Skip923@gmail.com", "Test1234");

		Assert.assertEquals(landingpage.errorMessage(), "Incorrect email or password.");
	}

	@Test
	public void ProductErrorValidation() throws IOException {

		String prdname = "qwerty";

		ProductCatalogue productCatalogue = landingpage.loginapplication("Skip1234@gmail.com", "Test1234");

		List<WebElement> Items = productCatalogue.getProduct();

		productCatalogue.addPrdtocart(prdname);

		MyCartPage myCartPage = productCatalogue.goToCart();

		myCartPage.returnaddedProducttocart();

		Boolean match = myCartPage.VerifyProductDisplay("qwerty33");

		Assert.assertFalse(match);

	}

}
