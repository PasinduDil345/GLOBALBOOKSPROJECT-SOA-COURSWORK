GlobalBooks: A Contemporary SOA Platform
This document describes the GlobalBooks platform, a modern, service-oriented architecture (SOA) for managing and fulfilling book orders. This system is a practical demonstration of a hybrid architecture that integrates both SOAP and RESTful services. It also showcases a message-driven approach to illustrate best practices in enterprise architecture.

System Capabilities
Hybrid Service Model: The system is built on a cohesive architecture that uses both SOAP and REST protocols to handle different types of service interactions.

Decoupled Integration: An event-driven architecture leveraging RabbitMQ enables seamless asynchronous communication between services, ensuring they are not tightly dependent on one another.

Robust Security: The platform implements a comprehensive security framework with OAuth2 for REST APIs and WS-Security for SOAP services.

Scalability: The system's services are built as containerized microservices for straightforward deployment and scaling using technologies like Docker and Kubernetes.

Observability: Integrated health checks and metrics provide essential monitoring capabilities for all services.

Detailed Documentation: The project includes complete API and architectural documentation to aid understanding and future development.

Comprehensive Testing: Extensive test suites for unit, integration, and performance testing ensure the reliability of all services.

Architectural Overview
Core Services
Catalog Service (SOAP)

Port: 8080

Technology: Developed with Java Spring Boot and JAX-WS.

Protocol: SOAP with WS-Security.

Functionality: Manages the book catalog, handles real-time pricing and availability checks, and provides search functionality. It exposes a WSDL file for client consumption.

Orders Service (REST)

Port: 8082

Technology: Built using Java Spring Boot.

Protocol: RESTful API with OAuth2 authentication.

Functionality: Manages the entire order lifecycle, from creation to tracking, and orchestrates business processes. It publishes order-related events to a RabbitMQ message broker.

Payments Service (REST)

Port: 8083

Technology: A REST microservice developed in Java Spring Boot.

Functionality: Processes secure payments, handles refunds, and maintains transaction history. It's designed with PCI-DSS compliance in mind.

Shipping Service (REST)

Port: 8084

Technology: A REST microservice also developed in Java Spring Boot.

Functionality: Manages shipment tracking, delivery estimates, and address validation, providing real-time shipping updates.

Integration and Supporting Architecture
Message Broker (RabbitMQ): This component acts as the Enterprise Service Bus (ESB), facilitating asynchronous messaging. It includes dead-letter queues for robust error handling.

Process Orchestration (BPEL): BPEL coordinates the complex order fulfillment workflow, managing long-running transactions and error compensation.

Security Framework: The system utilizes OAuth2 with JWT tokens for REST services and WS-Security for SOAP. It also implements Role-Based Access Control (RBAC) to manage user permissions.

System Requirements
Development Environment
Java Development Kit (JDK): 11 or newer

Maven: 3.6+

Docker Engine: 20.10+

Docker Compose: 2.0+

Git: 2.0+

Memory: 8GB RAM minimum (16GB recommended)

Storage: 20GB free disk space

Production Environment
Kubernetes Cluster: 1.20+

Node Specifications: 16GB RAM, 4 CPU cores, and 50GB storage per node.

Requires support for a Load Balancer and Persistent Volumes.

Getting Started
To get started, developers must clone the project's repository. After setting up environment variables, the services can be built and packaged using the provided scripts. Deployment can be initiated using Docker Compose, which starts all services in detached containers.

To verify the system's health, one can run a verification script or manually check the following health endpoints:

Catalog Service: http://localhost:8080/soap/catalog?wsdl

Orders API: http://localhost:8082/api/v1/orders/health

Payments API: http://localhost:8083/api/v1/payments/health

Shipping API: http://localhost:8084/api/v1/shippings/health

RabbitMQ Dashboard: http://localhost:15672 (using admin/password123).

API documentation is accessible at http://localhost:8082/swagger-ui.html, with architectural diagrams and an integration guide also provided.

Service Endpoints
Orders Service (REST API)
POST /api/v1/orders: Creates a new order.

GET /api/v1/orders: Retrieves all orders.

GET /api/v1/orders/{id}: Gets an order by its ID.

PUT /api/v1/orders/{id}/status: Updates an order's status.

DELETE /api/v1/orders/{id}: Cancels an order.

Payments Service (REST API)
POST /api/v1/payments/initiate: Initiates a payment.

POST /api/v1/payments/{id}/process: Processes a payment.

GET /api/v1/payments/{id}: Gets payment details.

GET /api/v1/payments/order/{orderId}: Retrieves payments by order.

Shipping Service (REST API)
POST /api/v1/shippings: Creates a new shipment.

POST /api/v1/shippings/{id}/process: Processes a shipment.

GET /api/v1/shippings/{id}: Gets shipment details.

GET /api/v1/shippings/tracking/{num}: Tracks a shipment.

Catalog Service (SOAP)
The WSDL for this service is available at http://localhost:8080/soap/catalog?wsdl. Operations include searchBooks, getBookById, getBookPrice, and checkAvailability.

Message Flow
A client places an order through the Orders Service REST API.

The Orders Service publishes an event to RabbitMQ.

The Payments and Shipping services consume these events to begin their processes.

A BPEL process coordinates the overall workflow to ensure a smooth order fulfillment.

Development and Contribution
Developers can set up the environment and build individual services by navigating to their respective directories and using a build tool like Maven. Services can also be run locally using the Maven plugin.

The project promotes best practices like static code analysis (mvn sonar:sonar), code formatting (mvn formatter:format), and a comprehensive testing strategy using unit (mvn test) and integration (mvn verify) tests.

Contributions are welcomed via pull requests, following a standard workflow of forking the repository, creating a feature branch, implementing changes, and adding tests.

The project is logically organized into a clear directory structure, making it easy for new contributors to understand.

License
This project is licensed under the MIT License.
