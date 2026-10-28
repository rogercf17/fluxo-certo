# ADR-0003: Multi-tenancy por coluna `organization_id`

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
Todos os registros de negócio pertencem a uma organização (BR-01). Falhas de isolamento expõem dados entre empresas e são o risco de maior impacto do projeto.

## Decisão
- Toda tabela de negócio tem `organization_id` (NOT NULL, com chave estrangeira e índice).
- O tenant é **derivado da identidade autenticada** (Membership ativa na sessão), nunca de `organizationId` enviado pelo frontend.
- Toda consulta e alteração filtra por `organization_id` na camada de serviço/repositório. Um usuário pode ter vínculo com mais de uma organização; a troca de organização ativa valida o Membership.
- As permissões são checadas por papel (Administrador, Financeiro, Aprovador, Solicitante, Auditor) em cada requisição.
- Testes automatizados contra IDOR/BOLA são obrigatórios (caso SEC-01).

## Alternativas consideradas
- **Schema por tenant / banco por tenant:** isolamento forte, mas migrações e operação muito mais complexas para o escopo.
- **Row Level Security do PostgreSQL:** boa defesa em profundidade; fica como evolução possível, não é requisito do MVP.

## Consequências
- Positivas: modelo simples, uma migração Flyway serve todos os tenants.
- Negativas: um filtro esquecido vaza dados. Mitigação: repositórios sempre recebem o `organizationId` do contexto de segurança, e os testes de integração (Testcontainers) cobrem cada recurso.
