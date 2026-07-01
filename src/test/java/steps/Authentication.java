package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class Authentication {

    
@Dado("^que o usuário está na página inicial de login do Swag Labs$")
public void que_o_usuário_está_na_página_inicial_de_login_do_Swag_Labs() throws Throwable {
    System.out.println("Teste 1");
}

@Quando("^o usuário preenche o campo \"([^\"]*)\" com \"([^\"]*)\"$")
public void o_usuário_preenche_o_campo_com(String arg1, String arg2) throws Throwable {
    System.out.println("Teste 2");
}

@Quando("^preenche o campo \"([^\"]*)\" com \"([^\"]*)\"$")
public void preenche_o_campo_com(String arg1, String arg2) throws Throwable {
    System.out.println("Teste 3");
}

@Quando("^clica no botão \"([^\"]*)\"$")
public void clica_no_botão(String arg1) throws Throwable {
        System.out.println("Teste 4");
}

@Então("^deve ser redirecionado para a vitrine de produtos da plataforma$")
public void deve_ser_redirecionado_para_a_vitrine_de_produtos_da_plataforma() throws Throwable {
    System.out.println("Teste 5");
}
    
@Então("^deve visualizar a mensagem de erro \"([^\"]*)\"$")
public void deve_visualizar_a_mensagem_de_erro(String arg1) throws Throwable {
    System.out.println("Teste 6");
}
}
