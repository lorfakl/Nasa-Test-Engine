package main.java.nasaTestSuite;

import java.io.File;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class TestCapabilities 
{
	
	public enum DevicePlatform
	{
		Undefined,
		iOS,
		Android
	}
	
	private DevicePlatform platform;
	private String platformVersion;
	private String deviceName;
	private String avd;
	private String app;
	private String automationName;
	
	public DevicePlatform GetPlatform(){return platform; }
	public String GetPlatformVersion() { return platformVersion; }
	public String GetDeviceName() { return deviceName; }
	public String GetAVD() { return avd; }
	public String GetApp() { return app; }
	public String GetAutomationName() { return automationName; }
	
	public void SetPlatform(String plat){platform = DevicePlatform.valueOf(plat);}
	public void SetPlatformVersion (String s){platformVersion = s;}
	public void SetDeviceName (String s){deviceName = s;}
	public void SetAVD (String s){avd = s;}
	public void SetApp (String s){app = s;}
	public void SetAutomationName (String s){automationName = s;}
	
	public TestCapabilities()
	{
		this.platform = DevicePlatform.Undefined;
		this.platformVersion = "";
		this.deviceName = "";
		this.avd = "";
		this.app = "";
		this.automationName = "";
	}
	
	public DesiredCapabilities AssignAppiumCapabilities()
	{
		String appPath = new File("").getAbsolutePath();
		appPath = appPath.concat("\\nasaTestSuite\\Android_AppUITesting.apk");
		DesiredCapabilities appiumSettings = new DesiredCapabilities();
		appiumSettings.setCapability("platform", /*this.GetPlatform().toString()*/"Android");
//		appiumSettings.setCapability("platformVersion", /*this.GetPlatformVersion()*/"9");//Android 9
//		appiumSettings.setCapability("platformVersion", /*this.GetPlatformVersion()*/"6.0");//htcm8
//		appiumSettings.setCapability("platformVersion", /*this.GetPlatformVersion()*/"6.0.1");//S7
		appiumSettings.setCapability("platformVersion", /*this.GetPlatformVersion()*/"7.0");//S8 150
//		appiumSettings.setCapability("platformVersion", /*this.GetPlatformVersion()*/"8.1.0");
//		appiumSettings.setCapability("platformVersion", /*this.GetPlatformVersion()*/"7.1.1"); //nexus 6p 170 offset
//		appiumSettings.setCapability("platformVersion", /*this.GetPlatformVersion()*/"7.1.2"); //pixel 1 
//		appiumSettings.setCapability("platformVersion", /*this.GetPlatformVersion()*/""); //pixel 1 trstn
		appiumSettings.setCapability("deviceName", /*this.GetDeviceName()*/"Galaxy s7");
		//appiumSettings.setCapability("avd", /*this.GetAVD()*/ "Nexus6P"); //removed to avoid emulation
		appiumSettings.setCapability("app", /*this.GetApp()*/"C:\\Users\\WoodmDav\\localDocuments\\myCode\\GIT\\Nasa Test Engine 1\\Nasa Test Engine 1\\src\\main\\resources\\Android_AppUITesting.apk");
		appiumSettings.setCapability("automationName", "UiAutomator2");
		
		//David: run directly from local app, might run faster?
		//com.ELXSmart
		//com.ELXSmart.ELXSmart
//		appiumSettings.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.ELXSmart");
//		appiumSettings.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ELXSmart.ELXSmart");
		
		
		
		
//		//FOR TESTSTUFF
		return appiumSettings;
//		DesiredCapabilities appiumSettings = new DesiredCapabilities();
//		appiumSettings.setCapability("platform", /*this.GetPlatform().toString()*/"Android");
//		appiumSettings.setCapability("platformVersion", /*this.GetPlatformVersion()*/"7.1.2");
//		appiumSettings.setCapability("deviceName", /*this.GetDeviceName()*/"Galaxy s7");
//		//appiumSettings.setCapability("avd", /*this.GetAVD()*/ "Nexus6P");
//		//appiumSettings.setCapability("app", /*this.GetApp()*/"C:\\Users\\WoodmDav\\Documents\\Android_AppUITesting.apk");
//		appiumSettings.setCapability("automationName", "UiAutomator2");
//		
//		//David: run directly from local app, might run faster?
//		//com.ELXSmart
//		//com.ELXSmart.ELXSmart
//		appiumSettings.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.apps.chromecast.app");
//		appiumSettings.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.apps.chromecast.app.core.MainActivity");
	}
	
	
	
	
}

