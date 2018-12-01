package main.java.nasaTestSuite;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Stromboli extends FrigiDriver
{
	private int mode;
	private Date timer;
	private int targTemp;
	private int currentTemp;
	private int speed; //0 low 1 medium 2 high 3 auto
	private boolean notifications; //0 off 1 on
	private boolean isCelsius;
	private String timeZone;
	private static AndroidDriver driver;
	private String name = "strombo";
	//TODO timer, speed, notifications, timezone all need to be abstracted
	
	
//	public Dehum(AndroidDriver driver, int implicitTime) {
//		this.driver = driver;
//		// TODO do i need to update appstart boolean?
//	}
	
	//Actions
	
	public void openControls() 
	{
		//TODO implement map navigation
		//Tap Back
		tapOnElement(findByXPath(MyXPath.backButton, BUTTON_WAIT)); 
		//Tap Strombo in list
		tapOnElement(findByXPath(MyXPath.getListApplianceName("Strombo"), BUTTON_WAIT));
//		tapOnElement(findByXPath(MyXPath.plainPowerButton, BUTTON_WAIT));trying to get power to work
		
	}

	//temp PLUS
	public void clickTempPlus() 
	{
		WebDriverWait wait = new WebDriverWait(driver, BUTTON_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.stromboTempUp)));
		WebElement tempPlusElm = findByXPath(MyXPath.stromboTempUp, BUTTON_WAIT);
		tempPlusElm.click();
		thinkWait();
	}
	
	//temp MINUS
	public void clickTempMinus() 
	{
		WebDriverWait wait = new WebDriverWait(driver,BUTTON_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.stromboTempDown)));
		WebElement tempMinusElm = findByXPath(MyXPath.stromboTempDown, false, driver);
		tempMinusElm.click();
		thinkWait();
	}
	
	//Humidity Speed Down
	public void clickSpeedUp() 
	{
		WebDriverWait wait = new WebDriverWait(driver,BUTTON_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.stromboSpeedup)));
		WebElement speedElm = findByXPath(MyXPath.stromboSpeedup, false, driver);
		speedElm.click();
		thinkWait();
	}
	
	//Humidity Speed Down
	public void clickSpeedDown() 
	{
		WebDriverWait wait = new WebDriverWait(driver,BUTTON_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.stromboSpeedDown)));
		WebElement speedElm = findByXPath(MyXPath.stromboSpeedDown, false, driver);
		speedElm.click();
		thinkWait();
	}
	
	public void clickModeUp() 
	{
		WebDriverWait wait = new WebDriverWait(driver,BUTTON_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.stromboModeUp)));
		WebElement modeElm = findByXPath(MyXPath.stromboModeUp, false, driver);
		modeElm.click();
		thinkWait();	
	}
	
	public void clickModeDown() 
	{
		WebDriverWait wait = new WebDriverWait(driver,BUTTON_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyXPath.stromboModeDown)));
		WebElement modeElm = findByXPath(MyXPath.stromboModeDown, false, driver);
		modeElm.click();
		 thinkWait();	
	}

	public void changeModeToCoolorEcon(){
		//Change mode until you reach a mode that can change the temperature
		int tempMode = getMode();
		while(tempMode==3 || tempMode==5) 
		{
			clickModeUp();
			tempMode = getMode();
		}
	}
	public void verifyApplianceName() 
	{
		clickOffSettings();
		
		//need ids
		String fake_applianceNameHead = "";
		String fake_applianceNameField = "";
		
		
		
		boolean applianceNameHeadShowing = searchForText("Appliance Name", BUTTON_WAIT);
	}
	
	public void setDriver(AndroidDriver driver) 
	{
		this.driver = driver;
	}

	public int getTargTemp() 
	{
		try {
			targTemp = Integer.parseInt(findByXPath(MyXPath.stromboTargetTemp, BUTTON_WAIT).getAttribute("data-value"));
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		return targTemp;
	}

	public int getNextExpectedMode() {
		try {
			mode = Integer.parseInt(findByXPath(MyXPath.stromboCurrentMode, BUTTON_WAIT).getAttribute("data-value"));
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		int nextExpectedMode = -1;
		switch (mode) {
	        case 4:  nextExpectedMode = 5;
	        	break;
	        case 5:  nextExpectedMode = 3;
	        	break;
	        case 3:  nextExpectedMode = 1;
	        	break;
			case 1:  nextExpectedMode = 4;
		    	break;
			default: System.out.println("Unknown mode: " + mode);
				break;
		}
		return nextExpectedMode;
	}

	public int getPrevExpectedMode() {
		mode = Integer.parseInt(findByXPath(MyXPath.stromboCurrentMode, BUTTON_WAIT).getAttribute("data-value"));
		int nextExpectedMode = -1;
		switch (mode) {
	        case 4:  nextExpectedMode = 1; //econ
	        	break;
	        case 5:  nextExpectedMode = 4;
	        	break;
	        case 3:  nextExpectedMode = 5;
	        	break;
			case 1:  nextExpectedMode = 3;
		    	break;
			default: System.out.println("Unknown mode: " + mode);
				break;
		}
		return nextExpectedMode;
	}

	public int getNextExpectedSpeed() {
		speed = Integer.parseInt(findByXPath(MyXPath.stromboCurrentFanSpeed, BUTTON_WAIT).getAttribute("data-value"));
		System.out.println("Speed by data value" + speed);
		int nextExpectedSpeed = -1;
		switch (speed) {
	        case 1:  nextExpectedSpeed = 2;
	        	break;
	        case 2:  nextExpectedSpeed = 4;
	        	break;
	        case 4:  nextExpectedSpeed = 7;
	        	break;
			case 7:  nextExpectedSpeed = 1;
		    	break;
			default: System.out.println("Unknown speed: " + speed);
				break;
		}
		return nextExpectedSpeed;
	}

	public int getPrevExpectedSpeed() {
		speed = Integer.parseInt(findByXPath(MyXPath.stromboCurrentFanSpeed, BUTTON_WAIT).getAttribute("data-value"));
		int prevExpectedSpeed = -1;
		switch (speed) {
	        case 1:  prevExpectedSpeed = 7;
	        	break;
	        case 2:  prevExpectedSpeed = 1;
	        	break;
	        case 4:  prevExpectedSpeed = 2;
	        	break;
			case 7:  prevExpectedSpeed = 4;
		    	break;
			default: System.out.println("Unknown speed: " + speed);
				break;
		}
		return prevExpectedSpeed;
	}
	
	public int getMode() 
	{
		mode = Integer.parseInt(findByXPath(MyXPath.stromboCurrentMode, BUTTON_WAIT).getAttribute("data-value"));
		return mode;
	}
	
	public int getSpeed() 
	{
		speed = Integer.parseInt(findByXPath(MyXPath.stromboCurrentFanSpeed, BUTTON_WAIT).getAttribute("data-value"));
		return speed;
	}

	public Date getTimer() 
	{
		return timer;
	}


	public String getTimeZone() 
	{
		return timeZone;
	}
	public String getName() {
		return name;
	}
	
	
	
}
