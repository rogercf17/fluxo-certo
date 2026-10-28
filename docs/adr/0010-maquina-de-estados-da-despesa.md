# ADR-0010: Máquina de estados da despesa (envio e rejeição)

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
A tabela de estados da spec cita `SUBMITTED` e `RESUBMITTED` nas transições, mas esses nomes não aparecem na lista de estados. É preciso fechar o conjunto de estados e como a rejeição é tratada.

## Decisão
Estados persistidos: `DRAFT`, `PENDING_APPROVAL`, `REJECTED`, `APPROVED`, `SCHEDULED`, `PAID`, `CANCELLED`. `SUBMITTED` e `RESUBMITTED` **não** são estados.

| De | Para | Condição |
|---|---|---|
| DRAFT | PENDING_APPROVAL | `submit`, valor acima do limite (ADR-0005) |
| DRAFT | APPROVED | `submit`, valor dentro do limite (auto-aprovação auditada) |
| DRAFT | CANCELLED | cancelamento autorizado |
| PENDING_APPROVAL | APPROVED / REJECTED | decisão de aprovador elegível |
| PENDING_APPROVAL | CANCELLED | conforme política |
| REJECTED | DRAFT | reabertura explícita (`reopen`) pelo Financeiro ou pelo solicitante |
| APPROVED | SCHEDULED / PAID / CANCELLED | fluxo autorizado |
| SCHEDULED | PAID / CANCELLED | registro manual |
| PAID, CANCELLED | (terminais) | PAID só é corrigido por evento compensatório |

- Reabrir uma rejeição volta a despesa para `DRAFT`; o histórico de decisões e a auditoria são preservados. O reenvio usa o mesmo `submit`.
- Toda transição é validada no servidor e ocorre em transação, com versionamento otimista (`@Version`). Transição inválida responde **409 Conflict** e não altera o estado (WF-01).
- `PAID` significa registro manual por usuário autorizado; o sistema não confirma liquidação bancária.

## Alternativas consideradas
- **Estado `RESUBMITTED`:** adiciona um estado que se comporta como `PENDING_APPROVAL`.

## Consequências
- Positivas: menos estados, mais simples de testar e de exibir na linha do tempo.
- Negativas: precisa de endpoint `reopen` (não listado na spec; adicionar ao OpenAPI).
- Testes: unitários cobrindo todas as transições válidas e inválidas; CON-01 para aprovações concorrentes.
