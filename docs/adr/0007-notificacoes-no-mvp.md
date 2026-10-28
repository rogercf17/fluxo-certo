# ADR-0007: Notificações no MVP

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
Alertas de vencimento (FR-09) são prioridade *Should*. Enviar e-mail real traz custo e riscos de duplicação, sem ser essencial para a demonstração.

## Decisão
- O MVP usa **notificações internas** (registro no banco, exibido na interface) e logs. E-mail é opcional e fica para a Sprint 5, se sobrar tempo.
- Um job agendado (`@Scheduled`) gera lembretes de vencimento.
- Deduplicação por chave lógica (despesa, tipo de alerta, destinatário, data), garantida por índice único, para que o job seja idempotente.
- Falhas são registradas em log e em tabela, com limite de retentativas e possibilidade de reprocessamento.
- A tabela de Outbox (`OutboxEvent`) só será criada se houver publicação assíncrona de eventos.

## Alternativas consideradas
- **E-mail desde o início:** exige provedor, templates e tratamento de entregabilidade.
- **Sem notificações:** descumpre FR-09 sem necessidade.

## Consequências
- Positivas: escopo pequeno e demonstrável.
- Negativas: o usuário precisa abrir a aplicação para ver os alertas.
- Evolução: um adaptador de e-mail pode ser plugado sobre as mesmas notificações.
