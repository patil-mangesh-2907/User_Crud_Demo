# User CRUD Demo

A backend REST API built with **Java and Spring Boot** to practice real-world backend development concepts and Git/GitHub workflow.

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Git & GitHub

## Features

- User CRUD Operations
- Soft Delete & Hard Delete
- DTO-based Request & Response
- Bean Validation
- Global Exception Handling
- Custom Exceptions
- Layered Architecture
- Profile-based Notification Services
- Dev, Prod & Test Profiles
- Profile-specific Configuration using `@Value`

## API Endpoints

- `POST /users` — Create User
- `GET /users` — Get All Users
- `GET /users/{id}` — Get User by ID
- `PUT /users/{id}` — Update User
- `PATCH /users/{id}/soft-delete` — Soft Delete User
- `DELETE /users/{id}` — Permanently Delete User
- `GET /users/deleted` — Get Deleted Users

## Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database