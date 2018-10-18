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
	private int notifications; //0 off 1 on
	private String timeZone;
	private static AndroidDriver driver;
	private String name = "dehum";
	
	
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
		System.out.println("recently added thinkwait");
		thinkWait();
	}
	
	//Humidity MINUS
	public void clickHumidMinus() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.humidMinusButton)));
		WebElement humidPlusElm = findByXPath(MyXPath.humidMinusButton, false, driver);
		humidPlusElm.click();
		thinkWait();
	}
	
	//Humidity Speed Down
	public void clickHumidSpeedUp() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.dSpeedUp)));
		WebElement humidPlusElm = findByXPath(MyXPath.dSpeedUp, false, driver);
		humidPlusElm.click();
		thinkWait();
	}
	
	//Humidity Speed Down
	public void clickHumidSpeedDown() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.racSpeedDown)));
		WebElement humidPlusElm = findByXPath(MyXPath.racSpeedDown, false, driver);
		humidPlusElm.click();
		thinkWait();
	}
	
	//State
	public void refreshHumidity() {
		
		//atttempt 3
		System.out.println("RefreshHumidity: Work in Progress");
		WebElement elem3 = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+ "CONT" +"\")");
		System.out.println("CONT: " + searchForText("CONT", SIGN_IN_WAIT));
		boolean contHumidity = searchForText("CONT", SIGN_IN_WAIT);
		if(contHumidity) 
		{
			
		}
		for(int i = 35; i <= 85; i = i + 5) 
		{
			String tempHumidity = String.valueOf(i);
			System.out.println("Looking for " + tempHumidity);
			boolean humidityFound = searchForText(tempHumidity, SIGN_IN_WAIT);
			if(humidityFound) 
			{
				System.out.println("Humidity Found: " + tempHumidity);
			}
		}
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


	public int getNotifications() {
		return notifications;
	}

	public String getTimeZone() {
		return timeZone;
	}


	public void setPowerOn(boolean powerOn) {
		this.powerOn = powerOn;
	}
	
	
	
	
}
