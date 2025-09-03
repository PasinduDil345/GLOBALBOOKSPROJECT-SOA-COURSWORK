GlobalBooks: A Modern SOA Platform


This document outlines the architecture and implementation of GlobalBooks, a robust Service-Oriented Architecture (SOA) platform designed for managing and processing book orders. This system is a showcase of real-world architectural patterns, including the use of both SOAP and REST services, and demonstrates asynchronous communication via enterprise messaging.

Key Architectural Features
Dual-Protocol Design: A hybrid architecture seamlessly integrating SOAP and RESTful services.

Message-Driven Integration: Utilizes a central message broker, RabbitMQ, to enable loose coupling between services.

Advanced Security: Incorporates OAuth2 for REST APIs and WS-Security for SOAP services to ensure secure communication.

Scalable by Design: Built with containerization in mind, supporting deployment on platforms like Docker and Kubernetes.

Operational Visibility: Includes integrated health checks and monitoring for all services.

Comprehensive Documentation: Full API and architectural documentation is available for clarity.

Robust Testing: The system comes with extensive test suites to guarantee reliability.

System Components
Core Services
Catalog Service (SOAP) - Port 8080

Technology: Java Spring Boot with JAX-WS.

Protocol: SOAP, secured with WS-Security.

Functionality: Manages the complete book catalog, handles real-time pricing and stock checks, and supports search operations.

Integration: Exposes a WSDL file for easy service discovery and client-side code generation.

Orders Service (REST) - Port 8082

Technology: Java Spring Boot.

Protocol: REST, protected by OAuth2.

Functionality: Manages the entire order lifecycle, including tracking, history, and business process orchestration.

Integration: Publishes events to RabbitMQ to notify other services of order status changes.

Payments Service (REST) - Port 8083

Technology: Java Spring Boot.

Protocol: REST.

Functionality: Handles secure payment processing, supports multiple payment methods, and manages transaction history and refunds.

Security: Designed to be compliant with PCI-DSS standards.

Shipping Service (REST) - Port 8084

Technology: Java Spring Boot.

Protocol: REST.

Functionality: Provides shipment tracking, delivery time estimates, multi-carrier support, and address validation.

Integration: Facilitates real-time shipping updates.

Integration Layer
RabbitMQ (Message Broker)

Acts as the central Enterprise Service Bus (ESB) for asynchronous communication.

Implements patterns for reliable message delivery and dead-letter queues for robust error handling.

BPEL (Business Process Execution Language)

Manages the complex, long-running business processes, specifically the order fulfillment workflow.

Handles error compensation and provides monitoring for business processes.

Security Framework

Uses OAuth2 and JWT for token-based authentication for REST services.

Leverages WS-Security for SOAP services.

Supports Role-Based Access Control (RBAC) to manage user permissions.

System Prerequisites
Development Environment
Java Development Kit (JDK): Version 11 or newer.

Build Tool: Maven 3.6+.

Containerization: Docker Engine 20.10+ and Docker Compose 2.0+.

Version Control: Git 2.0+.

System Resources: A minimum of 8GB RAM and 20GB of free disk space is required, with 16GB RAM recommended for optimal performance.

Production Environment
Orchestration: Kubernetes cluster version 1.20+.

Nodes: Each node should have at least 16GB RAM, 4 CPU cores, and 50GB of storage.

Infrastructure: Requires support for load balancing and persistent volumes.

Getting Started
Clone the Project

 

# Clone the repository
git clone <repository-url>
cd SOA

# Configure the environment variables
copy .env.example .env
# Modify .env with your specific settings
Build and Deploy

 

# Build all services from the root directory
./13-scripts/build-all-services.sh

# Start the services using Docker Compose
cd 10-deployment
docker compose up -d --build
Verify Deployment

 

# Run the health check script
./13-scripts/verify-deployment.sh
Alternatively, check the health endpoints manually:

Catalog: http://localhost:8080/soap/catalog?wsdl

Orders: http://localhost:8082/api/v1/orders/health

Payments: http://localhost:8083/api/v1/payments/health

Shipping: http://localhost:8084/api/v1/shippings/health

RabbitMQ Dashboard: http://localhost:15672 (Username: admin, Password: password123)

Access Documentation

REST API Docs: http://localhost:8082/swagger-ui.html

Architectural Diagrams: View the diagrams located in 01-design-artifacts/architecture-diagrams/.

Integration Guide: See 11-documentation/integration-guide.md for details on how to integrate with the system.

API Reference
Orders Service (REST)
Method	Endpoint	Description
POST	/api/v1/orders	Creates a new order.
GET	/api/v1/orders	Retrieves a list of all orders.
GET	/api/v1/orders/{id}	Fetches details for a specific order.
PUT	/api/v1/orders/{id}/status	Updates the status of an order.
DELETE	/api/v1/orders/{id}	Cancels an existing order.

Export to Sheets
Payments Service (REST)
Method	Endpoint	Description
POST	/api/v1/payments/initiate	Starts a new payment process.
POST	/api/v1/payments/{id}/process	Finalizes a payment.
GET	/api/v1/payments/{id}	Retrieves details for a specific payment.
GET	/api/v1/payments/order/{orderId}	Lists all payments for a given order.

Export to Sheets
Shipping Service (REST)
Method	Endpoint	Description
POST	/api/v1/shippings	Creates a new shipment.
POST	/api/v1/shippings/{id}/process	Finalizes the shipping process.
GET	/api/v1/shippings/{id}	Fetches details for a specific shipment.
GET	/api/v1/shippings/tracking/{num}	Tracks a shipment by its number.

Export to Sheets
Catalog Service (SOAP)
The full WSDL is available at http://localhost:8080/soap/catalog?wsdl.
The primary operations include:

searchBooks: Searches for books by a query or category.

getBookById: Retrieves a book's details using its unique ID.

getBookPrice: Gets the price of a specific book.

checkAvailability: Verifies if a book is in stock.

Message Workflow
Order Submission: A client places an order via the Orders Service REST API.

Event Emission: The Orders Service publishes an event to the RabbitMQ message broker.

Service Consumption: The Payments Service and Shipping Service consume the relevant events from the message queue.

Process Coordination: The BPEL process orchestrates the entire order fulfillment workflow.

Security
Current State
SOAP: Basic authentication is currently implemented, with an easy path to upgrade to WS-Security.

REST: Uses OAuth2 with JWT tokens.

Identity Management: Keycloak is included in the Docker setup for seamless identity management.

Future Enhancements
Full WS-Security with UsernameToken for SOAP.

Comprehensive OAuth2 implementation.

Mutual TLS authentication for secure inter-service communication.

Testing
API Testing: Postman collections are provided in 09-testing/postman/.

SOAP Testing: SOAP UI test suites can be found in 09-testing/soap-ui/.

Development Guide
Setting up the Environment
Ensure the required tools are installed:

 

# Check Java version
java -version

# Check Maven version
mvn -version

# Check Docker versions
docker --version
docker-compose --version
Building Services Individually
Navigate to each service's directory and run the Maven command:

 

# For the Catalog Service
cd 02-catalog-service
mvn clean package

# For the Orders Service
cd 03-orders-service
mvn clean package

# For the Payments Service
cd 04-payments-service
mvn clean package

# For the Shipping Service
cd 05-shipping-service
mvn clean package
Running Services Locally
From each service's directory, run the following command to start it:

 

# Catalog Service (Port 8080)
cd 02-catalog-service
mvn spring-boot:run

# Orders Service (Port 8082)
cd 03-orders-service
mvn spring-boot:run

# Payments Service (Port 8083)
cd 04-payments-service
mvn spring-boot:run

# Shipping Service (Port 8084)
cd 05-shipping-service
mvn spring-boot:run
Development Best Practices
Code Quality: Use mvn sonar:sonar for static analysis and mvn formatter:format for code formatting.

Testing: Run mvn test for unit tests and mvn verify for integration tests.

Documentation: Keep the API documentation up-to-date by running mvn javadoc:javadoc.

Monitoring
Each service has a dedicated health check endpoint.

The RabbitMQ management interface provides a dashboard for monitoring message flow.

Spring Boot Actuator exposes a wide range of metrics.

Deployment
Docker: Use docker compose up -d --build for a quick, local deployment.

Kubernetes: Manifests for production deployment are located in 10-deployment/kubernetes/.

Project Structure
The project is organized into a modular, self-describing directory structure.

Contributing
We welcome contributions! Please follow these steps:

Fork the repository.

Create a new feature branch.

Implement your changes and add corresponding tests.

Submit a pull request for review.

License
This project is open-source and released under the MIT License. For more details, see the LICENSE file.







