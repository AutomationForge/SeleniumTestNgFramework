package AutomationFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import AutomationFramework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest =  new ThreadLocal<ExtentTest>(); //ThreadSafe

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //Unique Thread id;
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 System.out.println("Test Passed: " + result.getName());
		 extentTest.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// System.out.println("Test Failed: " + result.getName());
		test.log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
		extentTest.get().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Not commonly used
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
