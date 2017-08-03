
#Tecnologias utilizadas

Java 8
Spring Boot
Spring Data
Tomcat embutido
H2database embutido
JPA
Swagger
Maven
JUNIT
#Arquitetura do projeto

Ambiente de execução embutido - Utilizado o Tomcat embutido, disponibilizado pelo SpringBoot.

REST - Esta aplicação disponibiliza um serviço REST para disponibilizar recursos de uma API de cadastro de campanhas. Recebe consome e responde no formato JSON.
Banco de Dados embutido - Utilizado o banco de dados H2database em memória.

#Pré requisitos

*Java 8
*Maven
*Configurações

Arquivos de propriedades da aplicação disponiveis no caminho: src/main/resources

*Application.properties : arquivo de propriedades do Spring 
*data.sql: Arquivo disponibilizado com carga de dados inicial executado automaticamente

A aplicação está configurada para subir na porta 8082, para alterar a mesma acessar arquivo Application.properties e alterar atributo server.port.

*Execução do projeto Maven: $ mvn clean package spring-boot:run

*Execução via eclipse ou equivalente: Executar a classe /torcedor/src/main/java/br/com/programa/torcedor/TorcedorApplication.java

*Execução via browser URL: http://localhost:8082/swagger-ui.html para visualizar todos os serviços disponiveis.
