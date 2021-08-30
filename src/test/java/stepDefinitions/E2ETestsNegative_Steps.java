package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Log;
import pageObjects.SignUpConfirmPage;
import pageObjects.SignUpPage;
import utilities.ConfigFileReader;

public class E2ETestsNegative_Steps {
	WebDriver driver;
	Scenario scenario;
	SignUpPage signuppage;
	SignUpConfirmPage signupconfirmpage;
	E2ETestsPositive_Steps pos_steps;
	String workemail_toenter;
	ConfigFileReader config;

	SoftAssert softAssert = new SoftAssert();

	public E2ETestsNegative_Steps(WebDriverManager webdrivermanger, SignUpPage signuppage,
			E2ETestsPositive_Steps pos_steps,SignUpConfirmPage signupconfirmpage,ConfigFileReader config) {
		this.driver = webdrivermanger.getDriver();
		this.signuppage = signuppage;
		this.pos_steps = pos_steps;
		this.signupconfirmpage=signupconfirmpage;
		this.config=config;
	}
	
	@Before(order=3)
	public void before(Scenario scenario) {
	    this.scenario = scenario;
	}
	
	//SoftAsserts are used to make sure all asserts/verification are executed even if one fails
	@After(order=3)
	public void after(Scenario scenario) {
		 softAssert.assertAll();
		  this.scenario = scenario; 		  
	}
	
	/*Function for validations on error messages for missing mandatory parameters during signup
	* Error messages are derived from ExpectedErrors_signuppage.properties file from src/test/resources/configuration
	*/
	@Then("user gets error message for missing details {string},{string},{string},{string}")
	public void user_gets_error_message_for_missing_details(String name, String workEmail, String password,
			String policyAgreement) {
		if (name.equals("")) {
			softAssert.assertEquals(signuppage.name_error().getText(),config.readProperty("missing_name_error_msg"));
			Log.info("Name_error: "+signuppage.name_error().getText());
			this.scenario.log("Name_error: "+signuppage.name_error().getText());
		}
		if (workEmail.equals("")) {
			softAssert.assertEquals(signuppage.workemail_error().getText(),config.readProperty("missing_workemail_error_msg"));
			Log.info("workEmail_error: "+signuppage.workemail_error().getText());
			this.scenario.log("workEmail_error: "+signuppage.workemail_error().getText());
		}
		if (password.equals("")) {
			softAssert.assertEquals(signuppage.password_error().getText(),config.readProperty("missing_password_error_msg"));
			Log.info("password_error: "+signuppage.password_error().getText());
			this.scenario.log("password_error: "+signuppage.password_error().getText());
		}
		if (policyAgreement.equals("")) {
			softAssert.assertEquals(signuppage.signup_terms_error().getText(),config.readProperty("missing_signup_terms_error_msg"));
			Log.info("policyAgreement_error: "+signuppage.signup_terms_error().getText());
			this.scenario.log("policyAgreement_error: "+signuppage.signup_terms_error().getText());
		}
	}

	/* Validates user is not navigated to Sign-up confirmation page */
	@Then("user is not navigated to confirmation page")
	public void user_is_not_navigated_to_confirmation_page() {
		softAssert.assertNotEquals(driver.getCurrentUrl(),signupconfirmpage.getConfirmpageurl());
		Log.info("User is not navigated to signupconfirmation page:"+driver.getCurrentUrl());
		this.scenario.log("User is not navigated to signupconfirmation page:"+driver.getCurrentUrl());
	}
    
	/*Validates user is on Sigup-Page when entered incorrect mandatory details*/
	@Then("user is still on signup page")
	public void user_is_still_on_signup_page() {
		softAssert.assertEquals(driver.getCurrentUrl(), signuppage.getSignuppageurl());
		Log.info("User is still on signup page: "+driver.getCurrentUrl());
		this.scenario.log("User is still on signup page: "+driver.getCurrentUrl());
	}

	/*Validates correct error messages are displayed when incorrect data is entered in WorkEmail & password feild
	 *Error messages are derived from ExpectedErrors_signuppage.properties file from src/test/resources/configuration */
	@Then("user gets error message for incorrect details {string} {string}")
	public void user_gets_error_message_for_incorrect_details(String key, String value) {
		if (key.equalsIgnoreCase("WorkEmail"))
		{
			if (value.contains("@") && !value.endsWith("@")) {
				//softAssert.assertEquals(signuppage.workemail_error().getText(),config.readProperty("incorrect_workemail_error_msg_server"));
				softAssert.assertEquals(signuppage.workemail_error().getText(),"This doesn’t look like an email address. Please check it for typos and try again.");
			    Log.info("Error message when workemail is incorrect: "+signuppage.workemail_error().getText());
			    this.scenario.log("Error message when workemail is incorrect: "+signuppage.workemail_error().getText());}
			else {
				softAssert.assertEquals(signuppage.workemail_error().getText(),config.readProperty("incorrect_workemail_error_msg"));
				Log.info("Error message when workemail is incorrect: "+signuppage.workemail_error().getText());
			    this.scenario.log("Error message when workemail is incorrect: "+signuppage.workemail_error().getText());}
		}
		else if(key.equalsIgnoreCase("Password")) {
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(ExpectedConditions.visibilityOf(signuppage.password_hint()));
			softAssert.assertEquals(signuppage.password_hint().getText(),
					config.readProperty("incorrect_password_error_msg"));
			Log.info("Error message when Password is incorrect: "+signuppage.password_hint().getText());
		    this.scenario.log("Error message when Password is incorrect: "+signuppage.password_hint().getText());
		}
	}

//	/* Code to enter same workemail as registered.
//	 * As testcases must be independent of each other a new user is registerd and his email address is tried to reenter for registration */
//	@When("user tries to register with existing email {string}")
//	public void user_tries_to_register_with_same(String string) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}

	/*Validates error messages when already existing email id is used for registration*/
	@Then("user gets error message for existing email")
	public void user_gets_error_message() {
		softAssert.assertEquals(signuppage.workemail_error().getText(),config.readProperty("email_already_registered_error"));
		Log.info("Error message when user uses already registered email address for signup"+signuppage.workemail_error().getText());
		this.scenario.log("Error message when user uses already registered email address for signup"+signuppage.workemail_error().getText());
	}

	/*As testcases must be independent of each other a new user is registerd and his email address is tried to reenter for registration */ 
	/*Reusability is perfomed by calling all required functions to register a client from E2ETestPositive.java*/
	@Given("user already sign up successfully with workemail {string}")
	public void user_already_sign_up_successfully_with_workemail(String string) {
		pos_steps.user_enters_in_name_textbox("Name");
		pos_steps.user_enters_in_workemail_textbox("WorkEmail");
		this.workemail_toenter = pos_steps.workemail_toenter;
		pos_steps.user_enters_in_password_textbox("password");
		pos_steps.user_selects_policy_agreement_checkbox("PolicyAgreement");
		pos_steps.user_clicks_button(string);
		Log.info("Sign-up a client with emailaddress:"+this.workemail_toenter);
		this.scenario.log("Sign-up a client with emailaddress:"+this.workemail_toenter);
	}

	/*code to entering existing email*/
	@When("user enters existing {string} in workemail textbox")
	public void user_enters_in_workemail_textbox_negative(String workemail) {
		signuppage.enter_workemail(this.workemail_toenter);
	}

}
