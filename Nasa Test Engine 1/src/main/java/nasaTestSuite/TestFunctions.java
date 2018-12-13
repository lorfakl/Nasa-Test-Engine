package main.java.nasaTestSuite;

import static org.junit.Assert.fail;

import org.openqa.selenium.WebElement;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.io.SegmentedStringWriter;
import main.java.nasaTestSuite.TestCapabilities;
import main.java.nasaTestSuite.MyXPath;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.String;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import javax.swing.JOptionPane;
import javax.xml.xpath.XPathConstants;

import io.appium.java_client.android.AndroidDriver;

public class TestFunctions {
	public final int OPEN_WAIT = 120;
	public final int UPDATE_WAIT = 240;
	public final int POWER_SECS = 20;
	public final int TOGGLE_SECS = 2000;//ms
	public final int BUTTON_WAIT = 20;
	public final int SIGN_IN_WAIT = 120;
	
	int oneMinute = 60;
	
	AndroidDriver driver;
	FrigiDriver d;
	Appliance app;
	Stromboli strombo;
	
	//I think I might wanna just put all this stuff in "Appliance" or more specific appliances.
	//TODO fix this so that the FrigiDriver methods are established in either TestFunctions or in FrigiDriver
	public TestFunctions(FrigiDriver frigiDriver, Appliance app){
		this.app = app;
		this.d = frigiDriver;
		this.strombo = new Stromboli(frigiDriver);
	}
	/**
	 * TODO test may run faster if you just add some random character to the name instead of naming it back and forth
	 * TODO change to static later?
	 * TODO Currently set up to check name, change name, and verify name all withing settings menu
	 * Need to implement back button and check name on the CONTROL screen rather than just the settings menu page
	 */
	public void changeName() {
		printStartTest("Change Name");
		
		WebElement currentNameFieldElem = d.findByXPath(MyXPath.applianceNameField, BUTTON_WAIT);
		
		String prevName = currentNameFieldElem.getAttribute("value");
		System.out.println("Previous Name: " + prevName);
		String expectedName = prevName + " renamed";
		currentNameFieldElem.clear();
		currentNameFieldElem.sendKeys(expectedName);
		d.tapByXPath(MyXPath.backButton, BUTTON_WAIT);
		WebElement currentNameLabelElem = d.findByXPath(MyXPath.getControlApplianceName("Strombo"), BUTTON_WAIT);
		String actualName = currentNameLabelElem.getText();
		strombo.openSettings();
		
		System.out.println("Expected name: " + expectedName);
		System.out.println("Actual name: " + actualName);

		currentNameFieldElem = d.findByXPath(MyXPath.applianceNameField, BUTTON_WAIT);
		currentNameFieldElem.clear();
		currentNameFieldElem.sendKeys(prevName);
		if(actualName.equals(expectedName)) {
			printEndTest("Change Name", "PASS");
		} else {
			printEndTest("Change Name", "FAIL");
			fail();
		}
	}

	public void cleanAir() {
		printStartTest("Clean Air");
		
		String expectedState = "";
		boolean success = true;
		WebElement cleanAirToggle = d.findByXPath(MyXPath.cleanAirToggle, false, driver);
		
		String prevState = cleanAirToggle.getAttribute("class");
		System.out.println("Previous Toggle State: " + prevState);
		
		d.tapByXPath(MyXPath.cleanAirToggle, TOGGLE_SECS);
		d.thinkWait();
		//if OFF class="toggle" when OFF     if ON class="toggle active"
		if(prevState.equals("toggle")){
			//clean air was off, after tap it should be on
			System.out.println("Turning Clean Air ON");
			expectedState = "toggle active";
		} else if(prevState.equals("toggle active")) {
			//clean air was on, after tap it should be off
			System.out.println("Turning Clean Air OFF");
			expectedState = "toggle";
		}else {
			System.out.println("UNEXPECTED CLEAN AIR TOGGLE STATE: " + prevState);
		}
		String actualState = cleanAirToggle.getAttribute("class");
		if(actualState.equals(expectedState)) {
			System.out.println("Clean Air 1st result: PASS");
		}else {
			System.out.println("Clean Air 1st result: FAIL");
			success = false;
		}


		prevState = actualState;
		System.out.println("Previous Toggle State: " + prevState);
		
		d.tapByXPath(MyXPath.cleanAirToggle, TOGGLE_SECS);
		d.thinkWait();
		//if OFF class="toggle" when OFF     if ON class="toggle active"
		if(prevState.equals("toggle")){
			//clean air was off, after tap it should be on
			System.out.println("Turning Clean Air ON");
			expectedState = "toggle active";
		} else if(prevState.equals("toggle active")) { 
			//clean air was on, after tap it should be off
			System.out.println("Turning Clean Air OFF");
			expectedState = "toggle";
		} else {
			System.out.println("UNEXPECTED CLEAN AIR TOGGLE STATE: " + prevState);
		}
		actualState = cleanAirToggle.getAttribute("class");
		if(actualState.equals(expectedState)) {
			System.out.println("Clean Air 2nd result: PASS");
		} else {
			System.out.println("Clean Air 2nd result: FAIL");
			success = false;
		}
		
		
		
		if(success) {
			printEndTest("Clean Air", "PASS");
		} else {
			printEndTest("Clean Air", "FAIL");
			fail();
		}
	}

	public void sleepMode() {
		printStartTest("Sleep Mode");
		
		String expectedState = "";
		boolean success = true;
		WebElement sleepModeToggle = d.findByXPath(MyXPath.sleepModeToggle, false, driver);
		//STOPPING POINT
		String prevState = sleepModeToggle.getAttribute("class");
		System.out.println("Previous Toggle State: " + prevState);
		
		d.tapByXPath(MyXPath.sleepModeToggle, TOGGLE_SECS);
		d.thinkWait();
		//if OFF class="toggle" when OFF     if ON class="toggle active"
		if(prevState.equals("toggle")){
			//clean air was off, after tap it should be on
			System.out.println("Turning Sleep Mode ON");
			expectedState = "toggle active";
		} else if(prevState.equals("toggle active")) {
			//clean air was on, after tap it should be off
			System.out.println("Turning Sleep Mode OFF");
			expectedState = "toggle";
		}else {
			System.out.println("UNEXPECTED SLEEP TOGGLE STATE: " + prevState);
		}
		String actualState = sleepModeToggle.getAttribute("class");
		if(actualState.equals(expectedState)) {
			System.out.println("Sleep 1st result: PASS");
		}else {
			System.out.println("Sleep 1st result: FAIL");
			success = false;
		}


		prevState = actualState;
		System.out.println("Previous Toggle State: " + prevState);
		
		d.tapByXPath(MyXPath.sleepModeToggle, TOGGLE_SECS);
		d.thinkWait();
		//if OFF class="toggle" when OFF     if ON class="toggle active"
		if(prevState.equals("toggle")){
			//clean air was off, after tap it should be on
			System.out.println("Turning Sleep Mode ON");
			expectedState = "toggle active";
		} else if(prevState.equals("toggle active")) { 
			//clean air was on, after tap it should be off
			System.out.println("Turning Sleep Mode OFF");
			expectedState = "toggle";
		} else {
			System.out.println("UNEXPECTED SLEEP TOGGLE STATE: " + prevState);
		}
		actualState = sleepModeToggle.getAttribute("class");
		if(actualState.equals(expectedState)) {
			System.out.println("Sleep Mode 2nd result: PASS");
		} else {
			System.out.println("Sleep Mode 2nd result: FAIL");
			success = false;
		}
		
		
		
		if(success) {
			printEndTest("Sleep Mode", "PASS");
		} else {
			printEndTest("Sleep Mode", "FAIL");
			fail();
		}
	}
	
	public void timeZone() {
		int c = 0;
		d.tapByXPath(MyXPath.timeZoneOuterButton, BUTTON_WAIT);
		for(int i = 0; i <= 11; i++) {
			System.out.println(c++);
			d.tapByXPath(MyXPath.getTimeZoneInnerButton(i), BUTTON_WAIT);
			System.out.println(c++);
			d.tapByXPath(MyXPath.timeZoneOuterButton, BUTTON_WAIT);
			System.out.println(c++);
			WebElement checkedElem = d.findByXPath(MyXPath.timeZoneChecked, BUTTON_WAIT);
			System.out.println(c++);
			String idString = checkedElem.getAttribute("id");
			System.out.println(c++);
			int expected = i;
			System.out.println(c++);
			int actual = idString.charAt(idString.length()-1);
			System.out.println(c++);
			Assert.assertEquals(expected, actual);
			System.out.println(c++);
		}
		System.out.println(c++);
	}
	
	//should fail
	public void testAssertFail() {
		Assert.assertEquals(1,0);
	}

	//should pass
	public void testAssertPass() {
		Assert.assertEquals(1,1);
	}
	public void notification() {
		
	}	
	////TEST METHODS////
	public void testPowerOn() 
	{
		//appliance.openControls(this.getName());//ASSUME FOR NOW YOU'RE JUST GOING TO BE ON STROMBOLI SCREEN
		printStartTest("Power on function");
		if(app.isPowerOn()) {//if power is on turn it off so we can test power on function
			System.out.println("Appliance was already on. Powering down to verify test.");
			d.tapByXPath(MyXPath.plainPowerButton, BUTTON_WAIT);
		}
		d.tapByXPath(MyXPath.plainPowerButton, BUTTON_WAIT);

		//verify
		if(app.isPowerOn()) {
			printEndTest("Power on function", "PASS");
		}else{
			printEndTest("Power on function", "FAIL");
			fail();
		}
	}
		
	public static void printStartTest(String testName) 
	{
		System.out.println("\n\n");
		System.out.println("==========================================================================");
		System.out.println("Starting Test - " + testName);
		System.out.println("==========================================================================");
	}
	
	public static void printEndTest(String testName, String result) 
	{
		System.out.println("==========================================================================");
		System.out.println("End Result - " + testName + ": " + result);
		System.out.println("==========================================================================");
		System.out.println();
	}
}
