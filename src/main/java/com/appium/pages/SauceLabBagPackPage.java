package com.appium.pages;

import com.appium.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SauceLabBagPackPage extends TestBase{

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]/android.widget.TextView")
	private MobileElement btnAddToCart;

	public SauceLabBagPackPage pressAddToCart() {
		click(btnAddToCart);
		return this;
	}
	
	
}
