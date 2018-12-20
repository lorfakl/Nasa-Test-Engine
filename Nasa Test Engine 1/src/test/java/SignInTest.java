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
	
	@BeforeClass//("^This code opens the app$")
	public static void launchMyTest()
	{
		//Setup app
		System.out.println("StromboliTest2");//delete later
		try {
			frigi = new FrigiDriver(new URL("http://localhost:4723/wd/hub"), new TestCapabilities().AssignAppiumCapabilities());
		} catch (Exception e) {
			e.printStackTrace();
		}
		app = new Appliance(frigi);
		test = new TestFunctions(frigi, app);		
		System.out.println("temporarily removed update");
		frigi.useWebContext();	

		app.tapSignInButton1();
	}

	@Test
	public void Sign_In() 
	{
		//Sign in
		app.signIn("eluxtester1@gmail.com", "123456");
		System.out.println("PASS: Sign In");
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
	public void Wrong_Credentials_Validation()
	{
//		test.wrongCredentialsValidation("eluxtester1@gmail.com", true);
//		test.wrongCredentialsValidation("eluxtester1@", false);
//		test.wrongCredentialsValidation("eluxtester1@com", false);
//		test.wrongCredentialsValidation("@gmail.com", false);
//		test.wrongCredentialsValidation("eluxtester1", false);
//		test.wrongCredentialsValidation("eluxtes    ter1@gmail.com", false);
	}
}