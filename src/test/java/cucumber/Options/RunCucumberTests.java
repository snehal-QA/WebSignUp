package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/*Cucumber Test runner to report setup. It will be available at target/Reports/cucumber-html-reports 
 *Cucumber TestRunner helps in executing all tests enlisted in feature files with below setup */

@CucumberOptions(
	features="src/test/resources/features",
	plugin ={"pretty", 
			 "html:target/Reports/cucumber-report.html",
			 "json:target/Reports/cucumber.json"},
	glue= {"stepDefinitions"},
	tags="@E2ETest",
	dryRun = false,
    monochrome = true)

public class RunCucumberTests extends AbstractTestNGCucumberTests{

}
