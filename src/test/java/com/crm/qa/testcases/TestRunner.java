package com.crm.qa.testcases;

import org.testng.TestNG;

import com.crm.qa.util.WebEventListener;

public class TestRunner {

	static TestNG testNg;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		
		WebEventListener web = 	new WebEventListener();
		
		testNg = new TestNG();
		
		testNg.setTestClasses(new Class[] {LoginPageTest.class});
		testNg.addListener(web);
		testNg.run();
		
		
		
		
		

	}

}
