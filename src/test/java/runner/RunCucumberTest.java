package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;       // <-- Novo pacote
import io.cucumber.junit.CucumberOptions; // <-- Novo pacote

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/features"}, // <-- Adicione as chaves { } aqui
    tags = "~@ignore",                         // No Cucumber 7, tags usam uma String simples sem chaves
    glue = {"steps"},
    plugin = {"pretty", "html:target/cucumber-reports.html"}, // Ajustado para gerar o arquivo .html corretamente
    monochrome = true
)
public class RunCucumberTest {

}
