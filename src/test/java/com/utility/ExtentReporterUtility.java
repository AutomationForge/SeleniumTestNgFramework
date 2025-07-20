package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {

	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public static void setupSparkReporter(String reportName) {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "//" + reportName);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

	}

	public static void createExtentTest(String testName) {

		ExtentTest Test = extentReports.createTest(testName);
		extentTest.set(Test);
	}

	public static ExtentTest getTest() {
		return extentTest.get();
	}

	public static void flushReport() {
		extentReports.flush();
	}
}
//
//-------------------------------------------------------------------------------------------------------
//
//Solution: We want to make our component thread safe , we are able to execute in multi threaded env then ThreadLocal will come in picture. In parallel execution we need to have thread safety.
//So when one person tries to access a product , we will create a local variable for the product and it will be isolated in the similar way if other person tries to access the same product then another thread local variable will be created. Because we have isolation other threads cannot access this particular variable the order state will not be corrupted means no illegal assignment / invalid vales can happen to this particular variable.
//The Solution: ThreadLocal<ExtentTest>
//
//private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
//✅ What ThreadLocal Does:
//•	Creates a separate copy of ExtentTest for each thread.
//•	Each test thread can set() and get() its own instance of ExtentTest.
//•	These instances are completely isolated, even though the ThreadLocal variable itself is static.
//🔒 How Thread Safety is Achieved Step by Step
//
//1. createExtentTest(String testName)
//
//ExtentTest Test = extentReports.createTest(testName);
//extentTest.set(Test);  // stores in thread-specific memory
//Each thread calls this and sets its own ExtentTest object.
//
//2. getTest()
//
//return extentTest.get();  // retrieves the thread-specific ExtentTest
//Whenever a test wants to log something, it gets its own test logger.
//
