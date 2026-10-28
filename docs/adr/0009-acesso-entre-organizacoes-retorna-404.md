# ADR-0009: Acesso entre organizações retorna 404

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
O caso SEC-01 aceita 403 ou 404 quando um usuário tenta acessar um recurso de outra organização, desde que a política seja consistente. Responder 403 confirma que o recurso existe.

## Decisão
Qualquer recurso que não pertença à organização do usuário autenticado responde **404 Not Found**, com a mesma mensagem e o mesmo formato de um recurso inexistente. **403** é reservado para quando o recurso existe na organização do usuário, mas o papel dele não permite a ação. A regra vale para todos os endpoints.

## Alternativas consideradas
- **403 em todos os casos:** simples, mas permite enumerar ids existentes de outras empresas.

## Consequências
- Positivas: não revela a existência de dados de outros tenants.
- Negativas: depuração um pouco menos direta; o `correlationId` e os logs internos compensam.
- Testes: cada recurso tem teste negativo entre organizações A e B verificando status e corpo idênticos aos de um id inexistente.
