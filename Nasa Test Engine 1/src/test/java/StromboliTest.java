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
	    WebElement signInButton = strombo.findByXpath("//button[contains(@class,\"sign-in--button\")]", (oneMinute*2));
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
//		frigi.clickByXpath(MyXPath.emailField, oneMinute);
//		frigi.typeEmail();
//		frigi.clickByXpath(MyXPath.passField, oneMinute);
//		frigi.typePassword();
//		frigi.clickByXpath(MyXPath.signInTwo, oneMinute);
//		System.out.println("PASS: Sign In");
//		strombo.thinkWait();
//		strombo.isPowerOn();
//	    System.out.println("App Launched");
//	    System.out.println();
	}
	
	@Test
	public void powerOn() 
	{
		for(int i = 0; i < 3; i++) {
			strombo.testPowerOn();
		}
	}
	
	@Test
	public void tempUp() 
	{
		strombo.printStartTest("Temperature Up");
		if(!strombo.isPowerOn()) {
			strombo.powerButton();
		}
		strombo.refreshTargTemp();
		int expectedTemp = strombo.getTargTemp() + 1;
		strombo.clickTempPlus();
		strombo.refreshTargTemp();
		
		System.out.println("Temp: " + strombo.getTargTemp());
		System.out.println("Expected: " + expectedTemp);
		if(expectedTemp == strombo.getTargTemp()) 
		{
			strombo.printEndTest("Temperature Up", "PASS");
		} 
		else 
		{
			strombo.printEndTest("Temperature Up", "FAIL");
			fail();
		}
	}
	
	@Test
	public void modeUp() 
	{
		strombo.printStartTest("Mode Up");
		
		if(!strombo.isPowerOn()) {
			strombo.powerButton();
		}
		strombo.refreshMode();
		int expectedMode = strombo.getMode() + 1;
		if(expectedMode == 4) 
		{
			expectedMode = 0;//account for going from Cool back to Econ (full cycle)
		}
		strombo.clickModeUp();
		strombo.refreshMode();

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

	
	@Test public void changeName() {
		strombo.testChangeName();
	}
	
	@Test public void webViewTest() {
		AndroidDriver tempDriver = strombo.getDriver();

		//do some web testing
		try {
			Set<String> contextNames = tempDriver.getContextHandles();
			for (String contextName : contextNames) {
			    System.out.println(contextNames); //prints out something like NATIVE_APP \n WEBVIEW_1
			}
			tempDriver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1
			
			WebElement myText = tempDriver.findElement(By.cssSelector("hidden"));
			WebElement myText2 = tempDriver.findElement(By.cssSelector("visible"));
			
		}catch (Exception e){
			System.out.println("well that didn't go so well");
		}

		tempDriver.context("NATIVE_APP");

	}

//	@Test 
//	public void speedUp() 
//	{
//		strombo.refreshSpeed();
//		int expectedSpeed = strombo.getSpeed() + 1;
//		strombo.clickSpeedUp();
//		strombo.refreshSpeed();
//		if(expectedSpeed == 4) 
//		{
//			expectedSpeed = 0; //Auto goes to Low (end cycle)
//		}
//		System.out.println("Speed: " + strombo.getSpeed());
//		System.out.println("Expected: " + strombo.getSpeed());
//		
//		if(expectedSpeed == strombo.getSpeed()) 
//		{
//			//pass
//		}
//		else 
//		{
//			fail();
//		}
//	}
	
}
