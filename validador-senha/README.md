# 🔐 Password Validator API

[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-02303a.svg)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Uma API REST robusta para validação de senhas seguindo padrões de segurança, construída com **Java 21**, **Spring Boot 3** e **Arquitetura Limpa**.

## 📋 Tabela de Conteúdos

- [Sobre o Projeto](#sobre-o-projeto)
- [Requisitos da Senha](#requisitos-da-senha)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura da Solução](#arquitetura-da-solução)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Princípios Aplicados](#princípios-aplicados)
- [Ambientes](#ambientes)
- [Como Executar](#como-executar)
- [API Endpoints](#api-endpoints)
- [Testes](#testes)
- [Exemplos de Uso](#exemplos-de-uso)
- [Decisões Técnicas](#decisões-técnicas)
- [Melhorias Futuras](#melhorias-futuras)

## 🎯 Sobre o Projeto

O **Password Validator API** é um serviço especializado em validar senhas de acordo com políticas de segurança pré-definidas. O projeto foi desenvolvido com foco em:

- **Código limpo e manutenível**
- **Arquitetura desacoplada e testável**
- **Extensibilidade para novas regras**
- **Baixa complexidade ciclomática**

### Premissas do Projeto

- A funcionalidade principal é **validar senhas**, sem necessidade de persistência
- A API é **stateless**, permitindo fácil escalabilidade horizontal
- As regras de validação são **configuráveis e extensíveis**

## 📜 Requisitos da Senha

Uma senha é considerada **válida** quando atende a **todos** os seguintes critérios:

| Critério | Descrição | Exemplo |
|----------|-----------|---------|
| ✅ **Comprimento mínimo** | 9 ou mais caracteres | `"AbTp9!fok"` |
| ✅ **Dígito** | Pelo menos 1 número (0-9) | `"AbTp9!fok"` |
| ✅ **Letra minúscula** | Pelo menos 1 letra (a-z) | `"AbTp9!fok"` |
| ✅ **Letra maiúscula** | Pelo menos 1 letra (A-Z) | `"AbTp9!fok"` |
| ✅ **Caractere especial** | Pelo menos 1 de: `!@#$%^&*()-+` | `"AbTp9!fok"` |
| ✅ **Sem repetição** | Nenhum caractere repetido | `"AbTp9!fok"` |

### Exemplos de Validação

```java
IsValid("")              // false - vazia
IsValid("aa")            // false - muito curta e sem requisitos
IsValid("ab")            // false - muito curta
IsValid("AAAbbbCc")      // false - sem dígito, sem especial, com repetição
IsValid("AbTp9!foo")     // false - caracteres repetidos ('o')
IsValid("AbTp9!foA")     // false - caracteres repetidos ('A')
IsValid("AbTp9 fok")     // false - contém espaço
IsValid("AbTp9!fok")     // true - senha válida ✅
🛠️ Tecnologias Utilizadas
Tecnologia	Versão	Propósito
Java	21	Linguagem principal
Spring Boot	3.4.1	Framework base
Spring Web	-	API REST
Spring Validation	-	Validação de entrada
Gradle	8.x	Gerenciador de dependências
JUnit 5	-	Testes unitários
MockMvc	-	Testes de integração
SLF4J	-	Logging
🏗️ Arquitetura da Solução
O projeto foi construído seguindo os princípios da Arquitetura Limpa (Clean Architecture) e Domain-Driven Design (DDD), garantindo separação de responsabilidades e baixo acoplamento.

Estrutura de Pastas
text
src/main/java/com/validadorsenha/
├── domain/                      # 🧠 Camada de Domínio (Regras de Negócio)
│   ├── exception/              # Exceções de domínio
│   ├── rule/                   # Implementações das regras (Strategy Pattern)
│   │   ├── PasswordRule.java   # Interface base
│   │   ├── LengthRule.java
│   │   ├── DigitRule.java
│   │   ├── LowercaseRule.java
│   │   ├── UppercaseRule.java
│   │   ├── SpecialCharRule.java
│   │   └── NoRepeatRule.java
│   └── service/                # Serviços de domínio
│       └── PasswordValidator.java
│
├── application/                # ⚙️ Camada de Aplicação (Casos de Uso)
│   └── service/                # Orquestração dos use cases
│       └── PasswordValidatorService.java
│
└── infrastructure/             # 🔌 Camada de Infraestrutura (Adaptadores)
    ├── config/                 # Configurações técnicas
    ├── controller/             # Controllers REST
    │   └── PasswordController.java
    ├── dto/                    # Data Transfer Objects
    │   ├── ValidationRequest.java
    │   └── ValidationResponse.java
    ├── exception/              # Exception handlers (ControllerAdvice)
    └── logging/                # Configurações de log
Fluxo de Validação
text
┌─────────────────────────────────────────────────────────────┐
│                      INFRASTRUCTURE                         │
│  ┌───────────────────────────────────────────────────────┐  │
│  │   Controllers, DTOs, Configurações, Logging          │  │
│  └───────────────────────────────────────────────────────┘  │
│                          ⬇️                                   │
│                      APPLICATION                             │
│  ┌───────────────────────────────────────────────────────┐  │
│  │   Services (Use Cases Orchestration)                  │  │
│  └───────────────────────────────────────────────────────┘  │
│                          ⬇️                                   │
│                         DOMAIN                               │
│  ┌───────────────────────────────────────────────────────┐  │
│  │   Business Rules (PasswordRule, Implementations)      │  │
│  └───────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
Descrição das Camadas
Camada	Responsabilidade	Exemplo
Domain	Regras de negócio puras, independentes de tecnologia	PasswordRule, LengthRule
Application	Orquestração de casos de uso, lógica de aplicação	PasswordValidatorService
Infrastructure	Adaptadores técnicos (web, banco, APIs externas)	PasswordController, DTOs
🎯 Princípios Aplicados
SOLID:

S: Single Responsibility (cada regra tem sua classe)

O: Open/Closed (novas regras são adicionadas sem modificar existentes)

L: Liskov Substitution (todas regras implementam a mesma interface)

I: Interface Segregation (interface coesa)

D: Dependency Inversion (camadas dependem de abstrações)

DDD: Entidades ricas e validações no domínio

Clean Architecture: Código independente de frameworks e UI

Fail Fast: Validação imediata de pré-condições

🌍 Ambientes
Ambiente	URL	Propósito
Local	http://localhost:8080	Desenvolvimento
Homologação	http://homolog.validador.com.br	Testes integrados
Produção	http://api.validador.com.br	Ambiente real
🚀 Como Executar
Pré-requisitos
Java 21 ou superior

Gradle 8.x (ou use o wrapper incluso)

Passo a Passo
Clone o repositório

bash
git clone https://github.com/seu-usuario/validador-senha.git
cd validador-senha
Compile o projeto

bash
./gradlew clean build
Execute a aplicação

bash
./gradlew bootRun
Acesse a API

text
http://localhost:8080
📡 API Endpoints
POST /api/v1/password/validate
Valida uma senha de acordo com as regras estabelecidas.

Request Body
json
{
  "password": "string"
}
Campo	Tipo	Obrigatório	Descrição
password	string	Sim	Senha a ser validada
Responses
✅ Senha Válida (200 OK)

json
{
  "valid": true,
  "message": "sua senha é valida"
}
❌ Senha Inválida (200 OK)

json
{
  "valid": false,
  "message": "A senha deve ter pelo menos 9 caracteres"
}
❌ Bad Request (400)

json
{
  "timestamp": "2024-01-01T00:00:00",
  "status": 400,
  "message": "Password cannot be null"
}
🧪 Testes
Executando os Testes
bash
# Todos os testes
./gradlew test

# Apenas testes unitários
./gradlew test --tests *Test

# Apenas testes de integração
./gradlew test --tests *IntegrationTest
Cobertura de Testes
Tipo de Teste	Descrição	Quantidade
Unitários	Testes isolados de cada regra e serviço	8+
Integração	Testes end-to-end do controller	10+
Cenários Testados
✅ Senha válida completa

❌ Senha com menos de 9 caracteres

❌ Senha sem dígito

❌ Senha sem letra minúscula

❌ Senha sem letra maiúscula

❌ Senha sem caractere especial

❌ Senha com caracteres repetidos

❌ Senha com espaços em branco

❌ Senha nula

❌ Requisição sem campo password

📝 Exemplos de Uso
cURL
bash
# Senha válida
curl -X POST http://localhost:8080/api/v1/password/validate \
  -H "Content-Type: application/json" \
  -d '{"password":"AbTp9!fok"}'

# Senha inválida (muito curta)
curl -X POST http://localhost:8080/api/v1/password/validate \
  -H "Content-Type: application/json" \
  -d '{"password":"Ab1!"}'

# Senha inválida (com espaço)
curl -X POST http://localhost:8080/api/v1/password/validate \
  -H "Content-Type: application/json" \
  -d '{"password":"AbTp9 fok"}'
Java (RestClient)
java
RestClient client = RestClient.create("http://localhost:8080");
ValidationRequest request = new ValidationRequest("AbTp9!fok");

ValidationResponse response = client.post()
    .uri("/api/v1/password/validate")
    .body(request)
    .retrieve()
    .body(ValidationResponse.class);
Python (requests)
python
import requests

response = requests.post(
    'http://localhost:8080/api/v1/password/validate',
    json={'password': 'AbTp9!fok'}
)
print(response.json())
💡 Decisões Técnicas
Decisão	Motivo	Benefício
Clean Architecture	Isolar regras de negócio	Facilidade de manutenção e testes
Strategy Pattern	Múltiplas regras de validação	Extensibilidade sem modificar código existente
DTOs imutáveis	Records do Java 21	Imutabilidade e menos código boilerplate
Testes parametrizados	@CsvSource no JUnit 5	Alta cobertura com menos código
Logging estruturado	SLF4J com MDC	Rastreabilidade e debugging facilitado
Validação case-sensitive	Requisito de negócio	Maior segurança
Premissas Adotadas
Caracteres repetidos: Validação case-sensitive ('A' ≠ 'a')

Espaços em branco: Senha com qualquer espaço é inválida

Mensagens: Em português para melhor entendimento do contexto

Null handling: Campo obrigatório com validação @NotNull

🔮 Melhorias Futuras
Curto Prazo
Adicionar cache para validações repetidas (Redis)

Implementar rate limiting para evitar brute force

Adicionar documentação OpenAPI (Swagger)

Médio Prazo
Internacionalização (i18n) para mensagens

Histórico de validações com auditoria

API Batch para validar múltiplas senhas

Longo Prazo
Implementar blacklist de senhas comuns

Criar dashboard de métricas com Grafana

Deploy automatizado na AWS (ECS/Lambda)

📊 Métricas e Observabilidade
O projeto expõe métricas via Spring Boot Actuator e Micrometer:

bash
# Health check
curl http://localhost:8080/actuator/health

# Métricas Prometheus
curl http://localhost:8080/actuator/prometheus
Métricas Disponíveis
password.validation.total - Total de validações (tags: result=true/false)

password.validation.duration - Tempo de validação por requisição

🤝 Contribuindo
Faça um fork do projeto

Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)

Commit suas mudanças (git commit -m 'Add some AmazingFeature')

Push para a branch (git push origin feature/AmazingFeature)

Abra um Pull Request

📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

👥 Autor
Elson - GitHub

🙏 Agradecimentos
Obrigado pela oportunidade de desenvolver este desafio

Inspirado em boas práticas de engenharia de software

Clean Architecture e SOLID como guias principais

<div align="center"> <sub>Built with ☕ by Elson</sub> </div> EOF ```
Opção 3: Script para Windows (PowerShell)
Salve como create-readme.ps1 e execute:

powershell
$content = @'
# 🔐 Password Validator API

[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-02303a.svg)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Uma API REST robusta para validação de senhas seguindo padrões de segurança, construída com **Java 21**, **Spring Boot 3** e **Arquitetura Limpa**.

## 📋 Tabela de Conteúdos

- [Sobre o Projeto](#sobre-o-projeto)
- [Requisitos da Senha](#requisitos-da-senha)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura da Solução](#arquitetura-da-solução)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Princípios Aplicados](#princípios-aplicados)
- [Ambientes](#ambientes)
- [Como Executar](#como-executar)
- [API Endpoints](#api-endpoints)
- [Testes](#testes)
- [Exemplos de Uso](#exemplos-de-uso)
- [Decisões Técnicas](#decisões-técnicas)
- [Melhorias Futuras](#melhorias-futuras)

[conteúdo completo continua...]
'@

$content | Out-File -FilePath README.md -Encoding UTF8
Write-Host "Arquivo README.md criado com sucesso!" -ForegroundColor Green