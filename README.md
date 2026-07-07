# SwagLabs UI Automation - Java, Selenium & Cucumber 🚀

Este repositório contém a suite de automação de testes de ponta a ponta (E2E) para a interface web da plataforma **SwagLabs (SauceDemo)**. O projeto foi desenvolvido em **Java**, utilizando **Selenium WebDriver** para manipulação dos elementos web, **Cucumber** para a escrita de cenários em formato BDD (Behavior-Driven Development) e **Maven** como gerenciador de dependências.

A arquitetura adota as melhores práticas de Engenharia de Qualidade, aplicando o padrão de projeto **Page Object Model (POM)** para isolar a lógica de mapeamento e interação com os elementos visuais das definições de passos dos testes.

---

## 🛠️ Tecnologias e Ferramentas

* **Java 17 / 25**: Linguagem base para desenvolvimento da suite.
* **Selenium WebDriver**: Framework de automação para execução de comandos nativos nos navegadores web.
* **Cucumber JVM**: Framework BDD para tradução de critérios de aceite e cenários de negócios em testes automatizados executáveis (Gherkin).
* **Maven**: Gerenciador de ciclo de vida e dependências do projeto (`pom.xml`).
* **JUnit**: Executor oficial de testes responsável por orquestrar o Runner do Cucumber.

---

## 📂 Estrutura do Projeto (Page Object Model)

A arquitetura do projeto divide rigidamente as responsabilidades entre páginas, contextos de driver e definições de passos:

```bash
📂 SWAGLABS
├── 📂 src
│   └── 📂 test
│       ├── 📂 java
│       │   └── 📂 com.example (ou pacote base correspondente)
│       │       ├── 📂 pages         # Mapeamento de elementos e ações da tela (Page Objects)
│       │       │   ├── LoginPage.java
│       │       │   ├── InventoryPage.java
│       │       │   ├── CartPage.java
│       │       │   └── CheckoutPage.java
│       │       ├── 📂 runner        # Executor oficial dos testes do Cucumber
│       │       │   └── RunCucumberTest.java
│       │       ├── 📂 steps         # Implementação textual dos passos (Step Definitions)
│       │       │   ├── Authentication.java
│       │       │   ├── InventorySteps.java
│       │       │   ├── CartSteps.java
│       │       │   └── CheckoutSteps.java
│       │       └── 📂 support       # Inicialização do navegador e controle do ciclo de vida
│       │           └── DriverContext.java
│       └── 📂 resources
│           └── 📂 features        # Cenários de comportamento escritos em Gherkin (.feature)
│               ├── authentication.feature
│               ├── inventory.feature
│               ├── cart.feature
│               └── checkout.feature
└── pom.xml                        # Configurações do ecossistema Maven e dependências do Selenium/Cucumber
```
## 🧪 Cobertura de Cenários Detalhada
### 🔐 Autenticação (authentication.feature)
* **Fluxo Principal:** Login com usuários válidos (standard_user, performance_glitch_user) e redirecionamento correto para a vitrine de produtos.

* **Fluxo de Exceção:** Validação de mensagens de erro amigáveis ao tentar logar com credenciais inválidas, campos em branco ou com o usuário bloqueado (locked_out_user).

### 📦 Vitrine e Produtos (inventory.feature)
* **Fluxo Principal:** Navegação pela listagem de produtos, validação visual da disposição dos itens e comportamento de filtros/ordenação na vitrine.

### 🛒 Carrinho de Compras (cart.feature)
* **Fluxo Principal:** Adição de produtos como "Sauce Labs Backpack" e "Sauce Labs Bike Light", persistência visual dos itens no carrinho, funcionalidade do botão "Continuar Comprando" e remoção correta de itens limpando o carrinho.

* **Fluxo de Exceção:** Tentativa de adicionar itens com IDs inexistentes, envio de quantidades negativas no payload do carrinho e persistência de dados com payloads corrompidos.

### 💳 Finalização de Compra (checkout.feature)
* **Fluxo Principal:** Preenchimento completo do formulário de entrega (Nome, Sobrenome e CEP), avanço para a tela de sumário, confirmação de pedido com sucesso e retorno para a página inicial através do botão "Back Home".

* **Fluxo de Exceção:** Tentativas de checkout contendo campos obrigatórios em branco na etapa de informações de entrega e tratamento de mensagens de erro da interface.

## 🚀 Como Executar os Testes

**Pré-requisitos:** 
1. Ter o **JDK (Java Development Kit)** devidamente instalado e configurado nas variáveis de ambiente (JAVA_HOME).
2. **Ter o Apache Maven** instalado e configurado no PATH do sistema.

**1. Clonando o Repositório**
```bash
git clone [https://github.com/seu-usuario/swaglabs-api-automation.git](https://github.com/seu-usuario/swaglabs-api-automation.git)
cd swaglabs-api-automation
```

**2. Executando toda a Suite de Testes via Linha de Comando (Maven)**
Para rodar todos os cenários mapeados no projeto compilando as dependências do zero:
```bash
mvn clean test
```

**3. Executando um Runner ou Classe Específica**
Caso queira disparar especificamente o executor do Cucumber ignorando travas padrão:
```bash
mvn test -Dtest=runner.RunCucumberTest
```

**4. Executando via IDE (VS Code / IntelliJ)**
* Abra o arquivo src/test/java/runner/RunCucumberTest.java.
* Clique no atalho visual Run Test (Code Lens) posicionado logo acima da linha de declaração da classe (public class RunCucumberTest).

## 📊 Relatórios e Asserções Técnicas
Cada cenário executado pelo Rest Assured implementa validações robustas divididas em 4 pilares:

* **Verificação de Infraestrutura:** Validação de tempo de resposta limite (Response Time inferior a 1200ms) e presença de headers de segurança e cache obrigatórios.

* **Código de Status HTTP Semânticos:** Garantia do cumprimento do protocolo REST (Ex: 200 OK para consultas efetuadas, 400 Bad Request para payloads corrompidos e 401 Unauthorized para acessos não autenticados).

* **Validação de Tipo do Content-Type:** Garantia do retorno estruturado estritamente em application/json; charset=utf-8.

* **Validação Estrita de Contrato (JSON Schema Validation):** Uso do plugin do Rest Assured para confrontar a estrutura do JSON retornado contra os schemas salvos na pasta resources/schemas. Propriedades ausentes ou alterações de tipagem quebram o teste imediatamente (fail-fast).

---
## 👨‍💻 Autor

**Edson José dos Santos**  
SDET (Software Development Engineer in Test) & Performance Enthusiast