 package com.appium.pages;

import com.appium.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductsPage extends TestBase{

	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"PRODUCTS\"]")	
	private MobileElement txtProducts;
	
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
	private MobileElement txtSauceLabBagPack;

	public ProductsPage getProductsTitle() {
		getAttribute(txtProducts, "text");
		return this;
	}
	
	public SauceLabBagPackPage pressSauceLabBagPack() {
		click(txtSauceLabBagPack);
		return new SauceLabBagPackPage();
	}
	
}
