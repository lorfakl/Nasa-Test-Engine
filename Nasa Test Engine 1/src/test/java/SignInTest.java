package test.java;

import org.junit.BeforeClass;
import org.junit.Ignore;

import main.java.nasaTestSuite.MyXPath;
import main.java.nasaTestSuite.Stromboli;
import main.java.nasaTestSuite.TestCapabilities;
import main.java.nasaTestSuite.TestFunctions;

import static org.junit.Assert.fail;

import java.net.URL;
import java.util.Set;

import org.apache.tools.ant.util.SymbolicLinkUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import main.java.nasaTestSuite.Appliance;
import main.java.nasaTestSuite.Dehum;
import main.java.nasaTestSuite.FrigiDriver;
import main.java.nasaTestSuite.MyXPath;

//@Ignore
public class SignInTest 
{
	static int oneMinute = 60;

	public static FrigiDriver frigi = null;
	public static Stromboli strombo = null;
	public static Appliance app = null;
	public static TestFunctions test = null;
	
	boolean testing = false;
	
	@BeforeClass//("^This code opens the app$")
	public static void setup()
	{
		try {
			frigi = new FrigiDriver(new URL("http://localhost:4723/wd/hub"), new TestCapabilities().AssignAppiumCapabilities(), 715); //was from 540-890
			System.out.println("temporarily removed update");
			frigi.useWebContext();	
			boolean calculatingOffset = false;
			app = new Appliance(frigi);
			test = new TestFunctions(frigi, app);	
			if(calculatingOffset) {
				frigi.tapByXPath(MyXPath.signInOne, frigi.BUTTON_WAIT);
				frigi.calculateOffset();				
			} else {
				app.tapSignInButton1();				
			}
			System.out.println("OFFSET: " + frigi.offset); //change offset back to protected later
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SignInTest");	
	}

	@Before
	public void resetErrors() {
		frigi.tapByXPath(MyXPath.backButton, frigi.BUTTON_WAIT);
		frigi.tapByXPath(MyXPath.signInOne, frigi.BUTTON_WAIT);
	}
	
	@Test
	public void Sign_In_Sign_Out() 
	{
		test.signInSignOutValidation();
	}
	
	@Test
	public void Empty_Email_Validation()
	{
		test.emptyEmailValidation();
	}
	
	@Test
	public void Empty_Password_Validation()
	{
		test.emptyPasswordValidation();
	}
	
	@Test
	public void Invalid_Email_Validation() 
	{
		test.printStartTest("Invalid Email Validation");
		test.invalidEmailValidation("eluxtester1@gmail.com", true);
		test.invalidEmailValidation("eluxtester1@gmail.", false);
		test.invalidEmailValidation("eluxtester1@.com", false);
	}
	
	@Test
	public void Short_Password_Validation()
	{
		test.shortPasswordValidation();
	}
	
	@Test
	public void Credential_Validation()
	{
		test.printStartTest("Credential Validation");
		test.credentialValidation("eluxtester1@gmail.com", "12345", false);
		System.out.println("Wrong pass Validated!");
		test.credentialValidation("wrongemail@gmail.com", "123456", false);
		System.out.println("Wrong email Validated!");
		//Unnecessary since the other test already checks correct credentials
		//test.credentialValidation("eluxtester1@gmail.com", "123456", true);
	}
	
	@Test
	public void forgotPassword() {
		test.printStartTest("Forgot Password");
		test.forgotPass("eluxtester1@gmail.com");
	}
	
	@Test
	public void showPassword() {
		test.showPass();
	}
}