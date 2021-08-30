package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import generic.generators;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.SignUpConfirmPage;
import pageObjects.SignUpPage;
import utilities.Log;



public class E2ETestsPositive_Steps {
	WebDriver driver;
	Scenario scenario;
	SignUpPage signuppage;
	SignUpConfirmPage signupconfirmpage;
	String workemail_toenter;
	SoftAssert softAssert= new SoftAssert();
	
	//Dependancy Injection to use data from various classesS
	public E2ETestsPositive_Steps(WebDriverManager webdrivermanger,SignUpPage signuppage,SignUpConfirmPage signupconfirmpage){
		 		this.driver=webdrivermanger.getDriver();
				this.signuppage=signuppage;
				this.signupconfirmpage=signupconfirmpage;
	}
	
	@Before(order=2)
	public void before(Scenario scenario) {
	    this.scenario = scenario;
	}
	
	//SoftAsserts are used to make sure all asserts/verification are executed even if one fails
	@After(order=2)
	public void after(Scenario scenario) {
		 softAssert.assertAll();
	    this.scenario = scenario;
	}
    
	//////////////////////////////////////Common methods used across feature files /////////////////////////////////////////////////////////////
	
	/* Code that helps browser to navigate to Miro Sign up page */	
	@Given("user navigates to miro sign up page")
	public void user_navigates_to_miro_sign_up_page(){
		driver.get(signuppage.getSignuppageurl());
		Log.info("User is navigated to MiroSignup page: "+signuppage.getSignuppageurl());
		scenario.log("User is navigated to MiroSignup page: "+signuppage.getSignuppageurl());
		
	}
	
	/*User enters name based on sent parameter,if parameter has value then it takes that values else generates a name through faker and uses that*/
	@When("user enters {string} in name textbox")
	public void user_enters_in_name_textbox(String name) {
		
	   String name_toenter=name.equalsIgnoreCase("Name")? generators.generate_name():name;
	   signuppage.enter_name(name_toenter);
	   Log.info("Entered Name:"+name_toenter);
	   scenario.log("Entered Name:"+name_toenter);
	}

	/*User enters workemail based on sent parameter,if parameter has value then it takes that values else generates a workeamil through faker and uses that*/
	@When("user enters {string} in workemail textbox")
	public void user_enters_in_workemail_textbox(String workemail) {
		
	    workemail_toenter= workemail.equalsIgnoreCase("Workemail")? generators.generate_email():workemail;
	    signuppage.enter_workemail(workemail_toenter);
	    Log.info("Entered workemail:"+workemail_toenter);
	    scenario.log("Entered workemail:"+workemail_toenter);	    
	}
	
	/*User enters password in password textbox; based on sent parameter, if parameter has password it will enter the sent else enter default Miro@2021*/
	@When("user enters {string} in password textbox")
	public void user_enters_in_password_textbox(String password) {
		
		String password_toenter=password.equalsIgnoreCase("Password")? "Miro@2021" : password;
	    signuppage.enter_password(password_toenter);
	    Log.info("Entered password:"+password_toenter);
	    scenario.log("Entered password:"+password_toenter);
	}
    
	/*User selects checkbox based on the send checkbox as parameter*/
	@When("user selects {string} checkbox")
	public void user_selects_policy_agreement_checkbox(String agreement) {
		if(agreement.equals("PolicyAgreement")) 	
			signuppage.check_signup_terms(true);
		else if (agreement.equals("News&UpdateAgreement"))
			signuppage.check_signup_subscribe(true);
		Log.info("Checked checkbox is:"+agreement);
		scenario.log("Checked checkbox is:"+agreement);
	}

	/* When user clicks submit buttton */
	@When("user clicks {string} button")
	public void user_clicks_button(String string) {
		 signuppage.click_signup__submit();
		 Log.info("Get Started now button is clicked");
		 scenario.log("Get Started now button is clicked");
	}

	/*Validates the url of the login page and accepts cookies*/
	@Then("miro sign up page is loaded in browser")
	public void miro_sign_up_page_is_loaded_in_browser() {
		softAssert.assertEquals(driver.getCurrentUrl(), signuppage.getSignuppageurl());
		signuppage.click_cookie();
		Log.info("URL of loaded page: "+ driver.getCurrentUrl());
		//this.scenario.log("URL of loaded page: "+driver.getCurrentUrl());
	}

	/*Validates user is navigated to email confirmation page*/
	@Then("user is navigated to Email Confirmation page")
	public void user_is_navigated_to_email_confirmation_page() {
		softAssert.assertEquals(driver.getCurrentUrl(), signupconfirmpage.getConfirmpageurl());
	    Log.info("Navigates to confirmation page: "+driver.getCurrentUrl());
	    scenario.log("Navigates to confirmation page: "+driver.getCurrentUrl());
	}
    
	/*Validates the Confirmation page title*/
	@Then("Email confirmation message contains {string}")
	public void email_confirmation_message_contains(String string) {
		softAssert.assertEquals(signupconfirmpage.signupConfirmTitle().getText(), string);
		Log.info("Title of the confirmation page: "+signupconfirmpage.signupConfirmTitle().getText());
		scenario.log("Title of the confirmation page: "+signupconfirmpage.signupConfirmTitle().getText());    
	}

	/*Validates registered email is displayed on Confirmation page*/
	@Then("Email confirmation message contains my {string}")
	public void email_confirmation_message_contains_my(String string) {
		Assert.assertTrue(signupconfirmpage.signupConfirmEmail().getText().contains(workemail_toenter));
		Log.info("Email id displayed on Confirmation page: "+signupconfirmpage.signupConfirmEmail().getText());
		scenario.log("Email id displayed on Confirmation page: "+signupconfirmpage.signupConfirmEmail().getText());
	}

	/*Validates a textbox is displayed to enter 6 digit code on Confirmation page*/
	@Then("Email confirmation message contains textbox to enter {int} digit code")
	public void email_confirmation_message_contains_textbox_to_enter_digit_code(Integer int1) {
		softAssert.assertTrue(signupconfirmpage.signupConfirmCode().isDisplayed());
		Log.info("A textbox is displayed to enter 6 digit code: "+signupconfirmpage.signupConfirmCode().isDisplayed());
		scenario.log("A textbox is displayed to enter 6 digit code: "+signupconfirmpage.signupConfirmCode().isDisplayed());
	}

}
