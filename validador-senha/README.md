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