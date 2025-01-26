package AutomationFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationFramework.Pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	Properties prop;
	public String url;
	public WebDriver driver;
	public LandingPage landingpage;

	public Properties intializeproperty() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//AutomationFramework//resources//GlobalData.properties");
		prop = new Properties();

		prop.load(fis);
		fis.close();
		// url = prop.getProperty("QAUrl");

		String url_Properties = prop.getProperty("QAUrl");
		String url_Maven = System.getProperty("QAUrl");
		url = url_Maven != null ? url_Maven : url_Properties;

		return prop;

	}

	public WebDriver initializeDriver() throws IOException {
		prop = intializeproperty();
		String Browser_Properties = prop.getProperty("browser");
		String Browser_Maven = System.getProperty("browser");
		String Browser = Browser_Maven != null ? Browser_Maven : Browser_Properties;

		if (Browser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();

			if (Browser.contains("headless")) {
				options.addArguments("headless");
			}

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
					+ "//src//main//java//AutomationFramework//resources//chromedriver.exe");

			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (Browser.equalsIgnoreCase("edge")) {
			// System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();

		} else if (Browser.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> dataList = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return dataList;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		String screenshotPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator
				+ testCaseName + ".png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(screenshotPath));
		// FileUtils.copyFile(src, new
		// File("D:/gj/SeleniumFrameworkDesign/reports/screenshot.png"));
		// FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "reports" +
		// testCaseName + ".png"));
		// return System.getProperty("user.dir") + "reports" + testCaseName + ".png";
//		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		return screenshotPath;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchapp() throws IOException {

		driver = initializeDriver();
		landingpage = new LandingPage(driver);

		landingpage.Goto(url);

		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.quit();
	}

}
