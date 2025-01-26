package AutomationFramework.tests;

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

import AutomationFramework.Pageobjects.LandingPage;

public class Standalonetests {

	public static void main(String[] args) {

		String prdname = "ZARA COAT 3";
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:/chrome/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		LandingPage landingpageobj = new LandingPage(driver);
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("Skip123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test1234");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='products']//h5//b")));

		List<WebElement> Items = driver.findElements(By.xpath("//section[@id='products']//h5//b"));

		WebElement product = Items.stream().filter(prd -> prd.getText().equalsIgnoreCase(prdname)).findFirst()
				.orElse(null);

		product.findElement(By.xpath("//button[text()=' Add To Cart']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> CartProduct = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));

		Boolean match = CartProduct.stream().anyMatch(pr -> pr.getText().equalsIgnoreCase(prdname));
		Assert.assertTrue(match);

		driver.findElement(By.xpath("//li[@class='totalRow']//button")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='payment']")));

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		Actions a = new Actions(driver);

		a.click(driver.findElement(By.cssSelector(".ta-item:nth-child(3)"))).build().perform();

		// driver.findElement(By.xpath("(//button[@class='ta-item list-group-item
		// ng-star-inserted'])[2]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click()",
				driver.findElement(By.cssSelector(".action__submit")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));

		String Message = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertEquals(Message, "THANKYOU FOR THE ORDER.");

		driver.quit();

	}

}
