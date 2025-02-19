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


public class MyCartPage extends AbstractComponent {
	public WebDriver driver;

	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
 // new comments added
	// Page factory
	@FindBy(xpath = "//div[@class='cartSection']//h3")
	private List<WebElement> CartProduct;
	
	@FindBy(xpath = "//li[@class='totalRow']//button")
	private WebElement Checkoutbtnclk;

	private By addedPrdtoappear = By.xpath("//div[@class='cartSection']//h3");

	private By checkoutButton =By.xpath("//button[@type='button' and text()='Checkout']");
	
	public List<WebElement> returnaddedProducttocart() {
		// waitForElementToAppear(toastgby);
		waitForElementToAppear(addedPrdtoappear);
		return CartProduct;
	}
	
	public Boolean VerifyProductDisplay(String prdname) {

		Boolean match = returnaddedProducttocart().stream().anyMatch(pr -> pr.getText().equalsIgnoreCase(prdname));
		
		return match;
		
	}



	public Paymentpage goToPaymentPage() {
		
		waitForElementToAppear(checkoutButton);
		//Checkoutbtnclk.click();
		clickElementWithJS(checkoutButton);
		Paymentpage paymentpage = new Paymentpage(driver);
		return paymentpage;

	}
}