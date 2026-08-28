# FleetOps - Order & Dispatch Service

Part of the **FleetOps Fleet & Logistics Dispatch System**, submitted for the
Enterprise Cloud Architecture (ITS 2130) capstone project.

## Student Information
- **Name:** K.D. Chanuth Dewhan
- **Student ID:** 241722017
- **Slack Handle:** @chanuthdewhan
- **GCP Project ID:** fleet-ops-506803

## Project Description
The core dispatch domain service for FleetOps. Owns customer records, driver
and vehicle fleet management, the order lifecycle (PENDING → ASSIGNED →
IN_TRANSIT → DELIVERED → CANCELLED), the assignment process that pairs an
order with an available driver and vehicle, and JWT-based authentication for
the entire system. Enforces business rules such as preventing an order from
being assigned twice, and validating that only available drivers and
vehicles can be assigned.

## Technology Stack
- Java 25
- Spring Boot 4.1
- Spring Data JPA, PostgreSQL (Cloud SQL in production)
- Spring Cloud Config Client, Eureka Client
- JWT (jjwt), Spring Security Crypto (BCrypt password hashing)
- MapStruct, Lombok
- RFC 9457 Problem Details for structured error responses

## Setup / Getting Started

```bash
git clone https://github.com/chanuthdewhan/fleetops-order-dispatch-service.git
cd fleetops-order-dispatch-service
./mvnw spring-boot:run
```

Runs on port `8000` locally. Requires `fleetops-service-registry` and
`fleetops-config-server` running first, and a local PostgreSQL instance
(see `fleetops-platform`'s `docker-compose.yml`).

## Key Endpoints
- `POST /api/v1/auth/register`, `POST /api/v1/auth/login`
- `GET/POST /api/v1/customers`, `GET/PUT/DELETE /api/v1/customers/{id}`
- `GET/POST /api/v1/drivers`, `PATCH /api/v1/drivers/{id}/status`
- `GET/POST /api/v1/vehicles`, `PATCH /api/v1/vehicles/{id}/status`
- `GET/POST /api/v1/orders`, `PATCH /api/v1/orders/{id}/status`
- `POST /api/v1/orders/{id}/assignment`

## Live Deployment
- **GCP Project ID:** fleet-ops-506803
- **Region:** asia-southeast1
- **Deployment model:** IaaS — Compute Engine, managed via PM2
- **Accessed via API Gateway:** http://34.21.225.166:80