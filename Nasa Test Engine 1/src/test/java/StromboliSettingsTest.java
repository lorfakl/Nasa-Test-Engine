package test.java;

import org.junit.BeforeClass;

import main.java.nasaTestSuite.MyXPath;
import main.java.nasaTestSuite.Stromboli;

import static org.junit.Assert.fail;

import java.util.Set;

import org.apache.tools.ant.util.SymbolicLinkUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import main.java.nasaTestSuite.Dehum;
import main.java.nasaTestSuite.FrigiDriver;
import main.java.nasaTestSuite.MyXPath;

public class StromboliSettingsTest 
{
	static int oneMinute = 60;
	
	public static FrigiDriver frigi = new FrigiDriver();
	public static Stromboli strombo = new Stromboli();
	@BeforeClass//("^This code opens the app$")
	public static void launchMyTest()
	{
		System.out.println("StromboliSettingsTest");//delete later
		//this.frigi = new FrigiDriver(20); //David: param is implicit time THIS BROKE SO HARD  NULLPOINTER LATER ON AT SIGN IN CAUSE UNKNOWN. Found out it was being reset between scenarios.
		frigi.startApp(1000000);//huge debug wait, was originally 20 seconds, this can be switched to infinite if needed
		
		strombo.setDriver(frigi.getDriver());  //David: used to start from frigi.startApp(), but I am trying to abstract that class
		System.out.println("temporarily removed update");
		
	    strombo.switchToWebView();
	    WebElement signInButton = strombo.findByXPath("//button[contains(@class,\"sign-in--button\")]", (oneMinute*2));
	    
	    strombo.tapOnElement(signInButton);
	    System.out.println("tapped");
	    
		frigi.tapByXPath(MyXPath.emailField, oneMinute);
		frigi.typeEmail(); //using old xpath
		frigi.tapByXPath(MyXPath.passField, oneMinute);
		frigi.typePassword();//using old xpath
		frigi.tapByXPath(MyXPath.signInTwo, oneMinute);
//		strombo.thinkWait();
		System.out.println("PASS: Sign In");
//		strombo.thinkWait();
//		strombo.isPowerOn();
	    System.out.println("App Launched");
	    System.out.println();
		strombo.thinkWait();
		strombo.openControls();
		strombo.thinkWait();
		strombo.openSettings();
//		strombo.thinkWait(); not needed?
	}
	
	//functional not passing
	@Test
	public void changeName() 
	{
		frigi.tests.testChangeName();
	}
	
	//untested
	@Test
	public void cleanAir() 
	{
		frigi.tests.testCleanAir();
	}

}