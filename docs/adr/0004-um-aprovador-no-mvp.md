# ADR-0004: Um aprovador por despesa no MVP

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
A spec propõe um aprovador elegível no MVP, mas pede que o modelo permita múltiplas etapas no futuro.

## Decisão
- Uma despesa que exige aprovação precisa de **uma** decisão de um usuário elegível (papel Aprovador, com permissão para a organização).
- `ApprovalDecision` é histórico append-only (id, organizationId, expenseId, approverId, decision, comment, decidedAt) e inclui o campo `step` (inteiro, valor fixo 1 no MVP).
- Rejeição exige justificativa obrigatória; aprovação aceita comentário opcional.
- Aprovação e rejeição geram evento de auditoria na mesma transação (ver ADR-0008).

## Alternativas consideradas
- **Múltiplas etapas desde já:** aumenta a complexidade de estados e de testes sem ganho para a demonstração.
- **Sem campo `step`:** exigiria migração de dados ao introduzir etapas.

## Consequências
- Positivas: máquina de estados simples e fácil de testar.
- Negativas: sem alçadas em cadeia no MVP.
- Evolução: uma política de múltiplas etapas usará `step` e uma tabela de política, sem refazer `ApprovalDecision`.
