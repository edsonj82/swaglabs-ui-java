package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.LoginPage;
import support.DriverContext;

public class Authentication {

    private LoginPage loginPage;

    @Dado("^que o usuário está na página inicial de login do Swag Labs$")
    public void abrirPaginaDeLogin() {
        loginPage = new LoginPage(DriverContext.getDriver());
        loginPage.open();
    }

    @Quando("^o usuário preenche o campo \"([^\"]*)\" com \"([^\"]*)\"$")
    public void preencherCampo(String campo, String valor) {
        if (campo.equalsIgnoreCase("Username")) {
            loginPage.enterUsername(valor);
        } else {
            loginPage.enterPassword(valor);
        }
    }

    @Quando("^preenche o campo \"([^\"]*)\" com \"([^\"]*)\"$")
    public void preencherCampoE(String campo, String valor) {
        preencherCampo(campo, valor);
    }

    @Quando("^clica no botão \"([^\"]*)\"$")
    public void clicarBotao(String botao) {
        loginPage.clickLogin();
    }

    @Então("^deve ser redirecionado para a vitrine de produtos da plataforma$")
    public void verificarRedirecionamentoParaInventario() {
        Assert.assertTrue(
            "URL esperada contendo 'inventory', mas foi: " + loginPage.getCurrentUrl(),
            loginPage.getCurrentUrl().contains("inventory")
        );
    }

    @Então("^deve visualizar a mensagem de erro \"([^\"]*)\"$")
    public void verificarMensagemDeErro(String mensagem) {
        String textoEsperado = mensagem.endsWith("...")
            ? mensagem.substring(0, mensagem.length() - 3)
            : mensagem;
        String textoErro = loginPage.getErrorMessage();
        Assert.assertTrue(
            "Mensagem esperada: '" + mensagem + "' não encontrada em: '" + textoErro + "'",
            textoErro.contains(textoEsperado)
        );
    }
}

