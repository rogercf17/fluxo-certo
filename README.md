# FluxoCerto

Plataforma web B2B multiempresa para gestão de contas a pagar, aprovações,
auditoria e projeção de fluxo de caixa. Projeto demonstrativo de portfólio:
usa dados fictícios, não movimenta dinheiro e não substitui ERP ou sistema bancário.

## Status
MVP em desenvolvimento (Sprint 0: setup técnico).

## Stack
Java 21, Spring Boot, PostgreSQL, Flyway · React, TypeScript, Vite · Docker Compose

## Como rodar
1. Copie `.env.example` para `.env` e defina `DB_PASSWORD`.
2. `docker compose up -d`
3. Backend: `cd backend && ./mvnw spring-boot:run`
4. Frontend: `cd frontend && npm install && npm run dev`

## Fora do escopo do MVP
Pagamentos reais, open finance, conciliação bancária, emissão fiscal, OCR/IA,
app mobile nativo e microsserviços.

## Decisões de arquitetura
Veja [docs/adr](docs/adr).