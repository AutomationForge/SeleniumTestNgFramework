package AutomationFramework.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Standaltest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:/chrome/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("Skip123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test1234");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='products']//h5//b")));

		String Product[] = { "ADIDAS ORIGINAL", "QWERTY", "ZARA COAT 3", "IPHONE 13 PRO" };

		addItems(driver, Product);

	}

	public static void addItems(WebDriver driver, String Product[]) {
		List<WebElement> Items = driver.findElements(By.xpath("//section[@id='products']//h5//b"));
		List itemsCart = Arrays.asList(Product);
		int j = 0;

		for (int i = 0; i < Items.size(); i++) {

			String Productname = Items.get(i).getText().trim();

			if (itemsCart.contains(Productname)) {

				WebElement Addtocartbtn = driver.findElements(By.xpath("//button[text()=' Add To Cart']")).get(i);

				// Addtocartbtn.click();

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Addtocartbtn);
			}

			if (j == Product.length) {
				break;
			}
		}

	}

}
