package main.java.nasaTestSuite;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Appliance {
	public final int OPEN_WAIT = 120;
	public final int UPDATE_WAIT = 240;
	public final int POWER_SECS = 20;
	public final int BUTTON_WAIT = 20;
	public final int SIGN_IN_WAIT = 120;
	public final int TOGGLE_SECS = 2000;//ms
	
	protected static FrigiDriver d;
	public Appliance(FrigiDriver driver) 
	{
		this.d = driver;
	}

	public void UpdateApp() 
	{
		System.out.println("UNIMPLEMENTED");
	}
	
	public void typeField(String xpath, String text) 
	{		
		WebElement elem = d.findByXPath(xpath, BUTTON_WAIT);
		elem.clear();
		elem.sendKeys(text);
	}
	
	public void typePassword(String password) 
	{
		d.tapByXPath(MyXPath.passField, BUTTON_WAIT);
		WebElement elem = d.findByXPath(MyXPath.passField, BUTTON_WAIT);
		elem.clear();
		elem.sendKeys(password);
	}
	
	public void signIn(String email, String password) 
	{
		//TODO move the email.clear into here
		typeField(MyXPath.emailField, email);
		typePassword(password);
		d.tapByXPath(MyXPath.signInTwo);	
		System.out.println("Signed into " + email);
	}
	
	public void signOut() 
	{
		System.out.println("SIGN OUT");
		d.tapByXPath(MyXPath.backButton, BUTTON_WAIT);
		openSettings();
		System.out.println("native scroll");
		d.useNativeContext();
		d.scrollDown();
		d.useWebContext();
		d.tapByXPath(MyXPath.signOutButton, BUTTON_WAIT);
		d.tapByXPath(MyXPath.signInOne, BUTTON_WAIT);
	}
	
	public void openSettings()
	{
		d.tapByXPath(MyXPath.settingsButton, BUTTON_WAIT);
	}

	public boolean isPowerOn() 
	{
		boolean powerOn = !d.searchForText("Off", MyXPath.offStatus, BUTTON_WAIT);
		System.out.println("isPowerOn: " + powerOn);
		return powerOn;
	}
	
	public void openControls(String applianceName) 
	{
		//TODO implement map navigation
		//Tap Back
		d.tapByXPath(MyXPath.backButton, d.BUTTON_WAIT);
		d.tapByXPath(MyXPath.getListApplianceName(applianceName), d.BUTTON_WAIT);
		
	}
}
