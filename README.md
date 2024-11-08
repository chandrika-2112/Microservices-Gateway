
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
- **Port**: Varies (as it's registered dynamically with Eureka).
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
- **Port**: Varies (as it's registered dynamically with Eureka).
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

### Summary of Microservice Functions

- **Service Registry (Eureka)**: Manages service registration and discovery.
- **Admin Server**: Assists with service monitoring and administration.
- **Department-Service**: Manages department data and uses Feign to fetch employee data by department.
- **Employee-Service**: Manages employee data and provides data to Department-Service through Feign.
- **API Gateway**: Centralized routing, load balancing, and access control for all services.

### Example Usage Flow

1. **Client Request**: A client requests department data, including employee details, by calling `http://localhost:9000/department/with-employees`.
2. **API Gateway Routing**: The API Gateway routes this request to `Department-Service`.
3. **Department-Service Feign Call**: The Department-Service, using Feign, internally calls Employee-Service to retrieve employee details by department.
4. **Consolidated Response**: The consolidated department and employee data is returned to the client via the API Gateway.

This architecture provides scalability, maintainability, and a clear separation of concerns, ideal for distributed and high-availability applications.
