package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

	private LoggerUtility() {

	}

	public static Logger getLogger(Class<?> clazz) {

		Logger logger = null;
		if (logger == null) {
			logger = LogManager.getFormatterLogger(clazz);
		}
		return logger;

	}

}

//
//Breakdown of What's Happening
//Step-by-Step:
//The method getLogger(Class<?> clazz) is static, so it's accessed without an instance.
//
//Inside the method:
//
//
//Logger logger = null;
//if (logger == null) {
//    logger = LogManager.getFormatterLogger(clazz);
//}
//return logger;
//This logic always creates a new local variable (logger is method-local), then assigns a logger and returns it.
//
//So:
//Every call creates a new local reference to a shared logger instance (not a new logger).
//
//Log4j2 internally handles logger caching and thread safety.
//
//
//1. How Log4j2 and Your Logger Are Linked
//✅ Your Utility Class:
//
//public static Logger getLogger(Class<?> clazz) {
//    return LogManager.getFormatterLogger(clazz);
//}
//✅ What This Does:
//It calls LogManager.getFormatterLogger(clazz) from Log4j2.
//
//This returns a Logger object associated with the given class.
//
//That logger is automatically configured using the settings in your log4j2.xml config file (assuming it's on the classpath).
//
//So this line creates the link:
//
//
//LogManager.getFormatterLogger(clazz);
//🔄 2. How Log4j2 Handles Logger Creation and Linking
//Behind the scenes:
//LogManager is the Log4j2 API’s way of accessing loggers.
//
//When you call getFormatterLogger(clazz) or getLogger(clazz):
//
//Log4j2 looks in its internal cache to check if a logger for that class already exists.
//
//If it does, it reuses it.
//
//If not, it creates a new one and stores it in the cache.
//
//This ensures:
//
//Only one logger per class is created.
//
//You don't have to manage logger instances manually.
//
//🧵 3. How Thread Safety Is Achieved
//✅ At a High Level
//Log4j2 ensures that:
//
//Loggers are thread-safe.
//
//Appenders (Console, File, etc.) are thread-safe.
//
//Configuration reloading is also safe in concurrent environments.
//
//You don’t need to write any special thread management code — Log4j2 handles it internally using things like:
//
//Atomic classes (e.g., AtomicReference)
//
//Locks/synchronization where needed
//
//Lock-free queues in async logging
//
