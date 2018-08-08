package test.java;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import main.java.nasaTestSuite.Dehum;
import main.java.nasaTestSuite.FrigiDriver;
import main.java.nasaTestSuite.MyXPath;

public class MyTest {

	

		
		public static FrigiDriver frigi = new FrigiDriver();
		public static Dehum dehum = new Dehum(); 
		static int oneMinute = 60;
		
		@Test//("^Appium server is running$")
		public void appium_server_is_running()  
		{//David

		    // Write code here that turns the phrase above into concrete actions
		}

		@Test//("^Phone or emulator are ready$")
		public void phone_or_emulator_are_ready()  
		{//David
		    // Write code here that turns the phrase above into concrete actions
		}

		@BeforeClass//("^This code opens the app$")
		public static void launchMyTest()
		{//David
			System.out.println("Launching App");//delete later
			//this.frigi = new FrigiDriver(20); //David: param is implicit time THIS BROKE SO HARD  NULLPOINTER LATER ON AT SIGN IN CAUSE UNKNOWN. Found out it was being reset between scenarios.
			//frigi.startApp(20); original working code
			frigi.startApp(1000000);//huge debug wait
			
			dehum.setDriver(frigi.getDriver());
			System.out.println("1");
			//frigi.UpdateApp();
			frigi.clickByXpath(MyXPath.updateButton, oneMinute*3);
			System.out.println("PASS: Scenario - Start and update the app");
//			frigi.clickSignIn1();
			
			
			frigi.clickByXpath(MyXPath.signInOne, 240);
			System.out.println("PASS: Scenario - Sign In Button");
		    frigi.clickByXpath(MyXPath.emailField, oneMinute);
		    frigi.typeEmail();
		    frigi.clickByXpath(MyXPath.passField, oneMinute);
		    frigi.typePassword();
		    frigi.clickByXpath(MyXPath.signInTwo, oneMinute);
		    System.out.println("App Launched");
		}
		
		
		//humidscen
		@Test
		public void dehumPowerOn() 
		{
			System.out.println("Testing: dehum power on");
			if(dehum.isPowerOn()) 
			{//if power is on turn it off so we can test power on function
				dehum.powerButton();
			}
			dehum.powerButton();
			//TODO navigate to dehum
			System.out.println("Completed: dehum power on");
		}
		
		//Humidity will change to 'CONT' if 85, 35 if 'CONT', or it will just increase by 5
		@Test//("^Humidity will change to 'CONT' if (\\d+), (\\d+) if 'CONT', or it will just increase by (\\d+)$")
		public void humidityUp()
		{
			System.out.println("Testing: dehum humidity up");
			
			System.out.println("HumidPRE1: " + dehum.isPowerOn());
			if(!dehum.isPowerOn()) 
			{//if power is not on, turn it on
				dehum.powerButton();
			}
//			if(frigi.getScreen() == "dehum") {
//				//navigate to dehum
//			}
			System.out.println("HumidPRE2: " + dehum.isPowerOn());
			
			try 
			{
				dehum.refreshHumidity();
				dehum.clickHumidPlus();
				frigi.thinkWait();
				
				
				int expectedHumidity = dehum.getHumidity() + 5;
				dehum.refreshHumidity();
				System.out.println("DEBUG1");
				int actualHumidity = dehum.getHumidity();
				if(expectedHumidity == 90) 
				{
					System.out.println("Handle CONT logic StepDef");
					
				}
				else if(expectedHumidity == 5)
				{
					System.out.println("Handle CONT logic StepDef");
				}
				else if(40 < expectedHumidity && expectedHumidity < 85) 
				{
					System.out.println("DEHUM PASS");
					System.out.println("Expected Humidity: " + expectedHumidity);
					System.out.println("Actual Humidity: " + expectedHumidity);
					if(actualHumidity != expectedHumidity) 
					{
						System.out.println("Expected Humidity: " + expectedHumidity);
						System.out.println("Actual Humidity: " + expectedHumidity);
						fail();
					}
				}
				else 
				{
					System.out.println("ERROR: Plus Button Pressed - Unexpected humidity");
				}
				System.out.println("PASS: Scenario: feature Dehumidifier Humidity Control - function UP");
				System.out.println("DEBUG2");
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				System.out.println("delete catch after debugging");
			}
			System.out.println("Completed: dehum humidity up");
		}
		
//		@Before
//		public void beforeHumidityDown(){
//			if(!dehum.isPowerOn()) {//if power is not on, turn it on
//				dehum.powerButton();
//			}
////			if(frigi.getScreen() == "dehum") {
////				//navigate to dehum
////			}
//		}
//		
//		@Test
//		public void humidityDown(){
//			try {
//				int expectedHumidity = dehum.getHumidity() - 5;
//				dehum.refreshHumidity();
//				System.out.println("DEBUG1");
//				int actualHumidity = dehum.getHumidity();
//				if(expectedHumidity == 30) {
//					System.out.println("Handle CONT logic StepDef");
//					
//				}else if(expectedHumidity == -5){
//					System.out.println("Handle 85 logic StepDef");
//				}else if(35 < expectedHumidity && expectedHumidity < 80) {
//					System.out.println("DEHUM PASS");
//					System.out.println("Expected Humidity: " + expectedHumidity);
//					System.out.println("Actual Humidity: " + expectedHumidity);
//					if(actualHumidity != expectedHumidity) {
//						System.out.println("Expected Humidity: " + expectedHumidity);
//						System.out.println("Actual Humidity: " + expectedHumidity);
//						fail();
//					}
//				}else {
//					System.out.println("ERROR: Plus Button Pressed - Unexpected humidity");
//				}
//				System.out.println("PASS: Scenario: feature Dehumidifier Humidity Control - function UP");
//				System.out.println("DEBUG2");
//			}catch(Exception e) {
//				e.printStackTrace();
//				System.out.println("delete catch after debugging");
//			}
//			
//		}
		
		@Test
		public void powerOff() 
		{
			if(!dehum.isPowerOn()) 
			{
				dehum.powerButton();
			}
			
			dehum.powerButton();
			System.out.println("Device shoud be off");
		}

	

}
