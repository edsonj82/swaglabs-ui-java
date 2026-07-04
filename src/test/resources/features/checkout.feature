# language: pt

Funcionalidade: Checkout de Compras do Swag Labs
  Como um usuário autenticado na plataforma Swag Labs
  Desejo finalizar minha compra informando dados de entrega
  Para receber os produtos adquiridos

  Cenário: Checkout concluído com sucesso
    Dado que o usuário iniciou o checkout com o produto "Sauce Labs Backpack" no carrinho
    Quando preenche as informações de entrega com "João", "Silva" e CEP "12345-678"
    Então deve avançar para a página de resumo do pedido
    Quando confirma o pedido clicando em Finish
    Então deve visualizar a confirmação de pedido realizado com sucesso

  Cenário: Retorno ao inventário após pedido confirmado
    Dado que o usuário iniciou o checkout com o produto "Sauce Labs Bike Light" no carrinho
    Quando preenche as informações de entrega com "Maria", "Souza" e CEP "98765-432"
    E confirma o pedido clicando em Finish
    E clica em Back Home após a confirmação
    Então deve visualizar a página de inventário de produtos

  Esquema do Cenário: Validação de campos obrigatórios no checkout
    Dado que o usuário iniciou o checkout com o produto "Sauce Labs Onesie" no carrinho
    Quando <acao>
    Então deve visualizar a mensagem de erro de checkout <mensagem>

    Exemplos:
      | acao                                          | mensagem                        |
      | tenta continuar sem preencher o campo de primeiro nome | "Error: First Name is required" |
      | tenta continuar sem preencher o campo de sobrenome     | "Error: Last Name is required"  |
      | tenta continuar sem preencher o CEP                    | "Error: Postal Code is required"|

  Cenário: Cancelar checkout e retornar ao carrinho
    Dado que o usuário iniciou o checkout com o produto "Sauce Labs Fleece Jacket" no carrinho
    Quando cancela o checkout
    Então deve retornar para a página do carrinho
