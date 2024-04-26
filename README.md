<div align="center">
  <h1>etiqa</h1>
  <p><img src="https://img.icons8.com/fluency/96/000000/online-store.png" alt="Online Store Icon"/> <b>Online Store</b></p>
</div>

# Etiqa Technical Assessment by Muhammad Hafizoddin

## Project Title
OnlineStore API: A Spring Boot Application for Managing Customers and Products.

## Description
OnlineStore API is a robust Spring Boot application designed to simplify the management of customers and products through a set of RESTful APIs.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Development](#development)
- [Usage](#usage)
- [Testing](#testing)

## Features
- **CRUD Operations:** Create, read, update, and delete operations for managing customers and products.
- **Search Functionality:** Search for products and customers using keywords.
- **Swagger Documentation:** Easily explore and interact with the APIs using Swagger UI.

## Prerequisites
Before you begin, ensure you have the following installed on your local machine:
- **Java 17:** Ensure Java Development Kit (JDK) version 17 or higher is installed.
- **Maven:** Make sure Maven is installed to build and manage the project dependencies.
- **Lombok:** Install Lombok plugin for your IDE (if using) to enable annotation-based boilerplate code generation.
- **Postman:** Install Postman to test the APIs.
- **MySQL:** Ensure MySQL is installed and running.

Additionally, for running the project using Docker, make sure you have:
- **Docker:** Install Docker to containerize and deploy the application.
- **Docker Compose:** Install Docker Compose to manage multi-container Docker applications.

## Getting Started

### For Linux, Mac, and Windows:

1. **Install Docker:**
   - Follow the official Docker documentation for detailed installation instructions specific to your operating system.

## Building and Running Docker Containers

To build and run the Docker containers for the project, follow these steps:

1. **Clone the repository:**
    ```bash
    git clone https://github.com/hachirobei/etiqa.git
    ```

2. **Navigate to the project directory:**
    ```bash
    cd etiqa/onlinestore
    ```

3. **Build and run the Docker containers:**
    ```bash
    docker-compose up -d --build
    ```

This command will start the containers in detached mode and build any necessary images.

## Project Structure

This project follows a modular and clean architecture, organized into various packages within the `com.etiqa.onlinestore` base package. Below is an overview of the different components of the project:

### Packages

- `config`: Includes configuration classes for the entire application. It typically contains security configuration, bean configurations, error handling, and Swagger API documentation setup.

- `controller`: Contains controller classes responsible for handling incoming HTTP requests, mapping those requests to appropriate service methods, and returning responses.

- `dto` (Data Transfer Objects): Holds classes used for encapsulating data and transferring it between the controller layer and the service layer.

- `entity`: Contains the domain model classes which represent the application's persistent data structure, typically mirroring the database tables.

- `exception`: Houses custom exceptions for the application, providing a cleaner way to handle errors and specific conditions that occur during the execution of the application.

- `repository`: Consists of interfaces that extend Spring Data JPA repositories, allowing for abstracted CRUD operations and database interactions.

- `service`: Contains the service classes where the business logic of the application resides, orchestrating data between the controllers and repositories.


## Development

### Controllers

Controllers are the entry point for HTTP requests and are responsible for interpreting user input and delegating business processing to the service layer.

### Services

The service layer contains the core business logic of the application. It operates between the controller layer and the repository layer, executing business operations.

### Repositories

Repositories provide a simple abstraction layer over the data access mechanism. They interact with the database and perform data retrieval and persistence operations.

### Configurations

Configurations set up the application's behavior through various settings. This includes both internal mechanisms like error handling and external integrations like API documentation.


## Usage

Please refer to the Swagger documentation for detailed information on how to use the APIs provided by this project.

### Swagger Documentation

Explore and interact with the APIs using Swagger UI. Access the API documentation via the following URL: [Swagger UI](http://localhost:8051/swagger-ui/index.html)

## Testing

To test the functionality of the Online Store Management System's APIs, you can use the provided Postman collection file named "Online Store.postman_collection". This collection contains pre-defined requests that you can import into your Postman application for testing purposes.

**Instructions:**

1. **Download the Postman Collection:**
   - Download the "Online Store.postman_collection" file from the project repository.

2. **Import Collection into Postman:**
   - Open Postman.
   - Click on the "Import" button in the top-left corner.
   - Select the downloaded "Online Store.postman_collection" file.
   - The collection will be imported into your Postman workspace.

3. **Test APIs:**
   - Explore the imported collection to view available requests.
   - Execute requests to interact with the Online Store Management System's APIs.
   - Verify responses to ensure the APIs function as expected.

Using the Postman collection simplifies the testing process and allows you to validate the functionality and performance of the Online Store Management System's APIs before deployment.
