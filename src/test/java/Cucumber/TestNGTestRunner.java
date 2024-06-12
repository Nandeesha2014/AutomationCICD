package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
// cucumber - to run we need to use TestNG / Junit
@CucumberOptions(features="src/test/java/cucumber",glue="Nandeesha.stepDefinition",
monochrome=true, tags = "@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	
	

}
