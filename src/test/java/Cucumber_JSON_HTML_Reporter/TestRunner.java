package Cucumber_JSON_HTML_Reporter;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Features", plugin ={"json:target/cucumber/Report.json","html:target/cucumber/report.html"}, glue = {"StepDefinitions"})
public class TestRunner {
}