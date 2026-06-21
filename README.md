# Fitness Coaching System

A Spring Boot REST API for managing fitness coaching operations. The project includes authentication, authorization, email verification, file upload support, and Dockerized deployment.

## Features

### Authentication & Security

* JWT Authentication
* Refresh Token Support
* Role-Based Authorization
* Spring Security Integration
* Email Verification
* Protected Endpoints

### User Management

* User Registration
* User Login
* Get Current User Information
* Update User Information
* Delete User Account

### File Management

* Upload Files
* Download Files
* Unique File Naming
* File Storage Support

### Backend Features

* Global Exception Handling
* DTO-Based Request/Response Structure
* Validation Support
* MySQL Integration
* Spring Data JPA
* Docker Support
* Docker Compose Support

## Technologies

* Java 25
* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL
* Maven
* Docker
* Docker Compose

## Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
│   ├── request
│   └── response
├── security
├── exception
└── config
```

## Authentication Flow

1. User registers an account.
2. Verification email is sent automatically.
3. User verifies the account using the verification link.
4. User logs in and receives JWT tokens.
5. Protected endpoints require a valid access token.

## API Endpoints

### Authentication

| Method | Endpoint           | Description            |
| ------ | ------------------ | ---------------------- |
| POST   | /api/auth/register | Register a new account |
| POST   | /api/auth/login    | Login                  |
| GET    | /api/auth/verify   | Verify email address   |

### User

| Method | Endpoint        | Description      |
| ------ | --------------- | ---------------- |
| GET    | /api/users/me   | Get current user |
| PUT    | /api/users/{id} | Update user      |
| DELETE | /api/users/{id} | Delete user      |

### File

| Method | Endpoint             | Description        |
| ------ | -------------------- | ------------------ |
| POST   | /api/file/upload     | Upload file        |
| GET    | /api/file/{fileName} | Download/View file |

## Running Locally

### Clone Repository

```bash
git clone <repository-url>
cd fitness-coaching-system
```

### Configure Environment

Create the required environment variables for:

```text
DB_URL
DB_USERNAME
DB_PASSWORD

MAIL_USERNAME
MAIL_PASSWORD

JWT_SECRET
```

### Run Application

```bash
mvn spring-boot:run
```

## Running with Docker

Build the application:

```bash
mvn clean package -DskipTests
docker build -t fitness-coaching-system:latest -f Dockerfile.dockerfile .
```

Start containers:

```bash
docker compose up
```

Application:

```text
http://localhost:8080
```

MySQL:

```text
Host: localhost
Port: 3307
Username: root
Password: root
Database: fitness_coaching_system_db
```

## Future Improvements

* Password Reset via Email
* Redis Caching
* Unit Tests
* Integration Tests
* API Documentation
* Cloud Deployment
* CI/CD Pipeline

## Author

Bekir Yıldız
