# Web_SignUP_E2ETests

	This project implements web automation testing to test Miro's SignUP page(E2E) using the below libraries.

## Tools/Framework/Libraries/Plugin
	-JDK 8
	-Maven - build tool
	-TestNG - test runner
	-Cucumber - BDD/Gherkin style feature files
	-Selenium - Web Test Automation library
	-Log4j - Logging
	-maven-cucumber-reporting - Enhanced Reporting

## Run the tests using maven on CLI: 
	#Tested Locally on Chrome Version 92.0.4515.159 (Official Build) (64-bit)
	#Tested Locally on Firefox version 91.0.2 (64-bit)
	#chromedriver.exe & geckodriver.exe supporting above chrome & firefox version is located at src/test/resources/drivers/
	#If browserType parameter is not provided in CLI it takes default Chrome driver

```shell script
	To execute tests on Chrome Browser
		mvn -DdriverType=chrome clean verify
	
	To execute tests on Firefox
		mvn -DdriverType=firefox clean verify
		
	If no parameters provided it defaults to Chrome-Headless (Recommended)
    	mvn clean verify
```

## Reports: 

	- Enhance HTML Report: (Recommended)
		target/Reports/cucumber-html-reports/overview-features.html
		
	- Basic HTML Report:
		target/Reports/cucumber-report.html
		
## Logs:
	- Log4j: Logs location: target/logs/MiroSignUpE2E.log
		
## BDD Cucumber(Feature file / Step definition)
	- BDD has a feature file/(s) enlisting all scenarios invoke the step definitions:
	- Scenarios are created in feature file as per the requirements,underlying code is present in Stepdefinitions;
	- Following the BDD practices for better communication on scenarios and coverage visibility

### Structure

	The specific classes contains constructor to achieve the Dependency Injection using Cucumber Picocontainer. 
	Picocontainer creates singleton instances during runtime and helps in passing information in the required classes. 

##src/main/java
	Generic: Generators.java: To create dynamic data used Faker library

##src/test/java

- Cucumber.Options: 

     This is heart of Cucumber; used cucumber-testng: All tests run through TestRunner code.

- pageObjects: Url & Webelements locators information is stored per WebPage for easy maintenance
	
	-SignUpConfirmPage.java:	
	-SignUpPage.java:
	
- StepDefinitions: 

	-Hooks:
		-@Before: This is first function for executing all scenarios; it creates the webdriver instance for every scenario
		-@After: This is last function for quitting the webdriver instance after executing each scenario  
		
	- E2ETestsPositive_Steps.java:
		-Underlying programming logic for scenarios written in E2ETestsPositive.feature
		-Many functions/steps are reusable and same are invoked for steps in E2ETestsNegative.feature
	
	-E2ETestsNegative_Steps.java:
		-Underlying programming logic for functions/steps that are new for negative testcase/(s)
	
	-WebDriverManager.java
		-Programming logic to select browser send via Maven parameter in CLI, creates that specific webdriver and 
		method to get the driver for further programming

	
- utilities:

	-ConfigReader.java
		Reads the configuration file at specified location
		
	-Log.java:
		To handle logs		

####src/test/resources

- features: Contains E2E scenarios for SignUp page

	-E2ETestsPositive.feature
	-E2ETestsNegative.feature
	
- drivers: 

	- chromedriver.exe file required for execution
	
- configuration: For better code maintainence

	-ExpectedErrors_SignUppage.properties: 	This contains expected error messages for various cases;which are used further in assertions


	
	
	
	
	
	
