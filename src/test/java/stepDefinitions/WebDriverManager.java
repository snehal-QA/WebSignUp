package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
	
	private WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;  //To get driver instance 
	}
	
	public void createWebDriver() {
		
		String driverType;
		try {
			//Gets devicetype from maven command line as a parameter; based on that selects appropriate web driver for testing	
			driverType = System.getProperty("driverType");	
				if(driverType==null) driverType="";
		
				switch(driverType) {
				case "chrome":{
									System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver.exe");
									this.driver=new ChromeDriver();
									break;
							}
				case "firefox":{
									System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/geckodriver.exe");
									this.driver=new FirefoxDriver();
									System.out.println("geckooooooooooddg "+this.driver);
									break;
							}
				default:{
									System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver.exe");
									//this.driver=new ChromeDriver();
									ChromeOptions options = new ChromeOptions();
									options.addArguments("headless");
									this.driver=new ChromeDriver(options);
									break;
						}	
				}
				this.driver.manage().window().maximize();
				this.driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		
		} catch (Exception e) {
				e.printStackTrace();
		}
	
	}
	
	public void quitWebDriver() {
		//Quits the driver instance
		this.driver.quit();
	}
}
