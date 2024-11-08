
# Microservices Project

This project uses several microservices in a distributed system architecture. Each microservice has its own responsibility, and they all communicate via a **Service Registry** and an **API Gateway**. The API Gateway is the entry point for clients, managing routing and load balancing between services.

#### 1. **Service Registry (Eureka Server)**

- The Service Registry allows each microservice to register itself, making it discoverable by other services. It simplifies service communication by enabling dynamic registration and lookup of services.
- **Port**: 8761
- **Endpoint**: 
  - `/eureka`: The primary endpoint for service discovery where other services can register and look up available services.
- **Example Access**: You can access the Eureka dashboard at `http://localhost:8761` to view registered services and their statuses.

#### 2. **Admin Server**

- The Admin Server registers with the Service Registry, allowing centralized management and monitoring of services. It helps manage registered services and performs health checks on the instances.
- **Port**: 1111
- **Endpoint**:
  - `/`: The main dashboard for managing services.
  - **Note**: This server primarily functions as an internal management tool rather than providing REST endpoints for clients.

#### 3. **Department-Service**

- The Department-Service handles department data and interacts with the Employee-Service to retrieve information about employees in a given department.
- **Port**: 8081 (varies as it's registered dynamically with Eureka).
- **Endpoints**:
  - **`/department`**: 
    - `POST`: Creates a new department.
    - `GET`: Retrieves a list of all departments.
  - **`/department/{id}`**: 
    - `GET`: Retrieves a department by its ID.
    - `DELETE`: Deletes a department by ID.
  - **`/department/with-employees`**:
    - `GET`: Retrieves all departments, including associated employee data. This endpoint uses a Feign client to communicate with the Employee-Service.

#### 4. **Employee-Service**

- The Employee-Service manages employee data and provides endpoints for CRUD operations on employees. It also supports retrieving employees by their department ID.
- **Port**: 8082 (varies as it's registered dynamically with Eureka).
- **Endpoints**:
  - **`/employee`**:
    - `POST`: Creates a new employee.
    - `GET`: Retrieves a list of all employees.
  - **`/employee/{id}`**:
    - `GET`: Retrieves an employee by ID.
  - **`/employee/department/{departmentId}`**:
    - `GET`: Retrieves a list of employees who belong to a specific department. This is used by the Department-Service for displaying department-employee relationships.

#### 5. **API Gateway**

- The API Gateway acts as a single entry point for clients, directing requests to the appropriate services based on URL paths. It provides load balancing, simplifies client interactions, and enhances security by controlling access to each service.
- **Port**: 9000
- **Routes**:
  - **`/department/**`**: Routes requests to the Department-Service.
    - Example endpoints through the gateway:
      - `http://localhost:9000/department` (for all department operations)
      - `http://localhost:9000/department/{id}`
      - `http://localhost:9000/department/with-employees`
  - **`/employee/**`**: Routes requests to the Employee-Service.
    - Example endpoints through the gateway:
      - `http://localhost:9000/employee`
      - `http://localhost:9000/employee/{id}`
      - `http://localhost:9000/employee/department/{departmentId}`

---

### Features
**Department Management**: CRUD operations for departments, along with Feign-based integration with the Employee service.
**Employee Management**: CRUD operations for employees and retrieval of employees by department.
**API Gateway**: Centralized routing for department and employee services.
**Service Discovery with Eureka**: All services register with Eureka for discoverability.
**Load Balancing**: Enabled through Eureka and Spring Cloud Gateway.
**Distributed Tracing with Zipkin**: For monitoring request flows across services.

### Running the Project
1. Start the Eureka Server (Service Registry) on port 8761.
2. Start the Admin Server.
3. Start the Department and Employee services.
4. Start the API Gateway on port 9000.
- You should be able to access the microservices through the API Gateway.
You can use Docker Compose to run all services together, or run them individually using Spring Boot’s built-in Maven or Gradle commands.

This architecture provides scalability, maintainability, and a clear separation of concerns, ideal for distributed and high-availability applications.
