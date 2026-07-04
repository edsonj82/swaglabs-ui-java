package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import support.DriverContext;

public class CheckoutSteps {

    private CheckoutPage checkoutPage;

    private void loginAddItemAndGoToCheckout(String itemName) {
        LoginPage loginPage = new LoginPage(DriverContext.getDriver());
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        InventoryPage inventoryPage = new InventoryPage(DriverContext.getDriver());
        inventoryPage.addItemToCartByName(itemName);
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(DriverContext.getDriver());
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
    }

    @Dado("^que o usuário iniciou o checkout com o produto \"([^\"]*)\" no carrinho$")
    public void usuarioNoPrimeiroPassoDoCheckout(String itemName) {
        loginAddItemAndGoToCheckout(itemName);
    }

    @Quando("^preenche as informações de entrega com \"([^\"]*)\", \"([^\"]*)\" e CEP \"([^\"]*)\"$")
    public void preencherInformacoesDeEntrega(String firstName, String lastName, String postalCode) {
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
        checkoutPage.enterFirstName(firstName);
        checkoutPage.enterLastName(lastName);
        checkoutPage.enterPostalCode(postalCode);
        checkoutPage.clickContinue();
    }

    @Quando("^tenta continuar sem preencher o campo de primeiro nome$")
    public void tentarContinuarSemPrimeiroNome() {
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
        checkoutPage.enterLastName("Silva");
        checkoutPage.enterPostalCode("12345-678");
        checkoutPage.clickContinue();
    }

    @Quando("^tenta continuar sem preencher o campo de sobrenome$")
    public void tentarContinuarSemSobrenome() {
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
        checkoutPage.enterFirstName("João");
        checkoutPage.enterPostalCode("12345-678");
        checkoutPage.clickContinue();
    }

    @Quando("^tenta continuar sem preencher o CEP$")
    public void tentarContinuarSemCEP() {
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
        checkoutPage.enterFirstName("João");
        checkoutPage.enterLastName("Silva");
        checkoutPage.clickContinue();
    }

    @Então("^deve visualizar a mensagem de erro de checkout \"([^\"]*)\"$")
    public void verificarErroDeCheckout(String mensagem) {
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
        String erroAtual = checkoutPage.getErrorMessage();
        Assert.assertTrue(
            "Mensagem esperada: '" + mensagem + "' não encontrada em: '" + erroAtual + "'",
            erroAtual.contains(mensagem)
        );
    }

    @Então("^deve avançar para a página de resumo do pedido$")
    public void verificarPaginaDeResumo() {
        String url = checkoutPage.getCurrentUrl();
        Assert.assertTrue(
            "URL deveria conter 'checkout-step-two', mas foi: " + url,
            url.contains("checkout-step-two")
        );
        Assert.assertEquals("Checkout: Overview", checkoutPage.getPageTitle());
    }

    @Quando("^confirma o pedido clicando em Finish$")
    public void confirmarPedido() {
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
        checkoutPage.clickFinish();
    }

    @Então("^deve visualizar a confirmação de pedido realizado com sucesso$")
    public void verificarConfirmacaoDePedido() {
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
        String header = checkoutPage.getCompleteHeader();
        Assert.assertTrue(
            "Header de confirmação esperado, mas foi: " + header,
            header.contains("Thank you for your order")
        );
    }

    @Quando("^cancela o checkout$")
    public void cancelarCheckout() {
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
        checkoutPage.clickCancel();
    }

    @Então("^deve retornar para a página do carrinho$")
    public void verificarRetornoAoCarrinho() {
        String url = DriverContext.getDriver().getCurrentUrl();
        Assert.assertTrue(
            "URL deveria conter 'cart', mas foi: " + url,
            url.contains("cart")
        );
    }

    @Quando("^clica em Back Home após a confirmação$")
    public void clicarBackHome() {
        checkoutPage = new CheckoutPage(DriverContext.getDriver());
        checkoutPage.clickBackHome();
    }
}
