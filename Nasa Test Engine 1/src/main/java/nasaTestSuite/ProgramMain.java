package main.java.nasaTestSuite;


import java.io.File;
import java.io.IOException;

import org.openqa.selenium.remote.DesiredCapabilities;

public class ProgramMain {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		
		TestCapabilities testcap = new TestCapabilities();
		testcap.AssignAppiumCapabilities();
		AppiumTest testObject = null;
		try 
		{
			testObject = new AppiumTest(20, testcap);
			testObject.RunProvision();
			testObject.CloseConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//testObject = new AppiumTest();
		}
		

	}
	
	public static void Print(String s)
	{
		System.out.println(s);
	}
	
	
}
