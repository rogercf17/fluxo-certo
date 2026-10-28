# ADR-0001: Monólito modular como arquitetura inicial

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
O FluxoCerto é um MVP de portfólio desenvolvido por uma pessoa. É preciso testar regras de negócio (aprovação, estados, projeção) sem acoplá-las à camada web e manter o custo operacional baixo.

## Decisão
Uma única aplicação Spring Boot (Java 21), organizada em módulos com fronteiras claras: `identity`, `suppliers`, `expenses`, `approvals`, `cashflow`, `audit` e `notifications`. Cada módulo separa API (controllers), aplicação (services), domínio e infraestrutura. Um único PostgreSQL é a fonte de verdade. O frontend (React + TypeScript + Vite) é um projeto separado no mesmo repositório.

## Alternativas consideradas
- **Microsserviços:** custo de deploy, observabilidade e comunicação entre serviços não se justifica no MVP.
- **Monólito sem módulos:** mais rápido no início, mas as regras de negócio acabam espalhadas e difíceis de testar.

## Consequências
- Positivas: um deploy, transações locais, setup simples com Docker Compose.
- Negativas: fronteiras entre módulos dependem de disciplina (não há isolamento físico).
- Ação: módulos não acessam repositórios uns dos outros; a comunicação passa por services ou eventos de domínio. Redis, RabbitMQ e outros componentes só entram quando houver necessidade medida.
