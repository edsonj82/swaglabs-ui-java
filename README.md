# SwagLabs API Automation - Rest Assured & Cucumber 🚀

Este repositório contém a suite de automação de testes de API para a plataforma **SwagLabs (SauceDemo API)**, desenvolvida utilizando **Rest Assured** com **Java**, integrada ao **Cucumber** para escrita de cenários em comportamento BDD (Behavior-Driven Development) e gerenciada via **Maven**.

O projeto foi estruturado seguindo as melhores práticas de Engenharia de Qualidade, adotando o padrão de arquitetura de camadas para testes de API, isolando as requisições base, especificações de contrato (JSON Schemas) e validações estritas de fluxos funcionais e de exceção.

---

## 🛠️ Tecnologias e Ferramentas

* **Java 17 / 25**: Linguagem base para desenvolvimento da suite de testes.
* **Rest Assured**: Biblioteca nativa Java para validação, envio de requisições HTTP e asserções de serviços REST.
* **Cucumber JVM**: Framework de BDD para tradução de critérios de aceite em cenários automatizados em formato Gherkin.
* **Maven**: Gerenciador de dependências e ciclo de vida do projeto (`pom.xml`).
* **JUnit 4 / 5**: Executor oficial dos testes e suporte à orquestração dos Runners.
* **Hamcrest**: Matchers avançados para asserções fluidas de payloads de resposta.

---

## 📂 Estrutura do Projeto e Camadas

A arquitetura do projeto foi modularizada para garantir alta reusabilidade de código e separação estrita de responsabilidades:

```bash
📂 SWAGLABS-API-AUTOMATION
├── 📂 src
│   └── 📂 test
│       ├── 📂 java
│       │   ├── 📂 runner       # Executores oficiais do Cucumber (JUnit Runners)
│       │   │   └── RunCucumberTest.java
│       │   └── 📂 steps        # Implementação em Java (Step Definitions) dos cenários BDD
│       │       ├── AuthenticationSteps.java
│       │       └── InventorySteps.java
│       └── 📂 resources
│           ├── 📂 features     # Especificações de cenários de negócio em Gherkin (.feature)
│           │   ├── authentication.feature
│           │   └── inventory.feature
│           └── 📂 schemas      # Arquivos JSON Schema para validação estrita de contrato
│               ├── auth_success_schema.json
│               └── inventory_list_schema.json
└── pom.xml                     # Gerenciamento de dependências, plugins de compilação e Surefire
```
## 🧪 Cobertura de Cenários Detalhada
### 🔐 Domínio: Authentication
* **Fluxos Funcionais (Success):** Autenticação de usuários válidos (standard_user, performance_glitch_user), geração e integridade de tokens ou cookies de sessão e tempos de resposta sob carga.

* **Exceções de Negócio & Contrato (Exceptions):** Tentativas de login com usuários bloqueados (locked_out_user), payloads com credenciais em branco, senhas inválidas e validação de mensagens semânticas de erro retornadas pelo servidor.

### 📦 Domínio: Inventory (Produtos e Estoque)
* **Fluxos Funcionais (Success):** Listagem completa de produtos, filtros por categorias (preço, nome alfabético), integridade dos dados estruturados de inventário.

* **Exceções de Negócio & Contrato (Exceptions):** Requisições de inventário sem autenticação prévia (validação de HTTP 401 Unauthorized), paginações com limites inválidos e requisições malformadas.

### 🛒 Domínio: Cart (Carrinho de Compras)
* **Fluxos Funcionais (Success):** Adição de múltiplos produtos ao carrinho, persistência do estado do carrinho por sessão de usuário, atualização de quantidades e remoção limpa de itens.

* **Exceções de Negócio & Contrato (Exceptions):** Tentativa de adicionar itens com IDs inexistentes, envio de quantidades negativas no payload do carrinho e persistência de dados com payloads corrompidos.

### 💳 Domínio: Checkout (Finalização de Compra)
* **Fluxos Funcionais (Success):** Envio de dados de faturamento válidos (First Name, Last Name, Postal Code), cálculo correto de taxas e impostos sobre o subtotal, fechamento de ordem com sucesso (201 Created ou 200 OK) e limpeza automática do carrinho.

* **Exceções de Negócio & Contrato (Exceptions):** Validação de payloads de checkout com campos obrigatórios ausentes (Postal Code em branco, nome nulo), falhas de conversão de tipos de dados nos campos de endereço e comportamento da API ao processar um checkout com o carrinho inteiramente vazio.

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