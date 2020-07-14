package com.appium.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.appium.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.screenrecording.CanRecordScreen;

public class TestBase {

	protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal<Properties> prop = new ThreadLocal<Properties>();
	protected static ThreadLocal<HashMap<String, String>> strings = new ThreadLocal<HashMap<String,String >>();
	protected static ThreadLocal<String> dateTime =new ThreadLocal<String>();
	protected static ThreadLocal <String> platform = new ThreadLocal<String>();
	protected static ThreadLocal <String> deviceName = new ThreadLocal<String>();
	TestUtils utils = new TestUtils();
	
	public AppiumDriver getDriver() {
		  return driver.get();
	  }
	  
	  public void setDriver(AppiumDriver driver2) {
		  driver.set(driver2);
	  }
	  
	  public Properties getProps() {
		  return prop.get();
	  }
	  
	  public void setProps(Properties prop2) {
		  prop.set(prop2);
	  }
	  
	  public HashMap<String, String> getStrings() {
		  return strings.get();
	  }
	  
	  public void setStrings(HashMap<String, String> strings2) {
		  strings.set(strings2);
	  }
	  
	  public String getDateTime() {
		  return dateTime.get();
	  }
	  
	  public void setDateTime(String dateTime2) {
		  dateTime.set(dateTime2);
	  }
	  
	  public String getPlatform() {
		  return platform.get();
	  }
	  
	  public void setPlatform(String platform2) {
		  platform.set(platform2);
	  }
	  
	  public String getDeviceName() {
		  return deviceName.get();
	  }
	  
	  public void setDeviceName(String deviceName2) {
		  deviceName.set(deviceName2);
	  }
	
	
	public TestBase()  {
		File xmlFile;
		String xmlFilePath = "/Users/maddy/eclipse-workspace/AppiumFrameworkSuper/src/main/resources/strings/strings.xml";
		
		try {
			xmlFile= new File(xmlFilePath); 

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}
	
	@BeforeMethod
	public void superBefore() {
		((CanRecordScreen)getDriver()).startRecordingScreen();
		System.out.println("Platform "+getPlatform());
		System.out.println("Device "+getDeviceName());
		
		utils.log("test");
		
	}
	
	@AfterMethod
	public synchronized void superAfter(ITestResult result) throws IOException {
		String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
		
		Map <String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();		
		String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName") 
		+ File.separator + getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();
		
		File videoDir = new File(dirPath);
		
		synchronized(videoDir){
			if(!videoDir.exists()) {
				videoDir.mkdirs();
			}	
		}
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
			stream.write(Base64.decodeBase64(media));
			stream.close();
			utils.log().info("video path: " + videoDir + File.separator + result.getName() + ".mp4");
		} catch (Exception e) {
			utils.log().error("error during video capture" + e.toString());
		} finally {
			if(stream != null) {
				stream.close();
			}
		}		
		
		
	}
	
	
	
	@Parameters({"emulator","udid","platformName","platformVersion","deviceName",
					"systemPort","chromeDriverPort","wdaLocalPort","webkitDebugProxyPort"})
	@BeforeTest
	public void setUp(String emulator,String udid,String platformName,String platformVersion,String deviceName , 
					@Optional("androidOnly")String systemPort,@Optional("androidOnly")String chromeDriverPort,
					@Optional("iOSOnly")String wdaLocalPort, @Optional("iOSOnly")String webkitDebugProxyPort) throws FileNotFoundException{
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/com/appium/config/config.properties");
		Properties prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setProps(prop);
		TestUtils utils;
		utils = new TestUtils();
		setDateTime(utils.dateTime());
		setPlatform(platformName);
		 setDeviceName(deviceName);
		URL url = null;
		AppiumDriver driver = null;
		try {
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("platformName", platformName);
			desiredCapabilities.setCapability("deviceName", deviceName);
			url = new URL(getProps().getProperty("appiumUrl"));
			switch(platformName) {
			case "Android":
				desiredCapabilities.setCapability("automationName",prop.getProperty("androidAutomationName"));
				desiredCapabilities.setCapability("app", "/Users/maddy/eclipse-workspace/AppiumFrameworkSuper/apps/Android_SauceLabs.apk");
				desiredCapabilities.setCapability("appPackage", prop.getProperty("androidAppPackage"));
				desiredCapabilities.setCapability("appActivity",prop.getProperty("androidAppActivity"));
				utils.log().info(prop.getProperty("androidAppActivity"));
				if(emulator.equalsIgnoreCase("true")) {
					desiredCapabilities.setCapability("platformVersion", platformVersion);
					desiredCapabilities.setCapability("avd", "Pixel_XL");
				}else {
					desiredCapabilities.setCapability("udid", udid);
				}
				desiredCapabilities.setCapability("systemPort", systemPort);
				desiredCapabilities.setCapability("chromeDriverPort",chromeDriverPort);
				driver = new AndroidDriver(url, desiredCapabilities);
				break;
			case "iOS":
				desiredCapabilities.setCapability("platformVersion", platformVersion);
				desiredCapabilities.setCapability("automationName", "XCUITest");
				desiredCapabilities.setCapability("app", getProps().getProperty("iOSAppLocation"));
				desiredCapabilities.setCapability("wdaLocalPort", wdaLocalPort);
				desiredCapabilities.setCapability("webkitDebugProxyPort",webkitDebugProxyPort);
				driver = new IOSDriver(url, desiredCapabilities);
				break;
			default:
				throw new Exception("Invalid platformName :"+platformName);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		setDriver(driver);
		utils.log().info(url);
	}
	
	public void waitForVisibility(MobileElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
			
	}
	
	public void click(MobileElement element) {
		waitForVisibility(element);
		element.click();
	}
	
	public void sendKeys(MobileElement element, String value) {
		waitForVisibility(element);
		element.clear();
		element.sendKeys(value);
	}
	
	public String getAttribute(MobileElement element, String attribute) {
		waitForVisibility(element);
		return element.getAttribute(attribute);
	}
	
	public String getText(MobileElement element) {
		waitForVisibility(element);
		return element.getText();
	}
	
	public void closeApp() {
		((InteractsWithApps)getDriver()).closeApp();
	}
	
	public void launchApp() {
		((InteractsWithApps)getDriver()).launchApp();
	}
	
	
	@AfterTest
	public void tearDown(){
		getDriver().quit();
	}
	
	public Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}
	
	
}
