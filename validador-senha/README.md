# Validor de Senha

### Criação da arquitetura baseada Arquitetura Limpa

* Camadas
domain: regras de negócio (mantêm as regras puras)
infrastructure: web, banco, APIs externas (adaptadores técnicos)
application: orquestra os use cases (logica do negocio)

*  Criação da api, para ter uma visão de comportamento .lógica de validação de senha
*  Banco H2 para manter o projeto rodando localmente e testa a construção