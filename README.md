# Aventureiros - Entrega Final AT

Projeto final da disciplina, consolidando as etapas desenvolvidas ao longo do trimestre.

O sistema reúne:

- camada web com endpoints REST
- persistência com Spring Data JPA e PostgreSQL
- domínio de auditoria (`audit`)
- domínio de aventura (`aventura`)
- consultas operacionais e relatórios
- integração com Elasticsearch
- uso de cache na consulta tática de missões

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Elasticsearch
- Maven

## Estrutura do projeto

- `audit`: organizações, usuários, papéis, permissões, auditoria e API keys
- `aventura`: aventureiros, companheiros, missões e participações
- `operacoes`: painel tático de missões
- `elastic`: buscas e agregações sobre o índice `guilda_loja`

## Como executar

### 1. PostgreSQL
Utilizar a imagem indicada pelo professor:

`leogloriainfnet/postgres-tp2-spring`

na tag compatível com o sistema operacional.

### 2. Elasticsearch
Utilizar a imagem indicada pelo professor:

`leogloriainfnet/elastic-tp2-spring`

Exemplo de execução:

```bash
docker run -d --name guilda-es -p 9200:9200 -e ES_JAVA_OPTS="-Xms512m -Xmx512m" elastic-tp2-spring:1.0-versao
