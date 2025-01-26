package AutomationFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationFramework.Pageobjects.OrdersPage;

public class AbstractComponent {
	public WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderBtn;
	

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForElementTodisAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

	}

	public void actionstoPerform(WebElement findBy) {

		Actions a = new Actions(driver);
		a.click(findBy).build().perform();
	}

	public void javascriptexecutortoPerform(WebElement findBy) {

		((JavascriptExecutor) driver).executeScript("arguments[0].click()", findBy);
	}

	public void gottocartpage() {

		cartBtn.click();

	}
	
	public void gottoorderPage() {

		orderBtn.click();

	}
	
}
