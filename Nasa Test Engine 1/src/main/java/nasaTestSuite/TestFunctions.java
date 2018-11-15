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
	FrigiDriver frigiDriver;
	
	//TODO fix this so that the FrigiDriver methods are established in either TestFunctions or in FrigiDriver
	public TestFunctions(FrigiDriver frigiDriver, AndroidDriver driver){
		this.frigiDriver = frigiDriver;
		this.driver = driver;
	}
	/**
	 * TODO change to static later?
	 * TODO Currently set up to check name, change name, and verify name all withing settings menu
	 * Need to implement back button and check name on the CONTROL screen rather than just the settings menu page
	 */
	public void testChangeName() {
		FrigiDriver.printStartTest("Change Name");
		
		WebElement currentNameElem = frigiDriver.findByXPath(MyXPath.applianceNameField, false, driver);
		
		String prevName = currentNameElem.getAttribute("value");
		System.out.println("Previous Name: " + prevName);
		String expectedName = prevName + " renamed";
		currentNameElem.clear();
		currentNameElem.sendKeys(expectedName);
		String actualName = currentNameElem.getAttribute("value");
		
		System.out.println("Expected name: " + expectedName);
		System.out.println("Actual name: " + actualName);

		currentNameElem.clear();
		currentNameElem.sendKeys(prevName);
		if(actualName.equals(expectedName)) {
			FrigiDriver.printEndTest("Change Name", "PASS");
		} else {
			FrigiDriver.printEndTest("Change Name", "FAIL");
			fail();
		}
	}

//    public static String cleanAirToggle = "//div[@id='cleanair-toggle']"; //if OFF class="toggle" when OFF     if ON class="toggle active"
	public void testCleanAir() {
		FrigiDriver.printStartTest("Clean Air");
		
		String expectedState = "";
		boolean success = true;
		WebElement cleanAirToggle = frigiDriver.findByXPath(MyXPath.cleanAirToggle, false, driver);
		
		String prevState = cleanAirToggle.getAttribute("class");
		System.out.println("Previous Toggle State: " + prevState);
		
		frigiDriver.tapByXPath(MyXPath.cleanAirToggle, TOGGLE_SECS);
		frigiDriver.thinkWait();
		//if OFF class="toggle" when OFF     if ON class="toggle active"
		if(prevState.equals("toggle")){
			//clean air was off, after tap it should be on
			System.out.println("Turning Clean Air ON");
			expectedState = "toggle active";
		} else if(prevState.equals("toggle")) {
			//clean air was on, after tap it should be off
			System.out.println("Turning Clean Air OFF");
			expectedState = "toggle";
		}else {
			System.out.println("UNEXPECTED CLEAN AIR TOGGLE STATE");
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
		
		frigiDriver.tapByXPath(MyXPath.cleanAirToggle, TOGGLE_SECS);
		frigiDriver.thinkWait();
		//if OFF class="toggle" when OFF     if ON class="toggle active"
		if(prevState.equals("toggle")){
			//clean air was off, after tap it should be on
			System.out.println("Turning Clean Air ON");
			expectedState = "toggle active";
		} else if(prevState.equals("toggle")) {
			//clean air was on, after tap it should be off
			System.out.println("Turning Clean Air OFF");
			expectedState = "toggle";
		} else {
			System.out.println("UNEXPECTED CLEAN AIR TOGGLE STATE");
		}
		actualState = cleanAirToggle.getAttribute("class");
		if(actualState.equals(expectedState)) {
			System.out.println("Clean Air 2nd result: PASS");
		} else {
			System.out.println("Clean Air 2nd result: FAIL");
			success = false;
		}
		
		
		
		if(success) {
			FrigiDriver.printEndTest("Clean Air", "PASS");
		} else {
			FrigiDriver.printEndTest("Clean Air", "FAIL");
			fail();
		}
	}
}
