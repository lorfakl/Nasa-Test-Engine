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
	public Appliance(FrigiDriver driver) {
		this.d = driver;
	}

	public void UpdateApp() 
	{
		System.out.println("UNIMPLEMENTED");
	}
	
	public void tapButton(String buttonXPath, String outputMsg) //Tap a button and have a print out
	{
	    d.tapByXPath(buttonXPath, BUTTON_WAIT);
	    System.out.println(outputMsg);
	}
	
	public void tapButton(String buttonXPath) //Overloaded Tap Button if you do not wish to print out
	{
		d.tapByXPath(buttonXPath, BUTTON_WAIT);
	}
	
	public void signIn(String email, String password) 
	{
		d.typeInField(MyXPath.emailField, email);
		d.typeInField(MyXPath.passField, password);
		tapButton(MyXPath.signInTwo, "Second Sign in button pressed");		
	}
	
	public void openSettings()
	{
		d.tapByXPath(MyXPath.settingsButton, BUTTON_WAIT);
	}

	////ACTIONS////
	
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
		boolean powerOn = !d.searchForText("Off", BUTTON_WAIT);
		System.out.println("isPowerOn: " + powerOn);
		return powerOn;
	}
	
	public void openControls(String applianceName) 
	{
		//TODO implement map navigation
		//Tap Back
		d.tapOnElement(d.findByXPath(MyXPath.backButton, d.BUTTON_WAIT)); 
		//Tap Strombo in list
		d.tapOnElement(d.findByXPath(MyXPath.getListApplianceName(applianceName), d.BUTTON_WAIT));
//		tapOnElement(findByXPath(MyXPath.plainPowerButton, BUTTON_WAIT));trying to get power to work
		
	}
}
