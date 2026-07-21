# Journey Service

The **Journey Service** is responsible for maintaining the history of all successful toll transactions by storing vehicle journey information.

After a toll payment is completed successfully, the Toll Service forwards the journey details to this microservice, where the information is saved for future reference, monitoring, and reporting.

This service functions as the journey history management module of the Smart Toll Plaza Automation System.

---

# Responsibilities

- Store completed journey records
- Maintain toll transaction history
- Retrieve saved journey details
- Search journeys using vehicle numbers
- Record entry and exit timestamps
- Store payment status information

---

# Architecture

```text
                Client
                   │
                   ▼
          JourneyController
                   │
                   ▼
           JourneyService
                   │
                   ▼
         JourneyRepository
                   │
                   ▼
              H2 Database
```

---

# Project Structure

```text
journey-service
│
├── controller
│     JourneyController
│
├── service
│     JourneyService
│
├── repository
│     JourneyRepository
│
├── entity
│     Journey
│
├── dto
│     JourneyDTO
│
├── advice
│     ErrorResponse
│     GlobalExceptionHandler
│
├── exception
│     JourneyNotFoundException
│
└── JourneyApiApplication
```

---

# Entity

## Journey

| Field | Type | Description |
|--------|------|-------------|
| id | Integer | Primary Key |
| vehicleNumber | String | Registered Vehicle Number |
| startTime | LocalDateTime | Journey Start Time |
| endTime | LocalDateTime | Journey End Time |
| plaza | String | Toll Plaza Name |
| amount | Double | Toll Fee |
| paymentStatus | String | Payment Status |

---

# Journey DTO

Used to receive journey details from the Toll Service.

```text
vehicleNumber

startTime

endTime

plaza

amount

paymentStatus
```

---

# Business Flow

```text
Receive Journey Request

          │

          ▼

Validate Request

          │

          ▼

Create Journey Entity

          │

          ▼

Store in Database

          │

          ▼

Return Success Response
```

---

# REST APIs

## Save Journey

```text
POST /api/v1/journeys
```

### Request

```json
{
    "vehicleNumber":"TN38AB1234",
    "startTime":"2026-07-18T10:30:00",
    "endTime":"2026-07-18T10:35:00",
    "plaza":"Chennai Toll Plaza",
    "amount":150,
    "paymentStatus":"SUCCESS"
}
```

### Response

```text
201 CREATED
```

<img width="1917" height="1023" alt="image" src="https://github.com/user-attachments/assets/03d610db-d9da-49f6-8356-e7f6d9bf9392" />

---

## Get All Journeys

```text
GET /api/v1/journeys
```

<img width="1917" height="1078" alt="image" src="https://github.com/user-attachments/assets/131b3bd0-288c-440d-87b9-4b540bae2a87" />

---

## Get Journey By Vehicle Number

```text
GET /api/v1/journeys/{vehicleNumber}
```

<img width="1917" height="1078" alt="image" src="https://github.com/user-attachments/assets/6fdb3546-cacf-404d-a75f-dcb71e09fa70" />

---

# Journey Lifecycle

```text
Vehicle Crosses Toll Plaza

        │

        ▼

Payment Completed

        │

        ▼

Toll Service

        │

        ▼

Journey Service

        │

        ▼

Journey Record Saved

        │

        ▼

Journey History Updated
```

---

# Validation

Incoming requests are validated before the journey information is stored.

Validation includes:

- Vehicle Number is required
- Toll Plaza is required
- Amount is required
- Payment Status is required
- Start Time is required
- End Time is required

---

# Payment Status

Supported values:

```text
SUCCESS

FAILED

PENDING
```

---

# Exception Handling

Global exception handling is implemented using:

```java
@RestControllerAdvice
```

### Handled Exceptions

- JourneyNotFoundException
- ValidationException

---

# Error Response

```json
{
    "statusCode":404,
    "errorType":"Journey Not Found",
    "errorMessage":"Journey not found for vehicle TN38AB1234",
    "timestamp":"2026-07-18T18:20:00"
}
```

---

# Repository

The repository extends:

```java
JpaRepository<Journey,Integer>
```

### Custom Methods

```java
findByVehicleNumber()

findAll()
```

---

# Integration

This service is mainly used by the **Toll Service**.

### Purpose

- Save successful toll transactions
- Maintain journey history
- Generate travel records

---

# Logging

SLF4J logging is used to monitor application activities.

The logs include:

- Journey creation
- Journey retrieval
- Validation failures
- Journey search requests
- Database operations

---

# Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- Lombok
- H2 Database
- Maven

---

# Running the Application

```bash
mvn spring-boot:run
```

### Default Port

```text
8083
```

---

# Testing

Recommended tools:

- Postman
- IntelliJ HTTP Client
- curl

---

# Future Improvements

- Journey Pagination
- Search by Date Range
- Vehicle-wise Reports
- Monthly Reports
- Excel Export
- PDF Export
- Dashboard Analytics
- Swagger/OpenAPI Documentation
- Docker Support
- MySQL/PostgreSQL Integration

---

# Author

**DIVYA A**

B.E. Computer Science and Engineering

Saveetha Engineering College

GitHub: https://github.com/Divya239505/Smart-Toll-Plaza-Automation
