# ADR-0002: Autenticação por sessão com cookie HttpOnly

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
A API precisa autenticar usuários e identificar a organização ativa em cada requisição (FR-01, BR-01). A spec deixa em aberto a escolha entre sessão por cookie e tokens, e proíbe guardar token de sessão em `localStorage` sem análise de risco.

## Decisão
- Sessão no servidor (Spring Security), identificada por cookie `HttpOnly`, `Secure` e `SameSite=Lax`.
- Proteção CSRF ativa, com token enviado em cabeçalho (`X-XSRF-TOKEN`) pelo frontend em requisições que alteram estado.
- CORS com origem do frontend explícita (sem curinga) e `allowCredentials=true`.
- Frontend e backend servidos no mesmo domínio ou em subdomínios do mesmo site no ambiente publicado.
- Senhas com hash forte via `DelegatingPasswordEncoder` (BCrypt ou Argon2).
- Respostas de login e logs nunca contêm senha, token ou identificador de sessão.

## Alternativas consideradas
- **JWT em header (localStorage):** exposto a roubo via XSS; revogação mais difícil.
- **JWT em cookie HttpOnly:** reintroduz CSRF sem a simplicidade da sessão nativa do Spring Security.

## Consequências
- Positivas: o JavaScript não lê a credencial; revogação imediata; integração nativa com Spring Security.
- Negativas: sessão tem estado no servidor. Com mais de uma instância, será preciso sessão compartilhada (Spring Session JDBC, sem exigir Redis).
- Ação: documentar variáveis de CORS e domínio no README; cobrir CSRF e logout com testes de API.
