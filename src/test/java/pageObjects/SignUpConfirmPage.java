package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.WebDriverManager;


public class SignUpConfirmPage {
	
	private WebDriver driver;
			
	public SignUpConfirmPage(WebDriverManager webdrivermanger){
		this.driver=webdrivermanger.getDriver();
		PageFactory.initElements(driver, this);
}
	//Confirmation page url
    private String confirmpageurl="https://miro.com/email-confirm/";
 	
    //Method to retreive confirmapageurl
	public String getConfirmpageurl() {
		return confirmpageurl;
	}

	//Locators for all Webelements on SignupConfirmation Page
	@FindBy(className="signup__title-form")
	private WebElement signupConfirmTitle;
	
	
	@FindBy(className="signup__subtitle-form")
	private WebElement signupConfirmEmail;
	
	@FindBy(id="code")
	private WebElement signupConfirmCode;
	
	//Methods to retreive the locators 
	public WebElement signupConfirmTitle() {
		return signupConfirmTitle;
	}
	
	public WebElement signupConfirmEmail() {
		return signupConfirmEmail;
	}
	
	public WebElement signupConfirmCode() {
		return signupConfirmCode;
	}
	
}
