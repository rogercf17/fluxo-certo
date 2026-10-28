# FluxoCerto — Frontend

Interface web do FluxoCerto, construída com React e TypeScript sobre o Vite.
Para a visão geral do projeto, veja o [README da raiz](../README.md).

## Stack

- React 19 + TypeScript
- Vite
- TanStack Query (dados do servidor)
- React Hook Form + Zod (formulários e validação)
- React Router
- Tailwind CSS
- Vitest + Testing Library (testes)
- ESLint

## Pré-requisitos

- Node.js 22 e npm
- Backend rodando em `http://localhost:8080` (veja o README da raiz)

## Scripts

| Comando           | O que faz                                                    |
| ----------------- | ------------------------------------------------------------ |
| `npm install`     | Instala as dependências (no CI usa-se `npm ci`)              |
| `npm run dev`     | Servidor de desenvolvimento em http://localhost:5173         |
| `npm run build`   | Checa tipos (`tsc -b`) e gera o build de produção em `dist/` |
| `npm run preview` | Serve o build de produção localmente                         |
| `npm run lint`    | Roda o ESLint                                                |
| `npm test`        | Roda os testes uma vez (Vitest)                              |

## Proxy para o backend

No desenvolvimento, o Vite encaminha as requisições abaixo para `http://localhost:8080`
(veja `vite.config.ts`). Assim o frontend usa caminhos relativos, sem configurar CORS:

- `/api` → API do FluxoCerto
- `/actuator` → health check do backend

## Testes

- Os testes ficam ao lado do código (`*.test.tsx`) e rodam em jsdom.
- A configuração global está em `src/setupTests.ts` (matchers do `jest-dom`).
- Chamadas ao backend são simuladas nos testes (`fetch` mockado); nenhum teste precisa do backend no ar.
- Os testes verificam a interface. Permissões na UI nunca substituem a autorização no backend.

## Estrutura

```
src/
  main.tsx        ponto de entrada e providers (React Query)
  App.tsx         componente raiz
  App.test.tsx    teste do componente raiz
  setupTests.ts   configuração dos testes
  index.css       estilos globais (Tailwind)
```

## Convenções

- Dados do servidor via TanStack Query; formulários com React Hook Form + Zod.
- Valores monetários chegam da API como strings decimais. Não faça cálculos financeiros com `number`.
- Toda tela deve tratar os estados de carregamento, vazio, sucesso e erro.
- Antes de abrir um PR, rode `npm run lint`, `npm test` e `npm run build`.
