package com.framework.Runner;

import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class MainRunner {

	public static void main(String[] args) {
		//String log4jConfPath = "/path/to/log4j.properties";
		//PropertyConfigurator.configure(log4jConfPath);
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(System.getProperty("user.dir")+"\\src\\test\\java\\com\\framework\\TestNG\\testng.xml");//path to xml..
		testng.setTestSuites(suites);
		testng.run();
	}

}
