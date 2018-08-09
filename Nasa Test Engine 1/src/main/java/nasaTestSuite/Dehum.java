package main.java.nasaTestSuite;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Dehum extends FrigiDriver{
	private boolean powerOn;
	private Date timer;
	private int humidity;//0 means cont
	private int fanSpeed; //0 lo 1 hi
	private String name;
	private int notifications; //0 off 1 on
	private String timeZone;
	private static AndroidDriver driver;
	
	
//	public Dehum(AndroidDriver driver, int implicitTime) {
//		this.driver = driver;
//		// TODO do i need to update appstart boolean?
//	}
	


	//Humidity PLUS
	public void clickHumidPlus() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.humidPlusButton)));
		WebElement humidPlusElm = findByXPath(MyXPath.humidPlusButton, false, driver);
		humidPlusElm.click();
	}
	
	//Humidity MINUS
	public void clickHumidMinus() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.humidMinusButton)));
		WebElement humidPlusElm = findByXPath(MyXPath.humidMinusButton, false, driver);
		humidPlusElm.click();
	}
	
	//Humidity Speed Down
	public void clickHumidSpeedUp() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.dSpeedUp)));
		WebElement humidPlusElm = findByXPath(MyXPath.dSpeedUp, false, driver);
		humidPlusElm.click();
	}
	
	//Humidity Speed Down
	public void clickHumidSpeedDown() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.dSpeedDown)));
		WebElement humidPlusElm = findByXPath(MyXPath.dSpeedDown, false, driver);
		humidPlusElm.click();
	}
	
	//State
	public void refreshHumidity() {
//		System.out.println("Refeshing Humidity");
//		myWaitXPath(MyXPath.targHumidity, 20);
//		WebDriverWait wait = new WebDriverWait(driver,20);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.targHumidity)));
//		WebElement elem  = findByXPath(MyXPath.targHumidity, false, driver);
//		String humidityString = getText(elem);
//		System.out.println("Humidity: " + humidityString);
//		//TODO hi! add logic for CONT
//		/*
//		 * if(humidityString
//		 */
//		humidity = Integer.parseInt(getText(elem));
//		System.out.println("Element is Displayed 85: " + elem.isDisplayed());
		
		
		
//		WebElement elem3 = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+ "CONT" +"\")");
//		System.out.println("WOW! elem3 found: " + elem3.getText());
////		System.out.println("getAttribute: " + elem3.getAttribute());
////		System.out.println("getCssValue: " + elem3.getCssValue());
//		System.out.println("getTagName: " + elem3.getTagName());
//		System.out.println("getText: " + elem3.getText());
//		
//
//		
//		String firstXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.widget.ListView/android.view.View[12]/android.view.View[1]";
//		//first check for CONT as it is a seperate xpath
//		myWaitXPath(MyXPath.contHumidity, 30);
//		WebElement elem2 = findByXPath(MyXPath.contHumidity, false, driver);
//		if(elem2.isEnabled()) {
//			System.out.println("CONT is displayed");
//			humidity = 0;
//		}
//		//check number valued humidity xpaths
//		String firstHalf = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.widget.ListView/android.view.View[";
//		String secondHalf = "]/android.view.View[1]";
//		for(int i = 2; i <= 12; i++) {
//			String currentXPath = firstHalf + i + secondHalf;
//			System.out.println("Current XPath: " + currentXPath);
//			elem2 = findByXPath(currentXPath, false, driver);
//			if(elem2.isEnabled()) {
//				System.out.println("true");
//				humidity = 25 + (i*5);//start a 35 humidity, and for each index go up by five
//				System.out.println("Humidity: " + humidity);
//				System.out.println("getText: " + elem2.getText());
//				System.out.println("getTagName: " + elem2.getTagName());
//			}
//		}
//		System.out.println("Humidity Refreshed");
//		System.out.println("Humidity: " + humidity);
		
		
		//atttempt 3
		System.out.println("RefreshHumidity: Work in Progress");
		WebElement elem3 = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+ "CONT" +"\")");
		System.out.println("CONT: " + lookForText("CONT", SIGN_IN_WAIT));
		boolean contHumidity = lookForText("CONT", SIGN_IN_WAIT);
		if(contHumidity) 
		{
			
		}
		for(int i = 2; i <= 12; i++) 
		{
			String tempHumidity = String.valueOf((25 + (i*5)));
			System.out.println("Looking for " + tempHumidity);
			boolean humidityFound = lookForText(tempHumidity, SIGN_IN_WAIT);
			if(humidityFound) 
			{
				System.out.println("Humidity Found: " + tempHumidity);
			}
		}
	}
	
	public void powerButton() 
	{
		clickByXpath(MyXPath.powerButton, POWER_SECS);
		thinkWait();
		if(powerOn) 
		{
			System.out.println("Dehum powering down");
			powerOn = false;
		}else 
		{
			System.out.println("Dehum powering on");
			powerOn = true;
		}
	}
	
	
	public void openControls() 
	{
		openControls("dehum");
	}
	
	public void setDriver(AndroidDriver driver) 
	{
		this.driver = driver;
	}

	public Date getTimer() {
		return timer;
	}

	public int getHumidity() {
		return humidity;
	}

	public int getFanSpeed() {
		return fanSpeed;
	}

	public String getName() {
		return name;
	}

	public int getNotifications() 
	{
		return notifications;
	}

	public String getTimeZone() 
	{
		return timeZone;
	}

	public boolean isPowerOn() 
	{
		return powerOn;
	}

	public void setPowerOn(boolean powerOn) 
	{
		this.powerOn = powerOn;
	}
	
	
	
	
}
