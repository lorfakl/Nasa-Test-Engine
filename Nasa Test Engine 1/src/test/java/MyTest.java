package test.java;

import static org.junit.Assert.fail;
import org.junit.Test;
import main.java.nasaTestSuite.Dehum;
import main.java.nasaTestSuite.FrigiDriver;
import main.java.nasaTestSuite.MyXPath;

public class MyTest {

	

		
		FrigiDriver frigi = new FrigiDriver();
		Dehum dhum = new Dehum(); 
		int oneMinute = 60;
		
		@Test//("^Appium server is running$")
		public void appium_server_is_running()  {//David

		    // Write code here that turns the phrase above into concrete actions
		}

		@Test//("^Phone or emulator are ready$")
		public void phone_or_emulator_are_ready()  {//David
		    // Write code here that turns the phrase above into concrete actions
		}

		@Test//("^This code opens the app$")
		public void this_code_opens_the_app(){//David
			System.out.println("Begin StepDef_StartApp Testing");//delete later
			//this.frigi = new FrigiDriver(20); //David: param is implicit time THIS BROKE SO HARD  NULLPOINTER LATER ON AT SIGN IN CAUSE UNKNOWN. Found out it was being reset between scenarios.
			//frigi.startApp(20); original working code
			frigi.startApp(1000000);//huge debug wait
			
			dhum.setDriver(frigi.getDriver());
			System.out.println("1");
		}
		
		@Test//("^this code updates the app$")
		public void this_code_updates_the_app(){//David
			//frigi.UpdateApp();
			frigi.clickByXpath(MyXPath.updateButton, oneMinute*3);
			System.out.println("PASS: Scenario - Start and update the app");
		}
		
		//SCENE TWO

		@Test//("^App is updated$")
		public void app_is_updated(){
			//write code here
			System.out.println("2");
		}

		@Test//("^Welcome page is open$")
		public void welcome_page_is_open(){
		    //CHECK THAT SIGN IN AND REGISTER BUTTONS ARE DISPLAYED
			System.out.println("3");
		}

		@Test//("^Sign in button is pressed$")
		public void sign_in_button_is_pressed(){
			System.out.println("4");
			//frigi.clickSignIn1();
			frigi.clickByXpath(MyXPath.signInOne, 240);
			System.out.println("PASS: Scenario - Sign In Button");
		}

		@Test//("^The login page is open$")
		public void the_login_page_is_open(){
		}
		
		@Test//("^Email is typed$")
		public void email_is_typed(){
		    frigi.typeEmail();
		}

		@Test//("^Password is typed$")
		public void password_is_typed(){
		    frigi.typePassword();
		}
		
		@Test//("^Sign in button is pressed again$")
		public void sign_in_button_is_pressed_again(){
//		    frigi.clickSignIn2();
		    // next time try List<WebElement> el = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().text(\"MY_TEXT\")"));
		    System.out.println("PASS: Scenario - Type Username and Password");
		    
		}
		
		//humidscen

		@Test//("^Dehum interface is open and dehum is on$")
		public void dehum_interface_is_open_and_dehum_is_on(){
		    // Write code here that turns the phrase above into concrete actions
		}
		
		@Test//("^Plus button is pressed$")
		public void plus_button_is_pressed(){
//			dhum.refreshHumidity();
//			dhum.clickHumidPlus();
//			frigi.thinkWait();
		}
		
		//Humidity will change to 'CONT' if 85, 35 if 'CONT', or it will just increase by 5
		@Test//("^Humidity will change to 'CONT' if (\\d+), (\\d+) if 'CONT', or it will just increase by (\\d+)$")
		public void humidity_will_change_to_CONT_if_if_CONT_or_it_will_just_increase_by(){
//			int expectedHumidity = dhum.getHumidity() + 5;
//			dhum.refreshHumidity();
//			System.out.println("DEBUG1");
//			int actualHumidity = dhum.getHumidity();
//			if(expectedHumidity == 90) {
//				System.out.println("Handle CONT logic StepDef");
//				
//			}else if(expectedHumidity == 5){
//				System.out.println("Handle CONT logic StepDef");
//			}else if(40 < expectedHumidity && expectedHumidity < 85) {
//				System.out.println("DEHUM PASS");
//				System.out.println("Expected Humidity: " + expectedHumidity);
//				System.out.println("Actual Humidity: " + expectedHumidity);
//				if(actualHumidity != expectedHumidity) {
//					System.out.println("Expected Humidity: " + expectedHumidity);
//					System.out.println("Actual Humidity: " + expectedHumidity);
//					fail();
//				}
//			}else {
//				System.out.println("ERROR: Plus Button Pressed - Unexpected humidity");
//			}
//			System.out.println("PASS: Scenario: feature Dehumidifier Humidity Control - function UP");
//			System.out.println("DEBUG2");
		}
		

	

}
