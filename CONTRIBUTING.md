# Guia de contribuição

Este guia define o fluxo de trabalho do FluxoCerto. Mesmo em um projeto individual,
seguir um padrão mantém o histórico legível e o CI confiável.

## Fluxo de trabalho
1. Crie uma branch a partir da `main`.
2. Faça commits pequenos e focados.
3. Abra um Pull Request para a `main` preenchendo o template.
4. Aguarde o CI ficar verde e faça a revisão (por outra pessoa ou pelo checklist do template).
5. Faça o merge. Não faça push direto na `main`.

## Branches
Formato: `tipo/descricao-curta-em-kebab-case`

| Tipo | Uso |
|---|---|
| `feat/` | Nova funcionalidade (`feat/cadastro-fornecedores`) |
| `fix/` | Correção de bug (`fix/virgula-main-tsx`) |
| `docs/` | Documentação e ADRs (`docs/readme-frontend`) |
| `chore/` | Configuração, dependências e build (`chore/spotless`) |
| `test/` | Testes sem mudança de comportamento |
| `refactor/` | Refatoração sem mudança de comportamento |

## Commits
Seguimos [Conventional Commits](https://www.conventionalcommits.org/pt-br/), em português,
no imperativo e com a primeira linha de até 72 caracteres.

```
tipo(escopo): descrição curta

Corpo opcional explicando o porquê da mudança.
```

Exemplos:
- `feat(suppliers): cadastrar fornecedor por organização`
- `fix(frontend): remover vírgula solta no main.tsx`
- `docs(adr): registrar decisão sobre anexos`
- `chore(backend): configurar Spotless`

## Pull Requests
- Um PR por assunto, pequeno o suficiente para revisar.
- O título segue o mesmo padrão dos commits.
- O CI precisa estar verde antes do merge.
- Mudanças em regras de negócio, autorização ou banco exigem testes.
- Mudanças de arquitetura exigem um novo ADR em `docs/adr` (nunca edite um ADR aceito: crie um que o substitua).

## Estilo de código
- O `.editorconfig` define encoding, quebra de linha e indentação. Use um editor compatível.
- **Backend:** Spotless (google-java-format, estilo AOSP).
  - Verificar: `./mvnw spotless:check`
  - Corrigir: `./mvnw spotless:apply`
- **Frontend:** Prettier e ESLint.
  - Corrigir formatação: `npm run format`
  - Verificar: `npm run format:check` e `npm run lint`

## Antes de abrir o PR
```
# backend
cd backend && ./mvnw spotless:apply && ./mvnw verify

# frontend
cd frontend && npm run format && npm run lint && npm test && npm run build
```

## Segurança e dados
- Nunca faça commit de `.env`, senhas, tokens ou chaves.
- Use apenas dados fictícios (seeds, testes e prints).
- Valores monetários: `NUMERIC` no banco e `BigDecimal` no Java. Nunca `float` ou `double`.
- Autorização e escopo de organização sempre no servidor.