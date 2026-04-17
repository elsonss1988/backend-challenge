# Validor de Senha

### Criação da arquitetura baseada Arquitetura Limpa

* Camadas
domain: regras de negócio (mantêm as regras puras)
infrastructure: web, banco, APIs externas (adaptadores técnicos)
application: orquestra os use cases (logica do negocio)

*  Criação da api, para ter uma visão de comportamento .lógica de validação de senha
*  Banco H2 para manter o projeto rodando localmente e testa a construção

Criação de contrato e Componentes para as regras solicitadas:
 Cada regra será um componente que vai seguir um contrato

- Nove ou mais caracteres (LengthRule)
- Ao menos 1 dígito (DigitRule - Não ser nulo)
- Ao menos 1 letra minúscula (LowerCaseRule)
- Ao menos 1 letra maiúscula (UpperCaseRule)
- Ao menos 1 caractere especial (SpecialCharacter) !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto(NoRepeatedRule)
