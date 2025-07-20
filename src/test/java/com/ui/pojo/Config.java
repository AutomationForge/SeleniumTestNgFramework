package com.ui.pojo;

import java.util.Map;

public class Config {

	Map<String, Environment> environments;
	
//	Map<String, Environment> environments; maps "DEV", "QA", "UAT" to Java Environment objects.
//
//	Each Environment contains fields like url and MAX_NUMBER_OF_ATTEMPTS.
//
//	This setup allows easy access:
//	environments.get("DEV").getUrl(); → returns "http://www.automat"

	public Map<String, Environment> getEnvironments() {
		return environments;
	}

	public void setEnvironments(Map<String, Environment> environments) {
		this.environments = environments;
	}

}
