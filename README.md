# 📚 GlobalBooks SOA System

A **modern and scalable Service-Oriented Architecture (SOA)** solution designed for an **enterprise-level book ordering and management platform**. This project demonstrates how **SOAP** and **REST** services can work together with **enterprise messaging patterns**, following **real-world architectural best practices**.

---

# 🌟 System Highlights
- **Hybrid Architecture** → Combines SOAP and REST seamlessly for flexible integration.  
- **Enterprise Messaging** → Event-driven communication powered by RabbitMQ.  
- **Security First** → Implements OAuth2 (REST) and WS-Security (SOAP) for robust protection.  
- **Scalability** → Microservices packaged in Docker and orchestrated with Kubernetes.  
- **Monitoring** → Built-in health checks, metrics, and observability tools.  
- **Documentation** → Detailed API references and architecture diagrams included.  
- **Testing** → Comprehensive unit, integration, and performance test suites.  

💡 This section gives a high-level summary so readers know what makes the system special at a glance.  

---

# 🏗️ Architecture Overview  

## 🔹 Core Services  

### 📖 Catalog Service (SOAP) – Port 8080  
- **Tech Stack** → Java Spring Boot + JAX-WS  
- **Protocol** → SOAP with WS-Security  
- **Key Features**:  
  - Manage and browse book catalog  
  - Real-time price & availability checks  
  - Category-based search functionality  
  - Sync with inventory system  
- **Integration** → WSDL available for service discovery  

---

### 🛒 Orders Service (REST) – Port 8082  
- **Tech Stack** → Java Spring Boot  
- **Protocol** → REST with OAuth2  
- **Key Features**:  
  - Order lifecycle management (create, update, cancel)  
  - Order history and tracking  
  - Business process orchestration  
  - Event publishing to RabbitMQ  
- **Integration** → Supports event-driven workflows  

---

### 💳 Payments Service (REST) – Port 8083  
- **Tech Stack** → Java Spring Boot  
- **Protocol** → REST microservice  
- **Key Features**:  
  - Secure payment handling  
  - Multiple payment methods  
  - Transaction history and refunds  
- **Security** → PCI-DSS readiness for compliance  

---

### 📦 Shipping Service (REST) – Port 8084  
- **Tech Stack** → Java Spring Boot  
- **Protocol** → REST microservice  
- **Key Features**:  
  - Shipment tracking and delivery updates  
  - Estimated delivery time calculation  
  - Multi-carrier logistics support  
  - Address validation  
- **Integration** → Provides real-time shipping updates  

💡 Breaking down services like this makes it easy for contributors and reviewers to understand what each service does and how they connect.  

---

## 🔹 Integration Architecture  

### 📨 Message Broker – RabbitMQ  
- Functions as an **Enterprise Service Bus (ESB)**  
- Enables asynchronous communication patterns  
- Supports dead-letter queues for error handling  
- Ensures message persistence and reliability  

---

### 🔄 Process Orchestration – BPEL  
- Coordinates **order fulfillment workflows**  
- Handles **long-running business processes**  
- Provides **compensation logic** for failed transactions  
- Enables business process monitoring and visibility  

---

### 🔐 Security Framework  
- **OAuth2** → Authentication & authorization for REST services  
- **WS-Security** → Ensures SOAP message integrity and confidentiality  
- **JWT** → Token-based access management  
- **RBAC** → Role-based access control for fine-grained permissions  

💡 This integration section shows how the system ties services together, ensures resilience, and secures communication.  

---

# ⚙️ System Requirements  

## 🖥️ Development Environment  
- JDK **11+**  
- Maven **3.6+**  
- Docker Engine **20.10+**  
- Docker Compose **2.0+**  
- Git **2.0+**  
- **8 GB RAM (16 GB recommended)**  
- **20 GB disk space**  

---

## ☁️ Production Environment  
- Kubernetes **1.20+ cluster**  
- Node requirements:  
  - **16 GB RAM minimum**  
  - **4 CPU cores minimum**  
  - **50 GB storage per node**  
- Load balancer support  
- Persistent volume support  

💡 Splitting dev and prod requirements makes it easier for teams to prepare their environments correctly.  

---

# 🏛️ Architecture Patterns Demonstrated  
- **Service-Oriented Architecture (SOA)** → Core business functionality encapsulated as SOAP services using Spring Web Services.  
- **Enterprise Service Bus (ESB)** → Message routing and transformation through a central integration layer.  
- **Event-Driven Architecture** → Asynchronous messaging enabled with RabbitMQ.  
- **Microservices** → Independent services packaged with Spring Boot and deployed in containers.  
- **API Gateway Pattern** → REST endpoints secured with OAuth2, routing external requests into internal services.  
- **Circuit Breaker Pattern** → Fault tolerance to prevent cascading failures during outages.  
- **Saga Pattern (via BPEL Orchestration)** → Long-running business transactions coordinated with BPEL.  

---

# 🛠️ Technologies Used  
- **Java 11** → Primary programming language  
- **Spring Boot** → Application framework for microservices  
- **Spring Web Services** → SOAP implementation  
- **RabbitMQ** → Message broker for event-driven communication  
- **Docker** → Service containerization  
- **Kubernetes** → Deployment and orchestration platform  
- **OAuth2** → REST API authentication and authorization  
- **WS-Security** → Security for SOAP services  
- **BPEL** → Business process orchestration (Saga pattern)  
- **H2 Database** → In-memory database for development/testing  

---

# 🚀 Quick Start Guide  

## 🔹 Clone and Setup  
```bash
# Clone the repository
git clone <repository-url>
cd SOA

# Setup environment variables
copy .env.example .env
# Edit .env with your configurations

🔹 Build and Deploy
# Build all services
./13-scripts/build-all-services.sh

# Deploy using Docker Compose
cd 10-deployment
docker compose up -d --build

🔹 Verify System Health
# Run health check script
./13-scripts/verify-deployment.sh


Or check endpoints manually:

Catalog Service → http://localhost:8080/soap/catalog?wsdl
Orders API → http://localhost:8082/api/v1/orders/health
Payments API → http://localhost:8083/api/v1/payments/health
Shipping API → http://localhost:8084/api/v1/shippings/health
RabbitMQ Dashboard → http://localhost:15672
 (default: admin/password123)

📄 Access Documentation

API Docs → http://localhost:8082/swagger-ui.html
Architecture Diagrams → 01-design-artifacts/architecture-diagrams/
Integration Guide → 11-documentation/integration-guide.md

🌐 Service Endpoints

Orders Service (REST API)
POST /api/v1/orders → Create new order
GET /api/v1/orders → Get all orders
GET /api/v1/orders/{id} → Get order by ID
PUT /api/v1/orders/{id}/status → Update order status
DELETE /api/v1/orders/{id} → Cancel order
GET /api/v1/orders/health → Health check

Payments Service (REST API)
POST /api/v1/payments/initiate → Initiate payment
POST /api/v1/payments/{id}/process → Process payment
GET /api/v1/payments/{id} → Get payment details
GET /api/v1/payments/order/{orderId} → Get payments by order
GET /api/v1/payments/health → Health check

Shipping Service (REST API)
POST /api/v1/shippings → Create shipping
POST /api/v1/shippings/{id}/process → Process shipping
GET /api/v1/shippings/{id} → Get shipping details
GET /api/v1/shippings/tracking/{num} → Track shipment
GET /api/v1/shippings/health → Health check

Catalog Service (SOAP)
WSDL → http://localhost:8080/soap/catalog?wsdl

Operations:
searchBooks → Search by query/category
getBookById → Get book details by ID
getBookPrice → Fetch book price
checkAvailability → Verify availability

🔀 Message Flow

Order Creation → Client sends request via Orders Service
Event Publishing → Orders Service publishes events to RabbitMQ
Payment Processing → Payments Service consumes payment events
Shipping Processing → Shipping Service consumes shipping events
Orchestration → BPEL coordinates the entire workflow

🔐 Security
Current Implementation
SOAP → Basic Auth (WS-Security extension planned)
REST → OAuth2 JWT token-based auth
Keycloak → Identity management integrated (via Docker setup)
Planned Enhancements
WS-Security with UsernameToken for SOAP
Full OAuth2 flows for REST APIs
Mutual TLS between services

🧪 Testing
Postman Collections
09-testing/postman/GlobalBooks-SOA-Catalog.postman_collection.json
09-testing/postman/GlobalBooks_Orders_API.postman_collection.json

SOAP UI Tests
09-testing/soap-ui/CatalogService-TestSuite.xml

💻 Development
Setting Up Environment

# Verify Java
java -version

# Verify Maven
mvn -version

# Verify Docker
docker --version
docker-compose --version

Build Services Individually

cd 02-catalog-service && mvn clean package
cd 03-orders-service && mvn clean package
cd 04-payments-service && mvn clean package
cd 05-shipping-service && mvn clean package


Run Services Locally

cd 02-catalog-service && mvn spring-boot:run   # Port 8080
cd 03-orders-service && mvn spring-boot:run    # Port 8082
cd 04-payments-service && mvn spring-boot:run  # Port 8083
cd 05-shipping-service && mvn spring-boot:run  # Port 8084

📊 Monitoring

Service Health Checks → Each service exposes health endpoints.
RabbitMQ Management Dashboard → Monitor queues and routing.
Spring Boot Actuator → Access metrics and system info.

🚀 Deployment
Docker Compose
cd 10-deployment
docker compose up -d --build

Kubernetes

Configurations → 10-deployment/kubernetes/
Includes deployments, services, and configs for cluster setup.

🧑‍💻 Development Best Practices
Code Quality

mvn sonar:sonar      # Run static analysis
mvn formatter:format # Format code

Testing

mvn test    # Unit tests
mvn verify  # Integration tests
Load test scripts → 09-testing/performance/

Documentation

mvn javadoc:javadoc
API docs → 11-documentation/api-docs/

📂 Project Structure

├── 01-design-artifacts/          
│   ├── architecture-diagrams/    
│   ├── governance-policy.md      
│   └── soa-design-document.md    
│
├── 02-catalog-service/           
│   ├── src/                      
│   ├── Dockerfile                
│   └── pom.xml                   
│
├── 03-orders-service/            
│   ├── src/                      
│   ├── Dockerfile                
│   └── pom.xml                   
│
├── 04-payments-service/          
│   ├── src/                      
│   ├── Dockerfile                
│   └── pom.xml                   
│
├── 05-shipping-service/          
│   ├── src/                      
│   ├── Dockerfile                
│   └── pom.xml                   
│
├── 06-bpel-orchestration/        
│   ├── deployment/               
│   ├── processes/                
│   └── wsdl/                     
│
├── 07-integration/               
│   ├── consumers/                
│   ├── producers/                
│   └── rabbitmq/                 
│
├── 08-security/                  
│   ├── certificates/             
│   ├── oauth2/                   
│   └── ws-security/              
│
├── 09-testing/                   
│   ├── integration-tests/        
│   ├── performance/              
│   ├── postman/                  
│   └── soap-ui/                  
│
├── 10-deployment/                
│   ├── docker-compose.yml        
│   ├── kubernetes/               
│   ├── cloud/                    
│   └── scripts/                  
│
├── 11-documentation/             
│   └── api-docs/                 
│
├── 12-reports/                   
│   └── viva-presentation/        
│
└── 13-scripts/                   
    ├── setup-environment.sh      
    └── test-soa-workflow.sh      

Contributing

We welcome contributions!
Fork this repository
Create a feature branch
Make your changes (add tests where possible)
Submit a pull request for review

📜 License

This project is licensed under the MIT License.