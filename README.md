# API Cadastro de Animais para Adocao

A API foi desenvolvida para gerenciar o cadastro de animais para adoção. Seu contexto de desenvolvimento é o exercício
de boas práticas de programação orientada a objeto, bem como a aplicação das arquiteturas de mercado. 
Boas práticas utilizando padrões de projeto, princípios SOLID e conceitos de Clean Code, além do desenvolvimento de testes unitários e automatizados utilizando JUnit e Mockito.   

---

## Tecnologias usadas

- Java 17
- Spring Boot
- PostgreSQL
- Docker & Docker Compose
- MapperStruct
- Gernciador de dependências Maven
---

## Pré-requisitos

- Docker instalado ([link](https://docs.docker.com/get-docker/))
- Docker Compose instalado (normalmente já vem com Docker Desktop)

---
## Sobre o Projeto

Trata-se de um projeto gerenciador de animais, contendo apenas um modulo: **Animal**.

O sistema execulta a atribuições de um CRUD básico.

Conta com uma única classe: **Animal** que possui os seguintes atributos:

- id
- nome
- especie
- raca
- idade
- disponivel
- dataDeResgate

O **id** é a chave primária que faz uso da estrategia **GenerationType.IDENTITY**, para persistir no banco de dados.

---
## Do Banco de Dados 

O projeto faz uso do **postgres**, que deve se instalado em um container no docker;

Na raiz do projeto, o arqivo .env contém todos os dados do banco.

---

## Configuração do ambiente


O `docker-compose.yml` está na pasta docker na raiz do projeto, e configurado para usar as variáveis contidas no arquivo **.env**, já citado.

---

## Executando o projeto com Docker

No terminal, na sub-pasta (docker) do projeto, execute:

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

Realizei testes unitários utilizando JUnit e Mockito. Criei classes Fixitures para reutilização de código nos testes. Compreendo que testes são uma parte fundamental de uma aplicação.

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