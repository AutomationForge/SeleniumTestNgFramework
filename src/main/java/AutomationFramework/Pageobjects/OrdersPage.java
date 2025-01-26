package AutomationFramework.Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import AutomationFramework.AbstractComponents.AbstractComponent;


public class OrdersPage extends AbstractComponent {
	public WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Page factory
	@FindBy(css = ".table td:nth-child(3)")
	List<WebElement> OrderProduct;
	
	@FindBy(xpath = "//li[@class='totalRow']//button")
	WebElement Checkoutbtnclk;

	By OrderIdtoappear = By.cssSelector(".thead-dark th:nth-child(1)");

	
	public List<WebElement> returnPurchasedProducttocart() {
		// waitForElementToAppear(toastgby);
		waitForElementToAppear(OrderIdtoappear);
		return OrderProduct;
	}
	
	public Boolean VerifyProductDisplayonOrderpage(String orderedprdname) {

		Boolean match = returnPurchasedProducttocart().stream().anyMatch(pr -> pr.getText().equalsIgnoreCase(orderedprdname));
		
		return match;
		
	}


	
}