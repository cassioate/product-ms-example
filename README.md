# product-ms

# Catálogo de Produtos API Rest - Java Spring - DESAFIO ANTIGO PARA FINS DE APRENDIZADO.

#### Autor: Cássio Aragão Tessaro | [LinkedIn](https://www.linkedin.com/in/ctessaro/)

## Requisitos do Projeto:

Desafio: [click](/desafio/desafio.md)

- Porta do projeto: 9999

## Acessar Swagger:

após iniciar o projeto entre no link:

http://localhost:9999/swagger-ui/

![Swagger-img](/desafio/assets/Swagger.png)

## Tecnologias/Dependências:

:ballot_box_with_check: Java 11
:ballot_box_with_check: Spring Boot
:ballot_box_with_check: JPA
:ballot_box_with_check: Spring Cache
:ballot_box_with_check: Lombok
:ballot_box_with_check: Validation
:ballot_box_with_check: Actuator
:ballot_box_with_check: Swagger
:ballot_box_with_check: Docker/Docker-compose
:ballot_box_with_check: Junit/Mockito/MockMvc
:ballot_box_with_check: PostgreSQL
:ballot_box_with_check: H2
:ballot_box_with_check: Devtools
:ballot_box_with_check: Jacoco

## Rodar projeto utilizando o maven:

Abra um terminal na pasta raiz do projeto e utilize:

```shell
mvn clean package spring-boot:run -Dmaven.test.skip=true
```
