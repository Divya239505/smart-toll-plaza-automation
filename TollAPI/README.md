# Toll Service

The **Toll Service** serves as the central coordination component of the Smart Toll Plaza Automation System.

Unlike the other microservices, it does **not** maintain a dedicated database. Instead, it interacts with the Vehicle Service, Wallet Service, and Journey Service to execute the complete toll payment process.

Whenever a vehicle reaches a toll plaza, this service verifies the vehicle details, obtains the corresponding FASTag ID, deducts the toll fee from the wallet, stores the journey information, and returns the payment result.

---

# Responsibilities

- Process toll payment requests
- Verify registered vehicle information
- Fetch FASTag details
- Deduct wallet balance
- Store journey records
- Return transaction status
- Coordinate communication between microservices

---

# Architecture

```text
                    Client
                       │
                       ▼
                TollController
                       │
                       ▼
                 TollService
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
VehicleClient     WalletClient    JourneyClient
      │                │                │
      ▼                ▼                ▼
 Vehicle API      Wallet API      Journey API
```

---

# Project Structure

```text
toll-service
│
├── controller
│     TollController
│
├── service
│     TollService
│
├── client
│     VehicleClient
│     WalletClient
│     JourneyClient
│
├── dto
│     TollRequest
│     TollResponse
│     JourneyDTO
│     WalletDTO
│     VehicleDTO
│
├── config
│     RestTemplateConfig
│
└── TollApiApplication
```

---

# Why No Database?

The Toll Service is designed only to coordinate business operations.

It does not store any persistent information.

Instead, it exchanges data with other services through REST APIs.

| Service | Responsibility |
|----------|---------------|
| Vehicle Service | Vehicle Verification |
| Wallet Service | Wallet Balance Deduction |
| Journey Service | Journey Storage |

This approach follows the **Database per Service** design principle and keeps the services loosely coupled.

---

# Toll Payment Workflow

```text
Receive Toll Request

        │

        ▼

Vehicle Service

Validate Vehicle

        │

        ▼

Retrieve FASTag ID

        │

        ▼

Wallet Service

Deduct Balance

        │

        ▼

Enough Balance?

        │

 ┌──────┴─────────┐

 │                │

No               Yes

 │                │

 ▼                ▼

Return Error   Journey Service

                    │

                    ▼

             Save Journey

                    │

                    ▼

          Return Success Response
```

---

# Service Communication

```text
                Toll Service

                      │

      ┌───────────────┼───────────────┐

      ▼               ▼               ▼

Vehicle API      Wallet API      Journey API

      │               │               │

Validate        Deduct Money     Save Journey

Vehicle         Update Balance   Return Success
```

---

# REST API

## Pay Toll

```text
POST /api/v1/tolls
```

### Request

```json
{
    "vehicleNumber":"TN38AB1234",
    "plaza":"Chennai Toll Plaza",
    "amount":150
}
```

### Success Response

```json
{
    "message":"Toll Paid Successfully",
    "vehicleNumber":"TN38AB1234",
    "fastagId":"FT1001",
    "amount":150,
    "status":"SUCCESS"
}
```

<img width="1917" height="958" alt="image" src="https://github.com/user-attachments/assets/eab39961-9f29-4e53-a741-39dc36baa39a" />

---

# Processing Flow

### Step 1

Receive Toll Request

↓

### Step 2

Invoke Vehicle Service

```text
GET /vehicles/{vehicleNumber}
```

↓

Vehicle Found?

↓

No → Return Error

↓

Yes

↓

Retrieve FASTag ID

↓

### Step 3

Invoke Wallet Service

```text
PUT /wallet/deduct
```

↓

Sufficient Balance?

↓

No

↓

Return Insufficient Balance

↓

Yes

↓

Wallet Updated

↓

### Step 4

Invoke Journey Service

```text
POST /journeys
```

↓

Journey Saved

↓

### Step 5

Return Success Response

---

# Request DTO

## TollRequest

```text
vehicleNumber

plaza

amount
```

---

# Response DTO

## TollResponse

```text
message

vehicleNumber

fastagId

amount

status
```

---

# External Clients

## VehicleClient

Responsibilities

- Verify vehicle details
- Retrieve FASTag information

---

## WalletClient

Responsibilities

- Deduct wallet balance
- Verify wallet details

---

## JourneyClient

Responsibilities

- Save journey records
- Return journey status

---

# RestTemplate

The Toll Service communicates with other microservices using:

```java
RestTemplate
```

A dedicated configuration class provides a reusable `RestTemplate` bean for all outgoing REST API requests.

---

# Error Handling

Possible failures include:

- Vehicle Not Found
- FASTag Not Found
- Insufficient Balance
- Wallet Service Unavailable
- Journey Service Unavailable
- Invalid Request
- Network Timeout

---

# Transaction Lifecycle

```text
Vehicle Arrives

      │

      ▼

Vehicle Validation

      │

      ▼

Wallet Deduction

      │

      ▼

Journey Recording

      │

      ▼

Transaction Completed
```

---

# Dependencies

The Toll Service depends on:

- Vehicle Service
- Wallet Service
- Journey Service

This service should be started **after** the above services are running.

---

# Technologies

- Java 21
- Spring Boot
- Spring Web
- RestTemplate
- Bean Validation
- Lombok
- Maven

---

# Running the Application

```bash
mvn spring-boot:run
```

### Default Port

```text
8084
```

---

# Service Startup Order

```text
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

# Future Improvements

- OpenFeign Client
- Resilience4j Circuit Breaker
- Retry Mechanism
- Distributed Tracing
- Kafka Integration
- Eureka Service Discovery
- Docker Support
- Kubernetes Deployment
- JWT Authentication
- Swagger/OpenAPI Documentation

---

# Author

**DIVYA A**

B.E. Computer Science and Engineering

Saveetha Engineering College

GitHub: https://github.com/Divya239505/Smart-Toll-Plaza-Automation
