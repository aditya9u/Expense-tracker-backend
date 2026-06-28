# Expense-tracker-backend
This is a backend project which is used to track expenses in day to day basis

## Features

## Tech Stack 
Spring Boot
MySql


## Prerequisites

## Installation

## Clone Repository

```bash
git clone https://github.com/aditya9u/Expense-tracker-backend.git
```

## Current Status

✅ Spring Boot Project Created
✅ Spring Data JPA Added
✅ MySQL Driver Added
✅ Profiles Configured
✅ Package Structure Created
✅ MySQL Docker Container Created
✅ Spring boot application connected to docker Container
✅ Actuators included 
✅ CRUD Operations for Category included
✅ Exception Handling for Category not found
✅ dto for ExcepionResponse and GlobalException handled



## Next Steps

1. Expense CRUD
2. User CRUD
3. DTOs
4. Validation
5. More Exception Handling
6. Security (JWT)
## Entity Relationship Diagram

User (1)
   │
   │ OneToMany
   ▼
Expense (*)
   ▲
   │ ManyToOne
   │
Category (1)
