# Vehicle Service

The **Vehicle Service** is responsible for handling vehicle registration and managing FASTag details within the Smart Toll Plaza Automation System.

It acts as the central repository for vehicle-related information used by other microservices. Before any toll transaction is processed, the Toll Service verifies the vehicle and retrieves the corresponding FASTag ID from this service.

---

# Responsibilities

- Register new vehicles
- Store FASTag information
- Retrieve vehicle records
- Update vehicle details
- Remove vehicle records
- Ensure unique vehicle registration numbers
- Ensure unique FASTag IDs

---

# Architecture

```text
                Client
                   │
                   ▼
          VehicleController
                   │
                   ▼
            VehicleService
                   │
                   ▼
         VehicleRepository
                   │
                   ▼
              H2 Database
```

---

# Project Structure

```text
vehicle-service
│
├── controller
│     VehicleController
│
├── service
│     VehicleService
│
├── repository
│     VehicleRepository
│
├── entity
│     Vehicle
│
├── dto
│     VehicleDTO
│
├── enums
│     VehicleType
│
├── advice
│     ErrorResponse
│     GlobalExceptionHandler
│
├── exception
│     DuplicateVehicleException
│     FastTagNotFoundException
│     VehicleNotFoundException
│
└── VehicleApiApplication
```

---

# Entity

## Vehicle

| Field | Type | Description |
|---------|------|-------------|
| id | Integer | Primary Key |
| vehicleNumber | String | Unique Registration Number |
| ownerName | String | Name of the Vehicle Owner |
| vehicleType | Enum | CAR / BUS / TRUCK / BIKE |
| fastagId | String | Unique FASTag Identifier |

---

# DTO

## VehicleDTO

Used to receive vehicle information from the client.

```java
vehicleNumber

ownerName

vehicleType

fastagId
```

Validation includes:

- Vehicle number format validation
- Mandatory field validation
- Owner name length validation
- Vehicle type verification
- FASTag ID requirement

---

# Vehicle Types

Supported vehicle categories:

```text
CAR

BUS

TRUCK

BIKE
```

---

# Business Flow

```text
Register Vehicle

       │

       ▼

Validate Request

       │

       ▼

Vehicle Number Exists?

       │

 ┌─────┴──────┐

 │            │

Yes          No

 │            │

 ▼            ▼

Throw      FASTag Exists?

Exception       │

         ┌─────┴─────┐

         │           │

       Yes          No

         │           │

         ▼           ▼

      Throw      Save Vehicle

      Exception      │

                     ▼

             Return Created
```

---

# REST APIs

## Register Vehicle

```text
POST /api/v1/vehicles
```

### Request

```json
{
  "vehicleNumber":"TN38AB1234",
  "ownerName":"Isravel",
  "vehicleType":"CAR",
  "fastagId":"FT1001"
}
```

### Response

```text
201 CREATED
```

#### Example Output

<img width="1917" height="915" alt="image" src="https://github.com/user-attachments/assets/cdcd331a-b6fa-4b0c-9a5e-8969feefe29d" />

---

## Get Vehicle

```text
GET /api/v1/vehicles/{vehicleNumber}
```

<img width="1917" height="873" alt="image" src="https://github.com/user-attachments/assets/2823893b-155f-4038-813e-efedc24fba0c" />

---

## Get All Vehicles

```text
GET /api/v1/vehicles
```

<img width="1912" height="1017" alt="image" src="https://github.com/user-attachments/assets/69ace1c4-af9e-4d5d-95dc-def13c92fac5" />

---

## Update Vehicle

```text
PUT /api/v1/vehicles/{id}
```

<img width="1917" height="925" alt="image" src="https://github.com/user-attachments/assets/3b18404b-942e-49ef-9501-367826131283" />

---

## Delete Vehicle

```text
DELETE /api/v1/vehicles/{id}
```

<img width="1917" height="602" alt="image" src="https://github.com/user-attachments/assets/afef7c41-4539-48a3-9381-57a135e3cf14" />

---

# Validation

| Field | Validation |
|---------|------------|
| Vehicle Number | Required + Regex Pattern |
| Owner Name | Minimum 3 Characters |
| Vehicle Type | Enum Validation |
| FASTag ID | Mandatory |

Example:

```text
TN42IK2007
```

---

# Exception Handling

Global exception handling is implemented using:

```java
@RestControllerAdvice
```

### Handled Exceptions

- DuplicateVehicleException
- VehicleNotFoundException
- FastTagNotFoundException
- MethodArgumentNotValidException

---

# Error Response

A common response format is returned for all exceptions.

```json
{
  "statusCode":400,
  "errorType":"Validation Failed",
  "errorMessage":"Vehicle already exists",
  "timestamp":"2026-07-18T12:00:00"
}
```

---

# Repository

The repository extends:

```java
JpaRepository<Vehicle,Integer>
```

### Custom Methods

```java
existsByVehicleNumber()

existsByFastagId()

findByVehicleNumber()
```

---

# Logging

SLF4J is used for application logging and monitoring.

The logs include:

- Vehicle registration
- Vehicle retrieval
- Vehicle update
- Vehicle deletion
- Duplicate vehicle detection
- Vehicle not found

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
8081
```

---

# Testing

Recommended tools for API testing:

- Postman
- IntelliJ HTTP Client
- curl

---

# Integration

This service is consumed by the **Toll Service** to:

- Validate vehicle information
- Retrieve the associated FASTag ID

---

# Future Improvements

- Pagination
- Advanced Vehicle Search
- Swagger/OpenAPI Documentation
- MySQL Support
- Unit Testing
- Docker Integration
- OpenFeign Client
- Eureka Service Discovery

---

# Author

**DIVYA A**

B.E. Computer Science and Engineering

Saveetha Engineering College

GitHub: https://github.com/Divya239505/Smart-Toll-Plaza-Automation
