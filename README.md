# TechnicalTest

Projeto gerado em Java 17.

Foi utilizado Spring Boot na construção da solução.

O projeto se encontra na versão 0.0.1

## O Projeto
O projeto é uma aplicação Rest back-end que teve por objetivo implementar um serviço de agendamento de transferências financeiras.

## Escolhas
Escolhi a arquitetura hexagonal como padrão arquitetural a fim de dividir a aplicação em camadas e isolar o dominio da aplicação, 
bem como o uso de inversão de dependencias.

## Instalação e Execução

Realizar o download ou clone do projeto através do GitHub.

```
https://github.com/rfelipesp/technical-test-back.git
```

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:

```
Java 17
Maven 3.9.4 
```

Feito isso, acesse o projeto:

cd technical-test
É preciso compilar o código e baixar as dependências do projeto:

```
mvn clean package
```

Finalizado esse passo, vamos iniciar a aplicação:

```
mvn spring-boot:run
```

Ao iniciar a aplicação ela carrega através do arquivo data.sql, localizado no diretório resources, os dados básicos referentes as taxas de transferência.

A aplicação estará disponível em http://localhost:8080

## Testes

Foram realizados testes unitários e de integração contemplando uma coberura superior a 90%

