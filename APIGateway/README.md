# API Gateway

The **API Gateway** acts as the centralized access point for the Smart Toll Plaza Automation System.

Rather than sending requests directly to individual microservices, clients interact only with the API Gateway. It receives incoming requests and forwards them to the appropriate microservice, ensuring centralized routing and seamless communication.

This approach simplifies client interaction, improves scalability, and provides a clean separation between clients and backend services.

---

# Responsibilities

- Provide a single entry point for all client requests
- Route requests to the appropriate microservice
- Hide internal service endpoints
- Simplify communication between clients and services
- Centralize request routing
- Improve system maintainability

---

# Architecture

```text
                    Client
                       │
                       ▼
                API Gateway
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
 Vehicle API      Wallet API      Journey API
                       │
                       ▼
                  Toll API
```

---

# Project Structure

```text
api-gateway
│
├── src
│
├── application.yml
│
├── ApiGatewayApplication
│
└── pom.xml
```

The API Gateway is intentionally lightweight and contains only the configuration required for request routing. It does not include controllers, services, repositories, or database-related components.

---

# Purpose of API Gateway

### Without API Gateway

```text
Client

 ├── Vehicle Service

 ├── Wallet Service

 ├── Journey Service

 └── Toll Service
```

In this approach, the client must know the address and port number of every individual service.

---

### With API Gateway

```text
             Client

                │

                ▼

          API Gateway

                │

     ┌──────────┼──────────┐

     ▼          ▼          ▼

 Vehicle     Wallet     Journey

 Service     Service     Service

                │

                ▼

          Toll Service
```

The client communicates through a single endpoint, while the gateway manages the routing internally.

---

# Default Port

```text
8080
```

---

# Route Configuration

| Incoming Request | Forwarded To |
|------------------|--------------|
| `/vehicles/**` | Vehicle Service |
| `/wallet/**` | Wallet Service |
| `/journeys/**` | Journey Service |
| `/toll/**` | Toll Service |

---

# Request Flow

## Vehicle Registration

```text
Client

   │

POST /vehicles

   │

   ▼

API Gateway

   │

   ▼

Vehicle Service

   │

   ▼

Response

   │

   ▼

Client
```

---

## Wallet Recharge

```text
Client

   │

PUT /wallet/recharge

   │

   ▼

API Gateway

   │

   ▼

Wallet Service

   │

   ▼

Response
```

---

## Toll Payment

```text
Client

      │

POST /toll/pay

      │

      ▼

API Gateway

      │

      ▼

Toll Service

      │

      ▼

Vehicle Service

      │

      ▼

Wallet Service

      │

      ▼

Journey Service

      │

      ▼

Return Success
```

---

# Example URLs

Instead of calling

```text
http://localhost:8081/api/v1/vehicles
```

Use

```text
http://localhost:8080/api/v1/vehicles
```

<img width="1917" height="736" alt="image" src="https://github.com/user-attachments/assets/f8332db2-3d24-454b-bfac-414e3b5fc026" />

---

Instead of

```text
http://localhost:8082/api/v1/wallets
```

Use

```text
http://localhost:8080/api/v1/wallets
```

<img width="1917" height="597" alt="image" src="https://github.com/user-attachments/assets/447e5c21-28fe-4aff-9080-9cdd3e1bc0b4" />

---

Instead of

```text
http://localhost:8083/api/v1/journeys
```

Use

```text
http://localhost:8080/api/v1/journeys
```

<img width="1917" height="1041" alt="image" src="https://github.com/user-attachments/assets/22bac05e-1c3e-4032-8bf3-df0892bf642e" />

---

Instead of

```text
http://localhost:8084/api/v1/tolls
```

Use

```text
http://localhost:8080/api/v1/tolls
```

<img width="1911" height="815" alt="image" src="https://github.com/user-attachments/assets/46851162-e9e6-493b-a434-48f26c009ae1" />

---

# Advantages

- Single access point for all APIs
- Simplified client configuration
- Centralized request routing
- Improved scalability
- Reduced client-side complexity
- Loose coupling between services
- Easier application maintenance

---

# Connected Services

| Service | Port |
|----------|------|
| Vehicle Service | 8081 |
| Wallet Service | 8082 |
| Journey Service | 8083 |
| Toll Service | 8084 |

---

# Running the Application

Start the services in the following sequence.

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

Run the API Gateway using:

```bash
mvn spring-boot:run
```

---

# Technologies

- Java 21
- Spring Boot
- Spring Cloud Gateway
- Spring Web MVC
- Maven

---

# Future Improvements

- Eureka Service Discovery
- Spring Cloud Config Server
- Load Balancing
- JWT Authentication
- Spring Security
- Rate Limiting
- API Versioning
- Circuit Breaker
- Distributed Tracing
- Request Logging
- Prometheus and Grafana Monitoring

---

# Related Services

- Vehicle Service
- Wallet Service
- Journey Service
- Toll Service

These services work together to build the complete **Smart Toll Plaza Automation System**.

---

# Author

**DIVYA A**

**B.E. Computer Science and Engineering**

Saveetha Engineering College

GitHub: https://github.com/Divya239505/Smart-Toll-Plaza-Automation

---

## Star this repository if you found it useful.
