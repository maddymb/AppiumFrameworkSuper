package com.appium.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.appium.base.TestBase;
import com.appium.pages.LoginPage;
import com.appium.pages.MenuPage;
import com.appium.pages.ProductsPage;
import com.appium.pages.SettingPage;

public class LoginTest extends TestBase{

	
	LoginPage objLoginPage;
	ProductsPage objProductsPage;
	SettingPage objSettingPage;
	MenuPage objMenuPage;
	
	@BeforeClass
	public void beforeClass() {
		 closeApp();
		 launchApp();
	}
	
	
	@BeforeMethod()
	public void before() {
		objLoginPage=new LoginPage();
	}
	
	@Test
	public void correctLoginTest() {
		objLoginPage.enterUserName("standard_user");
		objLoginPage.enterPassword("secret_sauce");
		objProductsPage=objLoginPage.clickLoginButton();
		System.out.println(objProductsPage.getProductsTitle());
		
	}
	
	@Test
	public void incorrectLoginTest() {
			objLoginPage.enterUserName("test");
			objLoginPage.enterPassword("test");
			objProductsPage=objLoginPage.clickLoginButton();
		
	}
	
	
	
	
}
