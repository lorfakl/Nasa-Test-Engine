package main.java.nasaTestSuite;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import javax.swing.JOptionPane;
import io.appium.java_client.android.AndroidDriver;
//adding old imports from some utube vid
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTest 
{
	private static AndroidDriver driver;
	private URL testServerAddress; 
	
	public AppiumTest()
	{
		try 
		{
			testServerAddress = new URL("http://localhost:4723/wd/hub");
		}
		catch(Exception e)
		{
			System.out.println("Fuck Java!");
		}
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Nexus 6P");
		capabilities.setCapability("platformVersion", "8.1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("avd", "Nexus6P");
		capabilities.setCapability("app", "C:\\Users\\TayloJih\\Downloads\\Android_AppUITesting.apk");
		capabilities.setCapability("automationName", "UiAutomator2");
		//capabilities.setCapability("newCommandTimeout", "300");
		Print("Default dumb things");
		driver = new AndroidDriver(testServerAddress, capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public AppiumTest(int implicitTime, TestCapabilities testCapabilities)
	{
		try 
		{
			testServerAddress = new URL("http://localhost:4723/wd/hub");
		}
		catch(Exception e)
		{
			System.out.println("Fuck Java!");
		}
		 //= new DesiredCapabilities();
		 DesiredCapabilities appiumSettings = testCapabilities.AssignAppiumCapabilities();
		try 
		{
			driver = new AndroidDriver<MobileElement>(testServerAddress, appiumSettings);
		}
		catch(Exception e)
		{
			Print("Some shit broke");
			Print(e.getMessage());
		}
		
		driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);

	}
	
	@SuppressWarnings("deprecation")
	public void RunProvision()
	{
		Point[] points = new Point[] { new Point(360, 2244), new Point(471, 388), new Point(463, 551), new Point(522, 967), new Point(669, 2251), new Point(74, 190), new Point(1303, 190), new Point(399, 1054), new Point(506, 721), new Point(669, 2251), new Point(669, 2251), new Point(669, 2251)  };
		
		Sleep(21000);
		
		//David: STEP ONE - UPDATE
		WebElement updateButton = driver.findElementById("android:id/button2");
		updateButton.click();
		
		//infoBox("Wait for the app to load");
		Print("Loading stuff");
		infoBox("I hate everything because it loads slowly");
		//Sleep(45000);
		
		//David: STEP TWO - Sign in
		MobileElement result = GrabFromClass("android.widget.Button",0, driver);
		result.click();
		
		WebElement emailField = FindByID("email", driver);
		WebElement passField = FindByID("password", driver);
		 
		if(emailField == null || passField == null)
		{
			List<MobileElement> editableFields = driver.findElementsByClassName("android.widget.EditText");
			Print("Result size " + editableFields.size());
			editableFields.get(0).sendKeys("eluxtester@gmail.com");
			editableFields.get(0).click();
			editableFields.get(1).click();
			editableFields.get(1).sendKeys("123456");
		}
		else
		{
			emailField.sendKeys("eluxtester@gmail.com");
			emailField.click();
			passField.sendKeys("123456");
			passField.click();
		}
		
		WebElement signInButton = FindByID("sign-in--button", driver);
		
		if(signInButton == null)
		{
			MobileElement button = GrabFromClass("android.widget.Button", 0, driver);
			button.click();
		}
		else
		{
			signInButton.click();
		}
		
		//David: STEP THREE - 
		
		Sleep(15000);
		WebElement addAppliance = FindByID("add-appliance", driver);
		if(addAppliance == null)
		{
			MobileElement element = GrabFromClass("android.widget.Button", 0, driver);
			Print(element.getAttribute("text"));
			//Print(element.getAttribute("id"));
			element.click();
		}
		else
		{
			addAppliance.click();
		}
		
		Sleep(2000);
		
		try
		{
			WebElement viewObjectWithId = driver.findElementByXPath("	/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[1]/android.view.View/android.view.View[1]");
			Print("Xpath works");
			viewObjectWithId.click();
		}
		catch(Exception e)
		{
			Tap(new Point(514, 745), driver, 2000);
			Print("Air Conditioner");
		}
		
		
		
		
		
		Print("Should be where the buttons at the bottom are");
		/*List<MobileElement> appliances = driver.findElementsByClassName("android.view.View");
		Print("Size of the view.View Elements: " + appliances.size());
		for(int i = 0; i < appliances.size(); i++)
		{
			Print("Element Text: " + appliances.get(i).getAttribute("text"));
			if(appliances.get(i).getAttribute("text").contains("Air"))
			{
				appliances.get(i).click();
			}
		}*/
		try
		{
			WebElement readyButton = FindByID("get-started-provisioning", driver);
			if(readyButton == null)
			{
				GrabFromClass("android.widget.Button", 0, driver).click();
			}
			else
			{
				readyButton.click();
			}
		}
		catch(Exception e)
		{
			GrabFromClass("android.widget.Button", 0, driver).click();
		}
		
		GrabFromClass("android.widget.Button", 1, driver).click();
		infoBox("Put the appliance into AP mode. Switch the Wifi and connect to appliance");
		SwitchWifi("Air");
		
		
		MobileElement button = GrabFromClass("android.widget.Button", 1, driver);
		Print("Attribute: " + button.getAttribute("text"));
		Print("Text: " + button.getText());
		Sleep(10000);
		button.click();
		infoBox("I hate everything and so do you");
		
		try
		{
			MobileElement errorCode = GrabFromClass("android.widget.Button", 0, driver);
			Print("Text of current button: " + errorCode.getText());
			if(errorCode.getAttribute("text").contains("DIS"))
			{
				errorCode.click();
			}
		}
		catch(Exception e)
		{
			Print("No Error Code Pop Up. Move on");
		}
		
		//DAVID WROTE THIS SHIT
		///TODO: GENERALIZE GTC LATER
		int targetIndex = -1;
		String thePath1 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[4]/android.view.View[";
	    String thePath2 = "]/android.view.View/android.view.View[2]";
	    
		for (int i = 1; i < 20; i++) 
		{
		    WebElement tempSSID = null;
		    String xpath = thePath1 + i + thePath2;
		    Print("Check XPath: " + xpath);
		    try 
		    {
		        tempSSID = driver.findElementByXPath(xpath);
		        if (tempSSID.getText() == "gtclab1") 
			    {
			        System.out.println("The correct index is: " + i);
			        System.out.println("The text is: " + tempSSID.getText());
			        Print("The text is: " + tempSSID.getAttribute("text"));
			        targetIndex = i;
			        break;
			    }
		    } 
		  	catch (Exception e) 
		   	{
		        System.out.println("Failed on iteration: " + i);
		        e.printStackTrace();
		    }
		}

		try 
		{
		    String targetSSIDXpath = thePath1 + targetIndex + thePath2;
			WebElement buttonSSID = driver.findElementByXPath(targetSSIDXpath);
			buttonSSID.click();
			Print("Xpath worked AGAIN!");
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		    infoBox("Just select do it manually");
		}
		//END DAVID WROTE THIS SHIT
		
		try
		{
			WebElement readyButton = FindByID("select-wifi-next", driver);
			readyButton.click();
		}
		catch(Exception e)
		{
			GrabFromClass("android.widget.Button", 0, driver).click();
			Print("Had to do the class thing");
		}
		
		/*DEMOS IN OTHER PLACES NEED TO CHANGE THE LINES ABOUT THE PASSWORD*/
		infoBox("Is the password set for the current location, like the lab and stuff");
		
		Sleep(3000);
		
		try
		{
			WebElement ssidPasswordField = FindByID("password", driver);
			ssidPasswordField.sendKeys("password1");
		}
		catch(Exception e)
		{
			MobileElement ssidPasswordField = GrabFromClass("android.widget.EditText", 0 , driver);
			ssidPasswordField.sendKeys("password1");
		}
		
		
		try
		{
			WebElement joinNetworkButton = FindByID("home-wifi-password-join", driver);
			joinNetworkButton.click();
		}
		catch(Exception e)
		{
			MobileElement joinNetworkButton = GrabFromClass("android.widget.Button", 0 , driver);
			joinNetworkButton.click();
			Print("Joined the network, very confident about this");
		}
		
		Sleep(15000);
		
		try
		{
			WebElement registrationNext = FindByID("register-appliance-next", driver);
			registrationNext = GrabFromClass("android.widget.Button", 0 , driver);
			registrationNext.click();
		}
		catch(Exception e)
		{
			MobileElement registrationNext = GrabFromClass("android.widget.Button", 0 , driver);
			registrationNext.click();
			Print("Appliance Connected, likely");
		}
		
		
		try
		{
			WebElement nameAppliance = FindByID("name-appliance-next", driver);
			nameAppliance.click();
		}
		catch(Exception e)
		{
			Sleep(3000);
			MobileElement nameAppliance = GrabFromClass("android.widget.Button", 0 , driver);
			nameAppliance.click();
			Print("Appliance Named");
		}
		
		try
		{
			WebElement provDone = FindByID("provision-done", driver);
			provDone.click();
			Print("Provision Done, Probably");
		}
		catch(Exception e)
		{
			Sleep(3000);
			MobileElement provDone = GrabFromClass("android.widget.Button", 0 , driver);
			provDone.click();
			Print("Provision Done");
		}
		
		Sleep(45000);
		
	}
	
	public void CloseConnection()
	{
		driver.quit();
	}
	
	private void Print(String msg)
	{
		System.out.println(msg);
	}
	
	public static void infoBox(String infoMessage)
    {
        String titleBar = "Pop up";
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	private MobileElement GrabFromClass(String className,int index, AndroidDriver d)
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MobileElement> results = d.findElementsByClassName(className);
		Print("Size of " + className + " Elements: " + results.size());
		return results.get(index);
	}
	
	@SuppressWarnings("deprecation")
	private void Tap(Point p, AndroidDriver d, int downTime)
	{
		Print("Tapping " +"(" + p.GetX() + "," + p.GetY()+ ")");
		TouchAction t = new TouchAction(d);
		//t.tap(p.GetX(), p.GetY());
		t.perform();
		
		try 
		{
			Thread.sleep(downTime);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void SwitchWifi(String ssid)
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
	
	private WebElement FindByID(String id, AndroidDriver d)
	{
		
		Sleep(2000);
		WebElement result = null;
		try
		{
			result = d.findElementById(id);
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Print("Failed to find Element with ID:" + id);
			return result;
		}
	}

	
}
