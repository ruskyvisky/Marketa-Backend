# Marketa - E-commerce Platform

Marketa is a feature-rich e-commerce platform built to demonstrate various functionalities of an online marketplace using a microservices architecture. It uses a robust backend powered by Spring Boot and a suite of supporting technologies to provide a reliable, scalable, and modular service.


- **Order Service**: Manages the order placement and processing logic.
- **Product Service**: Handles product-related operations, such as listing and adding products.
- **Inventory Service**: Manages inventory and verifies stock availability.
- **API Gateway**: Centralized entry point for routing and load balancing, built with Spring Cloud Gateway, to manage requests efficiently.

## Technologies Used
- **Spring Boot**: Core framework for developing each microservice
- **PostgreSQL & MySQL**: Databases used for different services
- **Kafka**: Message broker for handling asynchronous communication between services
- **Keycloak**: Authentication and authorization service for secure access management
- **Grafana Stack**:
  - **Prometheus**: Monitoring tool for collecting metrics
  - **Grafana**: Visualization dashboard for metrics and logs
  - **Loki**: Log aggregation system
  - **Tempo**: Distributed tracing backend for managing request tracing
- **API Gateway**: Configured using Spring Cloud Gateway MVC for efficient routing and load balancing between services

## API Routes
Here are the main API routes available in the Marketa platform:

| Route                        | Method | Description                                      |
|------------------------------|--------|--------------------------------------------------|
| `/api/order/placeorder`      | POST   | Allows users to place an order                   |
| `/api/product/all`           | GET    | Retrieves a list of all available products       |
| `/api/product/add`           | POST   | Adds a new product to the inventory              |
| `/api/inventory`             | GET    | Checks if a product is in stock                  |

> **Note**: The inventory route `/api/inventory?skuCode=_____&quantity=____` requires `skuCode` and `quantity` parameters to verify stock availability.
