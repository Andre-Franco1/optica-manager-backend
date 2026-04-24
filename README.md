# Optica Manager - Backend

Backend service for the Optica Manager system. This application is responsible for managing customers, sales, and invoice generation for an optical store.

## 📌 Overview

Optica Manager Backend is a REST API built to support an internal system for optical shops. It provides endpoints for:

- Customer management
- Sales registration
- Invoice generation
- Business rules enforcement

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- PostgreSQL

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+

### Installation

```bash
git clone https://github.com/your-username/optica-manager-backend.git
cd optica-manager-backend
mvn clean install
```

### Running the application
```bash
mvn spring-boot:run
```

## 📡 API Endpoints (Examples)

### 👤 Customers

- `GET /clients`  
  List all customers

- `POST /clients`  
  Create a new customer

- `GET /clients/{id}`  
  Get customer by ID

---

### 💰 Sales

- `POST /sales`  
  Register a new sale

- `GET /sales`  
  List all sales
