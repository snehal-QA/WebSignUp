package stepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.Log;

public class Hooks {

	private Scenario scenario;
	private WebDriver driver;
	private WebDriverManager webdrivermanager;

	public Hooks(WebDriverManager webdrivermanager) {
		this.webdrivermanager = webdrivermanager;
		
	}

	@Before(order=1)
	public void initilaize_browser(Scenario scenario) {
		this.scenario = scenario;
		this.webdrivermanager.createWebDriver();
		this.driver = webdrivermanager.getDriver();
		Log.info("Opens the selected browser from POM command line properties");
		this.scenario.log("Opens the selected browser from POM command line properties");
	}

	@After(order=1)
	public void quiteDriver() {
		webdrivermanager.quitWebDriver();
		Log.info("Quits the browser");
		this.scenario.log("Quits the browser");
	}
}
