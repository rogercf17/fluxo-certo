# FluxoCerto

Plataforma web B2B multiempresa para gestão de contas a pagar, aprovações,
auditoria e projeção de fluxo de caixa. Projeto demonstrativo de portfólio:
usa dados fictícios, não movimenta dinheiro e não substitui ERP ou sistema bancário.

## Status
MVP em desenvolvimento (Sprint 0: setup técnico).

## Stack
Java 21, Spring Boot, PostgreSQL, Flyway · React, TypeScript, Vite · Docker Compose

## Estrutura do repositório
| Pasta | Conteúdo |
|---|---|
| `backend/` | API Spring Boot (monólito modular) e migrações Flyway |
| `frontend/` | Aplicação React + TypeScript (Vite) |
| `docs/adr/` | Decisões de arquitetura ([índice](docs/adr/README.md)) |
| `docker-compose.yml` | Banco PostgreSQL para desenvolvimento local |

## Pré-requisitos
- Java 21 (JDK)
- Node.js 22 e npm
- Docker (com Docker Compose). Também é necessário para os testes do backend, que usam Testcontainers.

## Como rodar
1. Copie `.env.example` para `.env` e defina `DB_PASSWORD`.
2. Suba o banco: `docker compose up -d`
3. Backend: `cd backend && ./mvnw spring-boot:run`
4. Frontend: `cd frontend && npm install && npm run dev`

| Serviço | Endereço |
|---|---|
| Frontend (Vite) | http://localhost:5173 |
| Backend (API) | http://localhost:8080 |
| Health check | http://localhost:8080/actuator/health (deve responder `{"status":"UP"}`) |

O frontend encaminha `/api` e `/actuator` para o backend em `localhost:8080` durante o desenvolvimento.

### Variáveis de ambiente
| Variável | Padrão | Observação |
|---|---|---|
| `DB_PASSWORD` | — | Obrigatória |
| `DB_USER` | `fluxocerto` | |
| `DB_NAME` | `fluxocerto` | |
| `DB_PORT` | `5432` | |

O arquivo `.env` não é versionado. Nunca faça commit de credenciais reais.

## Como rodar os testes
| Onde | Comando | Observação |
|---|---|---|
| Backend | `cd backend && ./mvnw verify` | Sobe um PostgreSQL via Testcontainers; o Docker precisa estar ligado |
| Frontend | `cd frontend && npm test` | Vitest + Testing Library |
| Frontend (lint) | `cd frontend && npm run lint` | ESLint |
| Frontend (build) | `cd frontend && npm run build` | Checagem de tipos + build de produção |

O mesmo conjunto roda no CI (GitHub Actions) a cada push na `main` e em cada pull request.

## Documentação
- Especificação de produto e engenharia: [`docs/FluxoCerto_especificacao_squad.pdf`](docs/FluxoCerto_especificacao_squad.pdf)
- Decisões de arquitetura: [`docs/adr`](docs/adr)

## Fora do escopo do MVP
Pagamentos reais, open finance, conciliação bancária, emissão fiscal, OCR/IA,
app mobile nativo e microsserviços.