package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//features = "src/test/java/cucumber" = run all feature files present in this package
//glue = "stepDefinition" = map them
//monochrome = true = print the results in readable format
//plugin = {"html : target/cucumber.html"} = generate html report
//tags = "@Regression = tag i Regression olanları çalıştır

//extends AbstractTestNGCucumberTests = cucumber default olarak testng kodlarını okuyamıyor o yüzden bu extends kodunu yazdık


@CucumberOptions(features = "src/test/java/cucumber", glue = "stepDefinition",
monochrome = true, plugin= {"html:target/cucumber.html"}, tags = "@Regression")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {


}
