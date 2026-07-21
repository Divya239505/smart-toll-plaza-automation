# Wallet Service

The **Wallet Service** is responsible for handling FASTag wallet operations within the Smart Toll Plaza Automation System.

It manages wallet creation, balance maintenance, wallet recharges, toll amount deductions, and verifies whether sufficient funds are available before processing each toll transaction.

Whenever a toll payment is requested, the Toll Service interacts with this microservice to complete the payment process.

---

# Responsibilities

- Create FASTag wallets
- Recharge wallet balance
- Deduct toll charges
- Retrieve wallet information
- Delete existing wallets
- Maintain wallet balance
- Ensure FASTag IDs remain unique
- Verify sufficient wallet balance

---

# Architecture

```text
                Client
                   │
                   ▼
           WalletController
                   │
                   ▼
            WalletService
                   │
                   ▼
          WalletRepository
                   │
                   ▼
              H2 Database
```

---

# Project Structure

```text
wallet-service
│
├── controller
│     WalletController
│
├── service
│     WalletService
│
├── repository
│     WalletRepository
│
├── entity
│     Wallet
│
├── dto
│     WalletRequest
│     RechargeRequest
│     DeductRequest
│
├── advice
│     ErrorResponse
│     GlobalExceptionHandler
│
├── exception
│     DuplicateFastTagException
│     FastTagNotFoundException
│     InsufficientBalanceException
│     InvalidAmountException
│
└── WalletApiApplication
```

---

# Entity

## Wallet

| Field | Type | Description |
|--------|------|-------------|
| id | Integer | Primary Key |
| fastagId | String | Unique FASTag Identifier |
| balance | Double | Available Wallet Balance |

---

# DTOs

## WalletRequest

Used when registering a new wallet.

```text
fastagId
```

---

## RechargeRequest

```text
fastagId

amount
```

---

## DeductRequest

```text
fastagId

amount
```

---

# Business Flow

## Create Wallet

```text
Client

   │

   ▼

FASTag Exists?

   │

Yes──────────────► Throw Exception

   │

No

   │

Create Wallet

   │

Balance = 0

   │

Return Created
```

---

## Recharge Wallet

```text
Receive Request

      │

      ▼

FASTag Exists?

      │

No────────► Exception

      │

Yes

      ▼

Amount > 100 ?

      │

No────────► Invalid Amount

      │

Yes

      ▼

Update Balance

      │

Save Wallet

      │

Return Success
```

---

## Deduct Wallet Balance

```text
Receive Request

      │

      ▼

FASTag Exists?

      │

No────────► Exception

      │

Yes

      ▼

Sufficient Balance?

      │

No────────► Insufficient Balance

      │

Yes

      ▼

Deduct Amount

      │

Save Wallet

      ▼

Return Updated Wallet
```

---

# REST APIs

## Create Wallet

```text
POST /api/v1/wallets
```

### Request

```json
{
    "fastagId":"FT1001"
}
```

### Response

```text
201 CREATED
```

<img width="1917" height="841" alt="image" src="https://github.com/user-attachments/assets/afd93c08-54ac-470c-aa88-65fc67988f0e" />

---

## Get Wallet

```text
GET /api/v1/wallets/{fastagId}
```

<img width="1917" height="572" alt="image" src="https://github.com/user-attachments/assets/3817bdcd-1969-46a8-a4cf-8dfce9fe1583" />

---

## Get All Wallets

```text
GET /api/v1/wallets
```

<img width="1882" height="775" alt="image" src="https://github.com/user-attachments/assets/7f5ecd26-28d1-46a8-bb77-e1d83f44fef1" />

---

## Recharge Wallet

```text
PUT /api/v1/wallets/recharge
```

### Request

```json
{
    "fastagId":"FT1001",
    "amount":500
}
```

<img width="1917" height="852" alt="image" src="https://github.com/user-attachments/assets/275cc85c-93eb-44ec-a55c-d9adaa408279" />

---

## Deduct Wallet

```text
PUT /api/v1/wallets/deduct
```

### Request

```json
{
    "fastagId":"FT1001",
    "amount":150
}
```

<img width="1917" height="841" alt="image" src="https://github.com/user-attachments/assets/5ed1a892-7ce0-4646-a909-5102fa8a7a2f" />

---

## Delete Wallet

```text
DELETE /api/v1/wallets/{id}
```

<img width="1917" height="487" alt="image" src="https://github.com/user-attachments/assets/bd42d6c9-e03d-4525-a420-1d5c52b147d5" />

---

# Wallet Rules

## Wallet Creation

- Every FASTag ID must be unique.
- Newly created wallets have an initial balance of **₹0**.

---

## Recharge Rules

- The FASTag ID should already exist.
- The recharge amount must be greater than **₹100**.

---

## Deduction Rules

- A valid FASTag ID is required.
- The wallet should have enough balance.
- The balance is updated immediately after a successful deduction.

---

# Exception Handling

Global exception handling is implemented using:

```java
@RestControllerAdvice
```

### Handled Exceptions

- DuplicateFastTagException
- FastTagNotFoundException
- InvalidAmountException
- InsufficientBalanceException
- ValidationException

---

# Error Response

```json
{
    "statusCode":400,
    "errorType":"Validation Failed",
    "errorMessage":"Amount must be greater than 100",
    "timestamp":"2026-07-18T15:20:00"
}
```

---

# Repository

Extends

```java
JpaRepository<Wallet,Integer>
```

### Custom Methods

```java
findByFastagId()

existsByFastagId()
```

---

# Logging

The service uses **SLF4J** for application logging.

The logs include:

- Wallet creation
- Recharge operations
- Balance deduction
- Duplicate FASTag detection
- Invalid recharge amount
- Insufficient balance
- Wallet deletion

---

# Integration

This service is used by the **Toll Service**.

### Purpose

- Deduct toll charges
- Verify wallet balance before payment

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
8082
```

---

# Testing

Recommended tools:

- Postman
- IntelliJ HTTP Client
- curl

---

# Future Improvements

- Transaction History
- Recharge Records
- Wallet Statements
- Daily Transaction Limits
- Automatic Recharge
- JWT Authentication
- Swagger/OpenAPI
- Docker Support
- MySQL/PostgreSQL
- OpenFeign Client

---

# Author

**DIVYA A**

B.Tech Artificial Intelligence & Machine Learning

Saveetha Engineering College

GitHub
