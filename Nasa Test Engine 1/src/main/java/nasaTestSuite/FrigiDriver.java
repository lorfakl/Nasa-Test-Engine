package main.java.nasaTestSuite;

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

public class FrigiDriver 
{

	public final int OPEN_WAIT = 120;
	public final int UPDATE_WAIT = 240;
	public final int POWER_SECS = 20;
	public final int BUTTON_WAIT = 20;
	public final int SIGN_IN_WAIT = 120;
	
	int oneMinute = 60;

	private URL testServerAddress; 
	private static AndroidDriver driver; //David: why is this static?
	private boolean boolAppStart = false;
	private boolean boolAppUpdated = false;
	private String name = "default";
	
	public Dehum dhum;

	public void startApp(int implicitTime) 
	{
		TestCapabilities testCap = new TestCapabilities();
		testCap.AssignAppiumCapabilities();
		try 
		{
			testServerAddress = new URL("http://localhost:4723/wd/hub");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("StepDef StartApp() CAUGHT: Failed to load URL");
		}
		DesiredCapabilities appiumSettings = testCap.AssignAppiumCapabilities();
		try 
		{
			driver = new AndroidDriver<MobileElement>(testServerAddress, appiumSettings);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			driver.quit();
			System.out.println("StepDef StartApp() CAUGHT: Failed to initialize AndroidDriver driver");
		}
		System.out.println("implicit wait may be broken according to appium forum");
		driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);
		boolAppStart = true;
		
		//TODO add logic for list of appliances based on configurations
		//this.dhum = new Dehum(driver, implicitTime);
	}
//	
//	public FrigiDriver(int implicitTime) {
//		TestCapabilities testCap = new TestCapabilities();
//		testCap.AssignAppiumCapabilities();
//		try 
//		{
//			testServerAddress = new URL("http://localhost:4723/wd/hub");
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			System.out.println("StepDef StartApp() CAUGHT: Failed to load URL");
//		}
//		DesiredCapabilities appiumSettings = testCap.AssignAppiumCapabilities();
//		try 
//		{
//			driver = new AndroidDriver<MobileElement>(testServerAddress, appiumSettings);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			driver.quit();
//			System.out.println("StepDef StartApp() CAUGHT: Failed to initialize AndroidDriver driver");
//		}
//		
//		driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);
//		boolAppStart = true;
//		
//		//TODO add logic for list of appliances based on configurations
//		this.dhum = new Dehum(driver, implicitTime);
//	}
	
	public void useWebContext() {
		Set<String> contextNames = driver.getContextHandles();
		String webView = contextNames.toArray()[1].toString();
		System.out.println("Switching to web view: " + webView);
		driver.context(webView);
	}
	
	public void useNativeContext() {
		System.out.println("Switching to native view");
		driver.context("NATIVE_APP");
	}

	public void UpdateApp() 
	{
		Boolean looping = true;
		while(looping) 
		{ //David: removed wait time
			try 
			{
				WebElement updateButton = driver.findElementById("android:id/button2");
				updateButton.click();
				looping = false;
			}
			catch(Exception e) 
			{
				//David: This will loop for infinity if the update button never shows up
			}
		}
		
		boolAppUpdated = true;
	}
	public void clickSignIn1() 
	{
		try 
		{
			boolean looping = true;
			while(looping) 
			{
				MobileElement result = grabFromClass("android.widget.Button",0, driver);
				result.click();
				looping = false;
			}
		}
		catch(Exception e) 
		{
			System.out.println("looking for sign in");
		}
	}
	
	public void switchToWebView() {
		Set<String> availableContexts = driver.getContextHandles();
//		System.out.println("Total No of Context Found After we reach to WebView = " + availableContexts.size());
		for (String context : availableContexts) {
			System.out.println("Checking: " + context);
			if (context.contains("WEBVIEW")) {
				System.out.println("Switching to: " + context);
				driver.context(context);
				break;
			}
		}
	}
	//TODO FIND METHODS THAT DO NOT FIT INTO THE "APPLIANCE SUPERTYPE" DEFINITION
	public void typeEmail() 
	{
		myWaitXPath(MyXPath.emailField, oneMinute);
		WebElement elem = findByXPath(MyXPath.emailField, false, driver);
		elem.sendKeys("eluxtester1@gmail.com");
	}
	
	public void typePassword() 
	{
		myWaitXPath(MyXPath.passField, oneMinute);
		WebElement elem = findByXPath(MyXPath.passField, false, driver);
		elem.sendKeys("123456");
	}
	
	//Ended up not using this, don't delete for now
	public String checkScreen() 
	{
		//NO APPLIANCES: add-appliance (ID)
		//RAC: 
		for(int i = 0; i < 100; i++) {
			WebElement element = null;
			try {//Mean no appliances added
				element = driver.findElementById("com.ELXSmart:id/add-appliance");
				return "ProvisionScreen";
			}catch(Exception e) {
				System.out.println("ProvisionScreen null");
			}
			try {//means we're on dehum screen
				element = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[1]/android.view.View[1]");
				System.out.println("xpath works123");
				System.out.println("element get Text: " + element.getText());
				return "DehumScreen";
			}catch(Exception e) {
				System.out.println("DehumScreen null");
			}
			try {//means we're appliance list (after hitting back button)
				element = driver.findElementByXPath("	/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[4]/android.view.View[2]/android.widget.ListView");
				System.out.println("xpath works123");
				System.out.println("element get Text: " + element.getText());
				return "ApplianceListScreen";
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("ApplianceListScreen null");
			}
		}
		//TODO add rac and strombo. try to decrease the i of for loop or replace with waitFor. Try to add navigate functionality
		return null;//shouldn't get here. 
	}
	
	//srt: JIHAD'S HELPER METHODS
	private MobileElement grabFromClass(String className,int index, AndroidDriver d)
	{
		List<MobileElement> results = null;
		boolean looping = true;
		while(looping) {
			try {
				results = d.findElementsByClassName(className);
				if(results.size()>0) 
				{

					print("Size of " + className + " Elements: " + results.size());
					looping = false;
				}
			}catch(NullPointerException e) {
				print("Looking for button by class: " + className);
				//loops forever if button isn't there
			}
		}
		return results.get(index);
	}
	
	public void clickByXpath(String xPath, int waitSecs){
		myWaitXPath(xPath, waitSecs);
		try {
			WebElement elem = findByXPath(xPath, false, driver);
			elem.click();
		}catch(NullPointerException e){
			System.out.println("Failed to find XPath: " + xPath);
		}
	}
	
	public void myWaitXPath(String xPath, int waitSecs) 
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitSecs);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
		}catch (TimeoutException e) {
			System.out.println("XPath Failed: " + xPath);
			System.out.println("Timed out after " + waitSecs + " second(s)");
		}
	}
	
	public void myWaitText(String text, int waitSecs) 
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitSecs);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+ text +"\")")));
		}catch (TimeoutException e) {
			System.out.println("Timed out after " + waitSecs + " second(s)");
		}
	}
	
	public WebElement findByID(String id, boolean looping, AndroidDriver d)
	{
		WebElement result = null;
		if(looping == true) {
			while(looping) {
				try
				{
					result = d.findElementById("com.ELXSmart:id/"+id);
				}
				catch(Exception e)
				{
					print("Failed to find Element with ID:" + id);
				}
			}
		}else {
			try
			{
				result = d.findElementById("com.ELXSmart:id/"+id);
			}
			catch(Exception e)
			{
				print("Failed to find Element with ID:" + id);
			}
		}
		return result;
		
	}
	
	public void clickBackButton() 
	{
		System.out.println("Press back button please");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Times up.");
	}
	
	public void clickOffSettings() 
	{
		//meant to open settings menu while appliance is off
		System.out.println("WARNING: clickOffSettings() not yet implemented");
	}
	
	public void clickOnSettings()
	{
		//meant to open settings menu while appliance is on
		System.out.println("WARNING: clickOnSettings() not yet implemented");
	}
	
	public WebElement findByXPath(String xpath, boolean looping, AndroidDriver d)
	{
		WebElement result = null;
		if(looping == true) {
			while(looping) {
				try
				{
					result = d.findElementById(xpath);
				}
				catch(Exception e)
				{
					print("Failed to find Element with xPath:" + xpath);
				}
			}
		}else {
			try
			{
				result = d.findElementByXPath(xpath);
			}
			catch(Exception e)
			{
				print("Failed to find Element with xPath:" + xpath);
			}
		}
		return result;
		
	}
	

	public WebElement findByXPath(String xPath, int waitSecs)
	{
		myWaitXPath(xPath, waitSecs);
		try {
			WebElement elem = findByXPath(xPath, false, driver);
			return elem;
		}catch(NullPointerException e){
			System.out.println("Failed to find XPath: " + xPath);
		}
		return null;
	}
	
//	public WebElement findByXPath(String xpath, boolean looping, AndroidDriver driver)
//	{
//		myWait(xpath, BUTTON_WAIT);
//		return driver.findElementById(xpath);
//	}
	
//	public WebElement findByXPath(String xpath)
//	{
//		myWaitXPath(xpath, BUTTON_WAIT);
//		return driver.findElementById(xpath);
//	}
	
	
	private void switchWifi(String ssid)
	{
		String filePath = new File("").getAbsolutePath();
		filePath = filePath.concat("\\nasaTestSuite\\");
		//Print(filePath);
		
		String exeCommand = "cmd /c start WifiSwitcher.exe";
		String cmdLineArg = " \"" + ssid + "\"";
		String fullCommand = exeCommand + cmdLineArg;
		System.out.println(fullCommand);
		
		try 
		{	
			Runtime.getRuntime().exec(fullCommand, null, new File(filePath));
			System.out.println("Execute!");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void Sleep(int milli)
	{
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void print(String msg)
	{
		System.out.println(msg);
	}
	
	//TODO redesign think so that appium looks for a thinking element before each click rather than waiting after a click
	//There is potential for designing an abstract button class with code that comes with each button. Either that or add stuff to the tap methods
	/**
	 * Stops the driver while the app is thinking
	 */
	public void thinkWait() 
	{	
		System.out.println(1);
		myWaitXPath(MyXPath.thinking,30);
		System.out.println(2);
		try {
			WebElement thinking = driver.findElementByXPath(MyXPath.thinking);
			System.out.println(3);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			System.out.println(4);
			wait.until(ExpectedConditions.invisibilityOf(thinking));
			System.out.println(5);
		}catch(Exception e) {
			System.out.println(6);
			e.getMessage();
			System.out.println("CAUGHT ERROR: Thinking Stale Reference");
		}
		System.out.println(7);
	}
	
	public boolean searchForXPath(String xPath, int wait) 
	{
		boolean result = false;
		try {
			myWaitXPath(xPath, wait);
			WebElement elem = findByXPath(xPath, false, driver);
			result = true;
		}catch(WebDriverException e) {
			e.getMessage();
			System.out.println("XPath not found: " + xPath);
			System.out.println("Did that print twice?");
		}
		return result;
	}
	
	public boolean searchForText(String text, int wait) 
	{
		System.out.println("Searching for: " + text);
		boolean result = false;
		try {
			myWaitText(text, wait);
			WebElement elem = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+ text +"\")");
			result = true;
			System.out.println("Found text: " + text);
		}catch(WebDriverException e) {
			//e.printStackTrace();
			System.out.println("Not Found in Search: " + text);
		}
		return result;
	}

	public void tapByXPath(String xPath, int waitSecs) {
		myWaitXPath(xPath, waitSecs);
		WebElement elem = null;
		try {
			elem = findByXPath(xPath, false, driver);
		}catch(NullPointerException e){
			System.out.println("Failed to find XPath: " + xPath);
		}
		if(elem != null) {
			tapOnElement(elem);
		} else {
			System.out.println("Problem tapping xpath: " + xPath);
		}
	}
	
	public void tapOnElement(WebElement element){
		float[] elementLocation = getElementCenter(element);
		int elementCoordinateX, elementCoordinateY; 
		elementCoordinateX = Math.round(elementLocation[0]);
		elementCoordinateY = Math.round(elementLocation[1]);
		driver.context("NATIVE_APP");
//		TouchAction action = new TouchAction(driver);
//		action.tap(elementCoordinateX, elementCoordinateX).perform();
		MobileDriver mDriver = driver;
		new TouchAction(mDriver).tap(PointOption.point(elementCoordinateX, elementCoordinateY)).perform();

//		ActionParameter action = new ActionParameter("longPress", LongPressOptions());
//	    parameterBuilder.add(action);
	    //noinspection unchecked
	    
		useWebContext();
	}

	//My changes: offset
	public float[] getElementCenter(WebElement element){
		System.out.println();
		System.out.println("Tapping element: " + element);
		useWebContext();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		// get webview dimensions
		Long webviewWidth  = (Long) js.executeScript("return screen.width");
		Long webviewHeight = (Long) js.executeScript("return screen.height");
		System.out.println("webview width: " + webviewWidth);
		System.out.println("webview height: " + webviewHeight);
		// get element location in webview
		int elementLocationX = element.getLocation().getX();
		int elementLocationY = element.getLocation().getY();
		System.out.println("elementLocationX: " + elementLocationX);
		System.out.println("elementLocationY: " + elementLocationY);
		// get the center location of the element
		int elementWidthCenter = element.getSize().getWidth() / 2;
		int elementHeightCenter = element.getSize().getHeight() / 2;
		int elementWidthCenterLocation = elementWidthCenter + elementLocationX;
		int elementHeightCenterLocation = elementHeightCenter + elementLocationY;
		// switch to native context
		driver.context("NATIVE_APP");
		float deviceScreenWidth, deviceScreenHeight;
		// offset
		int s8offset = 160;//used to be 115
		// get the actual screen dimensions
		deviceScreenWidth  = driver.manage().window().getSize().getWidth();
		deviceScreenHeight = driver.manage().window().getSize().getHeight();
		System.out.println("deviceScreenWidth: " + deviceScreenWidth);
		System.out.println("deviceScreenHeight: " + deviceScreenHeight);
		// calculate the ratio between actual screen dimensions and webview dimensions
		float ratioWidth = deviceScreenWidth / webviewWidth.intValue();
		float ratioHeight = deviceScreenHeight / webviewHeight.intValue();
		// calculate the actual element location on the screen
		float elementCenterActualX = elementWidthCenterLocation * ratioWidth;
		float elementCenterActualY = (elementHeightCenterLocation * ratioHeight) + s8offset;
		System.out.println("elementCenterActualX: " + elementCenterActualX);
		System.out.println("elementCenterActualY: " + elementCenterActualY);
		System.out.println();
		float[] elementLocation = {elementCenterActualX, elementCenterActualY};
		// switch back to webview context
		useWebContext();
		return elementLocation;
	}
	
	////TEST METHODS////
	public void testPowerOn() 
	{
		//appliance.openControls(this.getName());//ASSUME FOR NOW YOU'RE JUST GOING TO BE ON STROMBOLI SCREEN
		printStartTest("Power on function");
		if(this.isPowerOn()) {//if power is on turn it off so we can test power on function
			System.out.println("Appliance was already on. Powering down to verify test.");
			tapByXPath(MyXPath.powerOffButton, BUTTON_WAIT);
		}
		tapByXPath(MyXPath.powerOnButton, BUTTON_WAIT);

		//verify
		if(this.isPowerOn()) {
			printEndTest("Power on function", "PASS");
		}else{
			printEndTest("Power on function", "FAIL");
			fail();
		}
	}
	
	
	
	public void printStartTest(String testName) 
	{
		System.out.println("\n\n");
		System.out.println("==========================================================================");
		System.out.println("Starting Test - " + testName);
		System.out.println("==========================================================================");
	}
	
	public void printEndTest(String testName, String result) 
	{
		System.out.println("==========================================================================");
		System.out.println("End Result - " + testName + ": " + result);
		System.out.println("==========================================================================");
		System.out.println();
	}
	/**
	 * TODO Currently set up to check name, change name, and verify name all withing settings menu
	 * Need to implement back button and check name on the CONTROL screen rather than just the settings menu page
	 */
	public void testChangeName() {
		printStartTest("Change Name");
		openSettings();
		
		WebElement currentNameElem = findByXPath(MyXPath.dehumNameValue, false, driver);
		String prevName = currentNameElem.getText();
		System.out.println("Previous Name: " + prevName);
		
		currentNameElem.sendKeys("strombo renamed");
		
		String expectedName = "strombo renamed";
		String actualName = currentNameElem.getText();
		
		System.out.println("Expected name: " + expectedName);
		System.out.println("Actual name: " + actualName);
		if(actualName.equals(expectedName)) {
			printEndTest("Change Name", "PASS");
		}else {
			printEndTest("Change Name", "FAIL");
			fail();
		}
	}
	////ACTIONS////
	public void openSettings(){
		clickByXpath(MyXPath.settingsButton, BUTTON_WAIT);
	}
	
//	//David: I plan on abstracting this class at a later date, but I don't want to break anything right now
//	public void powerButton() 
//	{
//		boolean powerOn = isPowerOn();
//		//assumes isPowerOn() has been used at the start of testing - David
//		clickByXpath(MyXPath.powerButton, POWER_SECS);
//		if(powerOn) 
//		{
//			System.out.println(this.getName() + " powering down");
//		}
//		else 
//		{
//			System.out.println(this.getName() + " powering on");
//		}
//		thinkWait();
//	}
	
	////GETTERS SETTERS////
	
//	public boolean isPowerOn() 
//	{
//		boolean powerOn = !searchForText("Off", BUTTON_WAIT);
//		System.out.println("isPowerOn: " + powerOn);
//		return powerOn;
//	}

	public boolean isPowerOn() 
	{
		boolean powerOn;
		try {
			findByXPath(MyXPath.powerOnButton, BUTTON_WAIT).isDisplayed();
			powerOn = true;	
		}catch(Exception e) {
			powerOn = false;
			System.out.println(e);
		}
		System.out.println("isPowerOn: " + powerOn);
		return powerOn;
	}
	
	public AndroidDriver getDriver() 
	{
		return driver;
	}
	public String getName() 
	{
		return name;
	}

	public boolean isBoolAppStart() 
	{
		return boolAppStart;
	}
	public void setBoolAppStart(boolean boolAppStart) 
	{
		this.boolAppStart = boolAppStart;
	}
	public boolean isBoolAppUpdated() 
	{
		return boolAppUpdated;
	}
	public void setBoolAppUpdated(boolean boolAppUpdated) 
	{
		this.boolAppUpdated = boolAppUpdated;
	}
}
