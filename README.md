# Expense-tracker-backend
This is a backend project which is used to track expenses in day to day basis

## Features
User Management<br> 
Category Management<br> 
Expense Management<br> 
Validation<br> 
Global Exception Handling<br> 
Swagger Documentation<br> 

## Tech Stack 
Spring Boot<br> 
Spring Data JPA<br> 
MySQL<br>
Docker<br>
Maven<br> 
Swagger/OpenAPI<br>


## Prerequisites
Before running this project, ensure the following are installed:

Java 21 (or your project's Java version)<br>
Maven 3.9+<br>
Docker Desktop<br>
Git<br> 

## Installation
MySQL Database

The application uses MySQL running inside a Docker container.

Start MySQL using Docker before running the application.

Example:

```bash
docker run -d \
  --name expense-tracker-mysql \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=expense_tracker \
  -p 3306:3306 \
  mysql:8.0
```
Configuration

Update the datasource configuration in:

see [application-dev.properties](src/main/resources/application-dev.properties)

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=root
Running the Application
mvn spring-boot:run
Swagger Documentation

After the application starts:

```bash
http://localhost:8080/swagger-ui/index.html
```

## Clone Repository

```bash
git clone https://github.com/aditya9u/Expense-tracker-backend.git
```

## Current Status
v 0.1.0
==================================================
```text
1. Spring Boot Project Created 
2. Spring Data JPA Added 
3. MySQL Driver Added 
4. Profiles Configured 
5. Package Structure Created 
6. MySQL Docker Container Created 
7. Spring boot application connected to docker Container 
8. Actuators included 
```

v 0.2.0
=========================================================
```text
1. CRUD Operations for Category included. 
2. Exception Handling for Category not found. 
3. dto for ExceptionResponse and GlobalException handled. 
4. CRUD Operations for User and Expense is created. 
5. Exception handling for usernotfound and expensenotfound is created. 
6. Validation of all dtos are created. 
7. Swagger is included for api documentation. 
```


## Next Steps

1. Pagination
2. Expense Filtering 
3. Search APIs
4. Auditing (createdAt, updatedAt)
5. Register/Login apis
6. Spring Security (JWT)
## Entity Relationship Diagram
```text
User (1)
   │
   │ OneToMany
   ▼
Expense (*)
   ▲
   │ ManyToOne
   │
Category (1)
