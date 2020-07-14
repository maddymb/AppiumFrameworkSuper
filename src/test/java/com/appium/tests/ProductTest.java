package com.appium.tests;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.appium.base.TestBase;
import com.appium.pages.LoginPage;
import com.appium.pages.MenuPage;
import com.appium.pages.ProductsPage;
import com.appium.pages.SettingPage;

public class ProductTest extends TestBase{

	
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
		objLoginPage.enterUserName("standard_user");
		objLoginPage.enterPassword("secret_sauce");
		objProductsPage=objLoginPage.clickLoginButton();
	}
	
	@AfterMethod()
	public void after() {
		objMenuPage = new MenuPage();
		objMenuPage.pressSettings();
		objSettingPage = new SettingPage();
		objSettingPage.pressLOGOUT();
	}
	
	
	
	@Test
	public void validateProduct() {

		objProductsPage.pressSauceLabBagPack();
	
		
		
	}
	
	@Test
	public void validateProductDetails() {
	
		objProductsPage.pressSauceLabBagPack();
		
	}
	
	
	
	
}
