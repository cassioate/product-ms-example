# product-ms

# Catálogo de Produtos API Rest - Java Spring - DESAFIO ANTIGO PARA FINS DE APRENDIZADO.

#### Autor: Cássio Aragão Tessaro | [LinkedIn](https://www.linkedin.com/in/ctessaro/)

### Requisitos do Projeto:

Desafio: [click](/desafio/desafio.md)

- Porta do projeto: 9999

### Tecnologias/Dependências:

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

## RODANDO A APLICAÇÃO

### OPÇÃO 1 - Rodar projeto utilizando o maven:

Abra um terminal na pasta raiz do projeto e utilize:

```shell
mvn clean package spring-boot:run -Dmaven.test.skip=true
```

### OPÇÃO 2 - Rodar projeto via IDE:

Siga os passos nas imagens(A sua IDE possivelmente será diferente, mas a logica por trás será a mesma):

Clique em Maven Build
![clean-install](/desafio/assets/clean-install.png)

Insira o seguinte: "clean package spring-boot:run -Dmaven.test.skip=true" e depois aperte em run
![clean-install](/desafio/assets/clean-install-2.png)

Agora é só esperar a aplicação subir.

### Acessar Swagger:

após iniciar o projeto entre no link:

http://localhost:9999/swagger-ui/

![Swagger-img](/desafio/assets/Swagger.png)

## TESTANDO A APLICAÇÃO

### OPÇÃO 1 - Rodar os testes do projeto utilizando o maven:

Abra um terminal na pasta raiz do projeto e utilize:

```shell
mvn clean test
```

### OPÇÃO 2 - Rodar os testes do projeto via IDE:

Siga os passos nas imagens(A sua IDE possivelmente será diferente, mas a logica por trás será a mesma):

Clique em Maven Build
![test-1](/desafio/assets/test3.png)

Insira o seguinte: "clean test" e depois aperte em run
![test-2](/desafio/assets/test4.png)
