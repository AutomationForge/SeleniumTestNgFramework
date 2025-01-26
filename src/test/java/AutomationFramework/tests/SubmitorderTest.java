package AutomationFramework.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationFramework.Pageobjects.LandingPage;
import AutomationFramework.Pageobjects.MyCartPage;
import AutomationFramework.Pageobjects.OrderConfirmationpage;
import AutomationFramework.Pageobjects.OrdersPage;
import AutomationFramework.Pageobjects.Paymentpage;
import AutomationFramework.Pageobjects.ProductCatalogue;
import AutomationFramework.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitorderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitorder(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingpage.loginapplication(input.get("email"), input.get("password"));

		List<WebElement> Items = productCatalogue.getProduct();

		productCatalogue.addPrdtocart(input.get("input"));

		MyCartPage myCartPage = productCatalogue.goToCart();

		myCartPage.returnaddedProducttocart();

		Boolean match = myCartPage.VerifyProductDisplay(input.get("input"));

		Assert.assertTrue(match);

		Paymentpage paymentpage = myCartPage.goToPaymentPage();

		paymentpage.countrySearchandSelection("India");

		OrderConfirmationpage orderConfirmationpage = paymentpage.placeorderbtnclk();

		String Message = orderConfirmationpage.goToOrderConfirmation();

		Assert.assertTrue(Message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dataProvider = "getData", dependsOnMethods = { "submitorder" })
	public void Verifyorder(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingpage.loginapplication(input.get("email"), input.get("password"));

		OrdersPage ordersPage = productCatalogue.goToorder();

		ordersPage.returnPurchasedProducttocart();

		Boolean result = ordersPage.VerifyProductDisplayonOrderpage(input.get("input"));

		Assert.assertTrue(result, "Ordered product is displayed on the Orders Page.");

	}

	
	@DataProvider
	public Object[][] getData() throws IOException {

//		 HashMap<String, String> map1 = new HashMap<>();
//		    map1.put("email", "Skip123@gmail.com");
//		    map1.put("password", "Test1234");
//		    map1.put("input", "ADIDAS ORIGINAL");
//
//		    HashMap<String, String> map2 = new HashMap<>();
//		    map2.put("email", "Skip1234@gmail.com");
//		    map2.put("password", "Test1234");
//		    map2.put("input", "ZARA COAT 3");

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//AutomationFramework//Data//Purchaseorder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() {
//
//		Object[][] obj = new Object[][] { { "Skip123@gmail.com", "Test1234", "ADIDAS ORIGINAL" },
//				{ "Skip1234@gmail.com", "Test1234", "ZARA COAT 3" } };
//
//		return obj;
//	}
}
