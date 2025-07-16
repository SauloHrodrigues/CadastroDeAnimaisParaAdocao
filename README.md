# CadastroDeAnimaisParaAdocao


# Projeto Animais para Adoção

Aplicação para gerenciamento de adoção de animais usando Spring Boot e PostgreSQL com Docker.

---

## Tecnologias usadas

- Java 19+
- Spring Boot
- PostgreSQL
- Docker & Docker Compose

---

## Pré-requisitos

- Docker instalado ([link](https://docs.docker.com/get-docker/))
- Docker Compose instalado (normalmente já vem com Docker Desktop)

---

## Configuração do ambiente

1. Crie um arquivo `.env` na raiz do projeto com as variáveis abaixo:

```env
POSTGRES_DB=banco_de_adocao
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/banco_de_adocao
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
```

2. Verifique se o `docker-compose.yml` está na raiz e configurado para usar essas variáveis.

---

## Executando o projeto com Docker

No terminal, na pasta do projeto, execute:

```bash
docker-compose up --build
```

Isso vai:

- Construir a imagem da aplicação
- Subir os containers da aplicação e do banco PostgreSQL
- Configurar o banco com as variáveis do `.env`

A aplicação ficará disponível em: [http://localhost:8080](http://localhost:8080)

---

## Parando a execução

Para parar e remover os containers, execute:

```bash
docker-compose down
```

---

## Rodando localmente (sem Docker)

Se preferir rodar localmente, configure seu banco PostgreSQL e altere o arquivo `application.properties` ou `application.yml` do Spring Boot com as credenciais corretas.

---

## Testes

Para rodar testes unitários, use:

```bash
./mvnw test
```

ou

```bash
mvn test
```

(se tiver Maven instalado)

---

## Estrutura do projeto

- `src/main/java`: Código fonte da aplicação
- `src/main/resources`: Configurações e arquivos estáticos
- `docker-compose.yml`: Configuração do Docker Compose
- `.env`: Variáveis de ambiente (não versionar se conter dados sensíveis)
- `Dockerfile`: Dockerfile para a aplicação

---

## Considerações finais

- Certifique-se que as portas 8080 (app) e 5433 (Postgres) estão livres no seu sistema.
- Para limpar volumes e dados do banco, remova o volume `postgres-data` com:

```bash
docker volume rm postgres-data
```

---