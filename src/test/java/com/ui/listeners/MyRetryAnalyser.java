package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyser implements IRetryAnalyzer {

	private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMAX_NUMBER_OF_ATTEMPTS();
	private static int currentAttempt = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (currentAttempt <= MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		} else
			return false;
	}

}
