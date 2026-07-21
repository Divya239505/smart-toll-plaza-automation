<div align="center">

# Smart Toll Plaza Automation System

### A Spring Boot Microservices-Based Toll Management Platform

An automated toll management solution built using **FASTag**, **REST APIs**, and **Spring Boot Microservices**.

![Java](https://img.shields.io/badge/Java-21-24292F?style=for-the-badge&logo=openjdk&logoColor=F89820)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-24292F?style=for-the-badge&logo=springboot&logoColor=6DB33F)
![API Gateway](https://img.shields.io/badge/API_Gateway-24292F?style=for-the-badge&logo=cloudflare&logoColor=F38020)
![H2 Database](https://img.shields.io/badge/H2_Database-24292F?style=for-the-badge&logo=sqlite&logoColor=4FC3F7)
![REST API](https://img.shields.io/badge/REST_API-24292F?style=for-the-badge&logo=postman&logoColor=FF6C37)
![Microservices](https://img.shields.io/badge/Microservices-24292F?style=for-the-badge&logo=docker&logoColor=2496ED)

</div>

---

# Overview

The **Smart Toll Plaza Automation System** is a Spring Boot microservices application created as a capstone project to streamline highway toll collection using FASTag technology.

The application removes the need for manual toll collection by verifying registered vehicles, validating wallet balances, processing toll deductions, maintaining journey records, and directing all client requests through a centralized API Gateway.

Each component is implemented as a separate Spring Boot microservice using a layered architecture and communicates synchronously through REST APIs.

---

# Objectives

- Digitize toll payment processing
- Maintain vehicle registration details
- Handle FASTag wallet operations
- Record travel history
- Support communication between microservices
- Process all requests via the API Gateway

---

# System Architecture

```text
                    Client
                       │
                       ▼
               API Gateway (8080)
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
 Vehicle Service   Wallet Service   Journey Service
      │
      │
      ▼
   Toll Service
```

---

# Microservices

| Service | Description | README.md |
|----------|-------------|----------|
| Vehicle Service | Handles vehicle registration and FASTag details |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/VehicleAPI/README.md)|
| Wallet Service | Controls FASTag wallet transactions and recharge |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/WalletAPI/README.md)|
| Journey Service | Maintains records of vehicle journeys |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/JourneyAPI/README.md)|
| Toll Service | Executes the complete toll payment process |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/TollAPI/README.md)|
| API Gateway | Acts as the unified access point for all APIs |[Readme-link](https://github.com/isravel-eng/smart-toll-plaza-automation/blob/main/APIGateway/README.md)|

---

# Toll Payment Flow

```text
Vehicle Number

      │
      ▼

Vehicle Service

      │
      ▼

Fetch FASTag ID

      │
      ▼

Wallet Service

      │
      ▼

Process Balance Deduction

      │
      ▼

Journey Service

      │
      ▼

Record Journey Details

      │
      ▼

Return Successful Response
```

---

# Repository Structure

```text
smart-toll-plaza-automation
│
├── api-gateway
│
├── vehicle-service
│
├── wallet-service
│
├── journey-service
│
├── toll-service
│
└── README.md
```

---

# Features

## Vehicle Service

- Add Vehicle
- Modify Vehicle Information
- Remove Vehicle
- View Vehicle Details
- Generate Unique Vehicle Number
- Generate Unique FASTag ID

---

## Wallet Service

- Open Wallet
- Recharge Wallet
- Deduct Wallet Amount
- View Wallet Balance
- Restrict Negative Balance

---

## Journey Service

- Record Journey Information
- Access Journey History
- Find Records Using Vehicle Number

---

## Toll Service

- Verify Vehicle
- Obtain FASTag Information
- Process Wallet Deduction
- Generate Journey Record
- Provide Payment Confirmation

---

## API Gateway

- Direct API Requests
- Unified Access Point
- Easy Client-Service Communication

---

# Tech Stack

## Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Cloud Gateway

---

## Database

- H2 Database

---

## Communication

- REST APIs
- RestTemplate

---

## Tools

- IntelliJ IDEA
- Postman
- Git
- GitHub
- Maven

---

# Project Structure

Each microservice is organized using a layered architecture.

```text
service-name
│
├── controller
├── service
├── repository
├── entity
├── dto
├── advice
├── exception
└── config
```

---

# Running the Project

Launch the services in the following sequence.

```
Vehicle Service
        ↓
Wallet Service
        ↓
Journey Service
        ↓
Toll Service
        ↓
API Gateway
```

---

# Default Ports

| Service | Port |
|----------|------|
| API Gateway | 8080 |
| Vehicle Service | 8081 |
| Wallet Service | 8082 |
| Journey Service | 8083 |
| Toll Service | 8084 |

---

# API Gateway Routes

| Route | Destination |
|---------|-------------|
| /vehicles/** | Vehicle Service |
| /wallet/** | Wallet Service |
| /journeys/** | Journey Service |
| /toll/** | Toll Service |

---

# End-to-End Workflow

1. Register a Vehicle
2. Create a Wallet
3. Recharge the Wallet
4. Complete Toll Payment
5. Deduct Wallet Balance
6. Save Journey Information
7. Send Payment Confirmation

---

# Key Concepts Demonstrated

- Spring Boot
- REST APIs
- Microservices Architecture
- Layered Architecture
- DTO Pattern
- Bean Validation
- Global Exception Handling
- RestTemplate
- API Gateway
- H2 Database
- Logging
- CRUD Operations

---

# Future Enhancements

- Eureka Service Discovery
- OpenFeign Client
- MySQL / PostgreSQL Support
- Docker Integration
- JWT-Based Authentication
- Spring Security
- Apache Kafka
- Redis Caching
- Swagger / OpenAPI Documentation
- Kubernetes Deployment

---

# Author

**DIVYA A**

B.E Computer Science Engineering

Saveetha Engineering College

GitHub:

https://github.com/Divya239505/Smart-Toll-Plaza-Automation

---

