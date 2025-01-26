package AutomationFramework.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationFramework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Page factory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordele;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = ".toast-message")
	WebElement errmsg;
	
	By msg =  By.cssSelector(".toast-message");

	public ProductCatalogue loginapplication(String email, String password) {

		userEmail.sendKeys(email);
		passwordele.sendKeys(password);

		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public void Goto(String url) {

		driver.get(url);
		//driver.manage().window().maximize();
	}
	
	
	public  String errorMessage() {

		waitForElementToAppear(msg);
		
		return errmsg.getText();
		
	}

}