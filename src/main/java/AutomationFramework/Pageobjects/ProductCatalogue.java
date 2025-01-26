package AutomationFramework.Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AutomationFramework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	public WebDriver driver;
	public WebElement cartBtn;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Page factory
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By toastmessage = By.cssSelector(".toast-message");
	// By productsby = By.cssSelector(".mb-3");
	By productsby = By.xpath("//div[@class='card-body']//b");

	// By Addtocartbtn = By.cssSelector(".card-body button:last-of-type");
	By Addtocartbtn = By.cssSelector(".mb-3 button:nth-child(4)");

	By spinner = By.cssSelector(".ngx-spinner-overlay");

	public List<WebElement> getProduct() {
		// waitForElementToAppear(toastgby);
		waitForElementToAppear(productsby);

		return products;
	}

	public WebElement getProductname(String prdname) {

		WebElement product = getProduct().stream().filter(prd -> prd.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(prdname)).findFirst()
				.orElse(null);

		return product;
	}

	public void addPrdtocart(String prdname) {
		WebElement prod = getProductname(prdname);

		prod.findElement(Addtocartbtn).click();
		waitForElementToAppear(toastmessage);
		waitForElementTodisAppear(spinner);

	}

	public MyCartPage goToCart() {

		gottocartpage();
		MyCartPage myCartPage = new MyCartPage(driver);
		return myCartPage;

	}

	public OrdersPage goToorder() {

		gottoorderPage();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;

	}

}