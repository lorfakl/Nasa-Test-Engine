package main.java.nasaTestSuite;

import io.appium.java_client.android.AndroidDriver;

public class Appliance {
	private static AndroidDriver driver;
	public Appliance(AndroidDriver driver) {
		this.driver = driver;
	}
}
