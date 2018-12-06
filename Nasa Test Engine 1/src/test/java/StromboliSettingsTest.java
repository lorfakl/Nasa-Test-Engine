package test.java;

import org.junit.BeforeClass;

import main.java.nasaTestSuite.MyXPath;
import main.java.nasaTestSuite.Stromboli;
import main.java.nasaTestSuite.TestCapabilities;
import main.java.nasaTestSuite.TestFunctions;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.apache.tools.ant.util.SymbolicLinkUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import main.java.nasaTestSuite.Appliance;
import main.java.nasaTestSuite.Dehum;
import main.java.nasaTestSuite.FrigiDriver;
import main.java.nasaTestSuite.MyXPath;

public class StromboliSettingsTest 
{
	static int oneMinute = 60;
	
	public static FrigiDriver frigi = null;
	public static Stromboli strombo = null;
	public static Appliance app = null;
	public static TestFunctions test = null;
	@BeforeClass//("^This code opens the app$")
	public static void launchMyTest()
	{
		System.out.println("StromboliSettingsTest");//delete later
		//this.frigi = new FrigiDriver(20); //David: param is implicit time THIS BROKE SO HARD  NULLPOINTER LATER ON AT SIGN IN CAUSE UNKNOWN. Found out it was being reset between scenarios.
		try {
			frigi = new FrigiDriver(new URL("http://localhost:4723/wd/hub"), new TestCapabilities().AssignAppiumCapabilities());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//huge debug wait, was originally 20 seconds, this can be switched to infinite if needed
		strombo = new Stromboli(frigi);
		test = new TestFunctions(frigi);
//		strombo.setDriver(frigi.getDriver());  //David: used to start from frigi.startApp(), but I am trying to abstract that class
		System.out.println("temporarily removed update");
		
		frigi.useWebContext();
	    app = new Appliance(frigi);
	    app.signIn("eluxtester1@gmail.com", "123456");
//		strombo.thinkWait();
		System.out.println("PASS: Sign In");
//		strombo.thinkWait();
//		strombo.isPowerOn();
	    System.out.println("App Launched");
	    System.out.println();
	    frigi.thinkWait();
		strombo.openControls();
		frigi.thinkWait();
//		System.out.println("ECON/COOL SETTINGS MODE");
//		if(strombo.isPowerOn()) {
//			frigi.clickByXpath(MyXPath.powerButton, frigi.BUTTON_WAIT);
//		}
		strombo.changeModeToCoolorEcon();
		strombo.openSettings();
		
//		strombo.thinkWait(); not needed?
	}
	
	@Test
	public void testAssertFail() 
	{
		test.testAssertFail();
	}
	
	@Test
	public void testAssertPass() 
	{
		test.testAssertPass();
	}
	
	//functional and passing
	@Test
	public void changeName() 
	{
		test.changeName();
	}
	
	//functional
	@Test
	public void cleanAir() 
	{
		test.cleanAir();
	}
	
	//functional
	@Test
	public void sleepMode() 
	{
		test.sleepMode();
	}
//	@Test
//	public void timeZone() 
//	{
//		frigi.tests.testTimeZone();
//	}
//
//	@Test
//	public void noftification() 
//	{
//		frigi.tests.notificationTest();
//	}
}