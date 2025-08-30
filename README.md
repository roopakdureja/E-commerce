# E-Commerce Backend (Spring Boot + PostgreSQL)
---
An E-Commerce Backend Application built with Java Spring Boot and PostgreSQL.  
It demonstrates real-world backend engineering practices including clean architecture, secure authentication, and integration with external services.
---

## Features

- **Database Design & Relationships**
    - Multiple entity relationship mappings (OneToOne, OneToMany, ManyToMany)
    - Connected to **PostgreSQL RDS instance**

- **Backend Architecture**
    - Layered architecture (Controller → Service → Repository → Entity)
    - DTOs for clean request/response handling
    - Global Exception Handling
    - Pagination and Sorting
    - Input Validations
    - Lombok for boilerplate reduction
    - Swagger UI – interactive API documentation

- **Security**
    - JWT Authentication & Authorization
    - Role-Based Access Control (Admin/User)
    - Google OAuth2 Login (external API integration using `RestTemplate`)
    - To access the API, you will need to obtain a JWT by authenticating with the /signi
    - n endpoint. The JWT should then be passed in the Authorize option available in the Swagger-ui.

- **Business Features**
    - Order Management – order placement and persistence
    - Email order confirmation (SMTP Integration)

---

## Tech Stack
- Backend: Java, Spring Boot, Spring Security
- Database: PostgreSQL (AWS RDS)
- Authentication: JWT + Google OAuth2
- Others: Lombok, SMTP (Gmail), Maven

---

## Example API Usage

### User Signup
**Request**
```
POST /api/auth/signup
Content-Type: application/json

{
    "username":"roopak",
    "email":"roopak033@gmail.com",
    "password":"password1",
    "role":["admin/user/seller"]
}
```
**Response**
```
{
  "User registered successfully!"
}
```
---

## Setup & Installation

1. Clone the repository
   ```bash
   git clone https://github.com/roopakdureja/E-commerce.git
   cd sb-ecomm
   ```
2. Configure environment variables in application.properties file
3. Run the application
    ```bash
   mvn spring-boot:run
   ```
4. Access Swagger UI - http://localhost:8080/swagger-ui/index.html

---

## Author
Roopak Dureja<br>
www.linkedin.com/in/roopak-dureja
<br>Date - August 28th, 2025
