package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.WebDriverManager;


public class SignUpPage {
	
	private WebDriver driver;
	WebDriverManager webdrivermanger;
			
	public SignUpPage(WebDriverManager webdrivermanger){
		this.driver=webdrivermanger.getDriver();
		PageFactory.initElements(driver, this);
}
   //SignupPage url	
   private String signuppageurl="https://miro.com/signup/";
	
   //Method to retreive Signuppage url
	public String getSignuppageurl() {
	return signuppageurl;}
	
	
	//Locators for all Webelements on SignupConfirmation Page
	@FindBy(id="name")
	private WebElement name_txtbox;
	
	@FindBy(id="nameError")
	private WebElement name_error;
	
	@FindBy(id="email")
	private WebElement workemail_txtbox;
	
	@FindBy(id="emailError")
	private WebElement workemail_error;
	
	@FindBy(id="password")
	private WebElement password_txtbox;
	
	@FindBy(xpath="//div[contains(@class,'signup__error-wrap-login js-empty-password')]")
	private WebElement password_error;
	
	@FindBy(className="signup__input-hint-text")
	private WebElement password_hint;
	
	@FindBy(id="signup-terms")
	private WebElement signup_terms_checkbox;
	
	@FindBy(id="termsError")
	private WebElement signup_terms_error;
	
	@FindBy(id="signup-subscribe")
	private WebElement signup_subscribe_checkbox;
	
	@FindBy(id="onetrust-accept-btn-handler")
	private WebElement cookie;
	
	@FindBy(className="signup__submit")
	private WebElement signup__submit_btn;
	
	@FindBy(id="a11y-signup-with-google")
	private WebElement google_icon;
	
	@FindBy(xpath="//img[@alt='Sign up with Slack']")
	private WebElement slack_icon;
	
	@FindBy(xpath="//img[@alt='Sign up with Office 365']")
	private WebElement office365_icon;
	
	@FindBy(xpath="//img[@alt='Sign up with Apple']")
	private WebElement apple_icon;
	
	@FindBy(xpath="//img[@alt='Sign up with Facebook']")
	private WebElement facebook_icon;
	
	//Methods to acess Webelements on SignUp page
	public void enter_name(String name)
	{
		name_txtbox.sendKeys(name);
	}
	
	public WebElement name_error()
	{
		return name_error;
	}
	
	public void enter_workemail(String workemail)
	{
		workemail_txtbox.sendKeys(workemail);
	}
	
	public WebElement workemail_error()
	{
		return workemail_error;
	}
	
	public void enter_password(String password)
	{
		password_txtbox.sendKeys(password);
	}
	
	public WebElement password_error()
	{
		return password_error;
	}
	
	public WebElement password_hint()
	{
		return password_hint;
	}
	
	public void check_signup_terms(boolean value)
	{
		if(value) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", signup_terms_checkbox);
		}
	}
	
	public WebElement signup_terms_error()
	{
		return signup_terms_error;
	}

	public void check_signup_subscribe(boolean value)
	{
		if(value) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", this.driver.findElement(By.id("signup-subscribe")));
		}
	}
	
	public void click_signup__submit()
	{
		signup__submit_btn.click();
	}
	
	public void click_google_icon()
	{
		google_icon.click();
	}
	
	public void click_slack_icon()
	{
		google_icon.click();
	}
	
	public void click_office365_icon()
	{
		google_icon.click();
	}
	
	public void click_apple_icon()
	{
		google_icon.click();
	}
	
	public void click_facebook_icon()
	{
		google_icon.click();
	}
	
	public void click_cookie() {
		cookie.click();
	}
	
}
