# ADR-0008: Auditoria atômica e retenção

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
A auditoria deve ser confiável: mudança sem histórico, ou histórico sem mudança, invalida o propósito (BR-08 e risco "Auditoria não atômica").

## Decisão
- `AuditEvent` é gravado **na mesma transação** da alteração de negócio. Sem publicação assíncrona no MVP, portanto sem Outbox.
- Campos: ator, organização, ação, tipo e id da entidade, instante em UTC e `metadata` limitada e validada.
- `metadata` nunca contém senha, token, identificador de sessão ou conteúdo de documento.
- A aplicação só faz INSERT e SELECT na tabela de auditoria; não há UPDATE nem DELETE por usuários ou pela API.
- Retenção configurável por organização e documentada; sem exclusão comum por usuários. Anonimização por LGPD é tratada como procedimento administrativo documentado.
- A consulta (`GET /api/v1/audit-events`) é paginada e restrita aos papéis Administrador e Auditor.

## Alternativas consideradas
- **Outbox desde já:** só se justifica com consumidores assíncronos.
- **Auditoria via logs:** não é consultável nem transacional.

## Consequências
- Positivas: histórico consistente e fácil de testar (AUD-01).
- Negativas: a tabela cresce continuamente; é preciso índices por organização, data e entidade.
