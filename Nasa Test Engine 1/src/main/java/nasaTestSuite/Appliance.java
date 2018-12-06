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
	
	public void tapSignInButton1() 
	{
	    WebElement signInButton = d.findByXPath(MyXPath.signInOne, BUTTON_WAIT);
	    d.tapOnElement(signInButton);
	    System.out.println("sign in button pressed");
	}
	
	public void typeEmail(String email) 
	{
		d.tapByXPath(MyXPath.emailField, BUTTON_WAIT);
		d.myWaitXPath(MyXPath.emailField, BUTTON_WAIT);
		WebElement elem = d.findByXPath(MyXPath.emailField, BUTTON_WAIT);
		elem.sendKeys(email);
	}
	
	public void typePassword(String password) 
	{
		d.tapByXPath(MyXPath.passField, BUTTON_WAIT);
		d.myWaitXPath(MyXPath.passField, BUTTON_WAIT);
		WebElement elem = d.findByXPath(MyXPath.passField, BUTTON_WAIT);
		elem.sendKeys(password);
	}
	
	public void tapSignInButton2() 
	{
	    WebElement signInButton = d.findByXPath(MyXPath.signInTwo, BUTTON_WAIT);
	    d.tapOnElement(signInButton);
	    System.out.println("sign in button pressed");
	}
	
	public void signIn(String email, String password) {
		tapSignInButton1();
		typeEmail(email);
		typePassword(password);
		tapSignInButton2();		
	}
	
	public void openSettings(){
		d.tapByXPath(MyXPath.settingsButton, BUTTON_WAIT);
	}
}
