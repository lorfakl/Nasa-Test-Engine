package test.java;

import org.junit.BeforeClass;
import org.junit.Ignore;

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
//@Ignore
public class testyTest 
{
//	static int oneMinute = 60;
//	
//	public static FrigiDriver frigi = null;
//	public static Stromboli strombo = null;
//	public static Appliance app = null;
//	public static TestFunctions test = null;
//	@BeforeClass//("^This code opens the app$")
//	public static void launchMyTest()
	{
//		//Setup app
//		System.out.println("StromboliSettingsTest");//delete later
//		try {
//			frigi = new FrigiDriver(new URL("http://localhost:4723/wd/hub"), new TestCapabilities().AssignAppiumCapabilities());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		strombo = new Stromboli(frigi);
//		test = new TestFunctions(frigi, strombo);		
//		System.out.println("temporarily removed update");
//		frigi.useWebContext();
//		
//		//Sign in
//	    strombo.signIn("eluxtester1@gmail.com", "123456");
//		System.out.println("PASS: Sign In");
////		strombo.isPowerOn();
//	    System.out.println("App Launched");
//	    System.out.println();
//	    frigi.thinkWait();
//		strombo.openControls();
//		frigi.thinkWait();
//		System.out.println("ECON/COOL SETTINGS MODE");
//		if(!strombo.isPowerOn()) {
//			System.out.println("Turning on appliance");
//			frigi.tapByXPath(MyXPath.plainPowerButton, frigi.BUTTON_WAIT);
//		}
//		strombo.changeModeToCoolorEcon();
//		strombo.openSettings();
	}
	
	@Test
	public void testAssertFail() 
	{
//		fail();
	}
	
	@Test
	public void testAssertPass() 
	{
		System.out.println("pass");
	}
}