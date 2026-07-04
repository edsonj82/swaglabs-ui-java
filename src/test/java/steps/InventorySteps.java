package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.InventoryPage;
import pages.LoginPage;
import support.DriverContext;

public class InventorySteps {

    private InventoryPage inventoryPage;
    private LoginPage loginPage;

    private void ensureLoggedIn() {
        loginPage = new LoginPage(DriverContext.getDriver());
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        inventoryPage = new InventoryPage(DriverContext.getDriver());
    }

    @Dado("^que o usuário está autenticado na plataforma Swag Labs$")
    public void usuarioAutenticado() {
        ensureLoggedIn();
    }

    @Então("^deve visualizar a página de inventário de produtos$")
    public void verificarPaginaDeInventario() {
        Assert.assertTrue(
            "URL deveria conter 'inventory', mas foi: " + inventoryPage.getCurrentUrl(),
            inventoryPage.getCurrentUrl().contains("inventory")
        );
        Assert.assertEquals("Products", inventoryPage.getPageTitle());
    }

    @Então("^deve visualizar (\\d+) produtos listados$")
    public void verificarQuantidadeDeProdutos(int quantidade) {
        int actual = inventoryPage.getInventoryItemCount();
        Assert.assertEquals(
            "Quantidade de produtos esperada: " + quantidade + ", mas foi: " + actual,
            quantidade, actual
        );
    }

    @Quando("^ordena os produtos por \"([^\"]*)\"$")
    public void ordenarProdutos(String opcao) {
        inventoryPage = new InventoryPage(DriverContext.getDriver());
        inventoryPage.selectSort(opcao);
    }

    @Então("^os produtos devem estar ordenados do menor para o maior preço$")
    public void verificarOrdenacaoMenorParaMaior() {
        java.util.List<org.openqa.selenium.WebElement> prices = inventoryPage.getItemPrices();
        double anterior = Double.MIN_VALUE;
        for (org.openqa.selenium.WebElement el : prices) {
            double preco = Double.parseDouble(el.getText().replace("$", ""));
            Assert.assertTrue(
                "Produtos fora de ordem: " + anterior + " > " + preco,
                preco >= anterior
            );
            anterior = preco;
        }
    }

    @Então("^os produtos devem estar ordenados do maior para o menor preço$")
    public void verificarOrdenacaoMaiorParaMenor() {
        java.util.List<org.openqa.selenium.WebElement> prices = inventoryPage.getItemPrices();
        double anterior = Double.MAX_VALUE;
        for (org.openqa.selenium.WebElement el : prices) {
            double preco = Double.parseDouble(el.getText().replace("$", ""));
            Assert.assertTrue(
                "Produtos fora de ordem: " + anterior + " < " + preco,
                preco <= anterior
            );
            anterior = preco;
        }
    }

    @Então("^os nomes dos produtos devem estar em ordem alfabética$")
    public void verificarOrdenacaoAlfabetica() {
        java.util.List<org.openqa.selenium.WebElement> names = inventoryPage.getItemNames();
        String anterior = "";
        for (org.openqa.selenium.WebElement el : names) {
            String nome = el.getText();
            Assert.assertTrue(
                "Produto fora de ordem alfabética: '" + anterior + "' > '" + nome + "'",
                nome.compareTo(anterior) >= 0
            );
            anterior = nome;
        }
    }

    @Então("^os nomes dos produtos devem estar em ordem alfabética inversa$")
    public void verificarOrdenacaoAlfabeticaInversa() {
        java.util.List<org.openqa.selenium.WebElement> names = inventoryPage.getItemNames();
        String anterior = "ZZZZZ";
        for (org.openqa.selenium.WebElement el : names) {
            String nome = el.getText();
            Assert.assertTrue(
                "Produto fora de ordem alfabética inversa: '" + anterior + "' < '" + nome + "'",
                nome.compareTo(anterior) <= 0
            );
            anterior = nome;
        }
    }

    @Quando("^adiciona o produto \"([^\"]*)\" ao carrinho$")
    public void adicionarProdutoAoCarrinho(String nomeProduto) {
        inventoryPage = new InventoryPage(DriverContext.getDriver());
        inventoryPage.addItemToCartByName(nomeProduto);
    }

    @Então("^o ícone do carrinho deve exibir \"([^\"]*)\" item\\(s\\)$")
    public void verificarBadgeCarrinho(String quantidade) {
        inventoryPage = new InventoryPage(DriverContext.getDriver());
        int expected = Integer.parseInt(quantidade);
        Assert.assertEquals(
            "Badge do carrinho deveria ser " + expected,
            expected, inventoryPage.getCartBadgeCount()
        );
    }

    @Quando("^remove o produto \"([^\"]*)\" do carrinho pela página de inventário$")
    public void removerProdutoInventario(String nomeProduto) {
        inventoryPage = new InventoryPage(DriverContext.getDriver());
        inventoryPage.removeItemFromCartByName(nomeProduto);
    }

    @Quando("^o usuário acessa o menu lateral$")
    public void acessarMenuLateral() {
        inventoryPage = new InventoryPage(DriverContext.getDriver());
        inventoryPage.openBurgerMenu();
    }

    @Quando("^clica em Logout$")
    public void clicarLogout() {
        inventoryPage.clickLogout();
    }

    @Então("^deve ser redirecionado para a página de login$")
    public void verificarRedirecionamentoParaLogin() {
        String url = DriverContext.getDriver().getCurrentUrl();
        Assert.assertTrue(
            "URL deveria ser a página de login, mas foi: " + url,
            url.equals("https://www.saucedemo.com/") || url.contains("saucedemo.com")
        );
    }

    @Quando("^navega para o carrinho$")
    public void navegarParaCarrinho() {
        inventoryPage = new InventoryPage(DriverContext.getDriver());
        inventoryPage.goToCart();
    }
}
