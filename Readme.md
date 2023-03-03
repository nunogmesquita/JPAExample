# Employee and Company Basic Example using JPA

This is a basic example of how to use Java Persistence API (JPA) to create and manage relationships between three
entities: Employee, Address and Company. An Employee can work for many Companies and a Company can have many Employees.
Each Employee has an Address. Each Company may have many Addresses.

## Requirements

To run this example, you need to have the following software installed:

- Java 8 or later
- Maven 3.2 or later
- MySQL server 5.6 or later

## How to run

1. Run a mysql server instance on your machine or mysqk docker instance:

- docker run -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=employee -p 3306:3306 -d mysql:8.0

2. If not exists, create a database named `employee` in your MySQL server.
3. Run the application.