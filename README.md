# User Registry Application

Este projeto é uma aplicação de registro de usuários, construída com Java e Spring Boot, que utiliza PostgreSQL como banco de dados.

## Pré-requisitos

- Docker e Docker Compose instalados na sua máquina.

## Estrutura do Docker

### Dockerfile

O `Dockerfile` utilizado para construir a aplicação é o seguinte:

```dockerfile
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]







```O arquivo docker-compose.yml para orquestrar a aplicação e o banco de dados
version: '3.8'

services:
  postgres:
    image: postgres:15  
    container_name: postgres_db  
    environment:
      POSTGRES_USER: postgres         
      POSTGRES_PASSWORD: postgres 
      POSTGRES_DB: postgres       
    ports:
      - "3003:5432"  
    volumes:
      - postgres_data:/var/lib/postgresql/data  

  user-registry:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/postgres
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres
      SPRING_APPLICATION_NAME: user-registry
      SERVER_SERVLET_CONTEXT_PATH: /user-registry

volumes:
  postgres_data:
    driver: local
