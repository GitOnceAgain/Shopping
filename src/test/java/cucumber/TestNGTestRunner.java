package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="groupid.StepDefinition", 
//monochrome=true, plugin={"html:target/cucumber.html"})
monochrome=true, tags="@Regression", plugin={"html:target/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
