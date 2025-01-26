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

public class Paymentpage extends AbstractComponent {
	public WebDriver driver;

	public Paymentpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Page factory
	@FindBy(xpath = "//div[@class='cartSection']//h3")
	List<WebElement> CartProduct;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement cntrydrpdown;

	@FindBy(css = ".ta-item:nth-child(3)")
	WebElement cntryselect;

	@FindBy(css = ".action__submit")
	WebElement placeorder;

	By PaymentPagetoappear = By.xpath("//div[@class='payment']");
	By Cntrytoappear = By.cssSelector(".ta-results");
	By placeordertoappear = By.cssSelector(".action__submit");

	public void countrySearchandSelection(String Countryname) {
		// waitForElementToAppear(toastgby);
		waitForElementToAppear(PaymentPagetoappear);
		cntrydrpdown.sendKeys(Countryname);
		waitForElementToAppear(Cntrytoappear);

		actionstoPerform(cntryselect);
		waitForElementToAppear(placeordertoappear);
	}

	public OrderConfirmationpage placeorderbtnclk() {

		javascriptexecutortoPerform(placeorder);
		OrderConfirmationpage orderConfirmationpage = new OrderConfirmationpage(driver);
		return orderConfirmationpage;

	}

}