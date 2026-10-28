# ADR-0006: Armazenamento de anexos via `StorageService`

- **Status:** Aceita
- **Data:** 2026-10-27

## Contexto
Despesas aceitam anexos (FR-04). Em desenvolvimento, o armazenamento deve ser simples; no ambiente publicado, deve ser privado e durável (BR-12).

## Decisão
- Interface `StorageService` no módulo `expenses`, com duas implementações: sistema de arquivos local (dev e testes) e bucket privado S3-compatível (ambiente publicado), escolhidas por configuração.
- O arquivo é salvo com nome interno (UUID) em `objectKey`; o nome original fica só como metadado (`fileName`).
- Validação no servidor: limite de tamanho (10 MB por arquivo), allowlist de tipos (PDF, PNG, JPEG) conferida pelo conteúdo, e não só pela extensão.
- Nenhum acesso público. Download autorizado no backend (tenant + permissão), via proxy ou URL assinada de curta duração.
- `sha256` é calculado e pode **sinalizar** arquivo idêntico para revisão, sem bloquear a cobrança (BR-09).

## Alternativas consideradas
- **Guardar arquivos no PostgreSQL:** simples, mas infla o banco e os backups.
- **S3 também em dev:** maior fidelidade, mas exige serviço extra no Docker Compose; pode ser adotado com MinIO se for útil.

## Consequências
- Positivas: o código da aplicação não depende do provedor.
- Negativas: é preciso testar as duas implementações.
- Ação: nunca registrar o conteúdo dos arquivos nos logs.
