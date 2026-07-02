package steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Authentication {

    private WebDriver driver;

    @Dado("^que o usuário está na página inicial de login do Swag Labs$")
    public void abrirPaginaDeLogin() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
        this.driver.get("https://www.saucedemo.com/");
    }

    @Quando("^o usuário preenche o campo \"([^\"]*)\" com \"([^\"]*)\"$")
    public void preencherCampo(String campo, String valor) {
        String fieldId = campo.equalsIgnoreCase("Username") ? "user-name" : "password";
        this.driver.findElement(By.id(fieldId)).sendKeys(valor);
    }

    @Quando("^preenche o campo \"([^\"]*)\" com \"([^\"]*)\"$")
    public void preencherCampoE(String campo, String valor) {
        String fieldId = campo.equalsIgnoreCase("Username") ? "user-name" : "password";
        this.driver.findElement(By.id(fieldId)).sendKeys(valor);
    }

    @Quando("^clica no botão \"([^\"]*)\"$")
    public void clicarBotao(String botao) {
        this.driver.findElement(By.id("login-button")).click();
    }

    @Então("^deve ser redirecionado para a vitrine de produtos da plataforma$")
    public void verificarRedirecionamentoParaInventario() {
        Assert.assertTrue(
            "URL esperada contendo 'inventory', mas foi: " + this.driver.getCurrentUrl(),
            this.driver.getCurrentUrl().contains("inventory")
        );
    }

    @Então("^deve visualizar a mensagem de erro \"([^\"]*)\"$")
    public void verificarMensagemDeErro(String mensagem) {
        String textoEsperado = mensagem.endsWith("...")
            ? mensagem.substring(0, mensagem.length() - 3)
            : mensagem;
        String textoErro = this.driver.findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertTrue(
            "Mensagem esperada: '" + mensagem + "' não encontrada em: '" + textoErro + "'",
            textoErro.contains(textoEsperado)
        );
    }

    @After
    public void fecharNavegador() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}

