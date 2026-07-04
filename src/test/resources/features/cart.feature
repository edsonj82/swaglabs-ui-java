# language: pt

Funcionalidade: Carrinho de Compras do Swag Labs
  Como um usuário autenticado na plataforma Swag Labs
  Desejo gerenciar os itens do meu carrinho
  Para controlar minha seleção de compras antes do checkout

  Cenário: Visualizar produto adicionado no carrinho
    Dado que o usuário adicionou "Sauce Labs Backpack" ao carrinho e está na página do carrinho
    Então o produto "Sauce Labs Backpack" deve estar listado no carrinho

  Cenário: Remover produto do carrinho
    Dado que o usuário adicionou "Sauce Labs Bike Light" ao carrinho e está na página do carrinho
    Quando remove o produto "Sauce Labs Bike Light" do carrinho
    Então o carrinho deve estar vazio

  Cenário: Continuar comprando a partir do carrinho
    Dado que o usuário adicionou "Sauce Labs Onesie" ao carrinho e está na página do carrinho
    Quando clica em Continuar Comprando
    Então deve retornar para a página de inventário

  Cenário: Avançar para o checkout a partir do carrinho
    Dado que o usuário adicionou "Sauce Labs Backpack" ao carrinho e está na página do carrinho
    Quando clica em Checkout
    Então deve ser direcionado para a página de checkout

  Cenário: Iniciar checkout com carrinho vazio
    Dado que o usuário está na página do carrinho sem itens
    Quando clica em Checkout
    Então deve ser direcionado para a página de checkout
