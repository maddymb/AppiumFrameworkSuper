package com.appium.pages;

import com.appium.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends TestBase {

	@AndroidFindBy(accessibility = "test-Username")
	@iOSXCUITFindBy(id="test-Username")
	private MobileElement inputUserName;

	@AndroidFindBy(accessibility = "test-Password")
	@iOSXCUITFindBy(id="test-Password")
	private MobileElement inputUserPassword;

	@AndroidFindBy(accessibility = "test-LOGIN")
	@iOSXCUITFindBy(id="test-LOGIN")
	private MobileElement btnLogin;

	public LoginPage enterUserName(String uName) {
		sendKeys(inputUserName, uName);
		return this;
	}

	public LoginPage enterPassword(String pass) {
		sendKeys(inputUserPassword, pass);
		return this;
	}

	public ProductsPage clickLoginButton() {
		click(btnLogin);
		return new ProductsPage();
	}

}
