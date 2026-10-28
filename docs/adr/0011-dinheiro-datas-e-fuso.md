# ADR-0011: Representação de dinheiro, datas e fuso

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
Erros de arredondamento e de fuso horário são fontes clássicas de bugs financeiros (BR-02, BR-11).

## Decisão
- **Dinheiro:** `NUMERIC(19,2)` no PostgreSQL e `BigDecimal` no Java. Moeda do MVP: BRL, validada contra um conjunto suportado, com CHECK de valor positivo. Nunca `float` ou `double`.
- **API:** valores monetários como strings decimais (`"1234.56"`); o cliente não calcula totais que o servidor considera oficiais.
- **Instantes:** `Instant`/`TIMESTAMPTZ`, sempre em UTC (criação, decisões, auditoria).
- **Vencimento:** `LocalDate`/`DATE` (data civil, sem hora).
- **Apresentação:** o fuso fica em `Organization.timezone` (padrão `America/Sao_Paulo`) e é aplicado só na exibição.
- **Projeção de caixa:** saldo projetado = saldo inicial + entradas previstas − saídas previstas no horizonte; rotulada como projeção, nunca como saldo bancário.

## Alternativas consideradas
- **Inteiros em centavos:** válido, mas `NUMERIC` com `BigDecimal` é o que a spec adota e é legível em consultas.

## Consequências
- Positivas: cálculos exatos; teste MNY-01 (10,10 + 0,20 = 10,30) cobre a regra.
- Ação: definir `RoundingMode` explícito (HALF_EVEN) onde houver divisão ou percentual.
