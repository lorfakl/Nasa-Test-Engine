package main.java.nasaTestSuite;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import main.java.nasaTestSuite.TestCapabilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import javax.swing.JOptionPane;
import javax.xml.xpath.XPathConstants;

import io.appium.java_client.android.AndroidDriver;

public class FrigiDriver {
	public final int OPEN_WAIT = 120;
	public final int UPDATE_WAIT = 240;
	public final int POWER_SECS = 30;
	public final int BUTTON_WAIT = 60;
	int oneMinute = 60;

	private URL testServerAddress; 
	private static AndroidDriver driver; //David: why is this static?
	private boolean boolAppStart = false;
	private boolean boolAppUpdated = false;
	
	public Dehum dhum;

	public void startApp(int implicitTime) {
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
	
	

	public void UpdateApp() {
		Boolean looping = true;
		while(looping) { //David: removed wait time
			try {
				WebElement updateButton = driver.findElementById("android:id/button2");
				updateButton.click();
				looping = false;
			}catch(Exception e) {
				//David: This will loop for infinity if the update button never shows up
			}
		}
		
		boolAppUpdated = true;
	}
	public void clickSignIn1() {
		try {
			boolean looping = true;
			while(looping) {
				MobileElement result = grabFromClass("android.widget.Button",0, driver);
				result.click();
				looping = false;
			}
		}catch(Exception e) {
			System.out.println("looking for sign in");
		}
	}
	
	public void typeEmail() {
		myWait(MyXPath.emailField, oneMinute);
		WebElement elem = findByXPath(MyXPath.emailField, false, driver);
		elem.sendKeys("eluxtester1@gmail.com");
	}
	
	public void typePassword() {
		myWait(MyXPath.passField, oneMinute);
		WebElement elem = findByXPath(MyXPath.passField, false, driver);
		elem.sendKeys("123456");
	}
	
//	//still needs work
	public void typeSignIn() {//David
		WebElement emailField = null;
		WebElement passField = null;

		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.emailField)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.passField)));
		
//		boolean looping = true;
//		while(looping) {
//			try {
//				emailField = FindByID("email", true, driver);
//				passField = FindByID("password", true, driver);
//				looping = false;
//				System.out.println("ID SUCCESS!!! :D");
//			}catch(Exception e){
//				//David: Loops forever if sign in fails
//			}
//		}
		
		 
		if(emailField == null || passField == null)
		{
			List<MobileElement> editableFields = driver.findElementsByClassName("android.widget.EditText");
			print("Result size " + editableFields.size());
			editableFields.get(0).sendKeys("eluxtester1@gmail.com");
			editableFields.get(0).click();
			editableFields.get(1).click();
			editableFields.get(1).sendKeys("123456");
		}else{
			emailField.sendKeys("eluxtester@gmail.com");
			emailField.click();
			passField.sendKeys("123456");
			passField.click();

			System.out.println("Find By ID is working");
		}
	}
	
	public void clickSignIn2() {
		WebElement signInButton = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.signInTwo)));
			signInButton = findByXPath(MyXPath.signInTwo, false, driver);
			signInButton.click();
		}catch(Exception e) {
			System.out.println("Looking for signin2");
		}
	}
	
	public String checkScreen() {
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
				if(results.size()>0) {

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
		myWait(xPath, waitSecs);
		WebElement elem = findByXPath(xPath, false, driver);
		elem.click();
	}
	
	public void myWait(String xPath, int waitSecs) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitSecs);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
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
//	public WebElement findByXPath(String xpath, boolean looping, AndroidDriver driver)
//	{
//		myWait(xpath, BUTTON_WAIT);
//		return driver.findElementById(xpath);
//	}
	
	public WebElement findByXPath(String xpath)
	{
		myWait(xpath, BUTTON_WAIT);
		return driver.findElementById(xpath);
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
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
	
	/**
	 * Stops the driver while the app is thinking
	 */
	public void thinkWait() {
		myWait(MyXPath.thinking,30);
		WebElement thinking = findByXPath(MyXPath.thinking, false, driver);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.invisibilityOf(thinking));
		}catch(WebDriverException e) {
			e.getMessage();
			System.out.println("CAUGHT ERROR: Thinking Stale Reference");
		}
	}
	
	
	
	
	//GETTERS SETTERS
	public AndroidDriver getDriver() {
		return driver;
	}
	public boolean isBoolAppStart() {
		return boolAppStart;
	}
	public void setBoolAppStart(boolean boolAppStart) {
		this.boolAppStart = boolAppStart;
	}
	public boolean isBoolAppUpdated() {
		return boolAppUpdated;
	}
	public void setBoolAppUpdated(boolean boolAppUpdated) {
		this.boolAppUpdated = boolAppUpdated;
	}
}
