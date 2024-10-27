# Marketa - E-commerce Platform

Marketa is a feature-rich e-commerce platform built to demonstrate various functionalities of an online marketplace. It uses a robust backend architecture with Spring Boot and a range of supporting technologies to provide a reliable and scalable service.

## Features
- Place orders for products
- List all available products
- Add new products
- Check inventory availability

## Technologies Used
- **Spring Boot**: Core framework for developing the backend services
- **PostgreSQL & MySQL**: Databases used for data storage
- **Kafka**: Messaging service for handling event-driven processes
- **Keycloak**: Authentication and authorization service for secure access management
- **Grafana Stack**:
  - **Prometheus**: Monitoring tool for collecting metrics
  - **Grafana**: Visualization dashboard for metrics and logs
  - **Loki**: Log aggregation system
  - **Tempo**: Distributed tracing backend for managing request tracing
- **API Gateway**: Configured using Spring Cloud Gateway MVC for efficient routing and load balancing

## API Routes
Here are the main API routes available in the Marketa platform:

| Route                        | Method | Description                                      |
|------------------------------|--------|--------------------------------------------------|
| `/api/order/placeorder`      | POST   | Allows users to place an order                   |
| `/api/product/all`           | GET    | Retrieves a list of all available products       |
| `/api/product/add`           | POST   | Adds a new product to the inventory              |
| `/api/inventory`             | GET    | Checks if a product is in stock                  |

> **Note**: The inventory route `/api/inventory?skuCode=_____&quantity=____` requires `skuCode` and `quantity` parameters to verify stock availability.
