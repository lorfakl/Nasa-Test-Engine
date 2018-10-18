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

public class StromboliTest 
{
	static int oneMinute = 60;
	
	public static FrigiDriver frigi = new FrigiDriver();
	public static Stromboli strombo = new Stromboli();
	@BeforeClass//("^This code opens the app$")
	public static void launchMyTest()
	{
		System.out.println("Launching App1");//delete later
		//this.frigi = new FrigiDriver(20); //David: param is implicit time THIS BROKE SO HARD  NULLPOINTER LATER ON AT SIGN IN CAUSE UNKNOWN. Found out it was being reset between scenarios.
		frigi.startApp(1000000);//huge debug wait, was originally 20 seconds
		
		strombo.setDriver(frigi.getDriver());  //David: used to start from frigi.startApp(), but I am trying to abstract that class
		System.out.println("temporarily removed update");
//		frigi.clickByXpath(MyXPath.updateButton, strombo.UPDATE_WAIT);
//		System.out.println("PASS: Start and update the app");

	    strombo.switchToWebView();
	    WebElement signInButton = strombo.findByXPath("//button[contains(@class,\"sign-in--button\")]", (oneMinute*2));
//	    System.out.println("Found SignInButton");
//	    strombo.useNativeContext();
//	    signInButton.click();
	    System.out.println("clicked");
	    strombo.tapOnElement(signInButton);
	    System.out.println("tapped");
//		WebElement signInButton = strombo.findByXPath("//button[contains(@class,\"sign-in--button\")]", true, strombo.getDriver());
//		signInButton.click();
//		System.out.println(signInButton);
	    System.out.println("Pass: new xpath click");
	    
//		frigi.clickByXpath(MyXPath.signInOne, 240);
		frigi.tapByXPath(MyXPath.emailField, oneMinute);
		frigi.typeEmail(); //using old xpath
		frigi.tapByXPath(MyXPath.passField, oneMinute);
		frigi.typePassword();//using old xpath
		frigi.tapByXPath(MyXPath.signInTwo, oneMinute);
		strombo.thinkWait();
		System.out.println("PASS: Sign In");
//		strombo.thinkWait();
//		strombo.isPowerOn();
	    System.out.println("App Launched");
	    System.out.println();
		strombo.openControls();
		strombo.thinkWait();
	}
	
//	@Test
//	public void powerOn() 
//	{
//		for(int i = 0; i < 3; i++) {
//			strombo.testPowerOn();
//		}
//	}
	
	@Test
	public void tempUp() 
	{
		if(!strombo.isPowerOn()) 
		{
			strombo.powerButton();
		}
		//Change mode until you reach a mode that can change the temperature
		if(strombo.getMode()==3 || strombo.getMode()==5) {
			strombo.clickModeUp();
		}
		int currentTemp = strombo.getTargTemp();
		System.out.println("Verify TargTemp = " + currentTemp);
		strombo.clickTempPlus();
		int expectedTemp = strombo.getTargTemp();
		System.out.println("Verify expectedTemp = " + expectedTemp);
		if(expectedTemp != (currentTemp +1)) {
			fail();
		}
	}
	
	@Test
	public void modeUp() 
	{
		strombo.printStartTest("Mode Up");
		
		if(!strombo.isPowerOn()) 
		{
			strombo.powerButton();
		}
		int expectedMode = strombo.getNextExpectedMode();
		strombo.clickModeUp();
		System.out.println("Mode: " + strombo.getMode());
		System.out.println("Expected: " + expectedMode);
		if(expectedMode == strombo.getMode()) 
		{
			strombo.printEndTest("Mode Up", "PASS");
		}else 
		{
			strombo.printEndTest("Mode Up", "FAIL");
			fail();
		}
	}

	
//	@Test public void changeName() {
//		strombo.testChangeName();
//	}
	
//	@Test public void webViewTest() {
//		AndroidDriver tempDriver = strombo.getDriver();
//
//		//do some web testing
//		try {
//			Set<String> contextNames = tempDriver.getContextHandles();
//			for (String contextName : contextNames) {
//			    System.out.println(contextNames); //prints out something like NATIVE_APP \n WEBVIEW_1
//			}
//			tempDriver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1
//			
//			WebElement myText = tempDriver.findElement(By.cssSelector("hidden"));
//			WebElement myText2 = tempDriver.findElement(By.cssSelector("visible"));
//			
//		}catch (Exception e){
//			System.out.println("well that didn't go so well");
//		}
//
//		tempDriver.context("NATIVE_APP");
//
//	}

	@Test 
	public void speedUp() 
	{
		strombo.printStartTest("Speed Up");

		if(!strombo.isPowerOn()) 
		{
			strombo.powerButton();
		}
		//Switch off dry mode
		if(strombo.getMode()==5) {
			strombo.clickModeUp();
		}
		int expectedSpeed = strombo.getNextExpectedSpeed();
		strombo.clickSpeedUp();
		System.out.println("Speed: " + strombo.getSpeed());
		System.out.println("Expected: " + expectedSpeed);
		if(expectedSpeed == strombo.getSpeed()) 
		{
			strombo.printEndTest("Speed Up", "PASS");
		}else 
		{
			strombo.printEndTest("Speed Up", "FAIL");
			fail();
		}
			
	}
	
}
