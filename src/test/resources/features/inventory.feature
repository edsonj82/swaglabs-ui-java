# language: pt

Funcionalidade: Vitrine de Produtos do Swag Labs
  Como um usuário autenticado na plataforma Swag Labs
  Desejo navegar pela vitrine de produtos
  Para visualizar, ordenar e gerenciar os itens disponíveis

  Cenário: Visualização da vitrine de produtos após login
    Dado que o usuário está autenticado na plataforma Swag Labs
    Então deve visualizar a página de inventário de produtos
    E deve visualizar 6 produtos listados

  Esquema do Cenário: Ordenação de produtos
    Dado que o usuário está autenticado na plataforma Swag Labs
    Quando ordena os produtos por <opcao>
    Então <validacao>

    Exemplos:
      | opcao                    | validacao                                              |
      | "Price (low to high)"    | os produtos devem estar ordenados do menor para o maior preço |
      | "Price (high to low)"    | os produtos devem estar ordenados do maior para o menor preço |
      | "Name (A to Z)"          | os nomes dos produtos devem estar em ordem alfabética         |
      | "Name (Z to A)"          | os nomes dos produtos devem estar em ordem alfabética inversa |

  Cenário: Adicionar produto ao carrinho a partir do inventário
    Dado que o usuário está autenticado na plataforma Swag Labs
    Quando adiciona o produto "Sauce Labs Backpack" ao carrinho
    Então o ícone do carrinho deve exibir "1" item(s)

  Cenário: Adicionar múltiplos produtos ao carrinho
    Dado que o usuário está autenticado na plataforma Swag Labs
    Quando adiciona o produto "Sauce Labs Backpack" ao carrinho
    E adiciona o produto "Sauce Labs Bike Light" ao carrinho
    Então o ícone do carrinho deve exibir "2" item(s)

  Cenário: Remover produto do carrinho pela página de inventário
    Dado que o usuário está autenticado na plataforma Swag Labs
    Quando adiciona o produto "Sauce Labs Backpack" ao carrinho
    E remove o produto "Sauce Labs Backpack" do carrinho pela página de inventário
    Então o ícone do carrinho deve exibir "0" item(s)

  Cenário: Realizar logout pelo menu lateral
    Dado que o usuário está autenticado na plataforma Swag Labs
    Quando o usuário acessa o menu lateral
    E clica em Logout
    Então deve ser redirecionado para a página de login

  Cenário: Navegar ao carrinho a partir do inventário
    Dado que o usuário está autenticado na plataforma Swag Labs
    Quando adiciona o produto "Sauce Labs Fleece Jacket" ao carrinho
    E navega para o carrinho
    Então o produto "Sauce Labs Fleece Jacket" deve estar listado no carrinho
