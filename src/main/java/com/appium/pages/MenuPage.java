package com.appium.pages;

import com.appium.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuPage extends TestBase {

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")
	private MobileElement btnSettings;

	@AndroidFindBy(className = "android.widget.ImageView")
	@iOSXCUITFindBy(id="test-Password")
	private MobileElement txtLogo;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"test-Cart\"]/XCUIElementTypeOther")	
	private MobileElement btnCart;

	public SettingPage pressSettings() {
		click(btnSettings);
		return new SettingPage();
	}

	public SettingPage pressCart() {
		click(btnCart);
		return new SettingPage();
	}

	public MenuPage getLogoText() {
		getAttribute(txtLogo, "text");
		return this;
	}

}
