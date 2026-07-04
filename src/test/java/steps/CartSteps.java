package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import support.DriverContext;

public class CartSteps {

    private CartPage cartPage;

    private void loginAndAddItem(String itemName) {
        LoginPage loginPage = new LoginPage(DriverContext.getDriver());
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        InventoryPage inventoryPage = new InventoryPage(DriverContext.getDriver());
        inventoryPage.addItemToCartByName(itemName);
        inventoryPage.goToCart();
        cartPage = new CartPage(DriverContext.getDriver());
    }

    @Dado("^que o usuário adicionou \"([^\"]*)\" ao carrinho e está na página do carrinho$")
    public void usuarioComItemNoCarrinho(String itemName) {
        loginAndAddItem(itemName);
    }

    @Dado("^que o usuário está na página do carrinho sem itens$")
    public void usuarioNoCarrinhoVazio() {
        LoginPage loginPage = new LoginPage(DriverContext.getDriver());
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        InventoryPage inventoryPage = new InventoryPage(DriverContext.getDriver());
        inventoryPage.goToCart();
        cartPage = new CartPage(DriverContext.getDriver());
    }

    @Então("^o produto \"([^\"]*)\" deve estar listado no carrinho$")
    public void verificarItemNoCarrinho(String itemName) {
        cartPage = new CartPage(DriverContext.getDriver());
        Assert.assertTrue(
            "Produto '" + itemName + "' não encontrado no carrinho",
            cartPage.isItemInCart(itemName)
        );
    }

    @Quando("^remove o produto \"([^\"]*)\" do carrinho$")
    public void removerItemDoCarrinho(String itemName) {
        cartPage = new CartPage(DriverContext.getDriver());
        cartPage.removeItemFromCart(itemName);
    }

    @Então("^o carrinho deve estar vazio$")
    public void verificarCarrinhoVazio() {
        cartPage = new CartPage(DriverContext.getDriver());
        Assert.assertEquals(
            "O carrinho deveria estar vazio",
            0, cartPage.getCartItemCount()
        );
    }

    @Quando("^clica em Continuar Comprando$")
    public void clicarContinuarComprando() {
        cartPage = new CartPage(DriverContext.getDriver());
        cartPage.clickContinueShopping();
    }

    @Então("^deve retornar para a página de inventário$")
    public void verificarRetornoAoInventario() {
        String url = DriverContext.getDriver().getCurrentUrl();
        Assert.assertTrue(
            "URL deveria conter 'inventory', mas foi: " + url,
            url.contains("inventory")
        );
    }

    @Quando("^clica em Checkout$")
    public void clicarCheckout() {
        cartPage = new CartPage(DriverContext.getDriver());
        cartPage.clickCheckout();
    }

    @Então("^deve ser direcionado para a página de checkout$")
    public void verificarPaginaDeCheckout() {
        String url = DriverContext.getDriver().getCurrentUrl();
        Assert.assertTrue(
            "URL deveria conter 'checkout', mas foi: " + url,
            url.contains("checkout")
        );
    }
}
