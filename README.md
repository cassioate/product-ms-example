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
:ballot_box_with_check: Flyway

## RODANDO A APLICAÇÃO

##### OBS: O Banco que será utilizado no ambiente local, será o H2 em memoria, no fim desse Readme estará a forma de utilizar o docker, e nele será utilizado o banco PostgreSQL.

- ### OPÇÃO 1 - Rodar projeto utilizando o maven:

  Abra um terminal na pasta raiz do projeto e utilize:

  ```shell
  mvn clean package spring-boot:run -Dmaven.test.skip=true
  ```

- ### OPÇÃO 2 - Rodar projeto via IDE:

  Siga os passos nas imagens(A sua IDE possivelmente será diferente, mas a logica por trás será a mesma):

  Clique em Maven Build

  ![clean-install](/desafio/assets/clean-install.png)

  Insira o seguinte: "clean package spring-boot:run -Dmaven.test.skip=true" e depois aperte em run

  ![clean-install](/desafio/assets/clean-install-2.png)

  Agora é só esperar a aplicação subir.

- ### Acessar Swagger:

  após iniciar o projeto entre no link:

  http://localhost:9999/swagger-ui/

  ![Swagger-img](/desafio/assets/Swagger.png)

## TESTANDO A APLICAÇÃO

- ### OPÇÃO 1 - Rodar os testes do projeto utilizando o maven:

  Abra um terminal na pasta raiz do projeto e utilize:

  ```shell
  mvn clean test
  ```

- ### OPÇÃO 2 - Rodar os testes do projeto via IDE:

  Siga os passos nas imagens(A sua IDE possivelmente será diferente, mas a logica por trás será a mesma):

  Clique em Maven Build

  ![test-1](/desafio/assets/test3.png)

  Insira o seguinte: "clean test" e depois aperte em run

  ![test-2](/desafio/assets/test4.png)

- ### Verificar cobertura dos testes:

  Para garantir que esta tudo ok, execute os seguintes passos antes:

  Clique em Maven Build

  ![clean-install](/desafio/assets/clean-install.png)

  Insira o seguinte: "clean test" e depois aperte em run

  ![test4](/desafio/assets/test4.png)

  Procure o arquivo index.html mostrado na imagem abaixo

  ![test-jacoco-1](/desafio/assets/test-jacoco-1.png)

  Com o botão direito do mouse no arquivo index.html clique em open-with e depois clique em web-browser

  ![test-jacoco-1](/desafio/assets/test-jacoco-2.png)

  Agora você pode verificar a cobertura do teste

  ![test-jacoco-3](/desafio/assets/test-jacoco-3.png)

## UTILIZANDO DOCKER:

Requisitos: `maven`, `docker` e `docker-compose`.

Com docker rodando, abra um terminal e execute o comando na pasta raiz do projeto:

```shell
mvn clean package -DskipTests && docker-compose up -d --build
```

## Finalizar container e remover volume/imagem/containers:

```shell
docker-compose down -v --remove-orphans
```

##### OBS: Ao rodar com o docker, o perfil de ambiente que será utilizado será o de DEV, utilizando assim o banco PostgreSQL.
