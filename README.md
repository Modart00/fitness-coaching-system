# Fitness Coaching System

Role-based fitness coaching backend application built with Spring Boot.

## Features

- JWT Authentication
- Role Based Authorization
- User Management
- Workout Plan Management
- Exercise Management
- Progress Tracking
- Global Exception Handling
- Request Validation

## Roles

### USER
- View own profile
- View assigned workout plans
- View progress history

### COACH
- Create and manage exercises
- Create workout plans for users
- Create and update user progress

### ADMIN
- Full system access

## Technologies

- Java 25
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- MySQL
- Hibernate
- Lombok

## Getting Started

1. Clone the repository
2. Configure MySQL connection in application.properties
3. Run the application
4. Use /api/auth/register and /api/auth/login endpoints
