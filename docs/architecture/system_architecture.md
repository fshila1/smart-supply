
# SmartSupply — System Architecture Document
Version: 1.0  
Audience: Engineering, Architecture, DevOps, Product  
Authors: Architecture Team

---

# 1. Executive Summary

SmartSupply is an intelligent B2B supply chain system designed to manage:

- Inventory
- Order lifecycle
- Payments
- Fraud evaluation
- Notifications
- Analytics & reporting

The architecture follows a microservices + event-driven model using Spring Boot, Kafka, Kubernetes, and PostgreSQL.

## System Goals
- High throughput order processing
- Eventual consistency across distributed services
- Near real-time stock updates
- Secure, PCI-conscious payments integration
- Operational observability (metrics, logs, tracing)
- Resilience and self-healing strategies

## Key Decisions
- Microservices + DB-per-service
- Kafka as event backbone
- Saga (choreography) for long-running workflows
- Outbox pattern for reliable events
- JWT for authentication
- Kubernetes for orchestration

---

# 2. Requirements & Constraints

## 2.1 Functional Requirements
- Admin & vendor dashboards for managing stock & products
- Place orders & orchestrate reservation → payment → confirmation
- Real-time stock reservation with TTL
- Notifications for payment, stock, and system events
- Analytics dashboards for business reporting

## 2.2 Non-functional Requirements
- Latency: <300ms for initial order ACK
- Availability: 99.9% target uptime
- Consistency: eventual consistency across domains
- Scalability: thousands of orders/minute
- Security: PCI-DSS alignment; no card storage

## 2.3 Constraints
- Java 11+, Spring Boot
- Angular SPA/PWA frontend
- Kafka for events
- PostgreSQL/MySQL
- Deployable on EKS/GKE/AKS

---

# 3. Domain Decomposition & Bounded Contexts

## Order
Order creation, lifecycle management, payment triggering.

## Inventory
Stock, reservations, TTL-based release, warehouse management.

## Payment
Payment initiation, fraud check, gateway integration.

## Notification
Email, SMS, push, retry queue, DLQ.

## Fraud Detection
Rules, event ingestion, fraud flagging.

## Analytics
Long-term event ingestion and reporting.

## Identity
JWT issuance, roles, RBAC.

---

# 4. Architecture Style & High-Level Decisions

## Microservices Architecture
- Each domain has its own deployable service
- Each service owns its own database

## Event-driven
- Kafka for domain events
- REST for synchronous commands/queries

## Saga Choreography
- No 2PC
- Compensation events handle failures

## Security
- JWT for APIs
- Secrets in Vault / Secret Manager
- TLS everywhere

---

# 5. High-level (C4) Architecture

## C1 — System Context
SmartSupply interacts with:
- Admin users
- Vendors
- Payment gateways
- Notification providers (SMS/Email)
- External analytics/BI systems

## C2 — Container Diagram

Containers:
- API Gateway
- Identity Service
- Order Service
- Inventory Service
- Payment Service
- Fraud Detection Service
- Notification Service
- Analytics Service
- Kafka
- Redis
- Databases
- Angular Frontend
- External Providers

## C3 — Component Diagrams

### Order Service Components
- Order Controller
- Order Orchestrator
- ReservationConsumer
- PaymentConsumer
- OrderDB (orders, order_items, statuses)

### Inventory Service Components
- Stock Controller
- Reservation Engine
- TTL Expiry Worker
- InventoryDB

### Payment Service Components
- Gateway Client
- FraudCheckConsumer
- PaymentDB

### Notification Service Components
- Template Engine
- Email/SMS Adapters
- Retry Worker
- NotificationDB + Redis

### Fraud Detection Components
- Rules Engine
- Event Consumer
- FraudDB

---

# 6. Event Model

## Core Kafka Topics
- order.created
- stock.reservation.requested
- stock.reserved
- stock.reservation.failed
- stock.reservation.expired
- payment.initiation.requested
- payment.success
- payment.failure
- order.confirmed
- order.cancelled
- stock.deducted
- stock.released
- notification.*
- analytics.*
- fraud.detected

## Sample Event (OrderCreated)
```json
{
  "eventName": "OrderCreated",
  "version": 1,
  "timestamp": "2025-11-23T12:00:00Z",
  "correlationId": "uuid-order-123",
  "payload": {
    "orderId": "order-123",
    "userId": "user-456",
    "items": [
      {"sku": "SKU-1", "quantity": 10, "unitPrice": 12.50}
    ],
    "totalAmount": 125.0,
    "currency": "USD",
    "createdAt": "2025-11-23T12:00:00Z"
  }
}
```

---

# 7. Sequence Flows (High-Level)

## Order Creation Workflow
1. User submits order → OrderService stores PENDING → emits `order.created`
2. InventoryService reserves stock → emits `stock.reserved`
3. OrderService → updates to RESERVED → emits `payment.initiation.requested`
4. PaymentService → calls gateway → emits `payment.success`
5. OrderService → updates to CONFIRMED → emits `order.confirmed`
6. Inventory finalizes → stock.deducted
7. NotificationService sends messages
8. Analytics ingests all events

---

# 8. Data Model & DB Schema

## OrderDB
- orders
- order_items
- order_status_history

## InventoryDB
- product_stock
- stock_reservations

## PaymentDB
- payments

## NotificationDB
- notifications
- Redis retry queues

## Analytics
- denormalized events for reporting

---

# 9. Reliability & Resiliency

## Patterns
- Outbox pattern
- DLQ for Kafka consumers
- Exponential backoff
- Idempotent consumers
- Circuit breakers (external calls)

## Observability
- Structured logging with correlationId
- Prometheus metrics
- OpenTelemetry tracing
- Alerts on stuck states (e.g. RESERVED too long)

---

# 10. Security & Compliance
- JWT authentication
- RBAC (Admin, Vendor, Customer)
- Secrets vaulting
- No raw card storage (gateway tokenization)
- TLS everywhere
- Audit logs for sensitive actions

---

# 11. Operational Architecture
- Kubernetes deploys each microservice
- Kafka cluster
- PostgreSQL with replicas
- CI/CD via GitHub Actions or Jenkins
- Canary deploys for Payment & Inventory services

---

# 12. Failure Scenarios & Compensations

| Failure | Compensation |
|--------|--------------|
| Stock reservation failed | order.cancelled |
| Payment timeout | release stock, cancel order |
| Payment gateway down | retry + circuit breaker |
| Reservation expired | TTL worker releases reservation |
| Consumer lag | auto-scale consumer Pods |

---

# 13. Testing Strategy
- Unit tests
- Contract tests (API + events)
- Integration tests via Testcontainers
- Chaos engineering (Inventory & Payment failures)
- Load testing for order spikes

---

# 14. Deployment & Rollout Plan
1. Deploy core services (Order, Inventory, Payment)
2. Verify reservation & payment flows
3. Deploy Analytics & Notification services
4. Enable dashboards & alerts
5. Canary test payment paths
6. Gradually move traffic

---

# 15. Deliverables
- C4 diagrams
- Sequence diagrams
- Event schemas
- DB DDL scripts
- API specs (OpenAPI)
- Runbooks
- Security checklist
- Deployment guide

---

# 16. Go-Live Ops Checklist
- Kafka partitions sized
- Outbox worker healthy
- Health/readiness probes configured
- Tracing enabled
- Secrets rotated
- DB backups verified
- Alerts configured

---

# 17. Example API Endpoints

## Order
- POST /api/v1/orders
- GET /api/v1/orders/{id}
- PATCH /api/v1/orders/{id}/pay
