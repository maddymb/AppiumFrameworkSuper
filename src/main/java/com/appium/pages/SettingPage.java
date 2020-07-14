package com.appium.pages;

import com.appium.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SettingPage extends TestBase {

	@AndroidFindBy(accessibility = "test-ALL ITEMS")
	@iOSXCUITFindBy(id="test-ALL ITEMS")
	private MobileElement btnALLITEMS;

	@AndroidFindBy(accessibility = "test-WEBVIEW")
	@iOSXCUITFindBy(id="test-WEBVIEW")
	private MobileElement btnWEBVIEW;

	@AndroidFindBy(accessibility = "test-ABOUT")
	@iOSXCUITFindBy(id="test-ABOUT")
	private MobileElement btnABOUT;
	
	@AndroidFindBy(accessibility = "test-LOGOUT")
	@iOSXCUITFindBy(id="test-LOGOUT")
	private MobileElement btnLOGOUT;
	
	@AndroidFindBy(accessibility = "test-RESET APP STATE")
	@iOSXCUITFindBy(id="test-RESET APP STATE")
	private MobileElement btnRESETAPPSTATE;

	public SettingPage pressALLITEMS() {
		click(btnALLITEMS);
		return this;
	}

	public SettingPage pressWEBVIEW() {
		click(btnWEBVIEW);
		return this;
	}
	
	public SettingPage pressABOUT() {
		click(btnABOUT);
		return this;
	}

	public SettingPage pressRESETAPPSTATE() {
		click(btnRESETAPPSTATE);
		return this;
	}
	
	public LoginPage pressLOGOUT() {
		click(btnLOGOUT);
		return new LoginPage();
	}

}
