package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {},
        features = "src/test/resources/features",
        tags = {"~@ignore"},
        glue = {"steps"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class RunCucumberTest {

}
