package steps;

import org.openqa.selenium.By; // Adicione este import se ainda não tiver
import org.openqa.selenium.WebDriver; // <-- IMPORTAÇÃO DO WEBDRIVER QUE FALTAVA
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.pt.Dado;
// import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class Authentication {

    // 1. O driver precisa ser declarado EXATAMENTE aqui
    private WebDriver driver;

    @Dado("^que o usuário está na página inicial de login do Swag Labs$")
    public void que_o_usuário_está_na_página_inicial_de_login_do_Swag_Labs() {
        // Usa o 'this.' para garantir que estamos instanciando a variável global da classe
        this.driver = new ChromeDriver();
        this.driver.get("https://www.saucedemo.com/");
    }

    // 2. Os métodos precisam receber os parâmetros String da Feature entre os parênteses
    @Quando("^o usuário preenche o campo \"([^\"]*)\" com \"([^\"]*)\"$")
    public void o_usuário_preenche_o_campo_com(String campo, String valor) {
        this.driver.findElement(By.id("user-name")).sendKeys(valor);
    }

    @Quando("^preenche o campo \"([^\"]*)\" com \"([^\"]*)\"$")
    public void preenche_o_campo_com(String campo, String valor) {
        this.driver.findElement(By.id("password")).sendKeys(valor);
    }

    @Quando("^clica no botão \"([^\"]*)\"$")
    public void clica_no_botão(String botao) {
        this.driver.findElement(By.id("login-button")).click();
    }
}

