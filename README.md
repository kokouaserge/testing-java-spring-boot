# SpringBoot_PostgreSQL_CRUD_RESTAPI

Users Managment CRUD Operations using SpringBoot &amp; PostgreSQL

## Tools & Library used

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Spring Boot 2+]
- [Apache Commons]
- [Spring Data JPA]
- [PostgreSQL Connector]

## Running the application locally

1. Config DB postgres Local

Change in resources/application.properties
spring.datasource.url = YOUR_DB_POSTGRES_URL

2. Build the project using
   `mvn clean install`

3. Run using
   `mvn spring-boot:run`

4.See Swagger Doc API

url = http://localhost:3000/api/swagger-ui/index.html

Enjoy
