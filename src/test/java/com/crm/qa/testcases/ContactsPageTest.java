package com.crm.qa.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName="contacts";
	
	public  ContactsPageTest() {
		super();
	}

	

	@BeforeMethod
	public void setUp() {
		 initialization();
		 testUtil = new TestUtil();
		 contactsPage = new ContactsPage();
		 loginpage = new LoginPage();
		 homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		 testUtil.switchToFrame();
		 contactsPage = homepage.clickOnContactsLink();
	
}

	
	
	@Test(priority=1)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest() {
		contactsPage.selectContactsByName("test2 test2");
	}
	
	
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactsByName("test2 test2");
		contactsPage.selectContactsByName("Aajay34 Dinne34");
	}
	
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = null;
		try {
			data = TestUtil.getTestData(sheetName);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company) {
		 homepage.clickOnNewContactLink();
		 //contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		 contactsPage.createNewContact(title, firstName, lastName, company);
	}
	
	@AfterMethod
	public void tearDown() {
			driver.quit();
	}
}
