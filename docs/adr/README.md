# Architecture Decision Records (ADR)

Registro das decisões de arquitetura e produto do FluxoCerto. Cada ADR descreve o contexto, a decisão, as alternativas consideradas e as consequências. Para mudar uma decisão, crie um novo ADR que substitua o anterior (e marque o antigo como "Substituída por ADR-XXXX"); não apague o histórico.

| ADR | Título | Status |
|---|---|---|
| [0001](0001-monolito-modular.md) | Monólito modular como arquitetura inicial | Aceita |
| [0002](0002-autenticacao-sessao-cookie.md) | Autenticação por sessão com cookie HttpOnly | Aceita |
| [0003](0003-multitenancy-por-organizacao.md) | Multi-tenancy por coluna `organization_id` | Aceita |
| [0004](0004-um-aprovador-no-mvp.md) | Um aprovador por despesa no MVP | Aceita |
| [0005](0005-limite-de-aprovacao-e-segregacao.md) | Limite de aprovação e segregação de funções | Aceita |
| [0006](0006-armazenamento-de-anexos.md) | Armazenamento de anexos via `StorageService` | Aceita |
| [0007](0007-notificacoes-no-mvp.md) | Notificações no MVP | Aceita |
| [0008](0008-auditoria-e-retencao.md) | Auditoria atômica e retenção | Aceita |
| [0009](0009-acesso-entre-organizacoes-retorna-404.md) | Acesso entre organizações retorna 404 | Aceita |
| [0010](0010-maquina-de-estados-da-despesa.md) | Máquina de estados da despesa (envio e rejeição) | Aceita |
| [0011](0011-dinheiro-datas-e-fuso.md) | Representação de dinheiro, datas e fuso | Aceita |

Modelo para novos ADRs: [0000-template.md](0000-template.md)
