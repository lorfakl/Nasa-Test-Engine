package main.java.nasaTestSuite;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Dehum extends FrigiDriver{
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
		myWait(MyXPath.targHumidity, 20);
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
		
		
		
		
		
		String firstXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.widget.ListView/android.view.View[12]/android.view.View[1]";
		myWait(MyXPath.contHumidity, 30);
		WebElement elem2 = findByXPath(MyXPath.contHumidity, false, driver);
		if(elem2.isDisplayed()) {
			System.out.println("CONT is displayed");
			humidity = 0;
		}
		String firstHalf = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.widget.ListView/android.view.View[";
		String secondHalf = "]/android.view.View[1]";
		for(int i = 2; i <= 12; i++) {
			String currentXPath = firstHalf + i + secondHalf;
			System.out.println("Current XPath: " + currentXPath);
			elem2 = findByXPath(currentXPath, false, driver);
			if(elem2.isDisplayed()) {
				System.out.println("true");
				humidity = 35 + (i*5);//start a 35 humidity, and for each index go up by five
			}
		}
		System.out.println("Humidity: " + humidity);
	}
	public void setDriver(AndroidDriver driver) {
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

	public int getNotifications() {
		return notifications;
	}

	public String getTimeZone() {
		return timeZone;
	}
	
	
	
	
}
