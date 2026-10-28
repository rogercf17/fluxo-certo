# ADR-0005: Limite de aprovação e segregação de funções

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
BR-03 diz que valores acima do limite exigem aprovação e que limites são configuráveis por organização. A spec não define o que acontece abaixo do limite nem o padrão da segregação de funções (BR-04).

## Decisão
- A organização tem `approval_limit` (NUMERIC, BRL). O valor padrão é **0,00**, ou seja, toda despesa exige aprovação até que a organização configure outro limite.
- Despesa com valor **acima do limite** vai para `PENDING_APPROVAL` ao ser enviada.
- Despesa com valor **igual ou abaixo do limite** vai direto para `APPROVED`, com evento de auditoria `EXPENSE_AUTO_APPROVED` registrando a política aplicada.
- A organização tem a flag `require_segregation_of_duties`, **ativa por padrão**. Com ela ativa, o solicitante (`createdBy`) não pode aprovar a própria despesa (403, sem evento de negócio criado).
- A política é avaliada no servidor, sobre o valor persistido; nunca sobre valores calculados pelo cliente.
- Alterar valor, fornecedor ou vencimento após a aprovação invalida a aprovação anterior e exige nova aprovação (BR-05).

## Alternativas consideradas
- **Tudo exige aprovação, sempre:** ignora a configuração de limite pedida pela spec.
- **Segregação desligada por padrão:** padrão inseguro para um sistema financeiro.

## Consequências
- Positivas: padrão seguro e regra fácil de demonstrar (o seed define um limite para mostrar os dois caminhos).
- Negativas: um limite mal configurado aprova despesas automaticamente; por isso a aprovação automática é sempre auditada.
- Testes: WF-02 (autoaprovação), valor no limite exato, valor acima e abaixo, edição após aprovação.
