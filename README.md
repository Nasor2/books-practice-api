# Book Management API


## Project Overview

This RESTful API is designed for the **management of a book collection**. It enables comprehensive Create, Read, Update, and Delete (CRUD) operations on book records in a database. Built with Spring Boot, this API adheres to REST best practices and offers  interactive documentation via Swagger UI.

## Technologies and Tools Used

This project leverages a modern set of technologies and tools to ensure scalability, maintainability, and robustness:

* **Java 24:** The primary programming language.
* **Spring Boot 3.3.1:** A framework for rapidly building Java applications with minimal configuration.
    * **Spring Web:** For building RESTful APIs.
    * **Spring Data JPA:** For the data persistence layer using Hibernate.
    * **Spring Validation:** For validating incoming API data.
* **Hibernate:** JPA implementation for Object-Relational Mapping (ORM).
* **H2 Database:** An in-memory database used for development and testing. This allows the application to start quickly without the need for external database configuration.
* **Lombok:** A tool to reduce boilerplate code in Java classes (getters, setters, constructors, etc.).
* **SpringDoc OpenAPI (Swagger UI):** For automatic generation and interactive visualization of API documentation.
* **Maven:** Project management and build automation tool.
* **Git:** Version control system.


## Setup and Execution

### Prerequisites

* Java Development Kit (JDK) 24.
* Maven 3.x.x.

### Steps to Run Locally

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/Nasor2/books-practice-api
    cd books-practice-api
    ```

2.  **Build the Project:**
    ```bash
    mvn clean install
    ```

3.  **Run the Application:**
    ```bash
    mvn spring-boot:run
    ```
    The application will start on `http://localhost:8080` by default.

---

## API Documentation (Swagger UI)

Once the application is running, you can access the interactive API documentation via Swagger UI:

* **Swagger UI URL:** `http://localhost:8080/swagger-ui/index.html`

From this interface, you can:
* View all available endpoints.
* Understand data models (Book) and their fields.
* Test requests directly from the browser and view responses.
* Consult the expected HTTP response codes for each operation.
* Understand input parameter requirements.
