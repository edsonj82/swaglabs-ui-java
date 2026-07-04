package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;       // <-- Novo pacote
import io.cucumber.junit.CucumberOptions; // <-- Novo pacote

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/features"},
    tags = "not @ignore",
    glue = {"steps", "support"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)
public class RunCucumberTest {

}
