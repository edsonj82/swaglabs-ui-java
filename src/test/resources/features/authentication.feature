# language: pt

Funcionalidade: Autenticação de Usuários no Swag Labs
  Como um cliente cadastrado na plataforma Swag Labs
  Desejo inserir minhas credenciais de acesso
  Para conseguir navegar pelos produtos e realizar compras

  Contexto:
    Dado que o usuário está na página inicial de login do Swag Labs

  Cenário: Autenticação realizada com sucesso
    Quando o usuário preenche o campo "Username" com "standard_user"
    E preenche o campo "Password" com "secret_sauce"
    E clica no botão "Login"
    Então deve ser redirecionado para a vitrine de produtos da plataforma

  Esquema do Cenário: Validação de mensagens de erro impeditivas
    Quando o usuário preenche o campo "Username" com <usuario>
    E preenche o campo "Password" com <senha>
    E clica no botão "Login"
    Então deve visualizar a mensagem de erro <mensagem>

    Exemplos:
      | usuario           | senha          | mensagem                                              |
      | ""                | "secret_sauce" | "Epic sadface: Username is required"                  |
      | "standard_user"   | ""             | "Epic sadface: Password is required"                  |
      | "usuario_invalido"| "secret_sauce" | "Epic sadface: Username and password do not match..." |
      | "locked_out_user" | "secret_sauce" | "Epic sadface: Sorry, this user has been locked out." |